package com.dtoropineiro.authservice.repository;

import com.dtoropineiro.authservice.model.Record;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface RecordRepository extends PagingAndSortingRepository<Record,Long> {

    Optional<Record> findByTimestamp(Date date);

}
