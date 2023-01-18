package com.example.demo.Service.CsvWriter;

import com.example.demo.Domain.News;
import com.example.demo.Repository.NewsRepository;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvWriterService {
    private final NewsRepository repository;

    public Mono<ByteArrayInputStream> generateScv(@NotNull String newsSource) {
        String[] columns = {"newsSubject", "news"};

        List<News> news = repository.findAllByNewsSource(newsSource);

        return Mono.fromCallable(() -> {
            try {
                ByteArrayInOutStream stream = new ByteArrayInOutStream();
                OutputStreamWriter streamWriter = new OutputStreamWriter(stream);
                CSVWriter writer = new CSVWriter(streamWriter);

                ColumnPositionMappingStrategy<News> mappingStrategy = new ColumnPositionMappingStrategy<>();
                mappingStrategy.setType(News.class);
                mappingStrategy.setColumnMapping(columns);
                writer.writeNext(columns);

                StatefulBeanToCsv<News> beanToCsv = new StatefulBeanToCsvBuilder<News>(writer)
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                        .withMappingStrategy(mappingStrategy)
                        .withSeparator(',')
                        .build();

                beanToCsv.write(news);
                streamWriter.flush();
                return stream.getInputStream();
            }
            catch (CsvException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
