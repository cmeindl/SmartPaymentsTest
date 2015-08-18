package com.example2;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class ScheduledPayments_Array {

    public static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }

    }

    private String ScheduledPaymetnsGetURL = "http://rm1-smart-payments-api-dev.mybluemix.net/v1/scheduledPayments/";
    private ArrayList<ScheduledPayments> SPArray = new ArrayList<ScheduledPayments>();



    public ArrayList<ScheduledPayments> getSPArray() {
        return SPArray;
    }

public void SendToServer(ScheduledPayments SP){
    JSONObject returnedObject= new JSONObject();
    JSONObject payToAccountObject = new JSONObject();

    payToAccountObject.put("name",SP.getAccName());
    payToAccountObject.put("bsb",SP.getAccBSB());
    payToAccountObject.put("account",SP.getAccAccount());
   returnedObject.put("payToAccount",payToAccountObject);
    returnedObject.put("payPriority",SP.getPayPriority());
    returnedObject.put("customerNumber",SP.getCustomerNumber());
//    returnedObject.put("referenceNumber",SP.getReferenceNumber());
returnedObject.put("amount",SP.getAmount());
    returnedObject.put("payRule",SP.getPayRule());
    returnedObject.put("payBefore",SP.getPayBefore());
    returnedObject.put("payStatus", SP.getPaystatus());


    System.out.println(returnedObject.toString());

    HttpClient httpClient = HttpClientBuilder.create().build();

    try {
        HttpPost request = new HttpPost(ScheduledPaymetnsGetURL);

     //  StringEntity params =new StringEntity(returnedObject.toString());
        String JSONString  = "{  \"payToAccount\": {\"name\": \"string\", \"bsb\": \"string\", \"account\": \"string\"},\"customerNumber\":\"string\",\"referenceNumber\":\"string\",\"amount\":0,\"payRule\": \"priority\",\"payPriority\": \"1\",\"payBefore\": \"string\",\"payStatus\": \"open\"}";
       // String JSONString  = "{  \"customerNumber\":\"string\",\"referenceNumber\":\"string\",\"amount\":0,\"payRule\": \"priority\",\"payPriority\": \"1\",\"payBefore\": \"string\",\"payStatus\": \"open\"}";

        System.out.println(JSONString);
       // System.out.println(returnedObject.toString());

      // request.setEntity(new StringEntity(returnedObject.toString(), "UTF8"));
       // request.setEntity(new StringEntity(returnedObject.toString(), ContentType.create("APPLICATION_JSON")));
      request.setEntity(new StringEntity(returnedObject.toString(), ContentType.create("APPLICATION_JSON")));
      //  request.setEntity(new StringEntity(JSONString, ContentType.create("APPLICATION_JSON")));


//        request.setEntity(new StringEntity(returnedObject.toString(), ContentType.create("APPLICATION_JSON")));

        request.addHeader("content-type", "application/json");
        request.addHeader("Accept", "application/json");
     //   request.setEntity(params);
        HttpResponse response = httpClient.execute(request);
        System.out.println(response.toString());
        // handle response here...
    }catch (Exception ex) {
        // handle exception here
    } finally {
        httpClient.getConnectionManager().shutdown();

    }



}




    private ArrayList<ScheduledPayments> _BuildSchedulePayArray(String CustomerNumber){
        String JSON = "";
        try {
            JSON = readUrl(ScheduledPaymetnsGetURL + CustomerNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray JA = new JSONArray(JSON);
        int count = JA.length(); // get totalCount of all jsonObjects
        for (int i = 0; i < count; i++) {   // iterate through jsonArray
            JSONObject jsonObject = JA.getJSONObject(i);  // get jsonObject @ i position
            ScheduledPayments SP = new  ScheduledPayments();
            SP.setID(jsonObject.get("id").toString());
            SP.setKey(jsonObject.get("key").toString());
            JSONObject JO = new JSONObject(jsonObject.get("value").toString());
            SP.setCustomerNumber(JO.get("customerNumber").toString());
            try {
                SP.setReferenceNumber(JO.get("referenceNumber").toString());
            } catch(Exception e){}
            SP.setAmount(Double.parseDouble(JO.get("amount").toString()));
            SP.setPayRule(JO.get("payRule").toString());
            SP.setPayPriority(JO.get("payPriority").toString());
            SP.setPayBefore(JO.get("payBefore").toString());
            SP.setPaystatus(JO.get("payStatus").toString());
try {
    JSONObject JOAccount = new JSONObject(JO.get("payToAccount").toString());
    SP.setAccName(JOAccount.get("name").toString());
    SP.setAccBSB(JOAccount.get("bsb").toString());
    SP.setAccAccount(JOAccount.get("account").toString());
}catch(Exception c){}
            SPArray.add(SP);
        }


        return SPArray;
    }

    public ArrayList<ScheduledPayments> BuildSchedulePayArray(String CustomerNumber){

        return (_BuildSchedulePayArray(CustomerNumber));
    }

    public ArrayList<ScheduledPayments> BuildSchedulePayArray(){

        return (_BuildSchedulePayArray(""));

    }
}
