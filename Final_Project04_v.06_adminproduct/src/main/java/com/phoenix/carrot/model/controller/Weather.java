package com.phoenix.carrot.model.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.simple.JSONObject;

public class Weather {
	 private static final String HOST_URL = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?serviceKey=okJR2mnA1Rzmmk7CjZYkoyCo5Rl8TOuU4%2FIbVsOhh%2F6aZSGHZ1TQ%2BcHnM0LxasBAl7RZv5glsiirPyxly%2FjMAA%3D%3D&returnType=json&numOfRows=100&pageNo=1&sidoName=%EC%84%9C%EC%9A%B8&ver=1.0";

	 public static void main(String[] args) {
		get();
	}
	    public static void get() {
	        HttpURLConnection conn = null;
	        JSONObject responseJson = null;

	        try {
	            URL url = new URL(HOST_URL);

	            conn = (HttpURLConnection)url.openConnection();
	            conn.setConnectTimeout(5000);
	            conn.setReadTimeout(5000);
	            conn.setRequestMethod("GET");
	            //conn.setDoOutput(true);

	            JSONObject commands = new JSONObject();

	            int responseCode = conn.getResponseCode();
	            if (responseCode == 400 || responseCode == 401 || responseCode == 500 ) {
	                System.out.println(responseCode + " Error!");
	            } else {
	                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	                StringBuilder sb = new StringBuilder();
	                String line = "";
	                while ((line = br.readLine()) != null) {
	                    sb.append(line);
	                }
	                System.out.println(sb);
	                responseJson = new JSONObject();
	                //System.out.println(responseJson);
	               
	                StringBuilder json= sb.append(line); 
	            }
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (JSONException e) {
	            System.out.println("not JSON Format response");
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
}
