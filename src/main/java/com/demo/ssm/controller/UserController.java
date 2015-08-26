package com.demo.ssm.controller;

import com.demo.ssm.po.Score;
import com.demo.ssm.util.DownloadImageUtil;
import com.demo.ssm.util.ScoreUtil;
import org.apache.http.client.CookieStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
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
        httpSession.setAttribute("cookieStore",cookieStore);
        return "userLogin";
    }

    @RequestMapping("/getScore")
    public String getScore(String uid, String password, String validation,HttpSession httpSession,ModelMap modelMap) throws URISyntaxException {
        ScoreUtil scoreUtil = new ScoreUtil((CookieStore) httpSession.getAttribute("cookieStore"));
        List<Score> scores = scoreUtil.getScores(uid, password, validation);
        httpSession.setAttribute("scores", scores);
        List<Score> scoreList = new ArrayList<>();
        HashSet<String> semesterList = new HashSet<String>();
        double total = 0;
        double totalCredit = 0;
        for (Score score : scores) {
            semesterList.add(score.getSemester());
            if (score.getSemester().equals("14152")) {
                scoreList.add(score);
                total += score.getCredit() * score.getScore();
                totalCredit += score.getCredit();
            }
        }
        httpSession.setAttribute("semesterList",semesterList);
        total /= totalCredit;
        modelMap.addAttribute("scoreList", scoreList);
        modelMap.addAttribute("avgScore", total);
//        System.out.println(scores);
//        ScoreUtil scoreUtil = new ScoreUtil();
//        List<Score> scores = scoreUtil.getScores(uid, password, validation);
//        System.out.println(scores);
//        modelMap.addAttribute("scores", scores);
        return "showScore";
    }

    @RequestMapping("/showScore/{semester}")
    public String showScore(@PathVariable String semester, HttpSession httpSession,ModelMap modelMap) {
        List<Score> scores = (List<Score>) httpSession.getAttribute("scores");
        List<Score> scoreList = new ArrayList<>();
        double total = 0;
        double totalCredit = 0;
        for (Score score : scores) {
            if (score.getSemester().equals(semester)) {
                scoreList.add(score);
                if (score.getCourseType().equals("外语水平")) continue;
                total += score.getCredit() * score.getScore();
                totalCredit += score.getCredit();
            }
        }
        total /= totalCredit;
        modelMap.addAttribute("scoreList", scoreList);
        modelMap.addAttribute("avgScore", total);
        return "showScore";
    }
}
