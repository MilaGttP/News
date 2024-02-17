package com.example.news.data;

import com.example.news.models.News;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NewsRepositoryTests {
    @Autowired
    NewsRepository newsRepository;

    static News dataNews = new News("Test news 1", "Studying", "New Redactor", LocalDate.now(), "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
    static News newNews;

    @Test
    @Order(1)
    void save() {
        newNews = newsRepository.save(dataNews);
        Assertions.assertEquals(dataNews, newNews);
    }

    @Test
    @Order(2)
    void findAll() {
        List<News> news = newsRepository.findAll();
        Assertions.assertNotNull(news);
    }

    @Test
    @Order(3)
    void findById() {
        newNews = newsRepository.findById(dataNews.getId()).get();
        Assertions.assertEquals(dataNews, newNews);
    }
}
