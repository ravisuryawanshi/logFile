package creditSuisse.assignment.logFile.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import creditSuisse.assignment.logFile.handler.LogFileHandler;

@Service
public class LogFileService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogFileService.class);


	@Autowired
	private LogFileHandler logFileHandler;
	
	public void startLogFileProcess() {
		logFileHandler.parseAndSave();
	}
	
}
