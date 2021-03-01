package com.gojek.assignment.config;

import com.gojek.assignment.beans.ParkingEntrance;
import com.gojek.assignment.beans.ParkingPlace;
import com.gojek.assignment.beans.Vehicle;
import com.gojek.assignment.constants.ParkingConstants;
import com.gojek.assignment.constants.ParkingType;
import com.gojek.assignment.operation.ParkingSpace;
import com.gojek.assignment.helper.ParkingFloor;

import java.util.HashMap;
import java.util.Optional;

public class ParkingArea {

	private ParkingFloor[] parkingFloors;
	/* This is used to track all vehicles*/
    private final HashMap<String, ParkingPlace> parkingPlaceRecord;


	public ParkingArea(final ParkingFloor[] parkingFloors )
	{

		this.parkingFloors=parkingFloors;
		//this.fill(parkingFloors);
		parkingPlaceRecord=new HashMap<>();
	}

/*This is for static initialization with default config */
	public static void fill(Object[] a) {
		for (int i = 0, len = a.length; i < len; i++) {
			ParkingFloor parkingFloor=new ParkingFloor("F"+i, ParkingConstants.NO_OF_ROWS_PER_FLOOR, ParkingConstants.NO_OF_COLS_PER_FLOOR, i, null);
			a[i] = parkingFloor;
		}
	}

   public synchronized ParkingPlace parkVehicle(String registrationNo,String pos) throws Exception
   {
   	   if(isVehicleParked(registrationNo))
   	   {
   	   	   throw new Exception("Duplicate "+registrationNo+" found!!!");
       }
   	   ParkingFloor floor=findFloorNo();
   	   if(pos!=null && !pos.isEmpty())
   	   {
   	   	   int row=Integer.valueOf(pos.trim().split("-")[0]);
	       int col=Integer.valueOf(pos.trim().split("-")[1]);
	       if(!(floor.getParkingSpaces()[row][col] instanceof ParkingEntrance))
	       	  throw new Exception("Invalid Entrance entered for vehicle with registration no:"+registrationNo);
   	   	   floor.setParkingEntrance(floor.getParkingSpaces()[row][col]);
       }
   	   else
       {
	       Optional<ParkingSpace> optionalParkingSpace=floor.getParkingEntranceSet().stream().findFirst();
	       optionalParkingSpace.ifPresent(floor::setParkingEntrance);
       }

	   ParkingPlace parkingPlace=floor.findNearestParkingSpace();
	   parkingPlace.setNumber(registrationNo);
	   parkingPlace.setType(ParkingType.COMPACT);
	   parkingPlace.setFloorNo(floor.getFloorNo());
	   if(parkingPlace.isFree())
	   {
		       floor.addParkingPlace(parkingPlace);
			   parkingPlaceRecord.put(registrationNo,parkingPlace);
		       System.out.println("Vehicle with the Registration Number "+registrationNo+" parked and Allocated slot number: "+parkingPlace.getVehicle().getTicket().getTicketNo());
	   }
       return parkingPlace;
   }

	private ParkingFloor findFloorNo() throws Exception
	{
        for(int i=0;i<this.parkingFloors.length;i++)
        {
        	if(this.parkingFloors[i].getOccupiedParking()<this.parkingFloors[i].getTotalParkingSpace())
        	{
        		return this.parkingFloors[i];
	        }
        }
       throw new Exception("Sorry!!!! Parking is full");
	}

	private boolean isVehicleParked(String vehicleNumber)
	  {
	      return parkingPlaceRecord.containsKey(vehicleNumber)  ?true:false;

	  }

	public ParkingPlace unparkVehicle(String vehicleNumber,float parkingTime) throws Exception
	{
		if(isVehicleParked(vehicleNumber))
		{
           ParkingPlace parkingPlace=this.parkingPlaceRecord.get(vehicleNumber);
           ParkingFloor parkingFloor=this.parkingFloors[parkingPlace.getFloorNo()];
		   parkingPlace.setParkingTime(parkingTime);
		   Vehicle vehicle=parkingPlace.getVehicle();
           parkingFloor.freeSpot(parkingPlace);
		   this.parkingPlaceRecord.remove(vehicleNumber);
           System.out.println("Vehicle having Registration number "+ vehicleNumber+" with Slot Number"+vehicle.getTicket().getTicketNo() +" is free with Charge "+ vehicle.getTicket().getPaidAmount() );
		   return parkingPlace;
		}
       throw new Exception("Vehicle with the Registration Number "+vehicleNumber+" is not found!!!");
	}

	public ParkingFloor[] getParkingFloors() {

		return parkingFloors;
	}

	public HashMap<String, ParkingPlace> getParkingPlaceRecord() {

		return parkingPlaceRecord;
	}

	public void showStatus()
	{
		System.out.println(ParkingConstants.RED + "Slot No.      Registration No" + ParkingConstants.RESET);
		System.out.println("--------------------------------------------");
		for(int i=0;i<this.parkingFloors.length;i++)
		{
           ParkingFloor floor=this.parkingFloors[i];
           floor.getFloorStatus();
		}

	}
}
