package sms;

public class LoginPassword {
	 public static void sendSms(String m,String message) 
	    {        
	    		
	    	String resp=SST_SMS.bceSunSoftSend(m, message);
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
