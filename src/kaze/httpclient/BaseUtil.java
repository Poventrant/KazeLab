package kaze.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 枫叶 on 2016/1/17.
 */
public class BaseUtil {

    static class NestedHttpClient {
        static {
            HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
                public boolean retryRequest(IOException arg0, int executionCount, HttpContext arg2) {
                    System.out.println(executionCount + " times connecting failed, try again...");
                    if (executionCount > 5) {
                        System.out.println("Fail to connect, exit.");
                        return false;
                    }
                    return true;
                }
            };
//          HttpHost proxy = new HttpHost("127.0.0.1", 8888);       //开发时用
            httpclient = HttpClients.custom().setRetryHandler(myRetryHandler)/*.setProxy(proxy)*/.build();
        }

        private static CloseableHttpClient httpclient = null;
    }

    private static CloseableHttpClient getHttpClient() {
        return NestedHttpClient.httpclient;
    }

    public static HttpGet getHttpGet(String url) {
        HttpGet httpget = new HttpGet(url);
        setRequest(httpget);
        return httpget;
    }

    public static HttpPost getHttpPost(String url) {
        HttpPost httppost = new HttpPost(url);
        setRequest(httppost);
        return httppost;
    }

    private static void setRequest(HttpRequestBase request) {
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(2000)
                .setCookieSpec(CookieSpecs.STANDARD_STRICT).setSocketTimeout(5000).
                        setConnectTimeout(5000).build();
        request.setConfig(requestConfig);
    }

    public static void main(String[] args) {

        CloseableHttpClient httpclient = getHttpClient();

        HttpPost httppost = getHttpPost("http://gdxg.sysu.edu.cn/index.php/Article/update.html");
        httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httppost.setHeader(HttpHeaders.REFERER, "http://gdxg.sysu.edu.cn/index.php/Article/add.html");
        httppost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        httppost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        httppost.setHeader("Accept-Encoding", "gzip, deflate");
        httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0");
        httppost.setHeader("Host", "gdxg.sysu.edu.cn");


        httppost.setHeader("Cookie", "PHPSESSID=nhbqrvkaor2eom9qhimi3pjgo4; PHPSESSID_NS_Sig=oenCV6mfx2Zy6gq-");


        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("id", "182"));
        nameValuePairs.add(new BasicNameValuePair("type[]", "4"));
        nameValuePairs.add(new BasicNameValuePair("title", " ***** 测试专用，请忽略 ***** "));
        nameValuePairs.add(new BasicNameValuePair("content","<p>\t<strong>测试专用，<span+style=\"background-color:#FF9900;\">请忽略测试专用</span>，<em>请忽略测试专用，<span+style=\"color:#E53333;\">请忽略测试专用</span>，请忽略测试专用，请忽略测试专用，请忽略测试专用，请忽略测试专用，请忽略测试专用，请忽略</em></strong></p><p>\t<br+/><strong><em></em></strong></p><p>\t<a+class=\"ke-insertfile\"+href=\"/app/Public/kindeditor/attached/file/20130923/㶫ʡ�Ľѧ����.doc\"+target=\"_blank\">/app/Public/kindeditor/attached/file/20130923/㶫ʡĽѧ.doc</a><br+/><strong><em></em></strong></p><p>\t<strong><em><br+/></em></strong></p><p>\t<strong><em><a+class=\"ke-insertfile\"+href=\"/app/Public/kindeditor/attached/file/20160519/测试.txt\"+target=\"_blank\">/app/Public/kindeditor/attached/file/20160519/测试.txt</a></em></strong></p><p>\t<br+/><strong><em></em></strong></p><p>\t<strong><em><img+src=\"/app/Public/kindeditor/attached/image/20160519/move_CpxeO8QA87xL.gif\"+alt=\"\"+/><br+/></em></strong></p>"));

        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
            HttpEntity entity = httpclient.execute(httppost).getEntity();
            System.out.println(EntityUtils.toString(entity));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
