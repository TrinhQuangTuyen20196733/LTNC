package Entity;

import java.util.Date;

public class Airline  {
	private int id;
	
	private String name;
	
	private Date establishTime;
	
	private  String headQuatersAddress;
	
	private String founder;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEstablishTime() {
		return establishTime;
	}

	public void setEstablishTime(Date establishTime) {
		this.establishTime = establishTime;
	}

	public String getHeadQuatersAddress() {
		return headQuatersAddress;
	}

	public void setHeadQuatersAddress(String headQuatersAddress) {
		this.headQuatersAddress = headQuatersAddress;
	}

	public String getFounder() {
		return founder;
	}

	public void setFounder(String founder) {
		this.founder = founder;
	}

	public Airline(String name, Date establishTime, String headQuatersAddress, String founder) {
		
		this.name = name;
		this.establishTime = establishTime;
		this.headQuatersAddress = headQuatersAddress;
		this.founder = founder;
	}

	public Airline() {
		super();
	}
	
	



 
}
