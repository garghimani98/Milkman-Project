package billcollection;

import sms.LoginPassword;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import milkman.data;

public class billcollectionViewControlller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> combomonth;

    @FXML
    private ComboBox<Integer> comboyear;

    @FXML
    private TextField txtmobile;

    @FXML
    private TextField txtbill;
    PreparedStatement pst;
    Connection con;
    void doConnect()
    {
    		try 
    		{
    			Class.forName("com.mysql.jdbc.Driver");
    			System.out.println("Loaded...");
    			con=DriverManager.getConnection("jdbc:mysql://localhost/milkman project",data.uid,data.pwd);
    			System.out.println("Connected");
    			}
    		catch (Exception e) 
    		{
    			e.printStackTrace();
    		}		

    }
    int y1,m1;
    float bill=0;
    int status=0;
    String mob;
    @FXML
    void doUpdate(ActionEvent event) {
    	Alert alert =new Alert(AlertType.INFORMATION);
		alert.setTitle("Bill Status");
if(combomonth.getSelectionModel().isEmpty() || comboyear.getSelectionModel().isEmpty() || txtmobile.getText().equals(""))
{
	alert.setContentText("Please select year, month and mobile number");
	alert.show();
}
else{
    	try{
 			
 				pst=con.prepareStatement("update bill set status=? where mobile=? and month=? and year=?");
 				pst.setInt(1, 1);
 				pst.setInt(3, m1);
 	 			 pst.setInt(4, y1);
 	 			 pst.setString(2, mob);
 	 			 pst.executeUpdate();
 	 			alert.setContentText("Status Updated");
 	 			LoginPassword.sendSms(txtmobile.getText(), "Payment of month :"+combomonth.getValue()+" is received");
    	}
 	 			 catch(Exception e)
 	 {
 		 e.printStackTrace();
 		alert.setContentText("Error Occured in Updating Status");
 		 
 	 }
    	alert.show();
    	}
    }

    @FXML
    void dostatus(ActionEvent event) {
    	
    	Alert alert =new Alert(AlertType.INFORMATION);
		alert.setTitle("Bill Status");
if(combomonth.getSelectionModel().isEmpty() || comboyear.getSelectionModel().isEmpty() || txtmobile.getText().equals("")
		|| txtmobile.getLength()!=10)
{
	alert.setContentText("Please select year, month and mobile number");
	alert.show();
}
else{
    	 try{
 			y1=comboyear.getSelectionModel().getSelectedItem();
 			m1=combomonth.getSelectionModel().getSelectedItem();
 			 mob=txtmobile.getText();
 			 pst=con.prepareStatement("select total,status from bill where month=? and year=? and mobile=?");
 			 pst.setInt(1, m1);
 			 pst.setInt(2, y1);
 			 pst.setString(3, mob);
 			 ResultSet rs=pst.executeQuery();
 			if( rs.next()==false)
 				return;
 			 if(rs.getInt("status")==0)
 			 {
 			 bill=rs.getFloat("total");
 			  
 			txtbill.setText(String.valueOf(bill));
 			 }
 			 
 			 else{
 				alert.setContentText("No pending Bill");
 				alert.show();
 			}
 			 
 	 }
 	 catch(Exception e)
 	 {
 		 e.printStackTrace();
 		 
 	 }}
    }

    @FXML
    void initialize() {
    	for(int i=1;i<=12;i++){
    	    ArrayList<Integer>m=new ArrayList<Integer>(Arrays.asList(i));
    		combomonth.getItems().addAll(m);
    		}
    		 ArrayList<Integer>y=new ArrayList<Integer>(Arrays.asList(2017,2018,2019));
    	   comboyear.getItems().addAll(y);
    		doConnect();
    }
}
