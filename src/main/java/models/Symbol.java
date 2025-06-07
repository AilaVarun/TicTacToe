package models;

public class Symbol {

    private char SymbolChar;

    private String AvatarURL;

    public Symbol(char SymbolChar, String AvatarURL) {
        this.SymbolChar = SymbolChar;
        this.AvatarURL = AvatarURL;
    }

    public char getSymbolChar() {
        return SymbolChar;
    }

    public void setSymbolChar(char playerSymbol) {
        this.SymbolChar = SymbolChar;
    }

    public String getAvatarURL() {
        return AvatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        AvatarURL = avatarURL;
    }
}
