package com.haiyang.http.httpclient1.util;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

/**
 * @author shkstart
 * @create 2019-07-11 10:57
 *
 * 零时存储ip和端口
 * 进行ip和端口是否可用的测试
 */
public class HttpClientIPAgent {



    public static void main(String[] args) {

        HttpClientIPAgent httpClientIPAgent = new HttpClientIPAgent();
        httpClientIPAgent.TT01();
    }

    /*
    测试程序的进程管理
     */
   void  TT01(){
       HttpClientUtils httpClientUtils = new HttpClientUtils();
      Document document1 = httpClientUtils.httpClientIPAndPort();
      List<String> lists = httpClientUtils.ipAndport(document1);
      SaveIPAndPort(lists);

  }

    String  SaveIPAndPort(List<String> list){
        List<String> l1 = list;
        String [] strings = new String[l1.size()];
        l1.toArray(strings);
        for (int i = 0; i <strings.length ; i++) {
            System.out.println("ip和端口:"+strings[i]);
            String ipurl = strings[i];
            HttpClientIPAgent httpClientIPAgent = new HttpClientIPAgent();
            httpClientIPAgent.SocketT001(ipurl);
        }

        return  null;
    }


    /*
     /*
    检测ip地址和端口是否可用
     */
    String SocketT001(String httclienturl){
        Socket conn = new Socket();
        int sum=0,i=0,j=0;
        String ipandport = null;
        if (httclienturl != null || httclienturl.length()>=1) {
            String zs = httclienturl;
            String url = StringUtils.substringBefore(zs, ":");
            int port1 = Integer.parseInt(StringUtils.substringAfter(zs, ":"));
            try {
                conn.connect(new InetSocketAddress(url, port1), 50000);
                boolean res = conn.isConnected();
                if (res == true){
                    System.out.println("测试连接的结果:成功");
                    i=i+1;
                    ipandport = zs;
                }else {
                    System.out.println("测试连接的结果:失败");
                    j=j+1;
                }
            }
            catch (Exception e){
                System.out.println("此连接无效");
                e.printStackTrace();
            }
            finally {
                    try {
                        conn.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
        return ipandport;
    }


}
