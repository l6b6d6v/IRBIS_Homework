package com.example.demo.Repository;

import com.example.demo.Domain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface NewsRepository extends JpaRepository<News, Integer> {
}
