package beans;

import constants.ParkingType;
import constants.VehicleType;
import javafx.geometry.Pos;
import services.FareCalculate;

import java.util.Date;

public class ParkingFloor {

	String floorName;
	final int floorNo;
	private final ParkingPlace[][] parkingPlaces;
    private final int totalParkingSpace;
    private int occupiedParking;
	static int  index=0;


	ParkingFloor(String floorName, int nowOfRows, int noOfCols,int floorNo) {
		this.floorName = floorName;
		this.parkingPlaces = new ParkingPlace[nowOfRows][noOfCols];
		this.floorNo=floorNo;
		totalParkingSpace=noOfCols*nowOfRows;
	}

	public void addParkingPlace(ParkingPlace parkingPlace) {

		switch (parkingPlace.getType()) {
			case COMPACT:
			    Position pos=findNearestParkingSpace();
			    String slotNumber=parkingPlace.getFloorNo()+"F-"+pos.getRow()+""+pos.getCol();
                Vehicle vehicle=new Car(parkingPlace.getNumber(), VehicleType.CAR,new Ticket(slotNumber));
				parkingPlace.setVehicle(vehicle);
				placeVehicle(pos,parkingPlace);
                break;

				case LARGE:
				break;

			default:
				System.out.println("Invalid Parking Type!");
		}
	}

	private Position findNearestParkingSpace()
	{
         if(occupiedParking+1 <totalParkingSpace)
         {
         	//findNearestparking
         }

		return new Position(index++,0);
	}

	public void placeVehicle(Position position, ParkingPlace parkingPlace)
	{

			  this.parkingPlaces[position.getRow()][position.getCol()] = parkingPlace;
			  parkingPlace.setPosition(position);
			  occupiedParking++;

	}

	public void freeSpot(ParkingPlace parkingPlace)
	{
        this.parkingPlaces[parkingPlace.getPosition().getRow()][parkingPlace.getPosition().getCol()]=null;
		double charge= FareCalculate.calculateFare(parkingPlace.getParkingTime());
		parkingPlace.getVehicle().getTicket().setPaidAmount(charge);
		parkingPlace.getVehicle().getTicket().setUnparkedAt(new Date());
        occupiedParking--;
	}


}