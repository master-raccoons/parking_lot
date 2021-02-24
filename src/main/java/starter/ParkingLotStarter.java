package starter;

import beans.ParkingArea;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ParkingLotStarter
{
    //Logger logger=Logger.getLogger("ParkingLotStarter");
	public static void main(String args[]) throws Exception
	{
		try
		{
			//String resource="C:\\Users\\NEETEPAT\\parking-lot\\resource.properties";
			//List<String> listOfCommands= Files.readAllLines(Paths.get(""));
            List<String> cmnds=new ArrayList<>();
			ParkingArea parkingArea=new ParkingArea();
			cmnds.add("park KA-01-HH-1234");
			cmnds.add("park KA-01-HH-9999");
			cmnds.add("park KA-01-BB-0001");
			cmnds.add("park KA-01-HH-7777");
			cmnds.add("park KA-01-HH-2701");
			cmnds.add("park KA-01-HH-3141");
			cmnds.add("leave KA-01-HH-3141 3");
			cmnds.add("leave KA-01-HH-7777 2");
			cmnds.add("leave KA-01-HH-7777 2");
		    for(String cmnd:cmnds)
		                        {
		                        	if(cmnd.startsWith("park"))
		                        	{
				                        try {
					                        parkingArea.parkVehicle(cmnd.split(" ")[1]);
				                        } catch (Exception exception) {
					                        exception.printStackTrace();
				                        }

			                        }
			                        else if(cmnd.startsWith("leave"))
			                        {
				                        try {
					                        parkingArea.unparkVehicle(cmnd.split(" ")[1],Float.valueOf(cmnd.split(" ")[2]));
				                        } catch (Exception exception) {
					                        exception.printStackTrace();
				                        }
			                        }

		                        };
System.out.println("exiting the application!!!");
		}
		catch(Exception ex)
		{
		  ex.printStackTrace();

		}


	}


}
