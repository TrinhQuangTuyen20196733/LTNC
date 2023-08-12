package Entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Flight {
private int id;
private String code;
private String departureLocation;
private String destination;
private LocalDateTime departureTime;
private LocalDateTime landingTime;
private String boardingGate;
private int planeId;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getDepartureLocation() {
	return departureLocation;
}
public void setDepartureLocation(String departureLocation) {
	this.departureLocation = departureLocation;
}
public String getDestination() {
	return destination;
}
public void setDestination(String destination) {
	this.destination = destination;
}
public LocalDateTime getDepartureTime() {
	return departureTime;
}
public void setDepartureTime(LocalDateTime departureTime) {
	this.departureTime = departureTime;
}
public LocalDateTime getLandingTime() {
	return landingTime;
}
public void setLandingTime(LocalDateTime landingTime) {
	this.landingTime = landingTime;
}
public String getBoardingGate() {
	return boardingGate;
}
public void setBoardingGate(String boardingGate) {
	this.boardingGate = boardingGate;
}
public int getPlaneId() {
	return planeId;
}
public void setPlaneId(int planeId) {
	this.planeId = planeId;
}
public Flight(int id, String code, String departureLocation, String destination, LocalDateTime departureTime, LocalDateTime landingTime,
		String boardingGate, int planeId) {
	super();
	this.id = id;
	this.code = code;
	this.departureLocation = departureLocation;
	this.destination = destination;
	this.departureTime = departureTime;
	this.landingTime = landingTime;
	this.boardingGate = boardingGate;
	this.planeId = planeId;
}
public Flight(String code, String departureLocation, String destination, LocalDateTime departureTime, LocalDateTime landingTime,
		String boardingGate, int planeId) {
	super();
	this.code = code;
	this.departureLocation = departureLocation;
	this.destination = destination;
	this.departureTime = departureTime;
	this.landingTime = landingTime;
	this.boardingGate = boardingGate;
	this.planeId = planeId;
}
public Flight() {
	super();
}



}
