package ankurmgoyal.api;

import java.util.Collection;

public class MenuItem {

	private int pricePerUnit;
	private int posId;
	private boolean open;
	private String name;
	private boolean inStock;
	private int id;
	private Collection<PriceLevel> priceLevels;
	private Collection<Category> categories;
	
	
	public Collection<Category> getCategories() {
		return categories;
	}
	public void setCategories(Collection<Category> categories) {
		this.categories = categories;
	}
	public Collection<PriceLevel> getPriceLevels() {
		return priceLevels;
	}
	public void setPriceLevels(Collection<PriceLevel> priceLevels) {
		this.priceLevels = priceLevels;
	}
	public int getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(int pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	public int getPosId() {
		return posId;
	}
	public void setPosId(int posId) {
		this.posId = posId;
	}
	public boolean isInStock() {
		return inStock;
	}
	public void setInStock(boolean inStock) {
		this.inStock = inStock;
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
	
	
}
