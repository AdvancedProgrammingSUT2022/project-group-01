package utils;


public enum Commands {
    LOGIN("user login", new String[] { "username", "login" }),
    SIGN_UP("user create", new String[] { "username", "login", "nickname" }),
    PLAY_GAME("play game", null);

    String[] args;
    String offset;

    Commands(String offset, String[] args) {
        this.args = args;
        this.offset = offset;
    }
}
