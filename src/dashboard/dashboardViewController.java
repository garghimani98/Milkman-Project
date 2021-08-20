package dashboard;

import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class dashboardViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

   
    @FXML
    void doOpenbg(MouseEvent event) {
    	System.out.println("**************");
    	try{
    		
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("billgeneration/billView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    

    @FXML
    void doOpenbl(MouseEvent event) 
    {
    	
try{
    		
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("billlog/billlogView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void doOpencf(MouseEvent event) {
try{
    		
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("customerfinder/customerfindView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void doOpencp(MouseEvent event) {
try{
    		
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("milkman/milkmanView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void doOpenrl(MouseEvent event) {
try{
    		
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("routinelog/routineView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }
    
    @FXML
    void doOpenbc(MouseEvent event) {
try{
    		
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("billcollection/billcollectionView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
 }
    @FXML
    void doOpenrm(MouseEvent event) {
    	try{
    	    		
    				Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("routinemanager/routinemanagerView.fxml")); 
    				Scene scene = new Scene(root);
    				Stage stage=new Stage();
    				stage.setScene(scene);
    				stage.show();
    			}
    			catch(Exception e)
    			{
    				e.printStackTrace();
    				}
    			}
    
    @FXML
   
    
    void doOpendev(MouseEvent event) {
    	try{
    	    		
    				Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("developer/developerview.fxml")); 
    				Scene scene = new Scene(root);
    				Stage stage=new Stage();
    				stage.setScene(scene);
    				stage.show();
    			}
    			catch(Exception e)
    			{
    				e.printStackTrace();
    				}
    			}


    @FXML
    void initialize() {

    }
}
