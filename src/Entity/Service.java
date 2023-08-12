package Entity;

public class Service {
private int id;
private String name;
private String code;
private int cost;
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
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public int getCost() {
	return cost;
}
public void setCost(int cost) {
	this.cost = cost;
}
public Service(int id, String name, String code, int cost) {
	super();
	this.id = id;
	this.name = name;
	this.code = code;
	this.cost = cost;
}
public Service(String name, String code, int cost) {
	super();
	this.name = name;
	this.code = code;
	this.cost = cost;
}
public Service() {
	super();
}


}
