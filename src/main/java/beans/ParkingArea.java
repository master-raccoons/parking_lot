package beans;

public class ParkingArea {

	private ParkingPlace[][] parkingPlaces;

	ParkingArea(int nowOfRows,int noOfCols)
	{
		this.parkingPlaces=new ParkingPlace[nowOfRows][noOfCols];
	}

}
