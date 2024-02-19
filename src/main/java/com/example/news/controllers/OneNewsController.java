package com.example.news.controllers;

import com.example.news.data.services.NewsService;
import com.example.news.models.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OneNewsController {
    @Autowired
    NewsService newsService;

    @GetMapping("/oneNews/{id}")
    public String oneNews(@PathVariable("id") Integer id, Model model) {
        News news = newsService.findById(id);
        model.addAttribute("news", news);
        return "oneNews";
    }
}
