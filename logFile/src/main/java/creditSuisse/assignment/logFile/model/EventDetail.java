package creditSuisse.assignment.logFile.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "event_details")
public class EventDetail {
	
	@Id
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("duration")
	private long duration;
	
	@JsonProperty("eventType")
	private EventType eventType;
	
	@JsonProperty("eventType")
	private String hostName;
	
	@JsonProperty("alert")
	private boolean alert;

	public EventDetail() {
		
	}
	
	public EventDetail(Event event, long timeBetweenEvent) {
		this.id = event.getId();
		this.duration = timeBetweenEvent;
		this.eventType = event.getType();
		this.hostName = event.getHost();
		this.alert = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public boolean isAlert() {
		return alert;
	}

	public void setAlert(boolean alert) {
		this.alert = alert;
	}
}
