package com.dtoropineiro.authservice.service;

import com.dtoropineiro.authservice.model.Record;
import com.dtoropineiro.authservice.repository.RecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class RecordServiceImpl implements RecordService {

    RecordRepository recordRepository;

    @Override
    public List<Record> findAll(Integer page, Integer size) {
        return recordRepository.findAll(PageRequest.of(page, size)).toList();
    }
}
