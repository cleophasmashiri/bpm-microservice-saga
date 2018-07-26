package com.demo.facades;

import org.camunda.bpm.engine.impl.util.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cleophas on 2018/07/26.
 */
public class BookingFacade {

    public void makeBooking(String status, String email, String correlationId, String bookingType) throws Exception {

        System.out.println("correlationId: " + correlationId.toString());

        String port;
        if ("flight".equals(bookingType))
            port = "8000";
        else if ("hotel".equals(bookingType))
            port = "8001";
        else if ("car".equals(bookingType))
            port = "8002";
        else
            throw new Exception("Unknown Booking Type");

        String url = "http://localhost:" + port + "/api/" + bookingType;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Setting basic post request
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type","application/json");

        //Set the correlation stuff
        JSONObject camundajson = new JSONObject();
        camundajson.put("email", email);
        camundajson.put("status", status);
        camundajson.put("correlationId", correlationId);

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(camundajson.toString());
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("nSending 'POST' request to URL : " + url);
        String postJsonData = "{\"email:\" + email + \", status:\" + status}";
        System.out.println("Post Data : " + postJsonData);
        System.out.println("Response Code : " + responseCode);
    }
}
