package com.example.demo.Repository;

import com.example.demo.Domain.News;
import com.example.demo.Domain.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface NewsRepository extends JpaRepository<News, Integer> {

    List<News> findDistinctByNewsSourceIsNotNull();
    List<News> findDistinctByNewsSubjectIsNotNull();

    Page<News> findAllByNewsSource(String newsSource, Pageable pageable);
    Page<News> findByNewsSubject(String newsSubject, Pageable pageable);

    //List<News> findNewsByNewsSource(String newsSource);

    @NotNull
    List<News> findAll();

    @NotNull
    Page<News> findAll(@NotNull Pageable pageable);

    Page<News> findById(Integer id, Pageable pageable);

}
