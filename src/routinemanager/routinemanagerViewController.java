package routinemanager;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import milkman.data;

public class routinemanagerViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> combomonth;

    @FXML
    private ComboBox<Integer> comboyear;

    @FXML
    private TextField txtmob;

    @FXML
    private TableView<routinemanagerbean> tbllog;

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
    @FXML
    void doShow(ActionEvent event) {

    	TableColumn<routinemanagerbean,String> mobile=new TableColumn<routinemanagerbean, String>("mobile");//Dikhava Title
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//bean field name
    	
    	TableColumn<routinemanagerbean, Float> cq=new TableColumn<routinemanagerbean, Float>("cq");
    	cq.setCellValueFactory(new PropertyValueFactory<>("cq"));//field name in bean
    	cq.setMinWidth(100);
    	
    	
    	TableColumn<routinemanagerbean, Float> bq=new TableColumn<routinemanagerbean, Float>("bq");
    	bq.setCellValueFactory(new PropertyValueFactory<>("bq"));//field name in bean
    	bq.setMinWidth(100);
    	
    	
    	TableColumn<routinemanagerbean, Integer> date=new TableColumn<routinemanagerbean, Integer>("date");
    	date.setCellValueFactory(new PropertyValueFactory<>("date"));//field name in bean
    	date.setMinWidth(100);
    	

    	TableColumn<routinemanagerbean, Integer> month=new TableColumn<routinemanagerbean, Integer>("month");
    	month.setCellValueFactory(new PropertyValueFactory<>("month"));//field name in bean
    	month.setMinWidth(100);
    	

    	TableColumn<routinemanagerbean,Integer> year=new TableColumn<routinemanagerbean, Integer>("year");
    	year.setCellValueFactory(new PropertyValueFactory<>("year"));//field name in bean
    	year.setMinWidth(100);
    	

    	
    	tbllog.getColumns().clear();
    	tbllog.getColumns().addAll(mobile,cq,bq,date,month,year);
    	
    	ObservableList<routinemanagerbean> list=getRecordsFromTablearea(txtmob.getText(),combomonth.getValue(),comboyear.getValue());
    	tbllog.setItems(list);
    	
    }
    ObservableList<routinemanagerbean> getRecordsFromTablearea(String mobile,int month,int year)
   	{
   		ObservableList<routinemanagerbean> list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select * from routine where mobile=? and month=? and year=?");
   			int y1=comboyear.getSelectionModel().getSelectedItem();
 			int m1=combomonth.getSelectionModel().getSelectedItem();
 			String mo1=txtmob.getText();
   			  pst.setString(1, mo1);
   			pst.setInt(2, m1);
   			pst.setInt(3, y1);
   			ResultSet res=  pst.executeQuery();
   			while(res.next())
   			{String mobile1=res.getString("mobile");
				
				float cq=res.getFloat("cq");
				float bq=res.getFloat("bq");
				int date=res.getInt("date");
				int month1=res.getInt("month");
				int year1=res.getInt("year");
				
				routinemanagerbean bean=new routinemanagerbean(mobile1,cq,bq,date,month1,year1);
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
