package Pouzivatelia;

import java.util.Date;

import javafx.collections.ObservableList;

public interface Pouzivatel{
	public void nastavLogin(String username, String password);
	public Boolean overLogin(String username, String password);
	public String vratMeno();
	public String vratPriezvisko();
	public void nastavMeno(String meno);
	public void nastavPriezvisko(String priezvisko);
	public void pridajPredmet(Predmet predmetNovy);
	public Predmet vratPredmet(int i);
	public ObservableList<String> vratMenoPredmetov();
	public ObservableList<Znamka> vratZnamkyPredmetu(int i);
	public void pridajZnamku(int cisloPredmetu, double hodnota, double maxHodnota, Date datum);
}
