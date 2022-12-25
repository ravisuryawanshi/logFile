package creditSuisse.assignment.logFile.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("state")
	private String state;
	
	@JsonProperty("type")
	private EventType type;
	
	@JsonProperty("host")
	private String host;
	
	@JsonProperty("timestamp")
	private long timeStamp;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
