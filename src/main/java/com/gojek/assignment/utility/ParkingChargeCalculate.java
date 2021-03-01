package com.gojek.assignment.utility;

import static com.gojek.assignment.constants.TicketConstants.BASE_FARE;
import static com.gojek.assignment.constants.TicketConstants.HOURS_ALLOWED_FOR_BASE_FARE;
import com.gojek.assignment.constants.CurrencyType;

public class ParkingChargeCalculate {


	private final CurrencyType currencyType;

	ParkingChargeCalculate()
	{
		this.currencyType=CurrencyType.USD;
	}

	public static double calculateCharge(float hours)
	{
		Double fare=0d;
		if(hours <= HOURS_ALLOWED_FOR_BASE_FARE)
		{
			fare= BASE_FARE;
		}
        else if(hours>HOURS_ALLOWED_FOR_BASE_FARE)
        {
	        fare=(hours-1)*BASE_FARE;
        }

        return fare;
	}

}
