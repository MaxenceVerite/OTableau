package View;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OTableau extends Application{

	private Stage window;
	private Scene currentScene;
	private Parent root;
	
	public void start(Stage stage) throws Exception {
		window=stage;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(OTableau.class.getResource("/View/Login.fxml"));
		root = loader.load();
		currentScene = new Scene(root);
		window.setScene(currentScene);
		window.setResizable(false);
		window.show();
	}
	
public static void main(String[] args){
		launch(args);
	}

}

