package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem menuItemAbout;

	@FXML
	public void onMenuItemSellerAction() { // ação ao selecionar o menuItem
		System.out.println("onMenuItemSellerAction");
	}

	@FXML
	public void onMenuItemDepartmentAction() {
		loadView2("/gui/DepartmentList.fxml");
	}

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	}

	public void loadView(String absolutName) { // carrega a view ao clicarmos no lable
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absolutName)); // carrega o arquivo fxml
																						// selecionado
			VBox newVbox = loader.load();
			Scene mainScene = Main.getMainScene();
			VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent(); // inserir os children do About na
																					// View principal.

			Node mainMenu = mainVbox.getChildren().get(0);// armazena as informações do main Menu.
			mainVbox.getChildren().clear(); // limpa a children do mainVbox (Scene principal).
			mainVbox.getChildren().add(mainMenu); // adiciona o mainMenu antes armazenado.
			mainVbox.getChildren().addAll(newVbox.getChildren()); // adiciona o conteudo da nova VBox.

		} catch (IOException e) {
			Alerts.showAlert("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
		}

	}
	
	public void loadView2(String absolutName) { // carrega a view ao clicarmos no lable
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absolutName)); // carrega o arquivo fxml
																						// selecionado
			VBox newVbox = loader.load();
			Scene mainScene = Main.getMainScene();
			VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent(); // inserir os children do About na
																					// View principal.

			Node mainMenu = mainVbox.getChildren().get(0);// armazena as informações do main Menu.
			mainVbox.getChildren().clear(); // limpa a children do mainVbox (Scene principal).
			mainVbox.getChildren().add(mainMenu); // adiciona o mainMenu antes armazenado.
			mainVbox.getChildren().addAll(newVbox.getChildren()); // adiciona o conteudo da nova VBox.
			
			DepartmentListController controller = loader.getController();
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
			
		} catch (IOException e) {
			Alerts.showAlert("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
		}

	}


}

