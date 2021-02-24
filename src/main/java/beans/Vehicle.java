package beans;

import constants.VehicleType;

public abstract class Vehicle {
	private String licenseNumber;
	private  VehicleType type;
	Ticket ticket;

	public Vehicle(String licenseNumber, VehicleType type, Ticket ticket) {

		this.licenseNumber = licenseNumber;
		this.type = type;
		this.ticket = ticket;
	}
}
