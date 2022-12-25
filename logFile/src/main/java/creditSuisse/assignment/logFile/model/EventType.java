package creditSuisse.assignment.logFile.model;

public enum EventType {
	APPLICATION_LOG("APPLICATION_LOG");
	
	private String type;

	EventType(String type){
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
