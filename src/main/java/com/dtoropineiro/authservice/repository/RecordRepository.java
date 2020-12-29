package com.dtoropineiro.authservice.repository;

import com.dtoropineiro.authservice.model.Record;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends PagingAndSortingRepository<Record,Long> {
}
