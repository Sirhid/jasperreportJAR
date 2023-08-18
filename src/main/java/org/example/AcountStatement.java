/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.org.example;

import com.konylabs.middleware.common.JavaService2;
import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.controller.DataControllerResponse;
import com.konylabs.middleware.dataobject.Result;
import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.kony.kmsinvoke.util.HelperMethods;
import main.java.Generics.RestService;
import main.java.entity.TransactionInfo;
import main.java.services.AccountStatementService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 *
 * @author admin
 */
public class AcountStatement implements JavaService2 {
   
    
   private static final Logger logger = LogManager.getLogger(AcountStatement.class);
   public Object invoke(String methodID,Object[] inputArray,DataControllerRequest request, DataControllerResponse response ) throws Exception {
       RestService restConnector = new RestService();
       String methodUrl = "";
       String responsebody = "";
        List<TransactionInfo> transactionInfoList;
       Map<String, Object> parameters = new HashMap<>();
       Result result = new Result();
       logger.error("legalEntityId " + request.getParameter("legalEntityId"));
       if(request.getParameter("legalEntityId").equalsIgnoreCase("BF2260001")){
           String urlBf = HelperMethods.getConfigProperty("T24_TRANSACTIONS_HOST_URL_BF");
           methodUrl = urlBf;
           logger.error("methodUrl " + methodUrl);
       }else if (request.getParameter("legalEntityId").equalsIgnoreCase("SL6940001"))
       {
           String urlSl = HelperMethods.getConfigProperty("T24_TRANSACTIONS_HOST_URL_SL");
           methodUrl = urlSl; 
           logger.error("methodUrl " + methodUrl);
       }else if (request.getParameter("legalEntityId").equalsIgnoreCase("GM2700001"))
       {
          // String urlGm = HelperMethods.getConfigProperty("http://_$_T24_TRANSACTIONS_HOST_URL_GM_$_");
           String urlGm = "http://10.1.0.6:8186/infinity-provider-container/api";
           
           methodUrl = urlGm; 
           logger.error("methodUrl " + methodUrl);
           
       }
       else if (request.getParameter("legalEntityId").equalsIgnoreCase("GN3240001"))
       {
           String urlGn = HelperMethods.getConfigProperty("T24_TRANSACTIONS_HOST_URL_VB");
           methodUrl = urlGn; 
           logger.error("methodUrl " + methodUrl);
       }
       else
       {
           String urlGui = HelperMethods.getConfigProperty("T24_TRANSACTIONS_HOST_URL_VBG");
           methodUrl = urlGui; 
           logger.error("methodUrl " + methodUrl);
       }
       String resourcePath =  "/v2.0.0/holdings/transactions";
       
        try    {
    
//            Result result = new Result();
           String accountID = request.getParameter("accountNumber");
           if(!accountID.isEmpty() && !methodUrl.isEmpty()){
               logger.error("accountID " + accountID);
             parameters = getCustomerInfo(methodUrl,accountID,request.getParameter("legalEntityId").toString());
         logger.error("parameters " + parameters.toString());
           }
           String searchEndDate = request.getParameter("searchEndDate");
           logger.error("searchEndDate " + searchEndDate);
           String searchStartDate = request.getParameter("searchStartDate");
           logger.error("searchStartDate " + searchStartDate);
           String searchTransactionType =request.getParameter("searchTransactionType");
           logger.error("searchTransactionType " + searchTransactionType);
           String limits = "";
           String page_start = "";
           String minimumAmount = "";
           String maximumAmount = "";
           String legalEntityId = request.getParameter("legalEntityId");
           logger.error("legalEntityId " + legalEntityId);
                    
            if (!methodUrl.isEmpty()){     
            String finalMethodUrl = methodUrl.concat(resourcePath).concat("?listType=").concat("COMPLETED")
                    .concat("&accountId=").concat(accountID).concat("&transactionCode=").concat(searchTransactionType)
                    .concat("&dateFrom=").concat(searchStartDate)
                    .concat("&transactionCode=").concat(searchTransactionType)
                    .concat("&page_size=").concat(limits)
                    .concat("&page_start=").concat(page_start)
                    .concat("&dateTo=").concat(searchEndDate)
                    .concat("&minimumAmount=").concat(minimumAmount)
            .concat("&maximumAmount=").concat(maximumAmount);
               logger.error("finalMethodUrl " + finalMethodUrl);
            responsebody = restConnector.getAccountstatments(finalMethodUrl, legalEntityId);
            logger.error("responsebody " + responsebody);
              
            }
 
    try {
         transactionInfoList = AccountStatementService.extractJsonDataForTransactionInfo(responsebody);
    logger.error("transactionInfoList " + transactionInfoList.toString());
        String jasperPath = "";
        if(request.getParameter("legalEntityId").equalsIgnoreCase("GM2700001") || request.getParameter("legalEntityId").equalsIgnoreCase("SL6940001")){
            jasperPath = "acctStatmentFrench.jrxml";
        }else{
            jasperPath = "acctStatmentFrench.jrxml";
        }
        InputStream stream = new AcountStatement().getClass().getClassLoader().getResourceAsStream(jasperPath);
         logger.error("stream " + stream);

        JasperReport jasperReport = JasperCompileManager.compileReport(stream);
        
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(transactionInfoList);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        
        byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
                String base64String = Base64.getEncoder().encodeToString(pdfBytes);
                         logger.error("base64String " + base64String);

        result.addParam("PdfBase64",base64String);
        result.addOpstatusParam(0);
        result.addHttpStatusCodeParam(200);
        logger.error("Enter here 51 " + result); 
    
    }catch (JRException ex) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
    }
     
     }catch(Exception ex){
        logger.error("ex"+ex.getMessage());
        ex.printStackTrace();
    }
     
        return result;
}
   
   private static Map<String,Object> getCustomerInfo(String methodUrl, String accountId, String legalEntityId){
        RestService restConnector = new RestService();
        String accountsResourcePath = "/v3.0.0/holdings/accounts";
        String finalAccountsMethodUrl = methodUrl.concat(accountsResourcePath).concat("?").concat("accountId=").concat(accountId);
        String customerIdJsonData = restConnector.getAccountstatments(finalAccountsMethodUrl, legalEntityId);
        logger.error("customerIdJsonData " + customerIdJsonData);
        
        String customerId = AccountStatementService.getCustomerIdFromJsonArray(customerIdJsonData);
         logger.error("customerId " + customerId);
        String customerResourcePath = "/v3.0.0/party/customers";
        String finalCustomerMethodUrl = methodUrl.concat(customerResourcePath).concat("?").concat("customerId=").concat(customerId);
        String jsonData = restConnector.getAccountstatments(finalCustomerMethodUrl, legalEntityId);
         logger.error("jsonData " + jsonData);
        Map<String,Object> inputMap = AccountStatementService.getCustomerInfoFromJsonArray(jsonData);
        inputMap.put("account", accountId);
           logger.error("inputMap " + inputMap.toString());
        return inputMap;
   }

   public static void main(String[] args) throws IOException {
       RestService restConnector = new RestService();
      ;
       //Result result = new Result();
       String responsebody = "";
       String legalEntityId = "GM2700001";
       String accountID = "404016430980548";
       Map<String, Object> parameters = new HashMap<>();
       
       String methodUrl = "http://10.1.0.6:8186/infinity-provider-container/api/v2.0.0/holdings/transactions"
               + "?listType=COMPLETED"
               + "&accountId=404016430980548&transactionCode="
               + "&dateFrom=20230108&page_size=&page_start="
               + "&dateTo=20230301&minimumAmount=&maximumAmount=";
       
       responsebody = restConnector.getAccountstatments(methodUrl, legalEntityId);
       List<TransactionInfo> transactionInfoList = AccountStatementService.extractJsonDataForTransactionInfo(responsebody);
       System.out.println("parameters response >>>>> "+transactionInfoList.toString());
       
         InputStream stream = new AcountStatement().getClass().getClassLoader().getResourceAsStream("acctStatmentFrench.jrxml");
   
//         AcountStatement instance 
//            = new AcountStatement();
//         InputStream is = instance.getFileAsIOStream("acctStatmentFrench.jrxml");
//        instance.printFileContent(is);
//    is = instance.getFileAsIOStream("files/test.txt");
//        instance.printFileContent(is);
        
        
         System.out.println("stream response >>>>> "+stream);
       //  logger.error("stream >>>>" +stream);
try{
        JasperReport jasperReport = JasperCompileManager.compileReport(stream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(transactionInfoList);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
     String base64String = Base64.getEncoder().encodeToString(pdfBytes);
     System.out.println("base64String response >>>>> "+base64String);
//        result.addParam("PdfBase64",base64String);
//        result.addOpstatusParam(0);
//        result.addHttpStatusCodeParam(200);
//      
//    System.out.println("base64String response >>>>> "+result.toString());
     }catch (JRException ex) {
       // logger.error(ex.getMessage());
        ex.printStackTrace();
    } 
      
//     String methodUrl=  "http://10.1.0.6:8186/infinity-provider-container/api";
//      parameters = getCustomerInfo(methodUrl,accountID,legalEntityId);
//       System.out.println("parameters response >>>>> "+parameters.toString());
//       
     }
}
