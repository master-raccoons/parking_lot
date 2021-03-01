package com.gojek.assignment.operation;

import com.gojek.assignment.config.ParkingArea;

public class OperationPark extends ParkingOperation {

	private String vehicleEntryPos;
	public OperationPark(String registrationNo, ParkingArea parkingArea, String vehicleEntryPos) {

		super(registrationNo, parkingArea);
		this.vehicleEntryPos=vehicleEntryPos;
	}

	public void execute() throws Exception {

		if (this.parkingArea != null)
			parkingArea.parkVehicle(this.registrationNo,vehicleEntryPos);
	}
}
