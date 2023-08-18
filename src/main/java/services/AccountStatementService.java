/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.services;

import java.util.*;
import main.java.entity.TransactionInfo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author USER
 */
public class AccountStatementService {
    
    private static final Logger logger = LogManager.getLogger(AccountStatementService.class);
    
    public static List<TransactionInfo> extractJsonDataForTransactionInfo(String jsonData) {
        String transactionType = "";
        String amount = "";
        String accountId = "";
        String balance = "";
        String transactionReference = "";
        String displayName = "";
        String narrative = "";
        String bookingDate = "";
        String valueDate = "";
        String currencyId = "";
        String entryReference = "";

        JSONObject jsonArr = new JSONObject();
        JSONObject jso = new JSONObject();
        List<TransactionInfo> transactionList = new ArrayList<>();
// jsonData="{\"message\":{\"expiryTimestamp\":\"0\",\"recipients\":{\"recipient\":[{\"mobile\":\"+2267098808000\"}]},\"priorityService\":\"true\",\"content\":\"219\",\"startTimestamp\":\"0\"}}";
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonData);
            HashMap<String, Object> inputMap = new HashMap<>(jsonObject);
//            String body = inputMap.get("body").toString();
            JSONArray jsonArray = (JSONArray) inputMap.get("body");
            for (int i = 0; i < jsonArray.size(); i++) {

                jsonArr = (JSONObject) jsonArray.get(i);
                TransactionInfo transactionInfo = new TransactionInfo();
//                transactionType = jsonArr.get("transactionType").toString();
//                accountId = jsonArr.get("accountId").toString();
                amount = jsonArr.get("amount").toString();
                balance = jsonArr.get("balance").toString();
                transactionReference = jsonArr.get("transactionReference").toString();
                displayName = jsonArr.get("displayName").toString();
                narrative = jsonArr.get("narrative").toString();
                bookingDate = jsonArr.get("bookingDate").toString();
                valueDate = jsonArr.get("valueDate").toString();
                currencyId = jsonArr.get("currencyId").toString();
                entryReference = jsonArr.get("entryReference").toString();
                
                if(Double.parseDouble(amount) < 0){
                    transactionInfo.setWithdrawal("-"+ currencyId + " "+amount.substring(1));
                    transactionInfo.setDeposit("");
                }else{
                    transactionInfo.setDeposit(currencyId + " " +amount);
                    transactionInfo.setWithdrawal("");
                }
                transactionInfo.setDescription(displayName + " - " +narrative);
                transactionInfo.setDate(valueDate);
                transactionInfo.setTransactionId(transactionReference);
                transactionInfo.setBalance(currencyId +" " +balance);
                
                transactionList.add(transactionInfo);
                
                
            }
            
//            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(transactionList);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        }catch (ParseException e) {
            // LOG.error(" "+ e);
            logger.error(" "+ e);
//            return e.getMessage();
        }
        return transactionList;
    }
    
    
    public static String getCustomerIdFromJsonArray(String jsonData){
        String customerId = "";
          try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonData);
            HashMap<String, Object> inputMap = new HashMap<>(jsonObject);
            JSONArray jsonArray = (JSONArray) inputMap.get("body");
            
            
//            JSONArray bodyArray = rootObject.getJSONArray("body");
            if (jsonArray.size() > 0) {
                JSONObject firstItem = (JSONObject) jsonArray.get(0);
                customerId = firstItem.get("customerId").toString();
            } else {
                logger.error("No data found or unexpected JSON structure.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
          return customerId;
    }
    
    public static Map<String,Object> getCustomerInfoFromJsonArray(String jsonData){
        Map<String,Object> resultMap = new HashMap<>();
        
        String postCode = "";
        String address = "";
        String email = "";
        String mobilePhoneNumber = "";
        String title = "";
        String customerName = "";
            
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonData);
            HashMap<String, Object> inputMap = new HashMap<>(jsonObject);
            JSONArray jsonArray = (JSONArray) inputMap.get("body");
            
            if (jsonArray.size() > 0) {
                JSONObject firstItem = (JSONObject) jsonArray.get(0);

                // Extracting properties
                customerName = firstItem.get("customerName").toString();

                JSONArray addressesArray = (JSONArray) firstItem.get("addresses");
                if (addressesArray.size() > 0) {
                    JSONObject addressObject = (JSONObject) addressesArray.get(0);
                    postCode = addressObject.get("postCode").toString();
                    address = addressObject.get("address").toString();
                }

                title = firstItem.get("title").toString();

                JSONArray contactDetailsArray = (JSONArray) firstItem.get("contactDetails");
                if (contactDetailsArray.size() > 0) {
                    JSONObject contactDetails = (JSONObject) contactDetailsArray.get(0);
                    JSONArray emailsArray = (JSONArray) contactDetails.get("emails");
                    if (emailsArray.size() > 0) {
                        JSONObject emailObject = (JSONObject) emailsArray.get(0);
                        email = emailObject.get("email").toString();
                    }

                    JSONArray mobilePhoneNumbersArray = (JSONArray) contactDetails.get("mobilePhoneNumbers");
                    if (mobilePhoneNumbersArray.size() > 0) {
                        JSONObject mobilePhoneNumberObject = (JSONObject) mobilePhoneNumbersArray.get(0);
                        mobilePhoneNumber = mobilePhoneNumberObject.get("mobilePhoneNumber").toString();
                    }
                }

               
            } else {
                logger.error("No data found or unexpected JSON structure.");
            }
        } catch (ParseException | JSONException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
       
        
//        resultMap.put("postCode", postCode);
        resultMap.put("name", title + " " +customerName );
//        resultMap.put("account", customerInfo.getAccount());
        resultMap.put("phone", mobilePhoneNumber);
        resultMap.put("branch", postCode);
        resultMap.put("email", email);
        resultMap.put("address", address);
        
        return resultMap;
        
    }
    
    
    
    
}
