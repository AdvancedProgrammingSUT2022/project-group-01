package model;

public enum Command {
	LOGIN("user login", new String[] { "username", "login" }),
	SIGN_UP("user create", new String[] { "username", "login", "nickname" }),
	PLAY_GAME("play game", null),
	INCREASE_GOLD("increase", new String[]{"gold"}),
	INCREASE_TURN("increase", new String[]{"turn"}),
	CHANGE_NICKNAME("profile change",new String[]{"nickname"}),
	CHANGE_PASSWORD("profile change --password", new String[]{"current", "new"});

	public String[] necessaryArgs;
	public String offset;

	/**
	 * 
	 * @param offset
	 * @param necessaryArgs
	 */
	Command(String offset, String[] necessaryArgs) {
		this.necessaryArgs = necessaryArgs;
		this.offset = offset;
	}

}