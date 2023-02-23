/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.entities;
import com.mastercard.gateway.client.ApiClient;
import com.mastercard.gateway.client.ApiException;
import com.mastercard.gateway.client.Configuration;
import com.mastercard.gateway.client.api.PaymentSessionsApi;
import com.mastercard.gateway.client.model.*;

import java.math.BigDecimal;
/**
 *
 * @author user
 */
public class Paying {


    public static void main(String[] args) throws ApiException {

        ApiClient apiClient = Configuration.getDefaultApiClient();
        apiClient.setApiKey("YOUR_API_KEY");
        apiClient.setApiSecret("YOUR_API_SECRET");

        PaymentSessionsApi sessionsApi = new PaymentSessionsApi(apiClient);

        CreateSessionResponse session = sessionsApi.createSession(new CreateSessionRequest());
        String sessionId = session.getSession().getId();

        // create a payment request
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setSourceOfFunds(new SourceOfFunds()
                .setType(SourceOfFunds.Type.CARD)
                .setProvided(new Provided()
                        .setCard(new Card()
                                .setNumber("5555555555554444")
                                .setSecurityCode("123")
                                .setExpiry(new Expiry()
                                        .setMonth(12)
                                        .setYear(2023)))));

        paymentRequest.setTransaction(new Transaction()
                .setAmount(new BigDecimal("100.00"))
                .setCurrency("USD"));

        paymentRequest.setSession(new Session()
                .setId(sessionId));

        // send the payment request and handle the response
        PaymentResponse paymentResponse = sessionsApi.payment(paymentRequest);

        if (paymentResponse.getStatus() == PaymentResponse.Status.COMPLETED) {
            System.out.println("Payment was successful.");
        } else {
            System.out.println("Payment failed.");
        }
    }
}

}
