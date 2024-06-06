package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import util.ReadAndWriteFromTxtFile;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/views/LoginPage.fxml"));			
			Scene scene = new Scene(root,1000,700);
			scene.getStylesheets().add(getClass().getResource("/cssStyling/application.css").toExternalForm());
			
			ReadAndWriteFromTxtFile.ReadDictionary();
			ReadAndWriteFromTxtFile.ReadUsersFromTxtFile();
			ReadAndWriteFromTxtFile.ReadCommentsFromTxtFile();
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
