package com.demo.ssm.util;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by gmy on 15/8/12.
 */
public class DownloadImageUtil {
    public static CookieStore downloadImage(String url, String path, String filename) throws Exception {
        //用于保存cookie,为之后验证码校验做准备
        org.apache.http.client.CookieStore cookieStore = new BasicCookieStore();
        // 构造URL
        URL urlName = new URL(url);
        // 打开连接
        URLConnection con = urlName.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(5 * 1000);
        // 输入流
        InputStream is = con.getInputStream();

        //获取Cookie
        String cookieStr = con.getHeaderField("Set-Cookie");
        String[] split = cookieStr.split(";");
        for (String str : split) {
            String[] cookie = str.split("=");
            if (cookie.length > 1) {
                BasicClientCookie basicClientCookie = new BasicClientCookie(cookie[0], cookie[1]);
                basicClientCookie.setDomain("e.tju.edu.cn");
                cookieStore.addCookie(basicClientCookie);
            }
        }
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        OutputStream os = new FileOutputStream(file.getPath() + "/" + filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
        return cookieStore;
    }

//    public static void main(String[] args) {
//        try {
//            downloadImage("http://e.tju.edu.cn/Kaptcha.jpg","/Users/imac/javastudy/ssm/target/ssm/upload","Kaptcha.jpg");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
