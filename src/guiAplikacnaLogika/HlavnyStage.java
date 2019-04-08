package guiAplikacnaLogika;

import javafx.stage.Stage;

public class HlavnyStage {
	private static HlavnyStage single_instance = null;

	private Stage stage;

	private HlavnyStage() {
		setStage(new Stage());
	}

	public static HlavnyStage getInstance() {
		if (single_instance == null)
			single_instance = new HlavnyStage();

		return single_instance;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage hlStage) {
		this.stage = hlStage;
	}
}