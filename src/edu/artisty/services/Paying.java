/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.services;


import com.mastercard.api.core.ApiConfig;
import com.mastercard.api.core.exception.ApiException;
import com.mastercard.api.core.model.RequestMap;
import com.mastercard.api.core.security.oauth.OAuthAuthentication;
import com.mastercard.api.billpayvalidator.AccountValidation;
import com.mastercard.api.billpayvalidator.model.ValidationRequest;
import com.mastercard.api.billpayvalidator.model.ValidationResponse;



public class Paying {

       public ValidationResponse validateAccount(String accountNumber) throws ApiException {
    // Set up your API credentials
    String consumerKey = "your_consumer_key";
    String privateKey = "your_private_key";
    String environment = "sandbox"; // "production" for production

    ApiConfig.setAuthentication(new OAuthAuthentication(consumerKey, privateKey));
    ApiConfig.setEnvironment(environment);

    // Set up the validation request
    ValidationRequest validationRequest = new ValidationRequest();
    validationRequest.setAccountNumber(accountNumber);

    RequestMap request = new RequestMap();
    request.set("ValidationRequest", validationRequest);

    // Send the validation request and retrieve the validation response
    AccountValidation accountValidation = new AccountValidation();
    ValidationResponse validationResponse = accountValidation.create(request);
    return validationResponse;
}

        }
    





