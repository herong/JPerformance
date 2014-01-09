/**
 * 
 */
package com.github.herong.tools.jperformance.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * TODO 这里用文字描述这个类的主要作用
 * 
 * @author herong
 * @createTime 2013-12-3 下午01:06:35
 * @modifier
 * @modifyDescription 描述本次修改内容
 * @see
 */

public class Processor {
    private static int readTimeout = 60;
    private static int connectionTimeout = 60;

    /*private static final String URL = "http://sb-app-09.hnisi.com.cn:7001/web/ajax.do";*/
    private static final String URL = "http://192.168.16.7:7100/web/ajax.do";
    private static final Map<String, String> messageMap = new HashMap<String, String>(2);

    static {
        /*messageMap.put("1", "_isModel=true&params={\"oper\":\"fw_dict.query\",\"datas\":{\"dict\":{\"PROCESSSTATE\":[]}}}");
        messageMap.put("2", "_isModel=true&params={\"oper\":\"fw_dict.query\",\"datas\":{\"dict\":{\"PROCESSSTATE\":[]}}}");
        messageMap.put("3", "_isModel=true&params={\"oper\":\"fw_dict.query\",\"datas\":{\"dict\":{\"PROCESSSTATE\":[]}}}");
        messageMap.put("4", "_isModel=true&params={\"oper\":\"fw_dict.query\",\"datas\":{\"dict\":{\"PROCESSSTATE\":[]}}}");
        messageMap.put("5", "_isModel=true&params={\"oper\":\"fw_dict.query\",\"datas\":{\"dict\":{\"PROCESSSTATE\":[]}}}");*/
        
        messageMap.put("1", "_isModel=true&MenuId=undefined&params={\"oper\":\"QyxxwhAction.save\",\"datas\":{\"ncm_gt_企业基本信息\":{\"params\":{\"审核机构\":\"440100\",\"企业所在区域\":\"440103\",\"企业性质\":\"10\",\"行业类型\":\"0200\",\"所属产业\":\"1\",\"企业名称\":\"1\",\"组织机构代码\":\"1\",\"营业执照号码\":\"1\",\"法人联系电话\":\"1\",\"法定代表人\":\"1\",\"企业联系人\":\"1\",\"企业联系电话\":\"1\",\"企业注册地址\":\"1\",\"传真\":\"1\",\"电子邮箱\":\"1\",\"邮政编码\":\"1\",\"是否纳入监测范围\":\"1\"}}}}");
        messageMap.put("2", "_isModel=true&MenuId=undefined&params={\"oper\":\"QyxxwhAction.save\",\"datas\":{\"ncm_gt_企业基本信息\":{\"params\":{\"审核机构\":\"440100\",\"企业所在区域\":\"440103\",\"企业性质\":\"10\",\"行业类型\":\"0200\",\"所属产业\":\"1\",\"企业名称\":\"1\",\"组织机构代码\":\"1\",\"营业执照号码\":\"1\",\"法人联系电话\":\"1\",\"法定代表人\":\"1\",\"企业联系人\":\"1\",\"企业联系电话\":\"1\",\"企业注册地址\":\"1\",\"传真\":\"1\",\"电子邮箱\":\"1\",\"邮政编码\":\"1\",\"是否纳入监测范围\":\"1\"}}}}");
        messageMap.put("3", "_isModel=true&MenuId=undefined&params={\"oper\":\"QyxxwhAction.save\",\"datas\":{\"ncm_gt_企业基本信息\":{\"params\":{\"审核机构\":\"440100\",\"企业所在区域\":\"440103\",\"企业性质\":\"10\",\"行业类型\":\"0200\",\"所属产业\":\"1\",\"企业名称\":\"1\",\"组织机构代码\":\"1\",\"营业执照号码\":\"1\",\"法人联系电话\":\"1\",\"法定代表人\":\"1\",\"企业联系人\":\"1\",\"企业联系电话\":\"1\",\"企业注册地址\":\"1\",\"传真\":\"1\",\"电子邮箱\":\"1\",\"邮政编码\":\"1\",\"是否纳入监测范围\":\"1\"}}}}");
        messageMap.put("4", "_isModel=true&MenuId=undefined&params={\"oper\":\"QyxxwhAction.save\",\"datas\":{\"ncm_gt_企业基本信息\":{\"params\":{\"审核机构\":\"440100\",\"企业所在区域\":\"440103\",\"企业性质\":\"10\",\"行业类型\":\"0200\",\"所属产业\":\"1\",\"企业名称\":\"1\",\"组织机构代码\":\"1\",\"营业执照号码\":\"1\",\"法人联系电话\":\"1\",\"法定代表人\":\"1\",\"企业联系人\":\"1\",\"企业联系电话\":\"1\",\"企业注册地址\":\"1\",\"传真\":\"1\",\"电子邮箱\":\"1\",\"邮政编码\":\"1\",\"是否纳入监测范围\":\"1\"}}}}");
        messageMap.put("5", "_isModel=true&MenuId=undefined&params={\"oper\":\"QyxxwhAction.save\",\"datas\":{\"ncm_gt_企业基本信息\":{\"params\":{\"审核机构\":\"440100\",\"企业所在区域\":\"440103\",\"企业性质\":\"10\",\"行业类型\":\"0200\",\"所属产业\":\"1\",\"企业名称\":\"1\",\"组织机构代码\":\"1\",\"营业执照号码\":\"1\",\"法人联系电话\":\"1\",\"法定代表人\":\"1\",\"企业联系人\":\"1\",\"企业联系电话\":\"1\",\"企业注册地址\":\"1\",\"传真\":\"1\",\"电子邮箱\":\"1\",\"邮政编码\":\"1\",\"是否纳入监测范围\":\"1\"}}}}");
        
        
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String url = args[0];
        if (args.length == 2) {
            connectionTimeout = Integer.parseInt(args[1]);
        }
        System.out.println("请求地址:" + url);

    }

    public static String request() throws Exception {
        URL urlObj;
        String content = "";
        BufferedReader br = null;
        HttpURLConnection conn = null;
        try {
            urlObj = new URL(Environment.initParams.getUrl());
            System.out.println("【线程"+Thread.currentThread().getName()+"请求" + URL + "】");
            conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            
            final String output =  new String(Environment.initParams.getBody().getBytes(),Environment.initParams.getEncoding());
            System.out.println("发送信息:"+output);
            conn.setRequestMethod("POST");
            for (Map.Entry<String, String> reqParam : Environment.initParams.getReqParams().entrySet()) {
            	conn.setRequestProperty(reqParam.getKey(),reqParam.getValue());
            }
            conn.setReadTimeout(Environment.initParams.getResponse()*1000);
            conn.setConnectTimeout(Environment.initParams.getConnect()*1000);
            conn.setRequestProperty("Content-Length", "" + Integer.toString(output.getBytes("UTF-8").length));
            
            //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            //conn.setRequestProperty("Accept", "application/json, text/plain, */*");
           /* conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            conn.setRequestProperty("Referer", "http://sb-app-09.hnisi.com.cn:7001/web/ggfw/index.html#/wsyw");
            conn.setRequestProperty("Accept-Language", "zh-cn");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
            conn.setRequestProperty("Host", "sb-app-09.hnisi.com.cn:7001");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Pragma", "no-cache");
            conn.setRequestProperty("Content-Length", "" + Integer.toString(output.getBytes("UTF-8").length));
            conn.setReadTimeout(readTimeout*1000);
            conn.setConnectTimeout(connectionTimeout*1000);*/

            System.out.println("【线程"+Thread.currentThread().getName()+"获取输出流...】");
            //final DataOutputStream printout = new DataOutputStream(conn.getOutputStream());
            BufferedWriter printout = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(),Environment.initParams.getEncoding()));
            printout.write(output);
            printout.flush();
            printout.close();
            
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(),Environment.initParams.getEncoding()));
            StringBuffer sf = new StringBuffer();
            String sCurrentLine = "";
            while ((sCurrentLine = br.readLine()) != null) {
                sf.append(sCurrentLine).append("\r\n");
            }
            content = sf.toString().trim();
        } catch (MalformedURLException e) {
            System.out.println("线程"+Thread.currentThread().getName()+"请求链接错误!" + e.getLocalizedMessage());
            throw e;
        } catch (IOException e) {
            System.out.println("线程"+Thread.currentThread().getName()+"请求服务器出错!" + e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("线程"+Thread.currentThread().getName()+"请求服务器出错!" + e.getLocalizedMessage());
            throw e;
        } finally {

            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                }

            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return content;

    }
}
