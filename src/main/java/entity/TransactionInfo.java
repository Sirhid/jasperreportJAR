/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.entity;

/**
 *
 * @author USER
 */
public class TransactionInfo {

    /**
     * @return the transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId the transactionId to set
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the withdrawal
     */
    public String getWithdrawal() {
        return withdrawal;
    }

    /**
     * @param withdrawal the withdrawal to set
     */
    public void setWithdrawal(String withdrawal) {
        this.withdrawal = withdrawal;
    }

    /**
     * @return the deposit
     */
    public String getDeposit() {
        return deposit;
    }

    /**
     * @param deposit the deposit to set
     */
    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    /**
     * @return the balance
     */
    public String getBalance() {
        return balance;
    }
    @Override
    public String toString() {
        return "{" +
                    "transactionId :" + transactionId  +
                    ", date :" + date +
                    ", description :" + description  +
                    ", withdrawal :" + withdrawal +
                    ", deposit :" + deposit +
                    ", balance :" + balance +
                '}';
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(String balance) {
        this.balance = balance;
    }
    private String transactionId;
    private String date;
    private String description;
    private String withdrawal;
    private String deposit;
    private String balance;
}
