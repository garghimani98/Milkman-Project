package sms;

public class RecoverPassword {
	public  static void  recoverPassword()
	{
	String m="Password : garg";
	
	String resp=SST_SMS.bceSunSoftSend("9855478135", m);
	System.out.println(resp);
	
	if(resp.indexOf("Exception")!=-1)
		System.out.println("Check Internet Connection");
	
	else
		if(resp.indexOf("successfully")!=-1)
    		System.out.println("Sent");
		else
		System.out.println( "Invalid Number");
	}
}
