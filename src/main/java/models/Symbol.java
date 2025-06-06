package models;

public class Symbol {

    private char playerSymbol;

    private String AvatarURL;

    public Symbol(char playerSymbol, String AvatarURL) {
        this.playerSymbol = playerSymbol;
        this.AvatarURL = AvatarURL;
    }

    public char getPlayerSymbol() {
        return playerSymbol;
    }

    public void setPlayerSymbol(char playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public String getAvatarURL() {
        return AvatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        AvatarURL = avatarURL;
    }
}
