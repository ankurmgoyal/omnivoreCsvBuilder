package ankurmgoyal.api;

public class TicketItem {
	
	private int split;
	private int sentAt;
	private boolean sent;
	private int quantity;
	private int price;
	private String name;
	private int id;
	private String comment;
	private MenuItem menuItem;
	
	public int getSplit() {
		return split;
	}
	public void setSplit(int split) {
		this.split = split;
	}
	public int getSentAt() {
		return sentAt;
	}
	public void setSentAt(int sentAt) {
		this.sentAt = sentAt;
	}
	public boolean isSent() {
		return sent;
	}
	public void setSent(boolean sent) {
		this.sent = sent;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public MenuItem getMenuItem() {
		return menuItem;
	}
	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}
	
	
	
	
}
