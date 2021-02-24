package beans;

import java.util.Date;

public class Ticket {

	private final Date issueDate;
	private final String ticketNo;
	private  String paidAtCounter;
	private  double paidAmount;
	Ticket()
	{
		this.issueDate=new Date();
		ticketNo="";
	}

	public Date getIssueDate() {

		return issueDate;
	}

	public String getTicketNo() {

		return ticketNo;
	}

	public String getPaidAtCounter() {

		return paidAtCounter;
	}

	public double getPaidAmount() {

		return paidAmount;
	}
}
