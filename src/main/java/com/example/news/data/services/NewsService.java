package com.example.news.data.services;

import com.example.news.models.News;

import java.time.LocalDate;
import java.util.List;

public interface NewsService {
    News save(News news);

    News findById(Integer id);

    List<News> findAll();
    News getLatestNews();
    Long count();
    List<News> getNewsByDate(LocalDate date);
    List<News> getNewsByToday();
}
