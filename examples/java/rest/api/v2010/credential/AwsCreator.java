/*
 * Twilio - Accounts
 * This is the public Twilio REST API.
 *
 * The version of the OpenAPI document: 1.11.0
 * Contact: support@twilio.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.api.v2010.credential;

import com.twilio.base.Creator;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import com.twilio.converter.Converter;
import java.time.ZonedDateTime;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import com.twilio.converter.DateConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import lombok.ToString;


import java.net.URI;

/*
    * Twilio - Accounts
    *
    * This is the public Twilio REST API.
    *
    * API version: 1.11.0
    * Contact: support@twilio.com
*/

// Code generated by OpenAPI Generator (https://openapi-generator.tech); DO NOT EDIT.


public class AwsCreator extends Creator<Aws>{
    private String TestString;
    private Boolean TestBoolean;
    private Integer TestInteger;
    private BigDecimal TestNumber;
    private Float TestNumberFloat;
    private Double TestNumberDouble;
    private BigDecimal TestNumberInt32;
    private Long TestNumberInt64;
    private Map<String, Object> TestObject;
    private ZonedDateTime TestDateTime;
    private LocalDate TestDate;
    private String TestEnum;
    private List<Object> TestObjectArray;
    private Object TestAnyType;

    public AwsCreator(final String TestString) {
        this.TestString = TestString;
    }

    public AwsCreator setTestString(final String TestString){
        this.TestString = TestString;
        return this;
    }
    public AwsCreator setTestBoolean(final Boolean TestBoolean){
        this.TestBoolean = TestBoolean;
        return this;
    }
    public AwsCreator setTestInteger(final Integer TestInteger){
        this.TestInteger = TestInteger;
        return this;
    }
    public AwsCreator setTestNumber(final BigDecimal TestNumber){
        this.TestNumber = TestNumber;
        return this;
    }
    public AwsCreator setTestNumberFloat(final Float TestNumberFloat){
        this.TestNumberFloat = TestNumberFloat;
        return this;
    }
    public AwsCreator setTestNumberDouble(final Double TestNumberDouble){
        this.TestNumberDouble = TestNumberDouble;
        return this;
    }
    public AwsCreator setTestNumberInt32(final BigDecimal TestNumberInt32){
        this.TestNumberInt32 = TestNumberInt32;
        return this;
    }
    public AwsCreator setTestNumberInt64(final Long TestNumberInt64){
        this.TestNumberInt64 = TestNumberInt64;
        return this;
    }
    public AwsCreator setTestObject(final Map<String, Object> TestObject){
        this.TestObject = TestObject;
        return this;
    }
    public AwsCreator setTestDateTime(final ZonedDateTime TestDateTime){
        this.TestDateTime = TestDateTime;
        return this;
    }
    public AwsCreator setTestDate(final LocalDate TestDate){
        this.TestDate = TestDate;
        return this;
    }
    public AwsCreator setTestEnum(final String TestEnum){
        this.TestEnum = TestEnum;
        return this;
    }
    public AwsCreator setTestObjectArray(final List<Object> TestObjectArray){
        this.TestObjectArray = TestObjectArray;
        return this;
    }
    public AwsCreator setTestAnyType(final Object TestAnyType){
        this.TestAnyType = TestAnyType;
        return this;
    }

    @Override
    public Aws create(final TwilioRestClient client){
        String path = "/v1/Credentials/AWS";
        path = path.replace("{"+"TestString"+"}", this.TestString.toString());

        Request request = new Request(
            HttpMethod.POST,
            Domains.API.toString(),
            path
        );
        addPostParams(request);
        Response response = client.request(request);
        if (response == null) {
            throw new ApiConnectionException("Aws creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Aws.fromJson(response.getStream(), client.getObjectMapper());
    }
    private void addPostParams(final Request request) {
        if (TestString != null) {
        request.addPostParam("TestString", TestString.toString());
        }
        if (TestBoolean != null) {
        request.addPostParam("TestBoolean", TestBoolean.toString());
        }
        if (TestInteger != null) {
        request.addPostParam("TestInteger", TestInteger.toString());
        }
        if (TestNumber != null) {
        request.addPostParam("TestNumber", TestNumber.toString());
        }
        if (TestNumberFloat != null) {
        request.addPostParam("TestNumberFloat", TestNumberFloat.toString());
        }
        if (TestNumberDouble != null) {
        request.addPostParam("TestNumberDouble", TestNumberDouble.toString());
        }
        if (TestNumberInt32 != null) {
        request.addPostParam("TestNumberInt32", TestNumberInt32.toString());
        }
        if (TestNumberInt64 != null) {
        request.addPostParam("TestNumberInt64", TestNumberInt64.toString());
        }
        if (TestObject != null) {
        request.addPostParam("TestObject",  Converter.mapToJson(TestObject));
        }
        if (TestDateTime != null) {
        request.addPostParam("TestDateTime", TestDateTime.toString());
        }
        if (TestDate != null) {
        request.addPostParam("TestDate", TestDate.toString());
        }
        if (TestEnum != null) {
        request.addPostParam("TestEnum", TestEnum.toString());
        }
        if (TestObjectArray != null) {
        request.addPostParam("TestObjectArray", TestObjectArray.toString());
        }
        if (TestAnyType != null) {
        request.addPostParam("TestAnyType", TestAnyType.toString());
        }
    }


}
