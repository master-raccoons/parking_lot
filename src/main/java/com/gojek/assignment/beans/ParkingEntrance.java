package com.gojek.assignment.beans;

import com.gojek.assignment.operation.ParkingSpace;

import java.util.Objects;

public class ParkingEntrance extends ParkingSpace {

	private String entranceName;

	public String getEntranceName() {

		return entranceName;
	}

	public void setEntranceName(String entranceName) {

		this.entranceName = entranceName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ParkingEntrance entrance = (ParkingEntrance) o;
		boolean isEntranceDuplicate=Objects.equals(this.generateHashKey(), entrance.generateHashKey());
		if(isEntranceDuplicate) System.out.println("Duplicate Entrance Found!!!");
		return isEntranceDuplicate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.generateHashKey());
	}

	private String generateHashKey()
	{
	   return this.getFloorNo()+"-"+this.getPosition().getRow()+"-"+this.getPosition().getCol();
	}
}
