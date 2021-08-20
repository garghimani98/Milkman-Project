package billgeneration;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import java.sql.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import milkman.data;
import sms.LoginPassword;

public class billViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> mobile;

    @FXML
    private ListView<String> name;

    @FXML
    private ComboBox<Integer> month;

    @FXML
    private ComboBox<Integer> year;

    @FXML
    private ComboBox<Integer> date;

    @FXML
    private TextField txttotal;
    
    @FXML
    private TextField txtcrb;

    @FXML
    private TextField txtcqb;

    @FXML
    private TextField txtbqb;

    @FXML
    private TextField txtbbb;

    @FXML
    private TextField txtcbb;

    @FXML
    private TextField txtbrb;


    @FXML
    private TextField txtstatus;
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
 int y1,m1,d1,dv,nod=0;
 float cq=0,bq=0,qty1=0,qty2=0,p1,p2,total1=0;
 int count=0;
 @FXML
 void doBilll(ActionEvent event) {
	 Alert alert = new Alert(AlertType.INFORMATION);
 	alert.setTitle("Generate Bill");
	if(month.getSelectionModel().isEmpty() || year.getSelectionModel().isEmpty() || date.getSelectionModel().isEmpty())
	{
		alert.setContentText("Please select correct date,month and year");
		alert.show();
	}
	else{
		y1=year.getSelectionModel().getSelectedItem();
		m1=month.getSelectionModel().getSelectedItem();
		d1=date.getSelectionModel().getSelectedItem();
		nod=d1;
		try{
pst=con.prepareStatement("select * from bill where mobile=? and month=? and year=?");
pst.setString(1, mob);
int m=(m1==1)?12:m1-1;
pst.setInt(2,m);
pst.setInt(3, y1);
 ResultSet rs=pst.executeQuery();
while(rs.next())
{
	 count=1;
/*float cqt=rs.getFloat("cqt");
float bqt=rs.getFloat("bqt");
float cbill=rs.getFloat("cbill");
float bbill=rs.getFloat("bbill");
float total=rs.getFloat("total");
int status=rs.getInt("status");
txtcqb.setText(String.valueOf(cqt));
txtbqb.setText(String.valueOf(bqt));
txtcbb.setText(String.valueOf(cbill));
txtbbb.setText(String.valueOf(bbill));
txttotal.setText(String.valueOf(total));*/
}
if(count==0)
{
	pst=con.prepareStatement("select date from customer where mobile=? ");	
	pst.setString(1, mob);
	 ResultSet res=pst.executeQuery();
	 while(res.next())
	 {
		
		 dv=res.getDate("date").toLocalDate().getDayOfMonth();
		 /*float cr=res.getFloat("cr");
		 float br=res.getFloat("br");
		*/
	 }
	  nod=d1-dv+1;
	  }
	 pst=con.prepareStatement("select sum(cq),sum(bq)from routine where mobile=? and month=? and year=? ");
	 pst.setString(1, mob);
	 pst.setInt(2, m1);
	 pst.setInt(3 ,y1);
	 ResultSet res1=pst.executeQuery();
	 while(res1.next())
	 {
		  cq=res1.getFloat("sum(cq)");
		  bq=res1.getFloat("sum(bq)");
		 }
	// nod=d1;
	  qty1=(nod*(Float.parseFloat(txtcqb.getText())))+cq;
	  qty2=(nod*(Float.parseFloat(txtbqb.getText())))+bq;
	  p1=qty1*(Float.parseFloat(txtcrb.getText()));
	  p2=qty1*(Float.parseFloat(txtbrb.getText()));
	  total1=p1+p2;
	  txtcqb.setText(String.valueOf(qty1));
	  txtbqb.setText(String.valueOf(qty2));
	  txtcbb.setText(String.valueOf(p1));
	  txtbbb.setText(String.valueOf(p2));
	  txttotal.setText(String.valueOf(total1));
	  txtstatus.setText("0");
	  pst=con.prepareStatement("insert into bill values(?,?,?,?,?,?,?,?,?)");
	  pst.setString(1,mob);
      pst.setFloat(2, qty1);
      pst.setFloat(3, qty2);
      pst.setFloat(4, p1);
      pst.setFloat(5, p2);
      pst.setFloat(6, total1);
      pst.setInt(7,0);
      pst.setInt(8, m1);
      pst.setInt(9, y1);
      pst.executeUpdate();
      LoginPassword.sendSms(mob,"Bill of month :"+month.getSelectionModel().getSelectedItem()+" is :"+total1 );
		alert.setContentText("Bill Generated");
		alert.show();
	  
 }
catch(Exception e)
	 {
	e.printStackTrace();
	 }
	}}

 

String mob=null;
 String cus=null;

 @FXML
 void dofill(MouseEvent event) {
	
	 try
 	{
 		if(event.getClickCount()==2)
 		{
 			 mob=mobile.getSelectionModel().getSelectedItem();
 	    	int index=mobile.getSelectionModel().getSelectedIndex();
 	    name.getSelectionModel().select(index);
 	     cus=name.getSelectionModel().getSelectedItem();
 	    	pst=con.prepareStatement("select cq,bq,cr,br from customer where mobile=? and cname=?");
 	    	pst.setString(1, mob);
 	    	pst.setString(2, cus);
 		ResultSet rs=pst.executeQuery();
 	while(rs.next())
 	{
 		
 		Float cr=rs.getFloat("cr");
 		Float br=rs.getFloat("br");
 		Float cq=rs.getFloat("cq");
 		Float bq=rs.getFloat("bq");
 		txtcrb.setText(String.valueOf(cr));
 		txtbrb.setText(String.valueOf(br));
 		txtcqb.setText(String.valueOf(cq));
 		txtbqb.setText(String.valueOf(bq));
 		
 	}
 	}
 	}
 	catch(Exception e)
 	{
 		e.printStackTrace();
 	}
	 
	 }

 
 
 void   doInsert()
 {
 try {
		pst=con.prepareStatement("select cname,mobile from customer");
	ResultSet rs=pst.executeQuery();
	while(rs.next())
	{
		name.getItems().add(rs.getString("cname"));
		mobile.getItems().add(rs.getString("mobile"));
	}
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }


    @FXML
    void initialize() {
       
       name.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
   	mobile.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	for(int i=1;i<=12;i++){
    ArrayList<Integer>m=new ArrayList<Integer>(Arrays.asList(i));
	month.getItems().addAll(m);
	}
	 ArrayList<Integer>y=new ArrayList<Integer>(Arrays.asList(2017,2018,2019));
   year.getItems().addAll(y);
   for(int j=1;j<=31;j++){
	    ArrayList<Integer>ed=new ArrayList<Integer>(Arrays.asList(j));
		date.getItems().addAll(ed);
		}
   	doconnection();
    doInsert();
    }

	
	
}
