package creditSuisse.assignment.logFile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import creditSuisse.assignment.logFile.model.EventDetail;

public interface EventDetailRepository  extends JpaRepository<EventDetail, String> {
} 
