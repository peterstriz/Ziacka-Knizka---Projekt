package ZiackaKnizka;

public class ZiackaKnizkaSingleton {
	private static ZiackaKnizkaSingleton single_instance = null;

	private ZiackaKnizka ziackaKnizka;

	private ZiackaKnizkaSingleton() {
		setZiackaKnizka(new ZiackaKnizka());
	}

	public static ZiackaKnizkaSingleton getInstance() {
		if (single_instance == null)
			single_instance = new ZiackaKnizkaSingleton();

		return single_instance;
	}

	public ZiackaKnizka getZiackaKnizka() {
		return ziackaKnizka;
	}

	public void setZiackaKnizka(ZiackaKnizka ziackaKnizka) {
		this.ziackaKnizka = ziackaKnizka;
	}

}