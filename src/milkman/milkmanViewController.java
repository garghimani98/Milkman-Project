package milkman;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class milkmanViewController {
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtmob;

    @FXML
    private TextField txtcus;

    @FXML
    private TextField txtbq;

    @FXML
    private TextField txtcity;

    @FXML
    private TextField txtadd;

    @FXML
    private TextField txtbr;

    @FXML
    private TextField txtcr;

    @FXML
    private TextField txtcq;

    @FXML
    private TextField txtarea;

    @FXML
    private  DatePicker txtdate;
@FXML
    private TextField  txtresult;
    PreparedStatement pst;
    Connection con;
void doconnection()
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



 @FXML
    void doClose(ActionEvent event) {
    	Alert confirm=new Alert(AlertType.CONFIRMATION);
    	confirm.setTitle("Closing..");
    	confirm.setContentText("R U sure?");
    	Optional<ButtonType> res= confirm.showAndWait();
    	if(res.get()==ButtonType.OK)
    	{
    		txtmob.getScene().getWindow().hide();
    	}
    }

    @FXML
    void doDelete(ActionEvent event) {
    	if(txtmob.getText().equals("")||txtmob.getText().length()!=10)
    	{
    		
    		Alert alert =new Alert(AlertType.INFORMATION);
    		alert.setTitle("customer registration");
    		alert.setContentText("fill correct mobile number please");
    		alert.show();	
    	}
    	else{
    	try{
    		pst=con.prepareStatement("delete from customer where mobile=?");
    		pst.setString(1,txtmob.getText());
    		int count=pst.executeUpdate();
    		if(count==0){
    		txtresult.setText("invalid rollno");}
    		else{
    			txtresult.setText("deleted");
    		}
    		}
    	catch(Exception e)
    	{
    	e.printStackTrace();	
    	}
    		}
    }

    @FXML
    void doSave(ActionEvent event) {
    	Alert alert =new Alert(AlertType.INFORMATION);
		alert.setTitle("customer registration");
    	if(txtmob.getText().equals("")||txtcus.getText().equals("")||txtadd.getText().equals("")|| 
    			txtarea.getText().equals("")||txtcity.getText().equals("")
    					||txtmob.getText().length()!=10)
{
    		
    		alert.setContentText("fill complete details");
    		alert.show();
	}
    	else{
    	try{
    		pst=con.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?,?)");  	   
    		pst.setString(1, txtmob.getText());
    		pst.setString(2, txtcus.getText());
    		pst.setString(3, txtadd.getText());
    		pst.setString(4, txtarea.getText());
    		pst.setString(5, txtcity.getText());
    	    pst.setFloat(6, Float.parseFloat(txtcq.getText()));
    	    pst.setFloat(7, Float.parseFloat(txtcr.getText()));
    	    pst.setFloat(8, Float.parseFloat(txtbq.getText()));
    	    pst.setFloat(9, Float.parseFloat(txtbr.getText()));
    		pst.setDate(10,java.sql.Date.valueOf(txtdate.getValue()));
    		try{
        		pst.executeUpdate();
        	txtresult.setText("Saved");
        		alert.setContentText("Data Saved");
        		alert.show();
        		}
        		catch(Exception ex)
        		{
        			txtresult.setText("Duplicate Mobile Number ");
        			alert.setContentText("Duplicate entry");
        			alert.show();
        		}}
    		
    	catch(SQLException e)
    	{
    	e.printStackTrace();	
    	}}
    	
    	
    	    }
 
 
	@FXML
    void doSearch(ActionEvent event) {
    	//System.out.println("1");
		if(txtmob.getText().equals("")||txtmob.getText().length()!=10)
    	{
    		
    		Alert alert =new Alert(AlertType.INFORMATION);
    		alert.setTitle("customer registration");
    		alert.setContentText("fill correct mobile number please");
    		alert.show();	
    	}
    	else{
    	try{
    	pst=con.prepareStatement("select * from customer where mobile=?");
    	pst.setString(1,txtmob.getText());
    	ResultSet rs = pst.executeQuery();
    	boolean jasuus=false;
    	while(rs.next())
    {	
    	jasuus=true;
    	//String mobile=rs.getString("mobile");
    	String name=rs.getString("cname");
    	String address=rs.getString("address");
    	String area=rs.getString("area");
    	String city=rs.getString("city");
    	String cq=rs.getString("cq");
    	String cr=rs.getString("cr");
    	String bq=rs.getString("bq");
    	String br=rs.getString("br");
    	//Date d=rs.getDate("date");
    	System.out.println(" "+name+" "+address+" "+area+" "+city);
    	txtcus.setText(name);
    	txtadd.setText(address);
    	txtarea.setText(area);
    	txtcity.setText(city);
    	txtcq.setText(cq);
    	txtcr.setText(cr);
    	txtbq.setText(bq);
    	txtbr.setText(br);
    	txtdate.setValue(rs.getDate("date").toLocalDate());
    	}
    	if(jasuus==false){
    		txtresult.setText("ID not FOUND");
    	}}
    	catch(Exception e)
    	{
    	e.printStackTrace();	
    	}}
    	
    }
	
	@FXML
    void doUpdate(ActionEvent event) {
		if(txtmob.getText().equals("")||txtmob.getText().length()!=10)
    	{
    		
    		Alert alert =new Alert(AlertType.INFORMATION);
    		alert.setTitle("customer registration");
    		alert.setContentText("fill valid and correct mobile number please");
    		alert.show();	
    	}
    	else{
    	try{
    		pst=con.prepareStatement("update customer set cname=?,address=?,area=? ,city=?,date=?,cq=?,cr=?,bq=?,cr=? where mobile=?");
    		pst.setString(10,txtmob.getText());
    		pst.setString(1, txtcus.getText());
    		pst.setString(2, txtadd.getText());
    		pst.setString(3, txtarea.getText());
    		pst.setString(4, txtcity.getText());
    		pst.setString(5, String.valueOf(txtdate.getValue()));
    		pst.setFloat(6, Float.parseFloat(txtcq.getText()));
    		pst.setFloat(7, Float.parseFloat(txtcr.getText()));
    		pst.setFloat(8, Float.parseFloat(txtbq.getText()));
    		pst.setFloat(9, Float.parseFloat(txtbr.getText()));
    		int count=pst.executeUpdate();
    		if(count==0){
    			System.out.println("invalid mobile no");
    		txtresult.setText("invalid mobile number");
    		}
    		else
    		{
    			txtresult.setText("updated");
    		}}
    		catch(SQLException e)
        	{
        	e.printStackTrace();	
        	}}
    
    			
    }

    @FXML
    void initialize() {
        doconnection();
      
        txtcq.setText(String.valueOf(0.0));
        txtcr.setText(String.valueOf(0.0));
        txtbq.setText(String.valueOf(0.0));
        txtbr.setText(String.valueOf(0.0));
}
}
