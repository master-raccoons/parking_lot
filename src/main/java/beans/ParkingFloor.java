package beans;

import constants.ParkingType;
import constants.VehicleType;
import javafx.geometry.Pos;
import services.FareCalculate;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class ParkingFloor {

	private String floorName;
	private int floorNo;
	private final ParkingPlace[][] parkingPlaces;
    private final int totalParkingSpace;
    private int occupiedParking;
	static int  index=0;

	public String getFloorName() {

		return floorName;
	}

	public void setFloorName(String floorName) {

		this.floorName = floorName;
	}

	public int getFloorNo() {

		return floorNo;
	}
	public void setFloorNo(int floorNo) {

		this.floorNo=floorNo;
	}

	public ParkingPlace[][] getParkingPlaces() {

		return parkingPlaces;
	}

	public int getTotalParkingSpace() {

		return totalParkingSpace;
	}

	public int getOccupiedParking() {

		return occupiedParking;
	}

	public void setOccupiedParking(int occupiedParking) {

		this.occupiedParking = occupiedParking;
	}

	ParkingFloor(String floorName, int nowOfRows, int noOfCols, int floorNo) {
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
	        Position position= FindMinimumDistance();
            return position;
         }

		return new Position(0,0);
	}

	 private Position FindMinimumDistance()
	{

		int dx[] = { 0,  -1,  0,  1  };
		int dy[] = { 1,  0,  -1,  0 };
		Queue<Position> q = new LinkedList<>();
        Position entryPoint=new Position(0,0);
		q.add(entryPoint);
		//mat[x][y] = 0;
		ParkingPlace arr[][]=this.parkingPlaces;
        int x,y;
        Position pos=null;
        label:
		while (!q.isEmpty())
		{
			x = q.peek().row;
			y = q.peek().col;
			q.remove();
			for(int i = 0; i < 4; i++)
			{
				int a = x + dx[i];
				int b = y + dy[i];
				if(a==0 && b==0) continue;
				// Checking boundary condition
				if (a < 0 || a >= this.parkingPlaces.length ||
				    b >= this.parkingPlaces[0].length || b < 0)
					continue;
				// If the cell is not visited
				if (this.parkingPlaces[a][b] == null)
				{
					pos= new Position(a, b);
                    break label;
				}
				q.add(new Position(a, b));
			}
		}
		return pos;

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