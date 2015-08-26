package com.demo.ssm.util;


import com.demo.ssm.po.Score;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gmy on 15/8/16.
 */
public class ScoreUtil {
    private CookieStore cookieStore = new BasicCookieStore();
    private CookieStore cs = new BasicCookieStore();
    private String[] semesters = new String[10];


    public ScoreUtil(CookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }

    private boolean login(String uid, String password, String validation) throws IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpContext basicHttpContext = new BasicHttpContext();
        basicHttpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
        HttpPost httpPost = new HttpPost("http://e.tju.edu.cn/Main/logon.do");
        StringEntity reqEntity = new StringEntity("uid=" + uid + "&password=" + password + "&captchas=" + validation);
        httpclient.setCookieStore(cookieStore);
        // 设置类型
        reqEntity.setContentType("application/x-www-form-urlencoded");
        httpPost.addHeader("Host", "e.tju.edu.cn");
        // 设置请求的数据
        httpPost.setEntity(reqEntity);
        // 执行
        HttpResponse response = httpclient.execute(httpPost);
        //取得所有头内容
        Header[] headers = response.getAllHeaders();
        for (Header h : headers) {
            String name = h.getName();
            String value = h.getValue();
            if ("Set-Cookie".equalsIgnoreCase(name)) {
                String[] strs = value.split(";");
                for (String str : strs) {
                    String[] cookies = str.split("=");
                    if(cookies.length > 1){
                        cs.addCookie(new BasicClientCookie(cookies[0], cookies[1]));
                        System.out.println(cs.toString());
                    }
                }
            }
        }
        HttpEntity entity = response.getEntity();
        // 显示结果
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                entity.getContent(), "GBK"));
        String line = null;

        //返回是否登录成功的内容
        if(reader.readLine().contains("302")){
            //登录成功
            return true;}
        else{
            //登录失败
            return false;
        }
    }

    /**
     *
     * @param url 请求目标地址
     * @param encode 编码格式
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     *
     * 请求目标地址并返回内容
     *
     */
    private String getContent(String url, String encode) throws ClientProtocolException, IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String cookieStr = "";
        List<Cookie> list = cs.getCookies();
        for (Cookie cookie : list) {
            cookieStr += cookie.getName() + "=" + cookie.getValue() + ";";
        }
        for (Cookie cookie : cookieStore.getCookies()) {
            cookieStr += cookie.getName() + "=" + cookie.getValue() + ";";
        }
        // 请求目标地址并带上cookie
        HttpGet httpget = new HttpGet(url);
        httpget.setHeader("Cookie", cookieStr);
        // 执行
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        String resultStr ="";
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                entity.getContent(), encode));
        String line = null;
        while ((line = reader.readLine()) != null) {
            resultStr+=line;
        }
        return resultStr;
    }
    public List<Score> getScores(String uid, String pwd,String validation){
        try {
            if(login(uid, pwd,validation)){
                List<Score> list = new ArrayList<>();
                String startSemester = uid.substring(2, 4);
                int s = Integer.parseInt(startSemester);
                for(int i = 0; i < 5; i++){
                    semesters[i * 2] = s + "" + (s+1) + "1";
                    semesters[i * 2 + 1] = s + "" + (s+1) + "2";
                    s = s + 1;
                }
                for(int i = 0; i < 10; i++){
                    String logPath = "http://e.tju.edu.cn/Education/stuachv.do?todo=display&term="+semesters[i];

                    String content= getContent(logPath, "GBK");
                    Document doc = Jsoup.parse(content);
                    Elements tds = doc.select("table").get(21).select("tr");
                    for (int index = 1; index < tds.size(); index++) {
                        Elements data = tds.get(index).select("td");
                        Score score = new Score();
                        score.setSemester(data.get(0).text());
                        score.setCourseId(data.get(1).text());
                        score.setCourseName(data.get(2).text());
                        score.setCourseType(data.get(3).text());
                        score.setCourseKind(data.get(4).text());
                        score.setCredit(Double.parseDouble(data.get(5).text()));
                        if(data.get(6).text().equals("评价")){
                            score.setScore(-100.0);
                        }
                        else if(data.get(6).text().trim().equals("")){
                            continue;
                        }
                        else{
                            score.setScore(Double.parseDouble(data.get(6).text()));
                        }
                        score.setNote(data.get(8).text());
                        list.add(score);
                    }
                }
                return list;
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
