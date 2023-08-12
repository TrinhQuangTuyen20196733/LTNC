package Entity;

public class UserEntity  {
private int id ;
private String first_name;
private String last_name;
private String address;
private String gender;
private int age;
private String phone_number;
private String cmnd;
private String nationality;
private String email;
private String password;
private String role;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFirst_name() {
	return first_name;
}
public void setFirst_name(String first_name) {
	this.first_name = first_name;
}
public String getLast_name() {
	return last_name;
}
public void setLast_name(String last_name) {
	this.last_name = last_name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getPhone_number() {
	return phone_number;
}
public void setPhone_number(String phone_number) {
	this.phone_number = phone_number;
}
public String getCmnd() {
	return cmnd;
}
public void setCmnd(String cmnd) {
	this.cmnd = cmnd;
}
public String getNationality() {
	return nationality;
}
public void setNationality(String nationality) {
	this.nationality = nationality;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public UserEntity( String first_name, String last_name, String address, String gender, int age,
		String phone_number, String cmnd, String nationality, String email, String password,String role) {
	super();
	this.first_name = first_name;
	this.last_name = last_name;
	this.address = address;
	this.gender = gender;
	this.age = age;
	this.phone_number = phone_number;
	this.cmnd = cmnd;
	this.nationality = nationality;
	this.email = email;
	this.password = password;
	this.role=role;
}
public UserEntity() {
	super();
}



}
