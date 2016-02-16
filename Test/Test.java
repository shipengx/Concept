import java.net.*; 
import java.io.*; 
import java.util.*;

public class Test { 

    public static void main(String[] args) throws Exception { 
        sendGet();
    }


    // HTTP GET request
    private static void sendGet() throws Exception {

        String url = "http://stream.meetup.com/2/rsvps";
        URLConnection urlConnection = null;
        try {
            urlConnection = new URL(url).openConnection();
        } catch (IOException ioe) {
            System.err.println("openConnection = new URL(url).openConnection() " + ioe.getMessage());
            //  ioe.printStackTrace();
        }
        urlConnection.addRequestProperty("User-Agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/7046A194A");


        BufferedReader in = new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();


    }
    


}//end class

