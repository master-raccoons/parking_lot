package com.gojek.assignment.operation;

import com.gojek.assignment.config.ParkingArea;

public class OperationUndoPark extends ParkingOperation {

	private float hrs;

	public OperationUndoPark(String registrationNo, ParkingArea parkingArea, float hrs) {

		super(registrationNo, parkingArea);
		this.hrs = hrs;
	}

	public void execute() throws Exception {

		if (this.parkingArea != null)
			parkingArea.unparkVehicle(this.registrationNo, hrs);
	}
}
