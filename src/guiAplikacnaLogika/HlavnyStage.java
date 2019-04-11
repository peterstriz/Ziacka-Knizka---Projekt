package guiAplikacnaLogika;

import javafx.stage.Stage;

/**
 * HlavnyStage je trieda v ktorej je ulozena hlavnyStage, s pomocou navrhoveho
 * vzoru singleton.
 * 
 * @author Peter Striz
 */
public class HlavnyStage {
	/** Jedina instancia ktoru bude mozne vytvorit. */
	private static HlavnyStage single_instance = null;
	/** Jediny stage ktory bude zobrazovany. */
	private Stage stage;

	private HlavnyStage() {
		setStage(new Stage());
	}

	/**
	 * @return Vrati instanciu singletona.
	 */
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