package ankurmgoyal.api;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.theoryinpractise.halbuilder.api.ReadableRepresentation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import com.theoryinpractise.halbuilder.standard.StandardRepresentationFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestFactory {
	
	//given a URL, response getter conducts an http request to the omnivore api
	public static Response responseGetter(String url){
		
		try{
			Properties prop = new Properties();
	    	InputStream input = null;
	    	input = new FileInputStream("config.properties");
	    	prop.load(input);
			String api = prop.getProperty("Api-Key");
			
			final OkHttpClient client = new OkHttpClient(); 
		    Request request = new Request.Builder()
		        .url(url)
		        .addHeader("Api-Key", api)
		        .build();
	
		    Response response = client.newCall(request).execute();
		    if (!response.isSuccessful()) return null;
		    return response;
		}
		catch(Exception e){
			return null;
		}
	}
	
	//given a response and resource type, returns a collection of readable resources
	//example returns collection of readable resources representing clockentries for Resource.CLOCK_ENTRIES
	public static Collection<ReadableRepresentation> getMainResourceList(Response response, Resource resource){
		RepresentationFactory representationFactory = new StandardRepresentationFactory();
		InputStream responseStream = response.body().byteStream();
	    ReadableRepresentation representation = representationFactory.readRepresentation(RepresentationFactory.HAL_JSON,new InputStreamReader(responseStream));
	    Map<String,Collection<ReadableRepresentation>> resources = representation.getResourceMap();
	    Collection<ReadableRepresentation> resourceList;
	    
	    try{
	    	resourceList = resources.get(resource.json());
	    }
	    catch(Exception e){
	    	return null;
	    }
	    
	    try{
	    	String url = representation.getLinkByRel("next").getHref();
	    	resourceList.addAll(getMainResourceList(responseGetter(url),resource));
	    }
	    catch(Exception e){
	    }

	    return resourceList;
	}
	
	public static ReadableRepresentation getMainResource(Response response){
		RepresentationFactory representationFactory = new StandardRepresentationFactory();
		InputStream responseStream = response.body().byteStream();
	    ReadableRepresentation representation = representationFactory.readRepresentation(RepresentationFactory.HAL_JSON,new InputStreamReader(responseStream));
		return representation;
	}
	
	public static Collection<ClockEntry> clockEntryConverter(Collection<ReadableRepresentation> clockEntries){
		Collection<ClockEntry> toReturn = new HashSet<ClockEntry>();
		Iterator<ReadableRepresentation> iterator = clockEntries.iterator();
		while(iterator.hasNext()){
			ClockEntry toAdd = generateClockEntry(iterator.next());
			if(toAdd != null){
				toReturn.add(toAdd);
			}
		}
		return toReturn;
	}
	
	public static ClockEntry generateClockEntry(ReadableRepresentation clockEntry){
		ClockEntry toReturn = new ClockEntry();
		try{
			toReturn.setId(Integer.valueOf((String) clockEntry.getValue("id")));
			toReturn.setClockIn(Integer.valueOf((String) clockEntry.getValue("clock_in")));
			toReturn.setEmployee(generateEmployee(clockEntry.getResourcesByRel("employee").iterator().next()));
		}
		catch(Exception e){
			System.out.println("Resource is not a valid clockEntry");
			e.printStackTrace();
			return null;
		}
		
		try{
			toReturn.setClockOut(Integer.valueOf((String) clockEntry.getValue("clock_out")));
		}
		catch(Exception e){
		}
		
		return toReturn;
		
	}
	
	public static Collection<Ticket> ticketConverter(Collection<ReadableRepresentation> tickets){
		Collection<Ticket> toReturn = new HashSet<Ticket>();
		Iterator<ReadableRepresentation> iterator = tickets.iterator();
		while(iterator.hasNext()){
			Ticket toAdd = generateTicket(iterator.next());
			if(toAdd != null){
				toReturn.add(toAdd);
			}
		}
		return toReturn;
	}
	
	public static Ticket generateTicket(ReadableRepresentation ticket){
		Ticket toReturn = new Ticket();
		try{
			toReturn.setVoid(Boolean.valueOf((String) ticket.getValue("void")));
			
			Map<String,Integer> temp = (Map<String,Integer>) ticket.getValue("totals");
			toReturn.setTotal(temp.get("total"));
			toReturn.setTips(temp.get("tips"));
			toReturn.setTax(temp.get("tax"));
			toReturn.setSubTotal(temp.get("sub_total"));
			toReturn.setServiceCharges(temp.get("service_charges"));
			toReturn.setPaid(temp.get("paid"));
			toReturn.setOtherCharges(temp.get("other_charges"));
			toReturn.setItems(temp.get("items"));
			toReturn.setDue(temp.get("due"));
			toReturn.setDiscounts(temp.get("discounts"));
			
			toReturn.setTicketNumber(Integer.valueOf((String) ticket.getValue("ticket_number")));
			toReturn.setOpenedAt(Integer.valueOf((String) ticket.getValue("opened_at")));
			toReturn.setOpen(Boolean.valueOf((String) ticket.getValue("open")));	
			toReturn.setId(Integer.valueOf((String) ticket.getValue("id")));
			toReturn.setAutoSend(Boolean.valueOf((String) ticket.getValue("auto_send")));
			
		}
		catch(Exception e){
			System.out.println("Resource is not a valid ticket");
			e.printStackTrace();
			return null;
		}
		
		try{
			toReturn.setName((String) ticket.getValue("name"));
		}
		catch(Exception e){
		}
		
		try{
			toReturn.setGuestCount(Integer.valueOf((String) ticket.getValue("guest_count")));
		}
		catch(Exception e){
		}
		
		try{
			toReturn.setClosedAt(Integer.valueOf((String) ticket.getValue("closed_at")));
		}
		catch(Exception e){
		}
		
		
		return toReturn;
	}
	
	public static Collection<Employee> employeeConverter(Collection<ReadableRepresentation> employee){
				
		Collection<Employee> toReturn = new HashSet<Employee>();
		Iterator<ReadableRepresentation> iterator = employee.iterator();
		while(iterator.hasNext()){
			Employee toAdd = (Employee) generateEmployee(iterator.next());
			if(toAdd != null){
				toReturn.add(toAdd);
			}
		}
		return toReturn;
	}
	
	public static Employee generateEmployee(ReadableRepresentation employee){
		Employee toReturn = new Employee();
		
		
		try{
			
			toReturn.setId(Integer.valueOf((String) employee.getValue("id")));
		}
		catch(Exception e){
			System.out.println("Resource is not a valid employee");
			e.printStackTrace();
			return null;
		}
		
		try{
			toReturn.setPosId(Integer.valueOf((String) employee.getValue("pos_id")));
		}
		catch(Exception e){
		}
		
		try{
			toReturn.setLogin(Integer.valueOf((String) employee.getValue("login")));
		}
		catch(Exception e){
		}
		
		try{
			toReturn.setLastName((String) employee.getValue("last_name"));
		}
		catch(Exception e){
		}
		
		try{
			toReturn.setFirstName((String) employee.getValue("first_name"));
		}
		catch(Exception e){
		}
		
		try{
			toReturn.setCheckName((String) employee.getValue("check_name"));
		}
		catch(Exception e){
		}
		
		return toReturn;
	}
	
	public static Collection<Category> categoryConverter(Collection<ReadableRepresentation> categories){
		Collection<Category> toReturn = new HashSet<Category>();
		Iterator<ReadableRepresentation> iterator = categories.iterator();
		while(iterator.hasNext()){
			Category toAdd = generateCategory(iterator.next());
			if(toAdd != null){
				toReturn.add(toAdd);
			}
		}
		return toReturn;
	}
	
	public static Category generateCategory(ReadableRepresentation category){
		Category toReturn = new Category();
		try{
			toReturn.setId(Integer.valueOf((String) category.getValue("id")));
			toReturn.setName((String) category.getValue("name"));
			toReturn.setLevel(Integer.valueOf((String) category.getValue("level")));
			
		}
		catch(Exception e){
			System.out.println("Resource is not a valid category");
			e.printStackTrace();
			return null;
		}
		
		if(toReturn.getLevel()>0){
			try{			
				toReturn.setParent(generateCategory(category.getResourcesByRel("parent_category").iterator().next()));
			}
			catch(Exception e){
			}
		}
		
		return toReturn;
		
	}
	
	public static Collection<MenuItem> menuItemConverter(Collection<ReadableRepresentation> menuItems){
		Collection<MenuItem> toReturn = new HashSet<MenuItem>();
		Iterator<ReadableRepresentation> iterator = menuItems.iterator();
		while(iterator.hasNext()){
			MenuItem toAdd = generateMenuItem(iterator.next());
			if(toAdd != null){
				toReturn.add(toAdd);
			}
		}
		return toReturn;
	}
	
	@SuppressWarnings("unchecked")
	public static MenuItem generateMenuItem(ReadableRepresentation menuItem){
		MenuItem toReturn = new MenuItem();
		try{
			toReturn.setOpen(Boolean.valueOf((String) menuItem.getValue("open")));
			toReturn.setName((String) menuItem.getValue("name"));
			toReturn.setId(Integer.valueOf((String) menuItem.getValue("id")));
			
		}
		catch(Exception e){
			System.out.println("Resource is not a valid menuItem");
			e.printStackTrace();
			return null;
		}
		
		try{			
			toReturn.setPricePerUnit(Integer.valueOf((String) menuItem.getValue("price_per_unit")));
		}
		catch(Exception e){
		}
		
		try{			
			toReturn.setPosId(Integer.valueOf((String) menuItem.getValue("pos_id")));
		}
		catch(Exception e){
		}

		try{			
			toReturn.setInStock(Boolean.valueOf((String) menuItem.getValue("in_stock")));
		}
		catch(Exception e){
		}
		
		try{			
			toReturn.setPriceLevels(priceLevelConverter((List<ReadableRepresentation>) menuItem.getResourcesByRel("price_levels")));
		}
		catch(Exception e){
		}
		
		try{			
			toReturn.setCategories(categoryConverter((List<ReadableRepresentation>) menuItem.getResourcesByRel("menu_categories")));
		}
		catch(Exception e){
		}

		
		return toReturn;
		
	}
	
	public static Collection<PriceLevel> priceLevelConverter(Collection<ReadableRepresentation> priceLevels){
		Collection<PriceLevel> toReturn = new HashSet<PriceLevel>();
		Iterator<ReadableRepresentation> iterator = priceLevels.iterator();
		while(iterator.hasNext()){
			PriceLevel toAdd = generatePriceLevel(iterator.next());
			if(toAdd != null){
				toReturn.add(toAdd);
			}
		}
		return toReturn;
	}
	
	public static PriceLevel generatePriceLevel(ReadableRepresentation priceLevel){
		
		PriceLevel toReturn = new PriceLevel();
		try{
			toReturn.setPricePerUnit(Integer.valueOf((String) priceLevel.getValue("price_per_unit")));
			toReturn.setId(Integer.valueOf((String) priceLevel.getValue("id")));
			
		}
		catch(Exception e){
			System.out.println("Resource is not a valid priceLevel");
			e.printStackTrace();
			return null;
		}
		
		try{
			toReturn.setName((String) priceLevel.getValue("name"));
		}
		catch(Exception e){
		}

		
		return toReturn;
	}
	
	public static Collection<TicketItem> ticketItemConverter(Collection<ReadableRepresentation> ticketItems){
		Collection<TicketItem> toReturn = new HashSet<TicketItem>();
		Iterator<ReadableRepresentation> iterator = ticketItems.iterator();
		while(iterator.hasNext()){
			TicketItem toAdd = generateTicketItem(iterator.next());
			if(toAdd != null){
				toReturn.add(toAdd);
			}
		}
		return toReturn;
	}
	
	public static TicketItem generateTicketItem(ReadableRepresentation ticketItem){
		TicketItem toReturn = new TicketItem();
		
		try{
			toReturn.setSplit(Integer.valueOf((String) ticketItem.getValue("split")));
			toReturn.setQuantity(Integer.valueOf((String) ticketItem.getValue("quantity")));
			toReturn.setPrice(Integer.valueOf((String) ticketItem.getValue("price")));
			toReturn.setId(Integer.valueOf((String) ticketItem.getValue("id")));
			
		}
		catch(Exception e){
			System.out.println("Resource is not a valid ticketItem");
			e.printStackTrace();
			return null;
		}
		
		try{
			toReturn.setSentAt(Integer.valueOf((String) ticketItem.getValue("sent_at")));
		}
		catch(Exception e){
		}
		
		try{
			toReturn.setSent(Boolean.valueOf((String) ticketItem.getValue("sent")));
		}
		catch(Exception e){
		}
		
		try{
			toReturn.setName((String) ticketItem.getValue("name"));
		}
		catch(Exception e){
		}
		
		try{
			toReturn.setComment((String) ticketItem.getValue("comment"));
		}
		catch(Exception e){
		}
		
		try{
			toReturn.setMenuItem(generateMenuItem(ticketItem.getResourcesByRel("menu_item").iterator().next()));
		}
		catch(Exception e){
		}
		
		return toReturn;
	}
}
