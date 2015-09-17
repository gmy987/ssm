package com.demo.ssm.controller;

import com.demo.ssm.po.*;
import com.demo.ssm.service.AddressService;
import com.demo.ssm.service.EdgeService;
import com.demo.ssm.service.PathService;
import com.demo.ssm.service.PointService;
import com.demo.ssm.util.DownloadImageUtil;
import com.demo.ssm.util.ScoreUtil;
import org.apache.http.client.CookieStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by gmy on 15/8/12.
 */
@Controller
@RequestMapping("/tju")
public class UserController {
    @Autowired
    private PointService pointService;
    @Autowired
    private EdgeService edgeService;
    @Autowired
    private PathService pathService;
    @Autowired
    private AddressService addressService;
    @RequestMapping("/openMap")
    public String openMap() {
        return "map";
    }

    @RequestMapping("/login")
    public String login(HttpSession httpSession) throws Exception {
//        System.out.println(uid+" "+password+" "+validation);
//        String url = "http://e.tju.edu.cn/Main/logon.do?uid=UID&password=PASSWORD&captchas=VALIDATION";
//        url = url.replace("UID", uid).replace("PASSWORD", password).replace("VALIDATION", validation);
//        System.out.println(url);
//        String s = HttpRequest.doGet(url);
//        System.out.println(s);
//        modelMap.addAttribute("url", url);
        CookieStore cookieStore = DownloadImageUtil.downloadImage("http://e.tju.edu.cn/Kaptcha.jpg", "/Users/imac/javastudy/ssm/target/ssm/upload", "Kaptcha.jpg");
        httpSession.setAttribute("cookieStore", cookieStore);
        return "userLogin";
    }

    @RequestMapping("/getScore")
    public String getScore(String uid, String password, String validation,HttpSession httpSession,ModelMap modelMap) throws URISyntaxException {
        ScoreUtil scoreUtil = new ScoreUtil((CookieStore) httpSession.getAttribute("cookieStore"));
        List<Score> scores = scoreUtil.getScores(uid, password, validation);
        httpSession.setAttribute("scores", scores);
        List<Score> scoreList = new ArrayList<>();
        HashSet<String> semesterList = new HashSet<String>();
        for (Score score : scores) {
            semesterList.add(score.getSemester());
        }
        httpSession.setAttribute("semesterList",semesterList);
//        System.out.println(scores);
//        ScoreUtil scoreUtil = new ScoreUtil();
//        List<Score> scores = scoreUtil.getScores(uid, password, validation);
//        System.out.println(scores);
//        modelMap.addAttribute("scores", scores);
        return "showScore";
    }

    @RequestMapping("/showScore/{semester}")
    public @ResponseBody List<Score> showScore(@PathVariable String semester, HttpSession httpSession) {
        List<Score> scores = (List<Score>) httpSession.getAttribute("scores");
        List<Score> scoreList = new ArrayList<>();
        for (Score score : scores) {
            if (score.getSemester().equals(semester)) {
                scoreList.add(score);
            }
        }
        return scoreList;
    }
    @RequestMapping("/makeMap")
    public @ResponseBody List<Point> makeMap() {
        List<Point> points = new ArrayList<>();
        System.out.println("aaaaaaaa");
        File file = new File("/Users/imac/Desktop/newpoint.txt");
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 0;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
//                System.out.println("line " + line + ": " + tempString);
                String[] split = tempString.split(" ");
                Point point = new Point(Double.parseDouble(split[0]), Double.parseDouble(split[1]), Integer.parseInt(split[2])+1);
                points.add(point);
                line++;
//                pointService.insertPoint(point);
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        System.out.println("aaaaaaaa");
        file = new File("/Users/imac/Desktop/newedge.txt");
        reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 0;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                if (line % 100 == 0) {
                    System.out.println("bbbbb");
                }
                String[] split = tempString.split(" ");
//                for (String number : split) {
//                    System.out.print(number + " ");
//                }
//                System.out.println();
                Edge edge = new Edge(Integer.parseInt(split[0])+1, Integer.parseInt(split[1])+1);
//                points.add(point);
                line++;
//                edgeService.insertEdge(edge);
//                pointService.insertPoint(point);
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return points;
    }
    @RequestMapping("/addPath")
    public String addPath() {
        File file = new File("/Users/imac/Desktop/newfile.txt");
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 0;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line :" + line);
                String tmp1 = tempString.substring(0, tempString.indexOf(" "));
                tempString = tempString.substring(tempString.indexOf(" ")+1);
                String tmp2 = tempString.substring(0, tempString.indexOf(" "));
                tempString = tempString.substring(tempString.indexOf(" ") + 1);
                Path path = new Path(Integer.parseInt(tmp1), Integer.parseInt(tmp2), tempString);
//                pathService.insertPath(path);
                line++;
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return "success";
    }
    @RequestMapping("/addAddress")
    public String addAddress() {
        File file = new File("/Users/imac/Desktop/address.txt");
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            String tempString = null;
            int line = 0;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line :" + line+" "+tempString);
                String[] split = tempString.split(" ");
//                Path path = new Path(Integer.parseInt(tmp1), Integer.parseInt(tmp2), tempString);
//                pathService.insertPath(path);
                Address address = new Address(Integer.parseInt(split[0]),Integer.parseInt(split[2]),split[1]);
                addressService.insertAddress(address);
                line++;
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return "success";
    }
}
