package pouzivatelia;

import java.io.Serializable;

import guiAplikacnaLogika.ManazerLogin;

/**
 * Riaditel je trieda v ktorej su ulozene osobne udaje riaditela.
 * 
 * @author Peter Striz
 * @see Pouzivatel
 * @see OsobneUdaje
 */
public class Riaditel extends OsobneUdaje implements Serializable, Pouzivatel {
	private static final long serialVersionUID = 1L;

	public Riaditel(String meno, String priezvisko) {
		super(meno, priezvisko);
	}

	/**
	 * Prihlasovanie pouzivatela v ManazerLogin pomocou navrhoveho vzoru Visitor.
	 * 
	 * @see ManazerLogin
	 */
	public void login(ManazerLogin m) {
		m.login(this);
	}

}
