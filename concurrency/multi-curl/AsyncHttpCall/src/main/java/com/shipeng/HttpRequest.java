package com.shipeng;

import java.util.*;
import java.util.concurrent.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpRequest implements Callable {

    public String url;
    public int thread_number;
    public HttpRequest(String url, int thread_number) {
        this.url = url;
        this.thread_number = thread_number;
    }

    public String call() throws Exception{
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        String temp = " thread number: " + thread_number + ", response: ";
        response.append(temp);
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }//end call()

}//end class HttpRequest




