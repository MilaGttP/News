package com.example.news.controllers;

import com.example.news.data.services.NewsService;
import com.example.news.models.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NewsController {
    @Autowired
    NewsService newsService;

    @GetMapping("/")
    String load(Model model) {
        if (!model.containsAttribute("newsList")) {
            List<News> list = newsService.getNewsByToday();
            if (list.isEmpty()) {
                model.addAttribute("noNewsTodayMessage", "There aren`t news by today :(");
                list = new ArrayList<>();
            }
            model.addAttribute("newsList", list);
        }
        if (!model.containsAttribute("news")) {
            News latestNews = newsService.getLatestNews();
            if (latestNews == null) {
                latestNews = new News();
            }
            model.addAttribute("news", latestNews);
        }
        if (newsService.count() == 0) {
            model.addAttribute("emptyDatabaseMessage", "Database is empty :(");
        }
        return "news";
    }

    @PostMapping("filter")
    public String filter(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                         LocalDate date, RedirectAttributes redirectAttributes) {

        List<News> list = newsService.getNewsByDate(date);
        if (list.isEmpty()) {
            redirectAttributes.addFlashAttribute("noNewsTodayMessage", "There aren`t news by this date :(");
            list = new ArrayList<>();
        }
        News latestNews = newsService.getLatestNews();
        if (latestNews == null) {
            latestNews = new News();
        }

        if (newsService.count() == 0) {
            redirectAttributes.addFlashAttribute("emptyDatabaseMessage", "Database is empty :(");
        }

        redirectAttributes.addFlashAttribute("newsList", list);
        redirectAttributes.addFlashAttribute("news", latestNews);

        return "redirect:/";
    }
}
