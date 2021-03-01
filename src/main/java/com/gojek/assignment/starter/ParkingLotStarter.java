package com.gojek.assignment.starter;


import com.gojek.assignment.beans.ParkingEntrance;
import com.gojek.assignment.beans.Position;
import com.gojek.assignment.config.ParkingArea;
import com.gojek.assignment.factory.OperationsFactory;
import com.gojek.assignment.helper.ParkingAreaBuilder;
import com.gojek.assignment.helper.ParkingFloor;
import com.gojek.assignment.operation.ParkingSpace;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ParkingLotStarter
{

	public static void main(String args[]) throws Exception
	{
		try
		{
			//args=new  String[1];
			//args[0]="resource.properties";
			List<String> listOfCommands= Files.readAllLines(Paths.get(args[0]));
			ParkingArea parkingArea= new ParkingAreaBuilder(listOfCommands.get(0)).build().getParkingArea();
			listOfCommands.remove(0);
			new Thread(() -> {
				   listOfCommands.stream().forEach(input-> {
				try {
					OperationsFactory.getParkingOperation(input, parkingArea).execute();
					Thread.sleep(1000);
				} catch (Exception exception) {
					if(exception.getMessage()!=null)
						System.out.println(exception.getMessage());
				}
			});
				System.out.println("exiting the application!!!");
			}).start();

		}
		catch(Exception ex)
		{
		  ex.printStackTrace();

		}



	}


}
