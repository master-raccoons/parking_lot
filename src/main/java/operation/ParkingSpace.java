package operation;
/*
This interface has a special mechanism which will let us know what is type of the parking place

 */

import beans.Position;

public  class ParkingSpace {
	protected boolean isFree;
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

}
