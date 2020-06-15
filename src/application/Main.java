package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {

	private static Scene mainScene;

	@Override
	public synchronized void start(Stage primaryStage) { // synchronized -> garante que o processamento não vai ser interrompido.
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/mainView.fxml"));
			ScrollPane scrollPane = loader.load();

			scrollPane.setFitToHeight(true); // ajusta o tamanho dos menus para ocupar a tela inteira.
			scrollPane.setFitToWidth(true);

			mainScene = new Scene(scrollPane);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Sample JavaFX application");
			primaryStage.show();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static Scene getMainScene() {
		return mainScene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
