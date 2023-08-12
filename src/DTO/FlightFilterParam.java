package DTO;

import java.time.LocalDateTime;

public class FlightFilterParam {
private String departureLocation;
private String destination;
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

public FlightFilterParam(String departureLocation, String destination) {
	super();
	this.departureLocation = departureLocation;
	this.destination = destination;
}

}
