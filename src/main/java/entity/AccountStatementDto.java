package main.java.entity;

public class AccountStatementDto {


    private String accountNumber;
    private String accountName;
    private String searchTransactionType;
    private String searchStartDate;
    private String searchEndDate;
    private String dateFormat;
    private String fileType;
    private String title;
    private String generatedBy;


    // Getter Methods

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getSearchTransactionType() {
        return searchTransactionType;
    }

    public String getSearchStartDate() {
        return searchStartDate;
    }

    public String getSearchEndDate() {
        return searchEndDate;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public String getFileType() {
        return fileType;
    }

    public String getTitle() {
        return title;
    }

    public String getGeneratedBy() {
        return generatedBy;
    }

    // Setter Methods

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setSearchTransactionType(String searchTransactionType) {
        this.searchTransactionType = searchTransactionType;
    }

    public void setSearchStartDate(String searchStartDate) {
        this.searchStartDate = searchStartDate;
    }

    public void setSearchEndDate(String searchEndDate) {
        this.searchEndDate = searchEndDate;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGeneratedBy(String generatedBy) {
        this.generatedBy = generatedBy;
    }
}
