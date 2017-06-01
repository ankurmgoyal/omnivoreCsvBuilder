package ankurmgoyal.api;

public class Ticket {
	
	private boolean isVoid;
	
	private int total;
	private int tips;
	private int tax;
	private int subTotal;
	private int serviceCharges;
	private int paid;
	private int otherCharges;
	private int items;
	private int due;
	private int discounts;
	
	private int ticketNumber;
	private int openedAt;
	private boolean open;
	private String name;
	private int id;
	private int guestCount;
	private int closedAt;
	private boolean autoSend;
	
	
	public boolean isVoid() {
		return isVoid;
	}
	public void setVoid(boolean isVoid) {
		this.isVoid = isVoid;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTips() {
		return tips;
	}
	public void setTips(int tips) {
		this.tips = tips;
	}
	public int getTax() {
		return tax;
	}
	public void setTax(int tax) {
		this.tax = tax;
	}
	public int getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}
	public int getServiceCharges() {
		return serviceCharges;
	}
	public void setServiceCharges(int serviceCharges) {
		this.serviceCharges = serviceCharges;
	}
	public int getPaid() {
		return paid;
	}
	public void setPaid(int paid) {
		this.paid = paid;
	}
	public int getOtherCharges() {
		return otherCharges;
	}
	public void setOtherCharges(int otherCharges) {
		this.otherCharges = otherCharges;
	}
	public int getItems() {
		return items;
	}
	public void setItems(int items) {
		this.items = items;
	}
	public int getDue() {
		return due;
	}
	public void setDue(int due) {
		this.due = due;
	}
	public int getDiscounts() {
		return discounts;
	}
	public void setDiscounts(int discounts) {
		this.discounts = discounts;
	}
	public int getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public int getOpenedAt() {
		return openedAt;
	}
	public void setOpenedAt(int openedAt) {
		this.openedAt = openedAt;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGuestCount() {
		return guestCount;
	}
	public void setGuestCount(int guestCount) {
		this.guestCount = guestCount;
	}
	public int getClosedAt() {
		return closedAt;
	}
	public void setClosedAt(int closedAt) {
		this.closedAt = closedAt;
	}
	public boolean isAutoSend() {
		return autoSend;
	}
	public void setAutoSend(boolean autoSend) {
		this.autoSend = autoSend;
	}
	
	
	
}
