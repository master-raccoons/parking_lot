package factory;

import config.ParkingArea;
import operation.OperationCheckStatus;
import operation.OperationPark;
import operation.OperationUndoPark;
import operation.ParkingOperation;

public  class OperationsFactory {

	public static ParkingOperation getParkingOperation(String input, ParkingArea parkingArea) throws Exception {

		String inputArr[] = input.split(" ");
		String command = inputArr[0];
		String registrationNo = inputArr.length>1?inputArr[1]:null;
		float hrs = 0;
		if (inputArr.length > 2) {
			hrs = Float.valueOf(inputArr[2]);
		}
		if (command.equals("park")) {
			return new OperationPark(registrationNo, parkingArea);
		} else if (command.equals("leave")) {
			return new OperationUndoPark(registrationNo, parkingArea, hrs);
		} else if (command.equals("status")) {
			return new OperationCheckStatus(parkingArea);
		}
		throw new Exception("Invalid command!!!");
	}
}
