package Pouzivatelia;

public interface Pouzivatel {
	public void nastavLogin(String username, String password);

	public Boolean overLogin(String username, String password);

	public Boolean overUsername(String username);

	public String vratMeno();

	public String vratPriezvisko();

	public String vratCeleMeno();

	public void nastavMeno(String meno);

	public void nastavPriezvisko(String priezvisko);

//	public void pridajPredmet(Predmet predmetNovy);
//
//	public Predmet vratPredmet(int i);
//
//	public ObservableList<String> vratMenoPredmetov();
//
//	public ObservableList<Znamka> vratZnamkyPredmetu(int i);
//
//	public void pridajZnamku(int cisloPredmetu, String hodnota, String maxHodnota, String datum);
}
