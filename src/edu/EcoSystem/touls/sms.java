/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.touls;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import java.io.IOException;

/**
 *
 * @author zied
 */
public class sms {

    //public static final String API_KEY = "8259bc25";
   //public static final String API_SECRET = "xuatOHRTEry5K0cb";

    public void sendsms(String titre_article) throws IOException, NexmoClientException {
       /*  String tel = "+21650287104";
        AuthMethod auth = new TokenAuthMethod(API_KEY, API_SECRET);
        NexmoClient client = new NexmoClient((NexmoClient.Builder) auth);
        client.getSmsClient().submitMessage(
        new TextMessage("E SHOP", tel, "Nouveau Article: " + titre_article));*/
        

        NexmoClient client = new NexmoClient.Builder()
                .apiKey("8259bc25")
                .apiSecret("xuatOHRTEry5K0cb")
                .build();

        //String messageText = "Hello from Nexmo";
        TextMessage message = new TextMessage("Nexmo", "21650287104", titre_article);
        System.out.println(message.getMessageBody());
        client.getSmsClient().submitMessage(message);


/*   SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
            System.out.println(responseMessage);
        }*/

    }

}
