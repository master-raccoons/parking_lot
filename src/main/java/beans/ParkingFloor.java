package beans;

import constants.VehicleType;
import interfaces.ParkingSpace;
import services.ParkingChargeCalculate;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class ParkingFloor {

	private String floorName;
	private int floorNo;
	private final ParkingSpace[][] parkingSpaces;
    private final int totalParkingSpace;
    private int occupiedParking=1;


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

	public ParkingSpace[][] getParkingSpaces() {

		return parkingSpaces;
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
		this.parkingSpaces = new ParkingSpace[nowOfRows][noOfCols];
		this.floorNo=floorNo;
		totalParkingSpace=noOfCols*nowOfRows;
		fill(this.parkingSpaces);
	}

	public  void fill(Object[][] a) {
		for (int i = 0, len = a.length; i < len; i++)
		{
			for (int j = 0, len1 = a[0].length; j < len1; j++)
			{
				if(a[i][j]==null)
				a[i][j]=new ParkingPlace();
			}
		}
	}


	public void addParkingPlace(ParkingPlace parkingPlace) throws Exception {

		switch (parkingPlace.getType()) {
			case COMPACT:
			    String slotNumber=parkingPlace.getFloorNo()+"F-"+parkingPlace.getPosition().getRow()+""+parkingPlace.getPosition().getCol();
                Vehicle vehicle=new Car(parkingPlace.getNumber(), VehicleType.CAR,new Ticket(slotNumber));
				parkingPlace.setVehicle(vehicle);
				placeVehicle(parkingPlace);
                break;

				case LARGE:
				break;

			default:
				System.out.println("Invalid Parking Type!");
		}
	}

	public ParkingPlace findNearestParkingSpace() throws Exception
	{
         if(occupiedParking <totalParkingSpace)
         {
	         Position position= findMinimumDistance();
	         ParkingPlace parkingPlace=(ParkingPlace)this.parkingSpaces[position.getRow()][position.getCol()];
	         parkingPlace.setPosition(position);
	         return parkingPlace;
         }

		throw new Exception("No Parking available!!!");
	}

	 private Position findMinimumDistance()
	{

		int dx[] = { 0,  -1,  0,  1  };
		int dy[] = { 1,  0,  -1,  0 };
		Queue<Position> queue = new LinkedList<>();
        Set<String> set=new HashSet<>();
        Position entryPoint=null;
		if(this.parkingSpaces[0][0]!=null && this.parkingSpaces[0][0] instanceof ParkingEntrance)
		{
			entryPoint=((ParkingEntrance)this.parkingSpaces[0][0]).getPosition();
			queue.add(entryPoint);
		}
		else
			{
				queue.add(new Position(0,0))	;
			}
        int x,y;
        Position pos=null;
        label:
		while (!queue.isEmpty())
		{
			x = queue.peek().row;
			y = queue.peek().col;
			queue.remove();
			if (this.parkingSpaces[x][y] == null)
			{
				return new Position(x, y);
			}
			for(int i = 0; i < 4; i++)
			{
				int a = x + dx[i];
				int b = y + dy[i];
				if(entryPoint!=null && entryPoint.getRow()==a && entryPoint.getCol()==b)
				 continue;
				// Checking boundary condition
				if (a < 0 || a >= this.parkingSpaces.length ||
				    b >= this.parkingSpaces[0].length || b < 0)
					continue;
				// If the slot is not visited
				if ( this.parkingSpaces[a][b].isFree())
				{
					pos= new Position(a, b);
                    break label;
				}
				if(!set.contains(a + "" + b)) {
					set.add(a + "" + b);
					queue.add(new Position(a, b));
				}
			}
		}
		return pos;

	}



	public void placeVehicle( ParkingPlace parkingPlace)
	{
		      parkingPlace.setFree(false);
			  occupiedParking++;

	}

	public void freeSpot(ParkingPlace parkingPlace)
	{
        this.parkingSpaces[parkingPlace.getPosition().getRow()][parkingPlace.getPosition().getCol()].setFree(true);
		double charge= ParkingChargeCalculate.calculateFare(parkingPlace.getParkingTime());
		parkingPlace.getVehicle().getTicket().setPaidAmount(charge);
		parkingPlace.getVehicle().getTicket().setUnparkedAt(new Date());
        occupiedParking--;
		freeResources(parkingPlace);
	}

	private void freeResources(ParkingPlace parkingPlace)
	{
		parkingPlace.setVehicle(null);
		parkingPlace.setParkingTime(0f);
	}

	public void getFloorStatus()
	{

		for (int i = 0, len = this.getParkingSpaces().length; i < len; i++)
		{
			for (int j = 0, len1 = this.getParkingSpaces()[0].length; j < len1; j++)
			{
				if(!this.getParkingSpaces()[i][j].isFree() && this.getParkingSpaces()[i][j] instanceof  ParkingPlace)
				{
					ParkingPlace parkingPlace=(ParkingPlace)this.getParkingSpaces()[i][j];
					System.out.println(parkingPlace.getVehicle().getTicket().getTicketNo()+"  "+parkingPlace.getVehicle().getVehicleNumber());
				}
			}
		}

	}
}