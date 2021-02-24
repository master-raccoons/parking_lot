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
		    cmnds.stream().peek(cmnd->
		                        {
		                        	if(cmnd.startsWith("park"))
		                        	{
				                        try {
					                        parkingArea.parkVehicle(cmnd.split(" ")[0]);
				                        } catch (Exception exception) {
					                        exception.printStackTrace();
				                        }
			                        }

		                        });

		}
		catch(Exception ex)
		{
		  ex.printStackTrace();

		}


	}


}
