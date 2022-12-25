package creditSuisse.assignment.logFile.model;

public enum State {
	STARTED("STARTED"),
	FINISHED("FINISHED");
	
	private String state;

	State(String state){
		this.state = state;
	}

	public String getState() {
		return state;
	}
}
