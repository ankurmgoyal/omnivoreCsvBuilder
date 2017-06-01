package ankurmgoyal.api;

public class ClockEntry {
	
	private int id;
	private int clockOut;
	private int clockIn;
	private Employee employee;
	
	public ClockEntry(){
		
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClockOut() {
		return clockOut;
	}

	public void setClockOut(int clockOut) {
		this.clockOut = clockOut;
	}

	public int getClockIn() {
		return clockIn;
	}

	public void setClockIn(int clockIn) {
		this.clockIn = clockIn;
	}
	
	
}
