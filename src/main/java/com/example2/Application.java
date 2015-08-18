package com.example2;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;


@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

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

    public static void main(String args[]) {


      ScheduledPayments_Array SPAClass = new ScheduledPayments_Array();
        ArrayList<ScheduledPayments> SPA  = SPAClass.BuildSchedulePayArray();

        for (ScheduledPayments SP : SPA){
            System.out.println(SP.getAmount());

        }

       SPAClass.SendToServer(new ScheduledPayments( "accName",  "accBSB",  "accAccount",  "customerNumber",  "referenceNumber",  1.0,  "priority",  "1",  "payBefore",  "open"));
//        String JSON = "";
//        try {A
//            JSON = readUrl("http:/ /rm1-smart-payments-api-dev.mybluemix.net/v1/scheduledPayments/a");
//        } catch (Exception e) {a
//            e.printStackTrace();
//        }
//
//        JSONArray JA = new JSONArray(JSON);
//        int count = JA.length(); // get totalCount of all jsonObjects
//        for (int i = 0; i < count; i++) {   // iterate through jsonArray
//            JSONObject jsonObject = JA.getJSONObject(i);  // get jsonObject @ i position
//            System.out.println("jsonObject " + i + ": " + jsonObject);
//            JSONObject JO = new JSONObject(jsonObject.get("value").toString());
//            System.out.println(JO.get("referenceNumber").toString());
//        }
    }

}


