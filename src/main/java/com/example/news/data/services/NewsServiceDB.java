package com.example.news.data.services;

import com.example.news.data.repositories.NewsRepository;
import com.example.news.models.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class NewsServiceDB implements NewsService {
    @Autowired
    NewsRepository newsRepository;

    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }

    @Override
    public News findById(Integer id) {
        return newsRepository.findById(id).get();
    }

    @Override
    public List<News> findAll() {
        if (newsRepository.count() == 0) {
            return new ArrayList<>();
        } else {
            return newsRepository.findAll();
        }
    }

    @Override
    public News getLatestNews() {
        List<News> list = getNewsByToday();
        if (list == null) {
            return null;
        }
        return list.stream()
                .sorted(Comparator.comparing(News::getDate).reversed())
                .findFirst()
                .orElse(null);
    }

    @Override
    public Long count() {
        return newsRepository.count();
    }

    @Override
    public List<News> getNewsByDate(LocalDate date) {
        if (newsRepository.count() == 0) {
            return new ArrayList<>();
        } else {
            return newsRepository.findByDate(date);
        }
    }

    @Override
    public List<News> getNewsByToday() {
        List<News> todayNews = newsRepository.findByDate(LocalDate.now());
        if (todayNews == null) {
            return new ArrayList<>();
        } else {
            return todayNews;
        }
    }
}
