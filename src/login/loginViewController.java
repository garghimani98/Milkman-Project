package login;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import milkman.data;
import sms.LoginPassword;
import sms.RecoverPassword;

public class loginViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtkey;
    
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
    void doProceed(ActionEvent event) {
    	if(txtkey.getText().equals("garg")){
    	try{

	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("dashboard/dashboardView.fxml")); 
	Scene scene = new Scene(root);
	Stage stage=new Stage();
	stage.setScene(scene);
	stage.show();
}


    	catch(Exception ex)
    	{ex.printStackTrace();}}
    	else {
    		Alert confirm=new Alert(AlertType.CONFIRMATION);
    		confirm.setTitle("Closing..");
    		confirm.setContentText("invalid key?");
    		confirm.show();
    		
    	}
}
     @FXML
    void dorecover(ActionEvent event) {
    	RecoverPassword.recoverPassword();

    }
    @FXML
    void initialize() {
       doConnect();
    }
}
