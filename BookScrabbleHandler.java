package test;


import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class BookScrabbleHandler implements  ClientHandler {

    BufferedReader in;
    PrintWriter out;

    public BookScrabbleHandler(){
    }

    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        DictionaryManager manager = new DictionaryManager();
        try{
            out = new PrintWriter(outToClient);
            in = new BufferedReader(new InputStreamReader(inFromclient));
            String text = in.readLine();
            String key = text.substring(2);
            String[] fileName = key.split(",");
            if(Objects.equals(text.charAt(0), "Q"))
                out.println(manager.query(fileName));
            else
                out.println(manager.challenge(fileName));
            in.close();
            out.close();
        }catch(Exception e){e.printStackTrace();}
    }

    @Override
    public void close() {

    }
}
