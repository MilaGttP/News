package com.example.news.data.repositories;

import com.example.news.models.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findByDate(LocalDate date);
}
