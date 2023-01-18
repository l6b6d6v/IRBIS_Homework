package com.example.demo.Controller;

import com.example.demo.Service.CsvWriter.CsvWriterService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/downloadCsv")
public class DownloadController {
    private final CsvWriterService csvWriterService;

    public DownloadController(CsvWriterService csvWriterService) {
        this.csvWriterService = csvWriterService;
    }

    @GetMapping(value = "/{newsSource}")
    @ResponseBody
    public ResponseEntity<Mono<Resource>> downloadCsv(@PathVariable String newsSource) {
        String fileName = String.format("%s.scv", newsSource);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = " + fileName)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .body(csvWriterService.generateScv(newsSource)
                .flatMap(x -> {
                    Resource resource = new InputStreamResource(x);
                    return Mono.just(resource);
                }));
    }
}
