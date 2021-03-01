package com.gojek.assignment.operation;

import com.gojek.assignment.config.ParkingArea;

public class OperationCheckStatus extends ParkingOperation {

	public OperationCheckStatus(ParkingArea parkingArea) {

		super(parkingArea);
	}

	public void execute() throws Exception {

		if (this.parkingArea != null)
			parkingArea.showStatus();
	}
}
