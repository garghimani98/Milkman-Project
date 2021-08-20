package routinelog;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.mysql.jdbc.DocsConnectionPropsHelper;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import milkman.data;
public class routineViewController {

    

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> name;

    @FXML
    private ListView<String> address;

    @FXML
    private TextField txtcqr;

    @FXML
    private TextField txtmobr;

    @FXML
    private TextField txtbqr;

    @FXML
    private TextField txtbqu;

    @FXML
    private TextField txtcqu;

    @FXML
    private DatePicker date;

    @FXML
    private CheckBox ch;
    
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
String cus=null;
String add=null;

int ind;
    @FXML
    void doDelete(ActionEvent event) {
    	try{
    		ObservableList<String>all= name.getSelectionModel().getSelectedItems();
         ArrayList<String> arr=new ArrayList<String>();
         arr.addAll(all);
            ObservableList<Integer>ind=	name.getSelectionModel().getSelectedIndices();
       for(int i:ind)
       {
           address.getSelectionModel().select(i);
       }
      	 ObservableList<String> alla = address.getSelectionModel().getSelectedItems();
      	 ArrayList<String> addre = new ArrayList<String>();
      	 addre.addAll(alla);
      	 name.getItems().clear();
      	 address.getItems().clear();
      	 name.getItems().addAll(arr);
      	 address.getItems().addAll(addre);
      	 }
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		
    	}
    	
	}

       
    
    
	@FXML
    void doFill(MouseEvent event) {
		if(event.getClickCount()==1)
    	{
    		address.getSelectionModel().clearSelection();
    		ObservableList<Integer> index = name.getSelectionModel().getSelectedIndices();
    	     for(int i:index)
    	     {
    	    	 address.getSelectionModel().select(i);
    	     }
    	}
    	try
    	{
    		if(event.getClickCount()==2)
    		{
    			cus=name.getSelectionModel().getSelectedItem();
    	    	int index=name.getSelectionModel().getSelectedIndex();
    	    	address.getSelectionModel().select(index);
    	    	add=address.getSelectionModel().getSelectedItem();
    	    	pst=con.prepareStatement("select cq,bq,mobile from customer where cname=? and address=?");
    	    	pst.setString(1, cus);
    	    	pst.setString(2, add);
    		ResultSet rs=pst.executeQuery();
    	while(rs.next())
    	{
    		
    		Float cow=rs.getFloat("cq");
    		Float buffalo=rs.getFloat("bq");
    		String mobile=rs.getString("mobile");
    		txtcqr.setText(String.valueOf(cow));
    		txtbqr.setText(String.valueOf(buffalo));
    		txtmobr.setText(mobile);
    		txtcqu.setText("");
			txtbqu.setText("");
			ch.setSelected(false);
			date.setValue(null);
    		
    	}
    	}
    	}
    	catch(Exception  e)
    	{
    		e.printStackTrace();
    	}
    	}

    @FXML
    void doUpdate(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Routine Log");
    	 if(date.getValue()==null || txtmobr.getText().equals(""))
         {
      	   alert.setContentText("Enter complete details");
      	   alert.show();
         }
    	 else{
try{
	if(ch.isSelected()==true)
	{
		
		pst=con.prepareStatement("insert into routine values (?,?,?,?,?,?)");
		pst.setString(1, txtmobr.getText());
		pst.setFloat(2, (0-Float.parseFloat(txtcqr.getText())));
		pst.setFloat(3, (0-Float.parseFloat(txtbqr.getText())));
		pst.setInt(4, date.getValue().getDayOfMonth());
		pst.setInt(5,date.getValue().getMonthValue());
		pst.setInt(6, date.getValue().getYear());
		pst.executeUpdate();
		}
	else
	{
		 if(txtcqu.getText().matches("[0-9]*")==false || txtbqu.getText().matches("[0-9]*")==false || txtcqu.getText().equals("") || txtbqu.getText().equals(""))
  	   {
  		   alert.setContentText("Please enter correct details");
  		   alert.show();
  		  
  		   txtcqu.setText("");
  		   txtbqu.setText("");
  	   }
	
		pst=con.prepareStatement("insert into routine values (?,?,?,?,?,?)");
	    pst.setString(1, txtmobr.getText());
	    pst.setFloat(2, Float.parseFloat(txtcqu.getText()));
	    pst.setFloat(3, Float.parseFloat(txtbqu.getText()));
	    pst.setInt(4, date.getValue().getDayOfMonth());
	    pst.setInt(5, date.getValue().getMonthValue());
	    pst.setInt(6, date.getValue().getYear());
	    pst.executeUpdate();
	    alert.setContentText("Routine Log Updated");
		alert.show();
	}

	name.getSelectionModel().clearSelection();
	address.getSelectionModel().clearSelection();
	doInsert();
}
	

catch(Exception e)
{
e.printStackTrace();	
}
}

    }
    void doInsert()
    {
    	try {
			pst=con.prepareStatement("select cname,address from customer");
    	ResultSet rs=pst.executeQuery();
    	while(rs.next())
    	{
    		name.getItems().add(rs.getString("cname"));
    		address.getItems().add(rs.getString("address"));
    	}
    	}
    	catch (SQLException e) {
			
			e.printStackTrace();
		}
    }
   

    @FXML
    void initialize() {
    	txtcqu.setText("0.0");
    	txtbqu.setText("0.0");  
    	name.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	address.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
       doconnection();
       doInsert();
       

    }
}
