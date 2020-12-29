package com.dtoropineiro.authservice.controller;

import com.dtoropineiro.authservice.model.Record;
import com.dtoropineiro.authservice.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/records")
public class RecordController {

    RecordService recordService;

    @GetMapping("/historic/{page}/{size}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Record> record(@PathVariable("page")int page, @PathVariable("size")int size) {
        return recordService.findAll(size,page);
    }
}
