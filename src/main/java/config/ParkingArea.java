package config;

import beans.ParkingPlace;
import beans.Vehicle;
import constants.ParkingConstants;
import constants.ParkingType;
import helper.ParkingFloor;

import java.util.HashMap;

public class ParkingArea {

	private ParkingFloor[] parkingFloors;
    private final HashMap<String, ParkingPlace> parkingPlaceRecord;
	public ParkingArea()
	{
		parkingFloors=new ParkingFloor[ParkingConstants.NO_OF_FLOORS];
		this.fill(parkingFloors);
		parkingPlaceRecord=new HashMap<>();
	}

	public static void fill(Object[] a) {
		for (int i = 0, len = a.length; i < len; i++) {
			ParkingFloor parkingFloor=new ParkingFloor("F"+i,3,3,i);
			a[i] = parkingFloor;
		}
	}

   public synchronized ParkingPlace parkVehicle(String number) throws Exception
   {
   	   if(isVehicleParked(number))
   	   {
   	   	   throw new Exception("Duplicate "+number+" found!!!");
       }
   	   ParkingFloor floor=findFloorNo();
   	   ParkingPlace parkingPlace=floor.findNearestParkingSpace();
	   parkingPlace.setNumber(number);
	   parkingPlace.setType(ParkingType.COMPACT);
	   parkingPlace.setFloorNo(floor.getFloorNo());
	   if(parkingPlace.isFree())
	   {
		       floor.addParkingPlace(parkingPlace);
			   parkingPlaceRecord.put(number,parkingPlace);
		       System.out.println("Registration number "+number+" Allocated slot number: "+parkingPlace.getVehicle().getTicket().getTicketNo());
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
           System.out.println("Registration number"+ vehicleNumber+" with Slot Number"+vehicle.getTicket().getTicketNo() +" is free with Charge"+ vehicle.getTicket().getPaidAmount() );
		   return parkingPlace;
		}
       throw new Exception("Vehicle with the number "+vehicleNumber+" is not parked here");
	}

	public ParkingFloor[] getParkingFloors() {

		return parkingFloors;
	}

	public HashMap<String, ParkingPlace> getParkingPlaceRecord() {

		return parkingPlaceRecord;
	}

	public void showStatus()
	{
		System.out.println("Slot No.      Registration No");
		for(int i=0;i<this.parkingFloors.length;i++)
		{
           ParkingFloor floor=this.parkingFloors[i];
           floor.getFloorStatus();
		}

	}
}