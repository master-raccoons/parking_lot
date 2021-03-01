package com.gojek.assignment.operation;

import com.gojek.assignment.config.ParkingArea;

public abstract class ParkingOperation
{
	protected String registrationNo;
	protected ParkingArea parkingArea;

	public abstract void execute() throws Exception;
	ParkingOperation(String registrationNo,ParkingArea parkingArea)
	{
		this.registrationNo=registrationNo;
		this.parkingArea=parkingArea;
	}
	ParkingOperation(ParkingArea parkingArea)
	{
		this.parkingArea=parkingArea;
	}

}


