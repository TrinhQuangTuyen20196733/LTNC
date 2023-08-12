package Entity;

public class Plane {
private int id;
private String type;
private int cost;
private int maximumSpeed;
private  int airlineId;
private String code;
private int seatRow;
private int seatColumn;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public int getCost() {
	return cost;
}
public void setCost(int cost) {
	this.cost = cost;
}
public int getMaximumSpeed() {
	return maximumSpeed;
}
public void setMaximumSpeed(int maximumSpeed) {
	this.maximumSpeed = maximumSpeed;
}
public int getAirlineId() {
	return airlineId;
}
public void setAirlineId(int airlineId) {
	this.airlineId = airlineId;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public int getSeatRow() {
	return seatRow;
}
public void setSeatRow(int seatRow) {
	this.seatRow = seatRow;
}
public int getSeatColumn() {
	return seatColumn;
}
public void setSeatColumn(int seatColumn) {
	this.seatColumn = seatColumn;
}
public Plane(int id, String type, int cost, int maximumSpeed, int airlineId, String code, int seatRow, int seatColumn) {
	super();
	this.id = id;
	this.type = type;
	this.cost = cost;
	this.maximumSpeed = maximumSpeed;
	this.airlineId = airlineId;
	this.code = code;
	this.seatRow = seatRow;
	this.seatColumn = seatColumn;
}
public Plane(String type, int cost, int maximumSpeed, int airlineId, String code, int seatRow, int seatColumn) {
	super();
	this.type = type;
	this.cost = cost;
	this.maximumSpeed = maximumSpeed;
	this.airlineId = airlineId;
	this.code = code;
	this.seatRow = seatRow;
	this.seatColumn = seatColumn;
}
public Plane() {
	super();
}





}
