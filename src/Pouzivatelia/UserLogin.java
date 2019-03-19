package Pouzivatelia;

public abstract class UserLogin {
	private String username;
	private String password;

	public void nastavLogin(String username, String password) {
		nastavUserName(username);
		nastavPassword(password);
	}

	public Boolean overLogin(String username, String password) {
		if (this.username.equals(username) && this.password.equals(password))
			return true;
		else
			return false;
	}
	
	public Boolean overUsername(String username) {
		if (this.username.equals(username))
			return true;
		else
			return false;
	}

	public void nastavUserName(String username) {
		this.username = username;
	}

	public void nastavPassword(String password) {
		this.password = password;
	}

	public String vratUsername() {
		return this.username;
	}

	public String vratPassword() {
		return this.password;
	}

}
