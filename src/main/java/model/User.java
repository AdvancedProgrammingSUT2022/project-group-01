package model;

public class User {

	private final String username;
	//private undefinedType[] savedGames;//TODO handle here
	private String password;
	private String score;
	private String nickname;

	/**
	 * 
	 * @param username
	 * @param password
	 * @param nickname
	 */
	public User(String username, String password, String nickname) {
		// TODO - implement model.User.model.User
		throw new UnsupportedOperationException();
	}

	public String getUsername() {return this.username;}

	public String getPassword() {
		return this.password;
	}

	/**
	 * 
	 * @param passowrd
	 */
	public void setPassword(String passowrd) {
		this.password = passowrd;
	}

	public String getScore() {
		return this.score;
	}

	/**
	 * 
	 * @param score
	 */
	public void setScore(String score) {
		this.score = score;
	}

	public String getNickname() {
		return this.nickname;
	}

	/**
	 * 
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


}