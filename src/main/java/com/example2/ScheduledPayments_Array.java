package com.example2;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class ScheduledPayments_Array {
    public void MakePayment(ScheduledPayments SP) {
        System.out.println("Made Payment");
    }

    public void ChecktoMakePayment(ArrayList<ScheduledPayments> SPA, Double AccBal, String Customer, String Account) {

        // Find highest priority
        Integer HighPri = 99;
        for (ScheduledPayments SP : SPA) {
            if (!SP.getPaystatus().contains("paid")) {
                if (Integer.parseInt(SP.getPayPriority()) < HighPri) {
                    HighPri = Integer.parseInt(SP.getPayPriority());

                }
            }

        }


        for (ScheduledPayments SP : SPA) {

            if ((!SP.getPaystatus().contains("paid")) && Integer.parseInt(SP.getPayPriority()) == HighPri) {
                if (SP.getAmount() <= AccBal) {
                    ScheduledPayments_Array SPAClass = new ScheduledPayments_Array();
                    AccBal = AccBal - SP.getAmount();
                    MakePayment(SP);
                    SPAClass.UpdateStatusofSchedule(SP, "paid");
                    SPAClass.PutAccountBalance(AccBal, Customer, Account);


                }

            }


        }


    }

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

    private String ScheduledPaymetnsGetURL = "http://rm3-smart-payments-api-dev.mybluemix.net/v1/scheduledPayments";
    private String BlueMixBaseURL = "http://rm3-smart-payments-api-dev.mybluemix.net";
    private ArrayList<ScheduledPayments> SPArray = new ArrayList<ScheduledPayments>();

    public void sendSMS(String Number, String Message){
        JSONObject returnedObject = new JSONObject();
        JSONObject payToAccountObject = new JSONObject();

        returnedObject.put("mobileNumber", Number);
        returnedObject.put("smsText", Message);
        System.out.println(returnedObject.toString());
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost( BlueMixBaseURL +"/v1/sendSMS");
            System.out.println(returnedObject.toString());
            request.setEntity(new StringEntity(returnedObject.toString(), ContentType.create("APPLICATION_JSON")));

            request.addHeader("content-type", "application/json");
            request.addHeader("Accept", "application/json");
            HttpResponse response = httpClient.execute(request);
            System.out.println(response.toString());
            // handle response here...
        } catch (Exception ex) {
            // handle exception here
        } finally {
            httpClient.getConnectionManager().shutdown();
        }




    }


    public ArrayList<ScheduledPayments> getSPArray() {
        return SPArray;
    }

    public void SendToServer(ScheduledPayments SP) {
        JSONObject returnedObject = new JSONObject();
        JSONObject payToAccountObject = new JSONObject();
        payToAccountObject.put("name", SP.getAccName());
        payToAccountObject.put("bsb", SP.getAccBSB());
        payToAccountObject.put("account", SP.getAccAccount());
        returnedObject.put("payToAccount", payToAccountObject);
        returnedObject.put("payPriority", SP.getPayPriority());
        returnedObject.put("customerNumber", SP.getCustomerNumber());
        returnedObject.put("amount", SP.getAmount());
        returnedObject.put("payRule", SP.getPayRule());
        returnedObject.put("payBefore", SP.getPayBefore());
        returnedObject.put("payStatus", SP.getPaystatus());
        returnedObject.put("referenceNumber", SP.getReferenceNumber());
        System.out.println(returnedObject.toString());
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost(ScheduledPaymetnsGetURL);
            String JSONString = "{  \"payToAccount\": {\"name\": \"string\", \"bsb\": \"string\", \"account\": \"string\"},\"customerNumber\":\"string\",\"referenceNumber\":\"string\",\"amount\":0,\"payRule\": \"priority\",\"payPriority\": \"1\",\"payBefore\": \"string\",\"payStatus\": \"open\"}";
            System.out.println(JSONString);
            request.setEntity(new StringEntity(returnedObject.toString(), ContentType.create("APPLICATION_JSON")));

            request.addHeader("content-type", "application/json");
            request.addHeader("Accept", "application/json");
            HttpResponse response = httpClient.execute(request);
            System.out.println(response.toString());
            // handle response here...
        } catch (Exception ex) {
            // handle exception here
        } finally {
            httpClient.getConnectionManager().shutdown();
        }


    }

    public Double GetAccountBalance(String Customer, String Account) {


        String JSON = "";
        String Bal = "0";
        try {
            JSON = readUrl(BlueMixBaseURL +"/v1/accountBalance/"+ Customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray JA = new JSONArray(JSON);
        int count = JA.length(); // get totalCount of all jsonObjects
        for (int i = 0; i < count; i++) {   // iterate through jsonArray
            JSONObject jsonObject = JA.getJSONObject(i);  // get jsonObject @ i position
            JSONObject JO = new JSONObject(jsonObject.get("value").toString());
            JSONObject JOA = new JSONObject(JO.get("account").toString());
            System.out.println(JOA.get("account").toString());
            if (JOA.get("account").toString().contains(Account) ){
                Bal = JO.get("balance").toString();
            }
            System.out.println(JO.toString());


        }

        return Double.parseDouble(Bal);


    }

    public void PutAccountBalance(Double TheBalance, String Customer, String Account){

        JSONObject returnedObject = new JSONObject();
        JSONObject payToAccountObject = new JSONObject();
        payToAccountObject.put("name", "TransAccount");
        payToAccountObject.put("bsb", "032900");
        payToAccountObject.put("account", Account);
        returnedObject.put("account", payToAccountObject);
        returnedObject.put("customerNumber", Customer);
        returnedObject.put("balance", TheBalance);
        System.out.println(returnedObject.toString());
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPut request = new HttpPut(BlueMixBaseURL +"/v1/accountBalance?customerNumber=" + Customer + "&accountId=" + Account);
           // String JSONString = "{  \"payToAccount\": {\"name\": \"string\", \"bsb\": \"string\", \"account\": \"string\"},\"customerNumber\":\"string\",\"referenceNumber\":\"string\",\"amount\":0,\"payRule\": \"priority\",\"payPriority\": \"1\",\"payBefore\": \"string\",\"payStatus\": \"open\"}";
            System.out.println(returnedObject.toString());
            request.setEntity(new StringEntity(returnedObject.toString(), ContentType.create("APPLICATION_JSON")));

            request.addHeader("content-type", "application/json");
            request.addHeader("Accept", "application/json");
            HttpResponse response = httpClient.execute(request);
            System.out.println(response.toString());
            // handle response here...
        } catch (Exception ex) {
            // handle exception here
        } finally {
            httpClient.getConnectionManager().shutdown();
        }





    }

    public void DeleteSched(String CustNumber, String RefNumber) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            String DelURL = ScheduledPaymetnsGetURL + "?customerNumber=" + CustNumber + "&referenceNumber=" + RefNumber;
            HttpDelete request = new HttpDelete(DelURL);
            request.addHeader("Accept", "application/json");
            HttpResponse response = httpClient.execute(request);
            System.out.println(response.toString());
            // handle response here...
        } catch (Exception ex) {
            // handle exception here
        } finally {
            httpClient.getConnectionManager().shutdown();
        }

    }


    public void UpdateStatusofSchedule(ScheduledPayments SP, String paystatus) {
        JSONObject returnedObject = new JSONObject();

//        returnedObject.put("payStatus", paystatus);
//        System.out.println(returnedObject.toString());
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        try {
//            String DelURL = ScheduledPaymetnsGetURL + "?customerNumber=" + SP.getCustomerNumber() + "&referenceNumber=" + SP.getReferenceNumber();
//            HttpDelete request = new HttpDelete(DelURL);
//
//            System.out.println(DelURL);
//
//            request.addHeader("Accept", "application/json");
//            HttpResponse response = httpClient.execute(request);
//            System.out.println(response.toString());
//            // handle response here...
//        }catch (Exception ex) {
//            // handle exception here
//        } finally {
//            httpClient.getConnectionManager().shutdown();
//        }
        DeleteSched(SP.getCustomerNumber(), SP.getReferenceNumber());
        SP.setPaystatus("paid");
        SendToServer(SP);


    }


    private ArrayList<ScheduledPayments> _BuildSchedulePayArray(String CustomerNumber) {
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
            ScheduledPayments SP = new ScheduledPayments();
            SP.setID(jsonObject.get("id").toString());
            SP.setKey(jsonObject.get("key").toString());
            JSONObject JO = new JSONObject(jsonObject.get("value").toString());
            SP.setCustomerNumber(JO.get("customerNumber").toString());
            try {
                SP.setReferenceNumber(JO.get("referenceNumber").toString());
            } catch (Exception e) {
            }
            SP.setAmount(Double.parseDouble(JO.get("amount").toString()));
            SP.setPayRule(JO.get("payRule").toString());
            SP.setPayPriority(JO.get("payPriority").toString());
            try {
                SP.setPayBefore(JO.get("payBefore").toString());
            }catch(Exception e){}

            SP.setPaystatus(JO.get("payStatus").toString());
            try {
                JSONObject JOAccount = new JSONObject(JO.get("payToAccount").toString());
                SP.setAccName(JOAccount.get("name").toString());
                SP.setAccBSB(JOAccount.get("bsb").toString());
                SP.setAccAccount(JOAccount.get("account").toString());
            } catch (Exception c) {
            }
            SPArray.add(SP);
        }


        return SPArray;
    }

    public ArrayList<ScheduledPayments> BuildSchedulePayArray(String CustomerNumber) {

        return (_BuildSchedulePayArray(CustomerNumber));
    }

    public ArrayList<ScheduledPayments> BuildSchedulePayArray() {

        return (_BuildSchedulePayArray(""));

    }
}
