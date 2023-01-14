package com.example.demo.Service;

import com.example.demo.Domain.News;
import com.example.demo.Model.Request.CreateNewsRequest;
import com.example.demo.Model.Response.NewsResponse;
import com.example.demo.Repository.NewsRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<NewsResponse> findAll() {
        return newsRepository.findAll()
                .stream()
                .map(this::buildNewsResponse)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public NewsResponse findById(@NotNull Integer _idNews) {
        return newsRepository.findById(_idNews)
                .map(this::buildNewsResponse)
                .orElseThrow(() -> new EntityNotFoundException("News " + _idNews + " is not found"));
    }

    @NotNull
    @Override
    @Transactional
    public NewsResponse createNews(@NotNull CreateNewsRequest request) {
        News news = buildNewsRequest(request);
        return buildNewsResponse(newsRepository.save(news));
    }

    @NotNull
    @Override
    @Transactional
    public NewsResponse update(@NotNull Integer _idNews, @NotNull CreateNewsRequest request) {
        News news = newsRepository.findById(_idNews).orElseThrow(() -> new EntityNotFoundException("News " + _idNews + "is not found"));
        newsUpdate(news, request);
        return buildNewsResponse(newsRepository.save(news));
    }

    @Override
    @Transactional
    public void delete(@NotNull Integer _idNews) {
        newsRepository.deleteById(_idNews);
    }

    @NotNull
    private NewsResponse buildNewsResponse(@NotNull News news) {
        return new NewsResponse()
                .setNews(news.getNews())
                .setNewsSubject(news.getNewsSubject())
                .setNewsSource(news.getNewsSource());
    }

    @NotNull
    private News buildNewsRequest(@NotNull CreateNewsRequest request) {
        return new News()
                .setNews(request.getNews())
                .setNewsSubject(request.getNewsSubject())
                .setNewsSource(request.getNewsSource());
    }

    private void newsUpdate(@NotNull News news, @NotNull CreateNewsRequest request) {
        ofNullable(request.getNews()).map(news::setNews);
        ofNullable(request.getNewsSubject()).map(news::setNewsSubject);
        ofNullable(request.getNewsSource()).map(news::setNewsSource);
    }
}
