package com.gojek.assignment.beans;

import com.gojek.assignment.constants.VehicleType;

public abstract class Vehicle {
	private String vehicleNumber;
	private VehicleType type;
	private Ticket ticket;

	public Vehicle(String vehicleNumber, VehicleType type, Ticket ticket) {

		this.vehicleNumber = vehicleNumber;
		this.type = type;
		this.ticket = ticket;
	}

	public String getVehicleNumber() {

		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {

		this.vehicleNumber = vehicleNumber;
	}

	public VehicleType getType() {

		return type;
	}

	public void setType(VehicleType type) {

		this.type = type;
	}

	public Ticket getTicket() {

		return ticket;
	}

	public void setTicket(Ticket ticket) {

		this.ticket = ticket;
	}
}
