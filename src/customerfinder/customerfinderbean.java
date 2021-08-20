package customerfinder;

public class customerfinderbean {
	String mobile;
	String cname;
	String address;
	String area;
	String city;
	float cq;
	float cr;
	float bq;
	float br;
	String date;
public customerfinderbean(String mobile, String cname, String address, String area, String city, float cq, float cr,
			float bq, float br, String date) {
		super();
		this.mobile = mobile;
		this.cname = cname;
		this.address = address;
		this.area = area;
		this.city = city;
		this.cq = cq;
		this.cr = cr;
		this.bq = bq;
		this.br = br;
		this.date = date;
	}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getCname() {
	return cname;
}
public void setCname(String cname) {
	this.cname = cname;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getArea() {
	return area;
}
public void setArea(String area) {
	this.area = area;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public float getCq() {
	return cq;
}
public void setCq(float cq) {
	this.cq = cq;
}
public float getCr() {
	return cr;
}
public void setCr(float cr) {
	this.cr = cr;
}
public float getBq() {
	return bq;
}
public void setBq(float bq) {
	this.bq = bq;
}
public float getBr() {
	return br;
}
public void setBr(float br) {
	this.br = br;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}

}
