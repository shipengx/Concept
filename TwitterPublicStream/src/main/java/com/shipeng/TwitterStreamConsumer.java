package com.shipeng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;

public class TwitterStreamConsumer extends Thread {

    private static final String STREAM_URI = "https://userstream.twitter.com/1.1/user.json";
    //private static final String STREAM_URI = "https://stream.twitter.com/1.1/statuses/filter.json";

    public void run(){
        try{
            System.out.println("Starting Twitter public stream consumer thread.");

            // Enter your consumer key and secret below
            OAuthService service = new ServiceBuilder()
                .provider(TwitterApi.class)
                .apiKey("TkASrqKdiPLB4QZZj11bVQftq")
                .apiSecret("CH4YdJudWsDWPHpke94w6tSJF6IhECMm1OaJ0HVgkTETOzuphf")
                .build();

            // Set your access token
            Token accessToken = new Token("4048871658-l2Of5lEgSdZXOHFXf3Pwz2VnJBJSDdYbnEWmnOM", "8gTsAlr0jsDBxNuIvevodcALJz4FLODpcJczLqDf8ahkB");

            // Let's generate the request
            System.out.println("Connecting to Twitter Public Stream");
            OAuthRequest request = new OAuthRequest(Verb.POST, STREAM_URI);
            request.addHeader("version", "HTTP/1.1");
            request.addHeader("host", "stream.twitter.com");
            request.setConnectionKeepAlive(true);
            request.addHeader("user-agent", "Twitter Stream Reader");
            request.addBodyParameter("track", "java,heroku,twitter"); // Set keywords you'd like to track here
            service.signRequest(accessToken, request);
            Response response = request.send();

            // Create a reader to read Twitter's stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }

    }//end run()

}//end class


