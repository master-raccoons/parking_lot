package testcases;

import com.gojek.assignment.beans.ParkingEntrance;
import com.gojek.assignment.beans.ParkingPlace;
import com.gojek.assignment.beans.Position;
import com.gojek.assignment.config.ParkingArea;
import com.gojek.assignment.helper.ParkingFloor;
import com.gojek.assignment.operation.ParkingSpace;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class ParkingTest
{
	private static  ParkingArea parkingArea=null;
	@BeforeAll
	public static void setup()
	{
       System.out.println("Setting up");
		Set<ParkingSpace> entranceSet=new LinkedHashSet<>();// to reserve the insertion order
		ParkingEntrance entrance=new ParkingEntrance();
		entrance.setPosition(new Position(0, 0));
		entrance.setFree(false);
		entrance.setFloorNo(0);
		entranceSet.add(entrance);

		ParkingFloor floor=new ParkingFloor("Floor 0", 1, 5, 0, entranceSet);
		List<ParkingFloor> listFloor=new ArrayList<ParkingFloor>();
		listFloor.add(floor);
		parkingArea=new ParkingArea(listFloor.toArray(new ParkingFloor[listFloor.size()]));


	}

	@Test
	@Order(1)
	public void assertParkvehicle() throws Exception
	{
		ParkingPlace parkingPlace=parkingArea.parkVehicle("KA-01-HH-1234",null);
		assertFalse(parkingPlace.isFree());
	}

	@Test
	@Order(2)
	public void assertUnParkvehicle() throws Exception
	{
		ParkingPlace parkingPlace=parkingArea.unparkVehicle("KA-01-HH-1234",2);
		assertTrue(parkingPlace.isFree());
	}

	@Test
	@Order(3)
	public void assertVehicleParkingCharge() throws Exception
	{                parkingArea.parkVehicle("KA-01-HH-1234",null);
		ParkingPlace parkingPlace=parkingArea.unparkVehicle("KA-01-HH-1234",2);
		assertEquals(parkingPlace.getVehicle().getTicket().getPaidAmount(),10);
	}
	@Test
	@Order(4)
	public void assertDuplicateVehicleRegNo() throws Exception
	{
		String message="";
		String vehicleNo="KA-01-HH-1234";
		try
		{
			parkingArea.parkVehicle(vehicleNo,null);
			parkingArea.parkVehicle(vehicleNo,null);
		}
		catch (Exception ex)
		{
			message =ex.getMessage()	;
		}
		assertEquals(message,"Duplicate "+vehicleNo+" found!!!");
	}
	@Test
	@Order(4)
	public void assertParkingIsFull() throws Exception
	{

		try
		{
			parkingArea.parkVehicle("KA-01-HH-3234",null);
			parkingArea.parkVehicle("KA-01-HH-1235",null);

			parkingArea.parkVehicle("KA-01-HH-1236",null);
			parkingArea.parkVehicle("KA-01-HH-1237",null);

			parkingArea.parkVehicle("KA-01-HH-1238",null);
		}
		catch (Exception ex)
		{

		}
		assertEquals(5,parkingArea.getParkingFloors()[0].getOccupiedParking());

	}

}



