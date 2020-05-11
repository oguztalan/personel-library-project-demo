package com.oguztalan.project.webprogramlama.repository;


import com.oguztalan.project.webprogramlama.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<BookEntity, Long> {
    List<BookEntity> findAll();
}
