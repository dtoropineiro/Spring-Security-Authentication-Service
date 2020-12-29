package com.dtoropineiro.authservice.service;

import com.dtoropineiro.authservice.model.Record;

import java.util.List;

public interface RecordService {
    List<Record> findAll(Integer page, Integer size);
}
