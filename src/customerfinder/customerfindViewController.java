package customerfinder;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import milkman.data;

public class customerfindViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboarea;

    @FXML
    private TextField txtname;
    
    @FXML
    private TableView<customerfinderbean> tblcus;
    
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
 //String area=comboarea.getSelectionModel().getSelectedItem();

    @FXML
    void doFetcharea(ActionEvent event) { 

    	TableColumn<customerfinderbean,String> mobile=new TableColumn<customerfinderbean, String>("mobile");//Dikhava Title
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//bean field name
    	
    	TableColumn<customerfinderbean, String> cname=new TableColumn<customerfinderbean, String>("cname");
    	cname.setCellValueFactory(new PropertyValueFactory<>("cname"));//field name in bean
    	cname.setMinWidth(100);
    	
    	
    	TableColumn<customerfinderbean, String> address=new TableColumn<customerfinderbean, String>("address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));//field name in bean
    	address.setMinWidth(100);
    	
    	
    	TableColumn<customerfinderbean, String> area=new TableColumn<customerfinderbean, String>("area");
    	area.setCellValueFactory(new PropertyValueFactory<>("area"));//field name in bean
    	area.setMinWidth(100);
    	

    	TableColumn<customerfinderbean, String> city=new TableColumn<customerfinderbean, String>("city");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));//field name in bean
    	city.setMinWidth(100);
    	

    	TableColumn<customerfinderbean,Float> cq=new TableColumn<customerfinderbean, Float>("cq");
    	cq.setCellValueFactory(new PropertyValueFactory<>("cq"));//field name in bean
    	cq.setMinWidth(100);
    	

    	TableColumn<customerfinderbean, Float> bq=new TableColumn<customerfinderbean, Float>("bq");
    	bq.setCellValueFactory(new PropertyValueFactory<>("bq"));//field name in bean
    	bq.setMinWidth(100);
    	

    	TableColumn<customerfinderbean, Float> cr=new TableColumn<customerfinderbean, Float>("cr");
    	cr.setCellValueFactory(new PropertyValueFactory<>("cr"));//field name in bean
    	cr.setMinWidth(100);
    	
    	TableColumn<customerfinderbean, Float> br=new TableColumn<customerfinderbean, Float>("br");
    	br.setCellValueFactory(new PropertyValueFactory<>("br"));//field name in bean
    	br.setMinWidth(100);
    	
    	TableColumn<customerfinderbean, String> date=new TableColumn<customerfinderbean, String>("date");
    	date.setCellValueFactory(new PropertyValueFactory<>("date"));//field name in bean
    	date.setMinWidth(100);
    	
    	tblcus.getColumns().clear();
    	tblcus.getColumns().addAll(mobile,cname,address,area,city,cq,bq,cr,br,date);
    	
    	ObservableList<customerfinderbean> list=getRecordsFromTablearea(comboarea.getValue());
    	tblcus.setItems(list);
    	
    }
    ObservableList<customerfinderbean> getRecordsFromTablearea(String area)
   	{
   		ObservableList<customerfinderbean> list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select * from customer where area=?");
   			  pst.setString(1, area);
   			 
   			ResultSet res=  pst.executeQuery();
   			while(res.next())
   			{String mobile=res.getString("mobile");
				String cname=res.getString("cname");
				String address=res.getString("address");
				String area1=res.getString("area");
				String city=res.getString("city");
				float cq=res.getFloat("cq");
				float bq=res.getFloat("bq");
				float cr=res.getFloat("cr");
				float br=res.getFloat("br");
				
				
				String dopp=res.getString("date");
				
				customerfinderbean bean=new customerfinderbean(mobile,cname,address,area1,city,cq,bq,cr,br,dopp);
				list.add(bean);
   			} }
   		catch (SQLException e) 
   		{
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		
   		return list;
   	}
    

   
	@FXML
    void doFillall(ActionEvent event) {

    	TableColumn<customerfinderbean,String> mobile=new TableColumn<customerfinderbean, String>("mobile");//Dikhava Title
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//bean field name
    	
    	TableColumn<customerfinderbean, String> cname=new TableColumn<customerfinderbean, String>("cname");
    	cname.setCellValueFactory(new PropertyValueFactory<>("cname"));//field name in bean
    	cname.setMinWidth(100);
    	
    	
    	TableColumn<customerfinderbean, String> address=new TableColumn<customerfinderbean, String>("address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));//field name in bean
    	address.setMinWidth(100);
    	
    	
    	TableColumn<customerfinderbean, String> area=new TableColumn<customerfinderbean, String>("area");
    	area.setCellValueFactory(new PropertyValueFactory<>("area"));//field name in bean
    	area.setMinWidth(100);
    	

    	TableColumn<customerfinderbean, String> city=new TableColumn<customerfinderbean, String>("city");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));//field name in bean
    	city.setMinWidth(100);
    	

    	TableColumn<customerfinderbean,Float> cq=new TableColumn<customerfinderbean, Float>("cq");
    	cq.setCellValueFactory(new PropertyValueFactory<>("cq"));//field name in bean
    	cq.setMinWidth(100);
    	

    	TableColumn<customerfinderbean, Float> bq=new TableColumn<customerfinderbean, Float>("bq");
    	bq.setCellValueFactory(new PropertyValueFactory<>("bq"));//field name in bean
    	bq.setMinWidth(100);
    	

    	TableColumn<customerfinderbean, Float> cr=new TableColumn<customerfinderbean, Float>("cr");
    	cr.setCellValueFactory(new PropertyValueFactory<>("cr"));//field name in bean
    	cr.setMinWidth(100);
    	
    	TableColumn<customerfinderbean, Float> br=new TableColumn<customerfinderbean, Float>("br");
    	br.setCellValueFactory(new PropertyValueFactory<>("br"));//field name in bean
    	br.setMinWidth(100);
    	
    	TableColumn<customerfinderbean, String> date=new TableColumn<customerfinderbean, String>("date");
    	date.setCellValueFactory(new PropertyValueFactory<>("date"));//field name in bean
    	date.setMinWidth(100);
    	
    	tblcus.getColumns().clear();
    	tblcus.getColumns().addAll(mobile,cname,address,area,city,cq,bq,cr,br,date);
    	
    	ObservableList<customerfinderbean> list=getRecordsFromTable();
    	tblcus.setItems(list);
    }
   
	ObservableList<customerfinderbean> getRecordsFromTable()
   	{
   		ObservableList<customerfinderbean> list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select mobile,cname,address,area,city,cq,bq,cr,br,date from customer");
   			 
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				String mobile=rs.getString("mobile");
   				String cname=rs.getString("cname");
   				String address=rs.getString("address");
   				String area=rs.getString("area");
   				String city=rs.getString("city");
   				float cq=rs.getFloat("cq");
   				float bq=rs.getFloat("bq");
   				float cr=rs.getFloat("cr");
   				float br=rs.getFloat("br");
   				
   				
   				String dopp=rs.getString("date");
   				
   				customerfinderbean bean=new customerfinderbean(mobile,cname,address,area,city,cq,bq,cr,br,dopp);
   				list.add(bean);
   			} }
   		catch (SQLException e) 
   		{
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		
   		return list;
   	}

    @FXML
    void doSearch(ActionEvent event) {
    	TableColumn<customerfinderbean,String> mobile=new TableColumn<customerfinderbean, String>("mobile");//Dikhava Title
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//bean field name
    	
    	TableColumn<customerfinderbean, String> cname=new TableColumn<customerfinderbean, String>("cname");
    	cname.setCellValueFactory(new PropertyValueFactory<>("cname"));//field name in bean
    	cname.setMinWidth(100);
    	
    	
    	TableColumn<customerfinderbean, String> address=new TableColumn<customerfinderbean, String>("address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));//field name in bean
    	address.setMinWidth(100);
    	
    	
    	TableColumn<customerfinderbean, String> area=new TableColumn<customerfinderbean, String>("area");
    	area.setCellValueFactory(new PropertyValueFactory<>("area"));//field name in bean
    	area.setMinWidth(100);
    	

    	TableColumn<customerfinderbean, String> city=new TableColumn<customerfinderbean, String>("city");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));//field name in bean
    	city.setMinWidth(100);
    	

    	TableColumn<customerfinderbean,Float> cq=new TableColumn<customerfinderbean, Float>("cq");
    	cq.setCellValueFactory(new PropertyValueFactory<>("cq"));//field name in bean
    	cq.setMinWidth(100);
    	

    	TableColumn<customerfinderbean, Float> bq=new TableColumn<customerfinderbean, Float>("bq");
    	bq.setCellValueFactory(new PropertyValueFactory<>("bq"));//field name in bean
    	bq.setMinWidth(100);
    	

    	TableColumn<customerfinderbean, Float> cr=new TableColumn<customerfinderbean, Float>("cr");
    	cr.setCellValueFactory(new PropertyValueFactory<>("cr"));//field name in bean
    	cr.setMinWidth(100);
    	
    	TableColumn<customerfinderbean, Float> br=new TableColumn<customerfinderbean, Float>("br");
    	br.setCellValueFactory(new PropertyValueFactory<>("br"));//field name in bean
    	br.setMinWidth(100);
    	
    	TableColumn<customerfinderbean, String> date=new TableColumn<customerfinderbean, String>("date");
    	date.setCellValueFactory(new PropertyValueFactory<>("date"));//field name in bean
    	date.setMinWidth(100);
    	
    	tblcus.getColumns().clear();
    	tblcus.getColumns().addAll(mobile,cname,address,area,city,cq,bq,cr,br,date);
    	
    	ObservableList<customerfinderbean> list=getRecordsFromTablename(txtname.getText());
    	tblcus.setItems(list);

    }
    
    ObservableList<customerfinderbean> getRecordsFromTablename(String cname)
   	{
   		ObservableList<customerfinderbean> list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select mobile,cname,address,area,city,cq,bq,cr,br,date from customer where cname=?");
   			 pst.setString(1,txtname.getText());
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				String mobile=rs.getString("mobile");
   				String cname1=rs.getString("cname");
   				String address=rs.getString("address");
   				String area=rs.getString("area");
   				String city=rs.getString("city");
   				float cq=rs.getFloat("cq");
   				float bq=rs.getFloat("bq");
   				float cr=rs.getFloat("cr");
   				float br=rs.getFloat("br");
   				
   				
   				String dopp=rs.getString("date");
   				
   				customerfinderbean bean=new customerfinderbean(mobile,cname1,address,area,city,cq,bq,cr,br,dopp);
   				list.add(bean);
   			} }
   		catch (SQLException e) 
   		{
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		
   		return list;
   	}


   
    void fillcombo()
    {
    	try{
    	pst=con.prepareStatement("select distinct area from customer");
    	ResultSet rs=pst.executeQuery();
    	while(rs.next())
    	{
    		comboarea.getItems().add(rs.getString("area"));	
    		
    		
    		
    	}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		}
    	 }

    @FXML
    void initialize() {
       doConnect();
       fillcombo();

       
    }
}
