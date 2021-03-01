package com.gojek.assignment.factory;

import com.gojek.assignment.config.ParkingArea;
import com.gojek.assignment.operation.OperationCheckStatus;
import com.gojek.assignment.operation.OperationPark;
import com.gojek.assignment.operation.OperationUndoPark;
import com.gojek.assignment.operation.ParkingOperation;

public  class OperationsFactory {

	public static ParkingOperation getParkingOperation(String input, ParkingArea parkingArea) throws Exception {

		String inputArr[] = input.split(" ");
		String command = inputArr[0];
		String registrationNo = inputArr.length>1?inputArr[1]:null;
		float hrs = 0;


		if (command.equals("park")) {
            String entrancePos=inputArr.length > 2?inputArr[2]:null;
			return new OperationPark(registrationNo, parkingArea,entrancePos);
		} else if (command.equals("leave")) {
			if (inputArr.length > 2) {
				hrs = Float.valueOf(inputArr[2]);
			}
			return new OperationUndoPark(registrationNo, parkingArea, hrs);
		} else if (command.equals("status")) {
			return new OperationCheckStatus(parkingArea);
		}
       else
       	{
	        throw new Exception("Invalid command passed!!!");
        }


	}
}
