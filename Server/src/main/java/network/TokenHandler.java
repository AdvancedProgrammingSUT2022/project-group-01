package network;

import model.User;

public class TokenHandler {
	public static String generateToken(User user) {
		return user.getUsername() + ":" + user.getPassword();
	}
}
