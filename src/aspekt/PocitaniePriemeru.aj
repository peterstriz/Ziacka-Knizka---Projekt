package aspekt;

import javafx.collections.ObservableList;
import udaje.*;

/**
 * Pocitanie priemeru vsetkych znamok daneho ziaka.
 * 
 * @author Peter Striz
 *
 */
public aspect PocitaniePriemeru {
	transient private ObservableList<Znamka> aktZnamky;

	private void updatujPriemer() {
		double celkovo = 0;
		double celkovoMax = 0;
		for (Znamka z : aktZnamky) {
			celkovo += Double.parseDouble(z.getHodnotaS());
			celkovoMax += Double.parseDouble(z.getMaxHodnotaS());
		}
		System.out.println(celkovo + "/" + celkovoMax);
	}

	ObservableList<Znamka> around() : execution(* Predmet.vratZnamku(..)) {
		aktZnamky = proceed();
		updatujPriemer();
		return aktZnamky;
	}

	after() : call(* Znamka.setHodnotaS(..)) {
		updatujPriemer();
	}

	after() : call(* Znamka.setMaxHodnotaS(..)) {
		updatujPriemer();
	}
}
