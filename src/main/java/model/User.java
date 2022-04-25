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
		this.nickname = nickname;
		this.username = username;
		this.password = password;
	}

	public String getUsername() {return this.username;}

	public String getPassword() {
		return this.password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
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