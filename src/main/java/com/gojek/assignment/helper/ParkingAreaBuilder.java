package com.gojek.assignment.helper;

import com.gojek.assignment.beans.ParkingEntrance;
import com.gojek.assignment.beans.Position;
import com.gojek.assignment.config.ParkingArea;
import com.gojek.assignment.operation.ParkingSpace;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ParkingAreaBuilder {

	private ParkingArea parkingArea;
	private String structure;

public 	ParkingAreaBuilder(String input)
	{

		structure=input;

	}

	public ParkingAreaBuilder build() throws Exception
	{

		String parkingSlots=this.structure;
		int row=1;
		int col=1;
		if(parkingSlots.startsWith("create_parking_lot"))
		{
			String configArr=parkingSlots.substring("create_parking_lot ".length());
			String dataArr[]=configArr.split(" ");
			col=Integer.valueOf(dataArr[0])+1; // +1 is to start the slots with 1 not from 0
			if(dataArr.length>1)
			{
				col=Integer.valueOf(dataArr[0]);
				row=Integer.valueOf(dataArr[1]);
			}
		}
		else
			{
				throw new Exception("Incorrect parking command passed!!!");
			}
		Set<ParkingSpace> entranceSet=new LinkedHashSet<>();// to reserve the insertion order
		ParkingEntrance entrance=new ParkingEntrance();
		entrance.setPosition(new Position(0, 0));
		entrance.setFree(false);
		entrance.setFloorNo(0);
		entranceSet.add(entrance);

			/*ParkingEntrance entrance1=new ParkingEntrance();
			entrance1.setPosition(new Position(0,19));
			entrance1.setFree(false);
			entrance1.setFloorNo(0);
			entranceSet.add(entrance1);*/

		ParkingFloor floor=new ParkingFloor("Floor 0", row, col, 0, entranceSet);
		List<ParkingFloor> listFloor=new ArrayList<ParkingFloor>();
		listFloor.add(floor);
		 this.parkingArea=new ParkingArea(listFloor.toArray(new ParkingFloor[listFloor.size()]));
		 return this;
	}

	public ParkingArea getParkingArea()
	{
		return this.parkingArea;

	}

}
