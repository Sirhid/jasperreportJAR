package main.java.entity;

import java.util.List;

public class AccountStatemtRequestDTOT24 {
    private String accountID;
    private String limit;
    private String offset;
    private String page_start;
    private String searchStartDate;
    private String searchEndDate;
    private String searchMinAmount;
    private String searchMaxAmount;
    private String transactionType;
    private AccountStatementDto accountdtos;


    // Getter Methods
        public AccountStatementDto getAccountdtos(){
            return  accountdtos;
            }
    public String getAccountID() {
        return accountID;
    }

    public String getLimit() {
        return limit;
    }

    public String getOffset() {
        return offset;
    }

    public String getPage_start() {
        return page_start;
    }

    public String getSearchStartDate() {
        return searchStartDate;
    }

    public String getSearchEndDate() {
        return searchEndDate;
    }

    public String getSearchMinAmount() {
        return searchMinAmount;
    }

    public String getSearchMaxAmount() {
        return searchMaxAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    // Setter Methods

    public void setAccountdtos(AccountStatementDto accountdtos) {
        this.accountdtos = accountdtos;
    }
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public void setPage_start(String page_start) {
        this.page_start = page_start;
    }

    public void setSearchStartDate(String searchStartDate) {
        this.searchStartDate = searchStartDate;
    }

    public void setSearchEndDate(String searchEndDate) {
        this.searchEndDate = searchEndDate;
    }

    public void setSearchMinAmount(String searchMinAmount) {
        this.searchMinAmount = searchMinAmount;
    }

    public void setSearchMaxAmount(String searchMaxAmount) {
        this.searchMaxAmount = searchMaxAmount;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
