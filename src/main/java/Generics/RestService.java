package main.java.Generics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestService  {
    private  String finalstring = "";
 

    public  String getAccountstatments(String methodURL, String CompanyID){
        try{

            OkHttpClient client = new OkHttpClient().newBuilder().build();
            Request request = new Request.Builder().url(methodURL).method("GET", null)
                    .addHeader("Accept", "application/json").addHeader("companyid", CompanyID).build();
            Response response = client.newCall(request).execute();
            BufferedReader br;
            if ((200 <= response.code()) && (response.code() <= 299)) {
                if (response.body().byteStream() != null) {
                    br = new BufferedReader(new InputStreamReader(response.body().byteStream()));
                    StringBuilder sb = new StringBuilder();
                    String output;
                    while ((output = br.readLine()) != null) {
                        sb.append(output);
                    }
                    finalstring = sb.toString();
                }
            } else if (response.body().byteStream() != null) {
                br = new BufferedReader(new InputStreamReader(response.body().byteStream()));
                StringBuilder sb = new StringBuilder();
                String output;
                while ((output = br.readLine()) != null) {
                    sb.append(output);
                }
                finalstring = sb.toString();
                throw new RuntimeException(finalstring);
            }
        } catch (Exception e) {
        }
        return finalstring;

    }


    public static void main(String[] args) {

    }

}
