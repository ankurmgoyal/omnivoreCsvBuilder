package ankurmgoyal.api;

public enum Resource {
	
	EMPLOYEES("employees"),
	TICKETS("tickets"),
	CLOCK_ENTRIES("clock_entries"),
	CATEGORIES("menu/categories","categories"),
	MENU_ITEMS("menu/items","menu_items"),
	PRICE_LEVELS("menu/items/REPLACE/price_levels","price_levels"),
	TICKET_ITEMS("tickets/REPLACE/items","items");
	
	private String resource_url;
	private String resource_json;
	
	Resource(String resource){
		this.resource_url = resource;
		this.resource_json = resource;
	}
	
	Resource(String url,String json){
		this.resource_url = url;
		this.resource_json = json;
	}
	
	public String url(){
		return resource_url;
	}
	
	public String json(){
		return resource_json;
	}
	
	
}
