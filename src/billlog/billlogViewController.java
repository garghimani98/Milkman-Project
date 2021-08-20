package billlog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import milkman.data;

public class billlogViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> combomon;

    @FXML
    private ComboBox<Integer> comboyr;

    @FXML
    private TextField txtmob;
    
    @FXML
    private TextField txtPname;


    @FXML
    private TableView<billlogbean> tblbill;
    
    @FXML
    private RadioButton rbpaid;

    @FXML
    private RadioButton rbpending;
    
    @FXML
    private ToggleGroup status;



    ObservableList<billlogbean> list;
    
    
    URL url;
  	Media media;
  	MediaPlayer mediaplayer;
  	AudioClip audio;
      void playSong()
      {
      	
      	url=getClass().getResource("crash.mp3");
  		media=new Media(url.toString());
  		mediaplayer=new MediaPlayer(media);
  		mediaplayer.play();
      }
      void playSound(){
      	url=getClass().getResource("crash.wav");
  		audio=new AudioClip(url.toString());
  		audio.play();
      }
      
      
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
    void doFillm(ActionEvent event) {
    	playSound();
    	TableColumn<billlogbean, String> mobile=new TableColumn<billlogbean, String>("mobile ");//Dikhava Title
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//bean field name
    	mobile.setMinWidth(100);
    	
    	TableColumn<billlogbean, Float> cqt=new TableColumn<billlogbean, Float>("cqt");
    	cqt.setCellValueFactory(new PropertyValueFactory<>("cqt"));//field name in bean
        cqt.setMinWidth(100);
    	
    	
    	TableColumn<billlogbean, Float> bqt=new TableColumn<billlogbean, Float>("bqt");
    	bqt.setCellValueFactory(new PropertyValueFactory<>("bqt"));//field name in bean
    	bqt.setMinWidth(100);
    	
    	TableColumn<billlogbean, Float> cbill=new TableColumn<billlogbean, Float>("cbill");
    	cbill.setCellValueFactory(new PropertyValueFactory<>("cbill"));//field name in bean
    	cbill.setMinWidth(100);
    	
    	
    	TableColumn<billlogbean, Float> bbill=new TableColumn<billlogbean, Float>("bbill");
    	bbill.setCellValueFactory(new PropertyValueFactory<>("bbill"));//field name in bean
    	bbill.setMinWidth(100);
    	
    	
    	TableColumn<billlogbean, Float> total=new TableColumn<billlogbean, Float>("total");
    	total.setCellValueFactory(new PropertyValueFactory<>("total"));//field name in bean
    	total.setMinWidth(100);
    	
    	
    	TableColumn<billlogbean,Integer> status=new TableColumn<billlogbean, Integer>("status");
    	status.setCellValueFactory(new PropertyValueFactory<>("status"));//field name in bean
    	status.setMinWidth(100);
    	
    	
    	
    	TableColumn<billlogbean, Integer> month=new TableColumn<billlogbean, Integer>("month");
    	month.setCellValueFactory(new PropertyValueFactory<>("month"));//field name in bean
    	month.setMinWidth(100);
    	

    	TableColumn<billlogbean, Integer> year=new TableColumn<billlogbean, Integer>("year");
    	year.setCellValueFactory(new PropertyValueFactory<>("year"));//field name in bean
    	year.setMinWidth(100);
    	
    	tblbill.getColumns().clear();
    	tblbill.getColumns().addAll(mobile,cqt,bqt,cbill,bbill,total,status,month,year);
    	
    	 list=FXCollections.observableArrayList();
    	try {
 			  pst=con.prepareStatement("select * from bill where mobile=? order by month,year");
 			 pst.setString(1,txtmob.getText());
 			
 			ResultSet rs=  pst.executeQuery();
 			while(rs.next())
 			{
 				String mobile1=rs.getString("mobile");
 			Float cqt1=rs.getFloat("cqt");
 			Float bqt1=rs.getFloat("bqt");
 			Float cbill1=rs.getFloat("cbill");
 			Float bbill1=rs.getFloat("bbill");
 			Float total1=rs.getFloat("total");
 			int status1=rs.getInt("status");
 			int month1=rs.getInt("month");
 			int year1=rs.getInt("year");
 			billlogbean	bean=new billlogbean(mobile1,cqt1,bqt1,cbill1,bbill1,total1,status1,month1,year1);
 				list.add(bean);
 			}
 			
 			} 
 		catch (SQLException e) 
 		{
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		
    	tblbill.setItems(list);

    }
    

    @FXML
    void doFillmy(ActionEvent event) {
    	playSound();
    	TableColumn<billlogbean, String> mobile=new TableColumn<billlogbean, String>("mobile ");//Dikhava Title
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//bean field name
    	mobile.setMinWidth(100);
    	
    	TableColumn<billlogbean, Float> cqt=new TableColumn<billlogbean, Float>("cqt");
    	cqt.setCellValueFactory(new PropertyValueFactory<>("cqt"));//field name in bean
        cqt.setMinWidth(100);
    	
    	
    	TableColumn<billlogbean, Float> bqt=new TableColumn<billlogbean, Float>("bqt");
    	bqt.setCellValueFactory(new PropertyValueFactory<>("bqt"));//field name in bean
    	bqt.setMinWidth(100);
    	
    	TableColumn<billlogbean, Float> cbill=new TableColumn<billlogbean, Float>("cbill");
    	cbill.setCellValueFactory(new PropertyValueFactory<>("cbill"));//field name in bean
    	cbill.setMinWidth(100);
    	
    	
    	TableColumn<billlogbean, Float> bbill=new TableColumn<billlogbean, Float>("bbill");
    	bbill.setCellValueFactory(new PropertyValueFactory<>("bbill"));//field name in bean
    	bbill.setMinWidth(100);
    	
    	
    	TableColumn<billlogbean, Float> total=new TableColumn<billlogbean, Float>("total");
    	total.setCellValueFactory(new PropertyValueFactory<>("total"));//field name in bean
    	total.setMinWidth(100);
    	
    	
    	TableColumn<billlogbean,Integer> status=new TableColumn<billlogbean, Integer>("status");
    	status.setCellValueFactory(new PropertyValueFactory<>("status"));//field name in bean
    	status.setMinWidth(100);
    	
    	
    	
    	TableColumn<billlogbean, Integer> month=new TableColumn<billlogbean, Integer>("month");
    	month.setCellValueFactory(new PropertyValueFactory<>("month"));//field name in bean
    	month.setMinWidth(100);
    	

    	TableColumn<billlogbean, Integer> year=new TableColumn<billlogbean, Integer>("year");
    	year.setCellValueFactory(new PropertyValueFactory<>("year"));//field name in bean
    	year.setMinWidth(100);
    	
    	tblbill.getColumns().clear();
    	tblbill.getColumns().addAll(mobile,cqt,bqt,cbill,bbill,total,status,month,year);
    	
    	list=FXCollections.observableArrayList();
   		try {
   			  pst=con.prepareStatement("select * from bill where month=? and year=?");
   			 pst.setInt(1,combomon.getValue());
   			pst.setInt(2,comboyr.getValue());
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				String mobile1=rs.getString("mobile");
   			Float cqt1=rs.getFloat("cqt");
   			Float bqt1=rs.getFloat("bqt");
   			Float cbill1=rs.getFloat("cbill");
   			Float bbill1=rs.getFloat("bbill");
   			Float total1=rs.getFloat("total");
   			int status1=rs.getInt("status");
   			int month1=rs.getInt("month");
   			int year1=rs.getInt("year");
   			billlogbean	bean=new billlogbean(mobile1,cqt1,bqt1,cbill1,bbill1,total1,status1,month1,year1);
   				list.add(bean);
   			}
   			
   			} 
   		catch (SQLException e) 
   		{e.printStackTrace();
   		}
    	
    	//ObservableList<billlogbean> list=getRecordsFromTable(combomon.getValue(),comboyr.getValue());
    	tblbill.setItems(list);

    }
    
    
    @FXML
    void doFills(ActionEvent event)  {
    	playSound();
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Fetching data");
    	TableColumn<billlogbean, String> mobile=new TableColumn<billlogbean, String>("mobile");//Dikhava Title
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//bean field name
    	mobile.setMinWidth(100);
    	 
    	TableColumn<billlogbean, Float> cqt=new TableColumn<billlogbean, Float>("cqt");
    	cqt.setCellValueFactory(new PropertyValueFactory<>("cqt"));//field name in bean
        cqt.setMinWidth(100);
    	
    	
    	TableColumn<billlogbean, Float> bqt=new TableColumn<billlogbean, Float>("bqt");
    	bqt.setCellValueFactory(new PropertyValueFactory<>("bqt"));//field name in bean
    	bqt.setMinWidth(100);
    	
    	TableColumn<billlogbean, Float> cbill=new TableColumn<billlogbean, Float>("cbill");
    	cbill.setCellValueFactory(new PropertyValueFactory<>("cbill"));//field name in bean
    	cbill.setMinWidth(100);
    	
    	
    	TableColumn<billlogbean, Float> bbill=new TableColumn<billlogbean, Float>("bbill");
    	bbill.setCellValueFactory(new PropertyValueFactory<>("bbill"));//field name in bean
    	bbill.setMinWidth(100);
    	
    	
    	TableColumn<billlogbean, Float> total=new TableColumn<billlogbean, Float>("total");
    	total.setCellValueFactory(new PropertyValueFactory<>("total"));//field name in bean
    	total.setMinWidth(100);
    	
    	
    	TableColumn<billlogbean,Integer> status=new TableColumn<billlogbean, Integer>("status");
    	status.setCellValueFactory(new PropertyValueFactory<>("status"));//field name in bean
    	status.setMinWidth(100);
    	
    	
    	
    	TableColumn<billlogbean, Integer> month=new TableColumn<billlogbean, Integer>("month");
    	month.setCellValueFactory(new PropertyValueFactory<>("month"));//field name in bean
    	month.setMinWidth(100);
    	

    	TableColumn<billlogbean, Integer> year=new TableColumn<billlogbean, Integer>("year");
    	year.setCellValueFactory(new PropertyValueFactory<>("year"));//field name in bean
    	year.setMinWidth(100);
    	
    	tblbill.getColumns().clear();
    	tblbill.getColumns().addAll(mobile,cqt,bqt,cbill,bbill,total,status,month,year);
    	int status1=0;
    	if(rbpaid.isSelected()==true){
    		 status1=1;
    		
    	}
    	else if(rbpending.isSelected()==true){
    		 status1=0;
    		
    	}
    	 list=FXCollections.observableArrayList();

         if(combomon.getSelectionModel().isEmpty() || comboyr.getSelectionModel().isEmpty())
         {
         	alert.setContentText("Please select month and year");
         	alert.show();
         }
         else{
    	try {
 			  pst=con.prepareStatement("select * from bill where month=? and year=? and status=?");
 			 pst.setInt(1,combomon.getValue());
 			pst.setInt(2,comboyr.getValue());
 			pst.setInt(3,status1);
          ResultSet rs=  pst.executeQuery();
 			while(rs.next())
 			{
 				String mobile1=rs.getString("mobile");
 			Float cqt1=rs.getFloat("cqt");
 			Float bqt1=rs.getFloat("bqt");
 			Float cbill1=rs.getFloat("cbill");
 			Float bbill1=rs.getFloat("bbill");
 			Float total1=rs.getFloat("total");
 			int status21=rs.getInt("status");
 			int month1=rs.getInt("month");
 			int year1=rs.getInt("year");
 			billlogbean	bean=new billlogbean(mobile1,cqt1,bqt1,cbill1,bbill1,total1,status21,month1,year1);
 				list.add(bean);
 			}
 			
 			} 
 		catch (SQLException e) 
 		{
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    	
    	//ObservableList<billlogbean> list=getRecordsFromTablebill(combomon.getValue(),comboyr.getValue(),status2);
    	tblbill.setItems(list);}

    }

    @FXML
    void doSend(ActionEvent event) {
playSound();
    	try {
			writeExcel();
			txtPname.setText("Exported to excel..");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void writeExcel() throws Exception {
        Writer writer = null;
        try {
        	FileChooser chooser=new FileChooser();
	    	
        	chooser.setTitle("Select Path:");
        	
        	chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Files", "*.*")
                    
                );
        	 File file=chooser.showSaveDialog(null);
        	String filePath=file.getAbsolutePath();
        	if(!(filePath.endsWith(".csv")||filePath.endsWith(".CSV")))
        	{
        		txtPname.setText("file name should have .csv extension");
        		return;
        	}
        	 file = new File(filePath);
        	 
        	 
        	 
            writer = new BufferedWriter(new FileWriter(file));
            String text="product mobile,product cqt,product bqt,product cbill,productbbill,product total,product status,product month,product year\n";
           
            writer.write(text);
            for (billlogbean p : list)
            {
				text = p.getMobile()+ "," + p.getCqt()+ "," + p.getBqt()+ "," + p.getCbill()+"," + p.getBbill()+"," + p.getTotal()+"," + p.getStatus()+"," + p.getMonth()+"," + p.getYear()+"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            writer.flush();
             writer.close();
        }
    }

    
    
   

    @FXML
    void initialize() {
    	for(int i=1;i<=12;i++){
    	    ArrayList<Integer>m=new ArrayList<Integer>(Arrays.asList(i));
    		combomon.getItems().addAll(m);
    		}
    	rbpending.isSelected();
    		 ArrayList<Integer>y=new ArrayList<Integer>(Arrays.asList(2017,2018,2019));
    	   comboyr.getItems().addAll(y);
    	 
    	   	doConnect();
    	   	
    	    
    	    }


    }


