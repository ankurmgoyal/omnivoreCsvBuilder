package ankurmgoyal.omnivoreCsvBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Properties;

public class Builder {
	
	public static void main( String[] args )
    {
        try{
    	createCSV();
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        System.out.println("hEllo");
    }
	
	public static void createCSV() throws IOException{
    	LocalDate today = LocalDate.now(); 
    	int month = today.getMonthValue();
    	
    	System.out.println(month);
		
    	/*
    	File file = new File("test.csv");
		FileWriter fw = new FileWriter(file);
		fw.write("name,styleNumber,url\n");
		fw.flush();
		fw.close();
		*/
    }

}
