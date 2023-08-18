package main.java.org.example;
public class RibData {

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return the rib
     */
    public String getRib() {
        return rib;
    }

    /**
     * @param rib the rib to set
     */
    public void setRib(String rib) {
        this.rib = rib;
    }

    /**
     * @return the iban
     */
    public String getIban() {
        return iban;
    }

    /**
     * @param iban the iban to set
     */
    public void setIban(String iban) {
        this.iban = iban;
    }
    private String fullname;
    private String currency;
    private String rib;
    private String iban;
    
        @Override
    public String toString() {
        return "{" +
                    "fullname :" + fullname  +
                    ", currency :" + currency +
                    ", rib :" + rib  +
                    ", iban :" + iban +
                '}';
    }

}
