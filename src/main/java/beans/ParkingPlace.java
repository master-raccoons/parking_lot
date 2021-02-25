package beans;

import constants.ParkingType;
import interfaces.ParkingSpace;

public class ParkingPlace extends ParkingSpace {

	private String number;
	private Vehicle vehicle;
	private ParkingType type;
    private int floorNo;
    private float parkingTime;

	public float getParkingTime() {

		return parkingTime;
	}

	public void setParkingTime(float parkingTime) {

		this.parkingTime = parkingTime;
	}

	public int getFloorNo() {

		return floorNo;
	}

	public void setFloorNo(int floorNo) {

		this.floorNo = floorNo;
	}

	public void setNumber(String number) {

		this.number = number;
	}


	public void setVehicle(Vehicle vehicle) {

		this.vehicle = vehicle;
	}

	public void setType(ParkingType type) {

		this.type = type;
	}

	public String getNumber() {

		return number;
	}



	public Vehicle getVehicle() {

		return vehicle;
	}

	public ParkingType getType() {

		return type;
	}


}
