package creditSuisse.assignment.logFile.handler;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import creditSuisse.assignment.logFile.model.Event;
import creditSuisse.assignment.logFile.model.EventDetail;
import creditSuisse.assignment.logFile.model.State;
import creditSuisse.assignment.logFile.repository.EventDetailRepository;

@Component
public class LogFileHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogFileHandler.class);

	@Autowired
	private EventDetailRepository  eventDetailRepository;

	public void parseAndSave() {
		try {
			File file = new ClassPathResource("LogFile.txt").getFile();
			LineIterator lineIterator = FileUtils.lineIterator(file);
			String line =  null;
			Event event = null;
			Map<String,Event> eventMap = new HashMap<>();
			Map<String,EventDetail> eventDetailsMap = new HashMap<>();

			while (lineIterator.hasNext()) {
				line = lineIterator.nextLine();
				event = new ObjectMapper().readValue(line, Event.class);

				if(eventMap.containsKey(event.getId())) {
					Event existingEvent = eventMap.get(event.getId());
					// calculate time difference
					long timeBetweenEvent = getTimeDiffBetweenEvents(event, existingEvent);
					if(timeBetweenEvent > -1) {
						EventDetail eventDetail = new EventDetail(event, timeBetweenEvent);
						if(timeBetweenEvent > 4) {
							eventDetail.setAlert(true);
							LOGGER.info(" Event took long time :"+ eventDetail.getId() + " duration time: "+ timeBetweenEvent);
						}
						eventDetailsMap.put(eventDetail.getId(), eventDetail);
						eventMap.remove(eventDetail.getId());// this is required as it will remove processed events
					}
				} else {
					eventMap.put(event.getId(), event);
				}

				// save in chunk of 500 .This can be configurable
				if(eventDetailsMap.size() > 500) {
					//saving to DB
					saveEventDetails(eventDetailsMap.values());
					eventDetailsMap = new HashMap<String, EventDetail>();
				}
			}
			// suppose last eventDetail Map contains only 100 values then we need to save them as well
			// Above we are saving in a batch of 500
			if(eventDetailsMap.size() > 0) { 
				saveEventDetails(eventDetailsMap.values());
			}

		} catch (IOException e) {
			LOGGER.error(" Not able to read file. {} "+ e.getMessage());
		}
	}

	private void saveEventDetails(Collection<EventDetail> eventDetails) {
		LOGGER.trace("saving "+ eventDetails.size()+ " eventdetails");
		eventDetailRepository.saveAll(eventDetails);
	}

	private long getTimeDiffBetweenEvents(Event event, Event existingEvent) {
		//check if State is FINISHED or not
		Event finishedEvent = Stream.of(event,existingEvent).filter(e-> State.FINISHED.toString().equals(e.getState())).findFirst().orElse(null);
		Event startedEvent = Stream.of(event,existingEvent).filter(e-> State.STARTED.toString().equals(e.getState())).findFirst().orElse(null);

		if(finishedEvent != null && startedEvent != null) {
			return finishedEvent.getTimeStamp() - startedEvent.getTimeStamp();
		}
		return -1;
	}
}
