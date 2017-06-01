package ankurmgoyal.api;

public enum ClockEntryField implements Field {
	
	EMPLOYEE__ID("@employee.id"),
	JOB__ID("@job.id");
	
	private String field;
	
	ClockEntryField(String field){
		this.field = field;
	}
	
	@Override
	public String toString() {
		return field;
	
	}
	
}
