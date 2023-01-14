package com.example.demo.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Источник новости
    @Column(name = "source_news")
    private String newsSource;

    //Тема новости
    @Column(name = "subject_news")
    private String newsSubject;

    //Новость
    @Column(name = "news")
    private String news;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return this.news.equals(news.news) && newsSubject.equals(news.newsSubject) && newsSource.equals(news.newsSource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(news, newsSubject, newsSource);
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", Источник='" + newsSource + '\'' +
                ", Тематика='" + newsSubject + '\'' +
                ", Новость='" + news + '\'' +
                '}';
    }
}
