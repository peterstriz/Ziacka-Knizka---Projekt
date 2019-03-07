package Pouzivatelia;

public class UserLogin {
	private String userName;
	private String password;

	public Boolean overUserName(String userName) {
		if (this.userName.equals(userName))
			return true;
		else
			return false;
	}

	public Boolean overPassword(String password) {
		if (this.password.equals(password))
			return true;
		else
			return false;
	}

	public void nastavUserName(String userName) {
		this.userName = userName;
	}

	public void nastavPassword(String password) {
		this.password = password;
	}

}
