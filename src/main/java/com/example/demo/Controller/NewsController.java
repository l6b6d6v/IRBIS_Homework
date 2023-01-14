package com.example.demo.Controller;

import com.example.demo.Model.Request.CreateNewsRequest;
import com.example.demo.Model.Response.NewsResponse;
import com.example.demo.Service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<NewsResponse> findAll() {
        return newsService.findAll();
    }

    @GetMapping(value = "/{NewsId}", produces = APPLICATION_JSON_VALUE)
    public NewsResponse findById(@PathVariable Integer NewsId) {
        return newsService.findById(NewsId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public NewsResponse create(@RequestBody CreateNewsRequest request) {
        return newsService.createNews(request);
    }

    @PatchMapping(value = "/{NewsId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public NewsResponse update(@PathVariable Integer NewsId, @RequestBody CreateNewsRequest request) {
        return newsService.update(NewsId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{NewsId}", produces = APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Integer NewsId) {
        newsService.delete(NewsId);
    }
}