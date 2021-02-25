package services;

import beans.Ticket;
import constants.CurrencyType;
import constants.TicketConstants;

public class ParkingChargeCalculate {


	private final CurrencyType currencyType;

	ParkingChargeCalculate()
	{
		this.currencyType=CurrencyType.USD;
	}

	public static double calculateFare(float hours)
	{
		double fare=0;
		if(hours<=TicketConstants.BASE_FARE_PER_HOURS)
		{
			fare= TicketConstants.BASE_FARE * hours;
		}
        if(hours>2)
        {
	        fare+=hours*TicketConstants.BASE_FARE;
        }
        return fare;
	}

}
