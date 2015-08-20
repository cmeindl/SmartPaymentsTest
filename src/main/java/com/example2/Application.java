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

//
//        for (ScheduledPayments SP : SPA){
//            System.out.println(SP.getAmount());
//
//        }

     //  SPAClass.SendToServer(new ScheduledPayments( "accName",  "accBSB",  "accAccount",  "customerNumber",  "referenceNumber",  1.0,  "priority",  "1",  "payBefore",  "open"));
        //SPAClass.SendToServer(new ScheduledPayments( "Super Account",  "033900",  "90210",  "AwesomeMelon",  "Melon1",  300000.0,  "priority",  "1",  "payBefore",  "open"));
        //ScheduledPayments(String accname, String accbsb, String accaccount,String referenceNumber, Double amount, String payRule, String payPriority, String payBefore, String paystatus)

          // SPAClass.SendToServer(new ScheduledPayments("Wages","033900","90000","4_The_Wage_Slaves",3000000.00, "priority","1","2015-08-20","open"));
           // SPAClass.SendToServer(new ScheduledPayments("Aus Gov","013900","922000","Customs",9000.00, "standard","2","2015-08-20","open"));
         //   SPAClass.SendToServer(new ScheduledPayments("Wages","033900","90000","4_The_Wage_Slaves-SMALL",100.00, "priority","1","2015-08-20","open"));
        //    ScheduledPayments SP = new ScheduledPayments("Murder Inc","04300","9","Make_sure_We_Win",3000000.00, "standard","2","2015-08-20","open");
       //SPAClass.DeleteSched("AwesomeMelon","Customs");
        //SPAClass.SendToServer(SP);

      // ArrayList<ScheduledPayments> SPA  = SPAClass.BuildSchedulePayArray();

       // System.out.println( SPAClass.GetAccountBalance());
      // SPAClass.ChecktoMakePayment(SPA, SPAClass.GetAccountBalance("AwesomeMelon","90210"),"AwesomeMelon","90210");
       // SPAClass.ChecktoMakePayment(SPA, SPAClass.GetAccountBalance());
       // SPAClass.UpdateStatusofSchedule(SP,"paid");
        SPAClass.PutAccountBalance(40000.00,"AwesomeMelon","90210");
       //System.out.println( SPAClass.GetAccountBalance("AwesomeMelon","90210"));
        //SPAClass.sendSMS("+61407027559", "Some Random Text");
       // SPAClass.sendSMS("+61403367829", "Some Random Text");







//
//String JSON = "";
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


