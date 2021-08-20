package routinemanager;

public class routinemanagerbean {

String mobile;
float cq;
float bq;
int date;
int month;
int year;

public routinemanagerbean(String mobile, float cq2, float bq2, int date, int month, int year) {
		super();
		this.mobile = mobile;
		this.cq = cq2;
		this.bq = bq2;
		this.date = date;
		this.month = month;
		this.year = year;
}

public String getMobile() {
	return mobile;
}

public void setMobile(String mobile) {
	this.mobile = mobile;
}

public float getCq() {
	return cq;
}

public void setCq(int cq) {
	this.cq = cq;
}

public float getBq() {
	return bq;
}

public void setBq(int bq) {
	this.bq = bq;
}

public int getDate() {
	return date;
}

public void setDate(int date) {
	this.date = date;
}

public int getMonth() {
	return month;
}

public void setMonth(int month) {
	this.month = month;
}

public int getYear() {
	return year;
}

public void setYear(int year) {
	this.year = year;
}



}
