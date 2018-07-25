package Model;

public class encdata {
    private String plaintext , encryptedtext;

    public encdata() {
    }

    public encdata(String plaintext, String encryptedtext) {
        this.plaintext = plaintext;
        this.encryptedtext = encryptedtext;
    }


    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public String getEncryptedtext() {
        return encryptedtext;
    }

    public void setEncryptedtext(String encryptedtext) {
        this.encryptedtext = encryptedtext;
    }
}
