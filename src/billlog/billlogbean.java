package billlog;

public class billlogbean {
	
	String mobile;
	Float cqt;
	Float bqt;
	Float cbill;
	Float bbill;
	Float total;
	int status;
	int month;
	int year;
public billlogbean(String mobile, Float cqt, Float bqt, Float cbill, Float bbill, Float total, int status,
			int month, int year) {
		super();
		this.mobile = mobile;
		this.cqt = cqt;
		this.bqt = bqt;
		this.cbill = cbill;
		this.bbill = bbill;
		this.total = total;
		this.status = status;
		this.month = month;
		this.year = year;
	}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public Float getCqt() {
	return cqt;
}
public void setCqt(Float cqt) {
	this.cqt = cqt;
}
public Float getBqt() {
	return bqt;
}
public void setBqt(Float bqt) {
	this.bqt = bqt;
}
public Float getCbill() {
	return cbill;
}
public void setCbill(Float cbill) {
	this.cbill = cbill;
}
public Float getBbill() {
	return bbill;
}
public void setBbill(Float bbill) {
	this.bbill = bbill;
}
public Float getTotal() {
	return total;
}
public void setTotal(Float total) {
	this.total = total;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
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
