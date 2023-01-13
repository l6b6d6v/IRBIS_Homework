package com.example.demo.Domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Table;

import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID _idNews;

    //Источник новости
    @Column(name = "source_news")
    private String _newsSource;

    //Тема новости
    @Column(name = "subject_news")
    private String _newsSubject;

    //Новость
    @Column(name = "news")
    private String _news;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return _news.equals(news._news) && _newsSubject.equals(news._newsSubject) && _newsSource.equals(news._newsSource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_news, _newsSubject, _newsSource);
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + _idNews +
                ", Источник='" + _newsSource + '\'' +
                ", Тематика='" + _newsSubject + '\'' +
                ", Новость='" + _news + '\'' +
                '}';
    }
}
