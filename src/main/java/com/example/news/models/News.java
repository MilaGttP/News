package com.example.news.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String header;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private LocalDate date;
    @Column(length = 1500, nullable = false)
    private String text;

    public News(String header, String category, String author, LocalDate date, String text) {
        this.header = header;
        this.category = category;
        this.author = author;
        this.date = date;
        this.text = text;
    }
}
