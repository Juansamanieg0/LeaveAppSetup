package model;

public class LeaveTable {
String email,fromdate,todate,status;

public LeaveTable(String email, String fromdate, String todate, String status) {
	super();
	this.email = email;
	this.fromdate = fromdate;
	this.todate = todate;
	this.status = status;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getFromdate() {
	return fromdate;
}

public void setFromdate(String fromdate) {
	this.fromdate = fromdate;
}

public String getTodate() {
	return todate;
}

public void setTodate(String todate) {
	this.todate = todate;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

}
