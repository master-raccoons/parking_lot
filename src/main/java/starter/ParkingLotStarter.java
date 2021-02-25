package starter;

import beans.ParkingArea;
import beans.ParkingEntrance;
import beans.Position;

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
			cmnds.add("park KA-01-HH-3142");
			cmnds.add("park KA-01-HH-7778");
			cmnds.add("park KA-01-BB-0002");
			cmnds.add("park KA-01-BB-0003");
			cmnds.add("park KA-01-HH-7718");
			cmnds.add("park KA-01-BB-0022");
			cmnds.add("park KA-01-BB-0033");
			cmnds.add("leave KA-01-BB-0033 2");
			cmnds.add("leave KA-01-HH-9999 2");
			cmnds.add("park KA-01-BB-0007");
			cmnds.add("status");
			cmnds.add("leave KA-01-HH-9999 2");
			ParkingEntrance entrance=new ParkingEntrance();
			entrance.setPosition(new Position(0,0));
			entrance.setFree(false);
			parkingArea.getParkingFloors()[0].getParkingSpaces()[0][0]=entrance;
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
			                        else if(cmnd.startsWith("status"))
			                        {
				                        try {
					                        parkingArea.ShowStatus();
				                        } catch (Exception exception) {
					                        exception.printStackTrace();
				                        }
			                        }


		                        };

		}
		catch(Exception ex)
		{
		  ex.printStackTrace();

		}
		System.out.println("exiting the application!!!");

	}


}
