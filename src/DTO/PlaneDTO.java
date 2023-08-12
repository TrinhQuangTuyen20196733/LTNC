package DTO;

public class PlaneDTO {
	private int id;
	private String type;
	private int cost;
	private int maximumSpeed;
	private  int airlineId;
	private String code;
	private int row;
	private int column;
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
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public PlaneDTO(int id, String type, int cost, int maximumSpeed, int airlineId, String code, int row,
			int column) {
		super();
		this.id = id;
		this.type = type;
		this.cost = cost;
		this.maximumSpeed = maximumSpeed;
		this.airlineId = airlineId;
		this.code = code;
		this.row = row;
		this.column = column;
	}
	public PlaneDTO(String type, int cost, int maximumSpeed, int airlineId, String code, int row, int column) {
		super();
		this.type = type;
		this.cost = cost;
		this.maximumSpeed = maximumSpeed;
		this.airlineId = airlineId;
		this.code = code;
		this.row = row;
		this.column = column;
	}
	public PlaneDTO() {
		super();
	}
	
	
}
