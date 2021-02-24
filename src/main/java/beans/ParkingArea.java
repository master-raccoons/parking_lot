package beans;

import constants.ParkingConstants;
import constants.ParkingType;
import constants.VehicleType;

import java.util.HashMap;

public class ParkingArea {

	private ParkingFloor[] parkingFloors;
    private final HashMap<String,ParkingPlace> parkingPlaceRecord;
	ParkingArea()
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




}
