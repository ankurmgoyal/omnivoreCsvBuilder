package ankurmgoyal.api;

public enum TicketField implements Field{
	
	ID("id"),
	AUTO_SEND("auto_send"),
	CLOSED_AT("closed_at"),
	OPEN("open"),
	OPENED_AT("opened_at"),
	TICKET_NUMBER("ticket_number"),
	EMPLOYEE__ID("@employee.id"),
	ORDER_TYPE__ID("@order_type.id"),
	REVENUE_CENTER__ID("@revenue_center.id"),
	TABLE__ID("@table.id");
	
	private String field;
	
	TicketField(String field){
		this.field = field;
	}
	
	@Override
	public String toString() {
		return field;
	}
	
	
	
	

}
