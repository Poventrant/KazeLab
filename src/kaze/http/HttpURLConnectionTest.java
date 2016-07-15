//package kaze.http;
//
//
//import com.google.common.reflect.TypeToken;
//import com.google.gson.Gson;
//
//import javax.net.ssl.HttpsURLConnection;
//import java.io.*;
//import java.lang.reflect.Type;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.*;
///**
// * Created by 枫叶 on 2016/4/16.
// */
//public class HttpURLConnectionTest {
//
//    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36";
//
//    public final static Map<String, String> sessionMap = new HashMap<String, String>();
//
//    public static void main(String[] args) throws ExceptionLab {
//
//        HttpURLConnectionTest http = new HttpURLConnectionTest();
//
//        http.sendGet();
//
//        //get session
//        List<AbstractMap.SimpleEntry<String, String>> params = new ArrayList<AbstractMap.SimpleEntry<String, String>>();
//        params.add(new AbstractMap.SimpleEntry<String, String>("type", "p2p"));
//        String url = "https://api.realtimecat.com/v0.3/sessions";
//        //一个房间对应一个uuid,key为HR房间号
//        sessionMap.put("1" ,http.sendPost(url, params).get("uuid"));
//
//        //get token
//        String uuid = sessionMap.get("1");
//        params = new ArrayList<AbstractMap.SimpleEntry<String, String>>();
//        params.add(new AbstractMap.SimpleEntry<String, String>("type", uuid));
//        params.add(new AbstractMap.SimpleEntry<String, String>("type", "pub"));
//        url = "https://api.realtimecat.com/v0.3/sessions/" + uuid + "/tokens";
//        Map<String, String> tokenMap0 = http.sendPost(url, params);
//        Map<String, String> tokenMap1 = http.sendPost(url, params);
//        System.out.println(tokenMap0);
//        System.out.println(tokenMap1);
//    }
//
//    private Map<String, String> sendPost(String url, List<AbstractMap.SimpleEntry<String, String>> params) throws ExceptionLab {
//
//        URL obj = new URL(url);
//        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
//
//        //add reuqest header
//        con.setRequestMethod("POST");
//        con.setRequestProperty("User-Agent", USER_AGENT);
//        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//
//        con.setRequestProperty("X-RTCAT-APIKEY", "2871ccd3-eac6-4c49-9a5c-59d5aaaf696c");
//        con.setRequestProperty("X-RTCAT-SECRET", "8e3b282d-7816-4cc5-96ff-caf55cae6cee");
//
//
//        // Send post request
//        con.setReadTimeout(10000);
//        con.setConnectTimeout(15000);
//        con.setRequestMethod("POST");
//        con.setDoInput(true);
//        con.setDoOutput(true);
//
//        String postData = getQuery(params);
//        con.setRequestProperty("Content-Length", String.valueOf( postData.getBytes("UTF-8").length));
//
//        OutputStream os = con.getOutputStream();
//        BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(os, "UTF-8"));
//
//        writer.write(postData);
//        writer.flush();
//        writer.close();
//        os.close();
//
//        int responseCode = con.getResponseCode();
//
//        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//
//        //print result
//        Gson gson = new Gson();
//        Type type = new TypeToken<Map<String, String>>(){}.getType();
//        Map<String, String> resultMap = gson.fromJson(response.toString(), type);
//
//       return resultMap;
//    }
//
//    private void sendGet() throws ExceptionLab {
//
//        String url = "https://www.baidu.com/";
//
//        URL obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//        // optional default is GET
//        con.setRequestMethod("GET");
//
//        //add request header
//        con.setRequestProperty("User-Agent", USER_AGENT);
//
//        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);
//
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//
//        //print result
//        System.out.println(response.toString());
//
//    }
//
//    private String getQuery(List<AbstractMap.SimpleEntry<String, String>> params) {
//        StringBuilder result = new StringBuilder();
//        boolean first = true;
//        try {
//            for (AbstractMap.SimpleEntry<String, String> pair : params) {
//                if (first)
//                    first = false;
//                else
//                    result.append("&");
//
//                result.append(URLEncoder.encode(pair.getKey(), "UTF-8"));
//                result.append("=");
//                result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
//            }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return result.toString();
//    }
//}
