package stringsAssg1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Part4 {
    static void checkForYoutube() throws IOException {
        URL CseArticles = new URL("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(CseArticles.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            String temp = inputLine.toLowerCase();
            if(temp.contains("www.youtube.com")) {
                int posOfYoutube = temp.indexOf("www.youtube.com");
                String url = inputLine.substring(inputLine.lastIndexOf("\"",posOfYoutube),
                                                 inputLine.indexOf("\"",posOfYoutube)+1);
                System.out.println(url);
            }
        }
        in.close();
    }

    public static void main(String[] args) throws IOException{
        checkForYoutube();
    }
}
