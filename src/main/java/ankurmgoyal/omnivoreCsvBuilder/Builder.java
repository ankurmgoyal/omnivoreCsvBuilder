package ankurmgoyal.omnivoreCsvBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;

import ankurmgoyal.api.Category;
import ankurmgoyal.api.ComparisonOperator;
import ankurmgoyal.api.Condition;
import ankurmgoyal.api.Employee;
import ankurmgoyal.api.MenuItem;
import ankurmgoyal.api.TicketField;
import ankurmgoyal.api.TicketItem;
import ankurmgoyal.api.RequestFactory;
import ankurmgoyal.api.Resource;
import ankurmgoyal.api.Ticket;
import ankurmgoyal.api.UrlBuilder;
import okhttp3.Response;

public class Builder {
	
	static String loc;
	
	public static void main( String[] args )
    {
		System.out.println("File is writing...");
        try{
        	setLocationID();
        	createCSV();
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        System.out.println("File complete.");
    }
	
	public static void setLocationID() throws IOException{
		Properties prop = new Properties();
    	InputStream input = null;
    	input = new FileInputStream("config.properties");
    	prop.load(input);
		loc = prop.getProperty("locationId");
	}
	
	public static void createCSV() throws IOException{
    	LocalDate today = LocalDate.now(); 
    	int month = today.getMonthValue();
    	int day = today.getDayOfMonth();
    	
    	File file = new File(loc+"_"+month+"-"+day+".csv");
    	Collection<Ticket> tickets = getTickets();
    	
		FileWriter fw = new FileWriter(file);
		fw.write("Location,Item ID,Actual Price, Split, Quantity, Ticket ID, Voided?, Total, Tips, Tax, Sub Total, Service Charges, Paid, Other Charges, Items, Due, Discounts, Open Month, Open Day, Open Year, Open Hour, Open Minute, Close Month, Close Day, Close Year, Close Hour, Close Minute, Guest Count, Employee ID, First Name, Last Name, Menu Item ID, Item Name, Original Price, Category 1, Parent Category 1, Category 2, Parent Category 2\n");
		for(Ticket t: tickets){
			ticketWriter(t,fw);
		}
		fw.flush();
		fw.close();
    }
	
	public static Collection<Ticket> getTickets(){
		
		//Gets all Tickets in system. Conditional calls in API are currently malfunctioning
		String url = UrlBuilder.buildURL(loc, Resource.TICKETS);
		Response response = RequestFactory.responseGetter(url);
	    Collection<Ticket> tickets = RequestFactory.ticketConverter(RequestFactory.getMainResourceList(response,Resource.TICKETS));
	    
	    //Tools to truncate set of Tickets to only those received in past 30 days
	    Iterator<Ticket> it = tickets.iterator();
	    Collection<Ticket> toRemove = new HashSet<Ticket>();
	    int cutOff = (int)(System.currentTimeMillis()/1000) - (30 * 86400);
	   
	    //removes all tickets that are > 30 days old
	    while(it.hasNext()){
	    	Ticket t = it.next();
	    	if(t.getOpenedAt()<cutOff){
	    		toRemove.add(t);
	    	}
	    }
	    tickets.removeAll(toRemove);
	    return tickets;
	}
	
	public static void ticketWriter(Ticket ticket, FileWriter fw) throws IOException{
		Employee e = ticket.getEmployee();
		ZoneOffset zone = OffsetDateTime.now().getOffset();
		
		for(TicketItem t: ticket.getTicketItems()){
			fw.write(loc+",");
			
			//information about ticket item
			fw.write(t.getId()+",");
			fw.write(t.getPrice()+",");
			fw.write(t.getSplit()+",");
			fw.write(t.getQuantity()+",");
			
			//information about ticket
			fw.write(ticket.getId()+",");
			fw.write(ticket.isVoid()+",");
			fw.write(ticket.getTotal()+",");
			fw.write(ticket.getTips()+",");
			fw.write(ticket.getTax()+",");
			fw.write(ticket.getSubTotal()+",");
			fw.write(ticket.getServiceCharges()+",");
			fw.write(ticket.getPaid()+",");
			fw.write(ticket.getOtherCharges()+",");
			fw.write(ticket.getItems()+",");
			fw.write(ticket.getDue()+",");
			fw.write(ticket.getDiscounts()+",");
			LocalDateTime ldt = LocalDateTime.ofEpochSecond(ticket.getOpenedAt(), 0, zone);
			fw.write(ldt.getMonthValue()+",");
			fw.write(ldt.getDayOfMonth()+",");
			fw.write(ldt.getYear()+",");
			fw.write(ldt.getHour()+",");
			fw.write(ldt.getMinute()+",");
			if(ticket.getClosedAt() > 0){
				ldt = LocalDateTime.ofEpochSecond(ticket.getClosedAt(), 0, zone);
				fw.write(ldt.getMonthValue()+",");
				fw.write(ldt.getDayOfMonth()+",");
				fw.write(ldt.getYear()+",");
				fw.write(ldt.getHour()+",");
				fw.write(ldt.getMinute()+",");
			}
			else{
				fw.write(",,,,,");
			}
			fw.write(ticket.getGuestCount()+",");
			
			//information about employee
			fw.write(e.getId()+",");
			fw.write(e.getFirstName()+",");
			fw.write(e.getLastName()+",");
			
			//information about menu item
			MenuItem m = t.getMenuItem();
			fw.write(m.getId()+",");
			fw.write(m.getName()+",");
			fw.write(m.getPricePerUnit()+",");
			
			//information about category
			Collection<Category> categories = m.getCategories();
			for(Category c:categories){
				fw.write(c.getName()+",");
				if(c.getLevel()>0){
					fw.write(c.getParent().getName()+",");
				}
				else{
					fw.write(",");
				}
			}
		}
		fw.write("\n");
		
	}
	
}
