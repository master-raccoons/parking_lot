package beans;

import java.util.Date;

public class Ticket {

	private final Date issueDate;
	private Date unparkedAt;
	private final String ticketNo;
	private  String paidAtCounter;
	private  double paidAmount;
	public Ticket(String ticketNo)
	{
		this.issueDate=new Date();
		this.ticketNo=ticketNo;
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

	public void setPaidAtCounter(String paidAtCounter) {

		this.paidAtCounter = paidAtCounter;
	}

	public void setPaidAmount(double paidAmount) {

		this.paidAmount = paidAmount;
	}

	public Date getUnparkedAt() {

		return unparkedAt;
	}

	public void setUnparkedAt(Date unparkedAt) {

		this.unparkedAt = unparkedAt;
	}
}
