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
import java.util.ArrayList;
import java.util.List;

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
        List<String> lio = ipAndport(dov);
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

    List<String> ipAndport(Document document){
        int ports=0;
        String ips = null;
        Elements elements = null;
        List<String> lists = new ArrayList<>();
        try {
                elements = document.select("body").select("div[class^=body]").select("div[id^=content]")
                        .select("div[class^=con-body]").select("div").select("div[id^=list]")
                        .select("tbody").select("tr");
            for (Element element:elements){
                Elements ip = element.select("td[data-title^=IP]");
                Elements port  = element.select("td[data-title^=PORT]");
                Elements nmd   = element.select("td[data-title^=匿名度]");
                Elements lx    = element.select("td[data-title^=类型]");
                Elements wz    = element.select("td[data-title^=位置]");
                Elements yxsd  = element.select("td[data-title^=响应速度]");
                Elements pyasj = element.select("td[data-title^=最后验证时间]");
                String ipss = ip.text();
                String op = port.text();
                String ipport = ipss+":"+op;
                ports = Integer.parseInt(op);
                lists.add(ipport);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return lists;
    }

}
