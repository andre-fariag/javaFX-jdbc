package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
import model.services.SellerService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem menuItemAbout;

	@FXML
	public void onMenuItemSellerAction() { // ação ao selecionar o menuItem
		loadView("/gui/SellerList.fxml", (SellerListController controller) -> {
			controller.setSellerService(new SellerService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemDepartmentAction() {
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", x -> {});
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	}

	private synchronized <T> void loadView(String absolutName, Consumer<T> initializingAction) { // carrega a view ao
																									// clicarmos no
																									// lable
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

			// ativa a segunda função (lambda) como parâmetro na chamada da função
			T controller = loader.getController();
			initializingAction.accept(controller);

		} catch (IOException e) {
			Alerts.showAlert("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
		}

	}

}
