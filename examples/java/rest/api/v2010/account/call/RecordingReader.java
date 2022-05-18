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

package com.twilio.rest.api.v2010.account.call;

import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import com.twilio.base.Page;
import java.time.ZonedDateTime;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import lombok.ToString;



/*
    * Twilio - Accounts
    *
    * This is the public Twilio REST API.
    *
    * API version: 1.11.0
    * Contact: support@twilio.com
*/

// Code generated by OpenAPI Generator (https://openapi-generator.tech); DO NOT EDIT.


public class RecordingReader extends Reader<Recording> {
    private String CallSid;
    private String AccountSid;
    private ZonedDateTime DateCreated;
    private LocalDate DateTest;
    private ZonedDateTime DateCreatedBefore;
    private ZonedDateTime DateCreatedAfter;
    private Integer PageSize;

    public RecordingReader(final String CallSid){
        this.CallSid = CallSid;
    }
    public RecordingReader(final String AccountSid, final String CallSid){
        this.AccountSid = AccountSid;
        this.CallSid = CallSid;
    }

    public RecordingReader setDateCreated(final ZonedDateTime DateCreated){
        this.DateCreated = DateCreated;
        return this;
    }
    public RecordingReader setDateTest(final LocalDate DateTest){
        this.DateTest = DateTest;
        return this;
    }
    public RecordingReader setDateCreatedBefore(final ZonedDateTime DateCreatedBefore){
        this.DateCreatedBefore = DateCreatedBefore;
        return this;
    }
    public RecordingReader setDateCreatedAfter(final ZonedDateTime DateCreatedAfter){
        this.DateCreatedAfter = DateCreatedAfter;
        return this;
    }
    public RecordingReader setPageSize(final Integer PageSize){
        this.PageSize = PageSize;
        return this;
    }

    private void addQueryParams(final Request request) {
        if (DateCreated != null) {
            request.addQueryParam("DateCreated", DateCreated.format(DateTimeFormatter.ofPattern(Request.QUERY_STRING_DATE_TIME_FORMAT)));
        }
        else if (DateCreatedAfter != null || DateCreatedBefore != null) {
            request.addQueryDateTimeRange("DateCreated", DateCreatedAfter, DateCreatedBefore);
        }

        if (DateTest != null) {
            request.addQueryParam("DateTest", DateConverter.dateStringFromLocalDate(DateTest));
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }

    }

    @Override
    public ResourceSet<Recording> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    public Page<Recording> firstPage(final TwilioRestClient client) {
        String path = "/2010-04-01/Accounts/{AccountSid}/Calls/{CallSid}/Recordings.json";
        this.AccountSid = this.AccountSid == null ? client.getAccountSid() : this.AccountSid;
        path = path.replace("{"+"AccountSid"+"}", this.AccountSid.toString());
        path = path.replace("{"+"CallSid"+"}", this.CallSid.toString());

        Request request = new Request(
            HttpMethod.GET,
            Domains.API.toString(),
            path
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    private Page<Recording> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Recording read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Page.fromJson(
            "recordings",
            response.getContent(),
            Recording.class,
            client.getObjectMapper()
        );
    }

    @Override
    public Page<Recording> previousPage(final Page<Recording> page, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.API.toString())
        );
        return pageForRequest(client, request);
    }


    @Override
    public Page<Recording> nextPage(final Page<Recording> page, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.API.toString())
        );
        return pageForRequest(client, request);
    }

    @Override
    public Page<Recording> getPage(final String targetUrl, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            targetUrl
        );

        return pageForRequest(client, request);
    }
}

