package beans;

import constants.VehicleType;

public abstract class Vehicle {
	private String vehicleNumber;
	private  VehicleType type;
	Ticket ticket;

	public Vehicle(String vehicleNumber, VehicleType type, Ticket ticket) {

		this.vehicleNumber = vehicleNumber;
		this.type = type;
		this.ticket = ticket;
	}
}
