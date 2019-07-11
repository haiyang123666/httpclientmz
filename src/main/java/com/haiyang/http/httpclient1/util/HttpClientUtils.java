package com.haiyang.http.httpclient1.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author shkstart
 * @create 2019-07-10 14:45
 *
 * httpclient 的工具包
 */
public class HttpClientUtils {

    public static void main(String[] args) {
        HttpClientUtils h = new HttpClientUtils();
        // h.httpClientIPAndPort();
        h.tes001();

    }

    /*
    测试
     */
     void tes001(){
        Document dov = httpClientIPAndPort();
        int asd = ipAndport(dov);

    }

    /*
    本机ip去爬取代理网站的ip

     */
    Document httpClientIPAndPort (){
        String httpipAndPort=null;
        String url  = "https://www.kuaidaili.com/free/";
        Document htmldoc = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet =new HttpGet(url);
        try {
            RequestConfig config = RequestConfig.custom()
                    .setLocalAddress(InetAddress.getLocalHost())
                    .setConnectTimeout(5000)
                    .setSocketTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .build();
            httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:67.0) Gecko/20100101 Firefox/67.0");
            httpGet.setHeader("Accept"," text/css,*/*;q=0.1");
            httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
            httpGet.setHeader("Accept-Encoding","gzip, deflate, br");
            httpGet.setHeader("Connection"," keep-alive");
            httpGet.setHeader("Cookie","_ga=GA1.2.477671550.1562726664; _gid=GA1.2.486399294.1562726664; Hm_lvt_7ed65b1cc4b810e9fd37959c9bb51b31=1562726664,1562736860,1562741713; Hm_lpvt_7ed65b1cc4b810e9fd37959c9bb51b31=1562741713; _gat=1");
            httpGet.setConfig(config);
            try {
                CloseableHttpResponse response = httpClient.execute(httpGet);
                if (response != null){
                    HttpEntity entity = response.getEntity();
                    String result = EntityUtils.toString(entity,"UTF-8");
                    int state = response.getStatusLine().getStatusCode();
                    System.out.println("响应状态:"+state);
                    if (state == 200){
                        Document doc = Jsoup.parse(result);
                        htmldoc = doc;
                  //      System.out.println("documents:"+doc);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return htmldoc;
    }

    int ipAndport(Document document){
        int ips=0,ports=0;
        Elements elements = null;
        Elements iips = null;
        Elements poorts1 = null;
        try {
                elements = document.select("body").select("div[class^=body]").select("div[id^=content]")
                        .select("div[class^=con-body]").select("div").select("div[id^=list]")
                        .select("tbody").select("tr");
            iips = elements.select("td[data-title^=IP]");
            poorts1 = elements.select("td[data-title^=]");
            for (Element element:elements){
              //  System.out.println("e:"+element);
                System.out.println("ip:"+element.text());



              /*
                System.out.println("iips:"+iips);
                System.out.println("poorts1:"+poorts1);

                    */
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ips+ports;
    }

}
