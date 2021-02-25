package beans;

import interfaces.ParkingSpace;

public class ParkingEntrance extends ParkingSpace {

	private String entranceName;

	public String getEntranceName() {

		return entranceName;
	}

	public void setEntranceName(String entranceName) {

		this.entranceName = entranceName;
	}
}
