package DTO;

public class PlaneRes {
	private int id;
	private String type;
	private int cost;
	private int maximumSpeed;
	private  String airlineName;
	private String code;
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
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public PlaneRes(int id, String type, int cost, int maximumSpeed, String airlineName, String code) {
		super();
		this.id = id;
		this.type = type;
		this.cost = cost;
		this.maximumSpeed = maximumSpeed;
		this.airlineName = airlineName;
		this.code = code;
	}
	public PlaneRes(String type, int cost, int maximumSpeed, String airlineName, String code) {
		super();
		this.type = type;
		this.cost = cost;
		this.maximumSpeed = maximumSpeed;
		this.airlineName = airlineName;
		this.code = code;
	}
	public PlaneRes() {
		super();
	}
	
	
}
