package gui;

import ZiackaKnizka.ZiackaKnizkaSingleton;

public abstract class DefaultHodnoty {
	private HlavnyStage singleton = HlavnyStage.getInstance();
	private ZiackaKnizkaSingleton ziackaKnizka = ZiackaKnizkaSingleton.getInstance();

	protected int width = 800;
	protected int height = 600;

	protected int velkostTabulky = 150;
	protected int stredTabulky = 0;
	protected int velkostPolickaX = 100;
	protected int velkostPolickaY = 30;
	protected int medzera = 10;

	protected void logout() {
		Scena scena = new Scena(new ScenaLogin());
		singleton.getStage().setScene(scena.nastavScene(null));
		ziackaKnizka.serializuj();
	}
}
