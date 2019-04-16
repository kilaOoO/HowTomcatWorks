package com.howtomcatwork.ex01;

import java.io.IOException;
import java.io.InputStream;

public class Request {
    private InputStream inputStream;
    private String uri;
    public Request(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public void parse(){
        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for(int j=0;j<i;j++){
            request.append((char)buffer[j]);
        }
        System.out.print(request.toString());
        this.uri = parseUri(request.toString());
    }

    private String parseUri(String requestString){
        String[] strs = requestString.split(" ");
        String uri  = strs[1];
        if(uri!=null){
            return uri;
        }
        return null;
    }

    public String getUri(){
        return uri;
    }
}
