package beans;

import constants.ParkingConstants;
import constants.ParkingType;
import constants.VehicleType;

import java.util.HashMap;

public class ParkingArea {

	private ParkingFloor[] parkingFloors;
    private final HashMap<String,ParkingPlace> parkingPlaceRecord;
	public ParkingArea()
	{
		parkingFloors=new ParkingFloor[ParkingConstants.NO_OF_FLOORS];
		parkingPlaceRecord=new HashMap<>();
	}

   public ParkingPlace parkVehicle(String number) throws Exception
   {
   	   if(isVehicleParked(number))
   	   {
   	   	   throw new Exception("Duplicate "+number+" found!!!");
       }
   	   ParkingFloor floor=null;
   	   ParkingPlace parkingPlace=new ParkingPlace();
	   parkingPlace.setNumber(number);
	   parkingPlace.setType(ParkingType.COMPACT);
	   parkingPlace.setFloorNo(floor.floorNo);
	   floor.addParkingPlace(parkingPlace);
	   if(!parkingPlace.isFree())
	   {
		    synchronized (parkingPlaceRecord)
		   {
			   parkingPlaceRecord.put(number,parkingPlace);
		   }

	   }
       return parkingPlace;
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
           parkingFloor.freeSpot(parkingPlace);
           Vehicle vehicle=parkingPlace.getVehicle();
           System.out.println("Registration number"+ vehicleNumber+" with Slot Number"+vehicle.getTicket().getTicketNo() +" is free with Charge"+ vehicle.getTicket().getPaidAmount() );
		   return parkingPlace;
		}
       throw new Exception("Vehicle with the number "+vehicleNumber+" is not parked here");
	}


}
