package com.gojek.assignment.operation;
/*
This interface has a special mechanism which will let us know what is type of the parking place

 */

import com.gojek.assignment.beans.Position;

public  class ParkingSpace  {
	protected boolean isFree;
	protected int floorNo;
	public ParkingSpace()
	{
      this.isFree=true;
	}
	protected Position position;

	public void setFree(boolean free) {

		this.isFree = free;
	}
	public boolean isFree() {

		return isFree;
	}
	public Position getPosition() {

		return position;
	}

	public void setPosition(Position position) {

		this.position = position;
	}

	public int getFloorNo() {

		return floorNo;
	}

	public void setFloorNo(int floorNo) {

		this.floorNo = floorNo;
	}








}
