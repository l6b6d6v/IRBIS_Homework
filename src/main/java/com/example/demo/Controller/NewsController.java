package com.example.demo.Controller;

import com.example.demo.Domain.News;
import com.example.demo.Model.Request.CreateNewsRequest;
import com.example.demo.Model.Response.NewsResponse;
import com.example.demo.Model.Response.NewsSourceResponse;
import com.example.demo.Model.Response.NewsSubjectResponse;
import com.example.demo.Service.NewsService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    //API для получения данных из БД (I. По источнику)
    @GetMapping(value = "/source", produces = APPLICATION_JSON_VALUE)
    public List<NewsSourceResponse> findDistinctByNewsSourceIsNotNull() {
        return newsService.findDistinctByNewsSourceIsNotNull();
    }

    //API для получения данных из БД (II. По тематике)
    @GetMapping(value = "/subject", produces = APPLICATION_JSON_VALUE)
    public List<NewsSubjectResponse> findDistinctByNewsSubjectIsNotNull() {
        return newsService.findDistinctByNewsSubjectIsNotNull();
    }

    @GetMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    public List<NewsResponse> findAll() {
        return newsService.findAll();
    }

    //API для получения данных из БД (III. Всех)
    @GetMapping(value = "/pageable", produces = APPLICATION_JSON_VALUE)
    public List<NewsResponse> findAll(
            @PageableDefault(
                    size = 3,
                    sort = {"id"},
                    direction = Sort.Direction.DESC
            ) Pageable page) {
        return newsService.findAll(page);
    }

    //API для получения данных из БД (III. Всех по источнику)
    @GetMapping(value = "/src/{newsSource}",produces = APPLICATION_JSON_VALUE)
    public List<NewsResponse> findAllByNewsSource(
            @PathVariable String newsSource, @PageableDefault(
            size = 3,
            sort = {"id"},
            direction = Sort.Direction.DESC
    ) Pageable page) {
        return newsService.findAllByNewsSource(newsSource, page);
    }

    //API для получения данных из БД (III. Всех по тематике)
    @GetMapping(value = "/sbj/{newsSubject}",produces = APPLICATION_JSON_VALUE)
    public List<NewsResponse> findByNewsSubject(
            @PathVariable String newsSubject, @PageableDefault(
            size = 3,
            sort = {"id"},
            direction = Sort.Direction.DESC
    ) Pageable page
    ) {
        page = PageRequest.of(0, 3);
        return newsService.findByNewsSubject(newsSubject, page);
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