package operation;

import config.ParkingArea;

public class OperationCheckStatus extends ParkingOperation {

	public OperationCheckStatus(ParkingArea parkingArea) {

		super(parkingArea);
	}

	public void execute() throws Exception {

		if (this.parkingArea != null)
			parkingArea.showStatus();
	}
}
