package com.example.demo.Service;

import com.example.demo.Model.Request.CreateNewsRequest;
import com.example.demo.Model.Response.NewsResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface NewsService {
    @NotNull
    List<NewsResponse> findAll();

    @NotNull
    NewsResponse findById(Integer NewsId);

    @NotNull
    NewsResponse createNews(@NotNull CreateNewsRequest request);

    @NotNull
    NewsResponse update(Integer Id, @NotNull CreateNewsRequest request);

    void delete(Integer newsId);
}
