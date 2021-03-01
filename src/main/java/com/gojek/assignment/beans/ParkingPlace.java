package com.gojek.assignment.beans;

import com.gojek.assignment.constants.ParkingType;
import com.gojek.assignment.operation.ParkingSpace;

public class ParkingPlace extends ParkingSpace {

	private String number;
	private Vehicle vehicle;
	private ParkingType type;

    private float parkingTime;

	public float getParkingTime() {

		return parkingTime;
	}

	public void setParkingTime(float parkingTime) {

		this.parkingTime = parkingTime;
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
