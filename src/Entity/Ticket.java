package Entity;

public class Ticket {
private int seatId;
private int flightId;
private int cost;
private int status;
private int receiptId;
public int getSeatId() {
	return seatId;
}
public void setSeatId(int seatId) {
	this.seatId = seatId;
}
public int getFlightId() {
	return flightId;
}
public void setFlightId(int flightId) {
	this.flightId = flightId;
}
public int getCost() {
	return cost;
}
public void setCost(int cost) {
	this.cost = cost;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public int getReceiptId() {
	return receiptId;
}
public void setReceiptId(int receiptId) {
	this.receiptId = receiptId;
}
public Ticket(int seatId, int flightId, int cost, int status, int receiptId) {
	super();
	this.seatId = seatId;
	this.flightId = flightId;
	this.cost = cost;
	this.status = status;
	this.receiptId = receiptId;
}
public Ticket() {
	super();
}



}
