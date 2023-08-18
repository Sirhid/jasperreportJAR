package main.java.org.example;
import com.konylabs.middleware.common.JavaService2;
import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.controller.DataControllerResponse;
import com.konylabs.middleware.dataobject.Result;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperCompileManager;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class JasperReportRIB implements JavaService2 {
    private static final Logger logger = LogManager.getLogger(JasperReportRIB.class);
    //private String logoFileName = "kony_logo.png";


    public Object invoke(String methodID,Object[] inputArray,DataControllerRequest request, DataControllerResponse response ) throws Exception {
        try
        {
            Result result = new Result();
            String fullname = request.getParameter("fullname");
            logger.error("Enter here  fullname " + fullname);
            String iban = request.getParameter("iban");
            logger.error("Enter here  iban " + iban);
            String rib = request.getParameter("rib");
            String currency = request.getParameter("currency");
            logger.error("Enter here  currency " + currency);
            RibData ribb = new RibData();
            ribb.setCurrency(currency);
            ribb.setFullname(fullname);
            ribb.setIban(iban);
            ribb.setRib(rib);
            List<RibData> Riblist = new ArrayList<>();
            Riblist.add(ribb);
            logger.error("Enter here  currency " + Riblist);
            InputStream path = new JasperReportRIB().getClass().getClassLoader().getResourceAsStream("RibTemplate.jrxml");
            logger.error("path response >>>>> " + path.toString());
            try {
                logger.error("Saheed "  );
                System.out.println("path response >>>>> "+path);
                JasperReport jasperReport = JasperCompileManager.compileReport(path);
                logger.error("Enter here  49" + jasperReport);
                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Riblist);
                Map<String, Object> parameters = new HashMap<>();
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource);

                byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
                String base64String = Base64.getEncoder().encodeToString(pdfBytes);

                result.addParam("PdfBase64",base64String);
                result.addOpstatusParam(0);
                result.addHttpStatusCodeParam(200);
                logger.error("Enter here 51 " + result);

                return result;
            } catch (JRException e) {
                logger.error(e.getMessage());
                throw e; // Print the exception stack trace for debugging
            }

          //  return result;

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }
    
    public static void main(String[] args) {
        RibData ribb = new RibData();
            ribb.setCurrency("GMD");
            ribb.setFullname("EMEKA MUSA");
            ribb.setIban("1234566778888");
            ribb.setRib("34252562662672");
            
            List<RibData> Riblist = new ArrayList<>();
            Riblist.add(ribb);
            System.out.println("Riblist response >>>>> "+Riblist);
         InputStream stream = new JasperReportRIB().getClass().getClassLoader().getResourceAsStream("RibTemplate.jrxml");
       System.out.println("stream response >>>>> "+stream);
         try{
        JasperReport jasperReport = JasperCompileManager.compileReport(stream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Riblist);
        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
     String base64String = Base64.getEncoder().encodeToString(pdfBytes);
     System.out.println("base64String response >>>>> "+base64String);
     }catch (JRException ex) {
       // logger.error(ex.getMessage());
        ex.printStackTrace();
    }
    }
}
