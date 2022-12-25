package creditSuisse.assignment.logFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import creditSuisse.assignment.logFile.service.LogFileService;

@SpringBootApplication
public class LogFileApplication implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogFileApplication.class);

	@Autowired
	private LogFileService logFileService;

	public static void main(String[] args) {
		SpringApplication.run(LogFileApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logFileService.startLogFileProcess();
	}
}
