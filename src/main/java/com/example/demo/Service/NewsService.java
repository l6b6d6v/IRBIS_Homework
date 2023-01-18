package com.example.demo.Service;

import com.example.demo.Model.Request.CreateNewsRequest;
import com.example.demo.Model.Response.NewsResponse;
import com.example.demo.Model.Response.NewsSourceResponse;
import com.example.demo.Model.Response.NewsSubjectResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {
    @NotNull
    List<NewsResponse> findAll();

    @NotNull
    List<NewsResponse> findAll(Pageable page);

    @NotNull
    List<NewsResponse> findAllByNewsSource(String newsSource, Pageable page);

    @NotNull
    List<NewsResponse> findByNewsSubject(String newsSource, Pageable page);

    @NotNull
    NewsResponse findById(Integer NewsId);

    @NotNull
    List<NewsSourceResponse> findDistinctByNewsSourceIsNotNull();

    @NotNull
    List<NewsSubjectResponse> findDistinctByNewsSubjectIsNotNull();

    @NotNull
    NewsResponse createNews(@NotNull CreateNewsRequest request);

    @NotNull
    NewsResponse update(Integer Id, @NotNull CreateNewsRequest request);

    void delete(Integer newsId);
}
