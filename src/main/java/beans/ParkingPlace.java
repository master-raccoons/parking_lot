package beans;

import constants.ParkingType;


public class ParkingPlace {

	private String number;
	private boolean free;
	private Vehicle vehicle;
	private ParkingType type;
	private Position position;
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

	public void setFree(boolean free) {

		this.free = free;
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

	public boolean isFree() {

		return free;
	}

	public Vehicle getVehicle() {

		return vehicle;
	}

	public ParkingType getType() {

		return type;
	}

	public Position getPosition() {

		return position;
	}

	public void setPosition(Position position) {

		this.position = position;
	}
}
