package operation;

import config.ParkingArea;

public class OperationPark extends ParkingOperation {

	public OperationPark(String registrationNo, ParkingArea parkingArea) {

		super(registrationNo, parkingArea);
	}

	public void execute() throws Exception {

		if (this.parkingArea != null)
			parkingArea.parkVehicle(this.registrationNo);
	}
}
