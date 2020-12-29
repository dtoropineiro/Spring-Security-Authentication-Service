package com.dtoropineiro.authservice.controller;

import com.dtoropineiro.authservice.model.Record;
import com.dtoropineiro.authservice.model.User;
import com.dtoropineiro.authservice.repository.RecordRepository;
import com.dtoropineiro.authservice.service.CalculateService;
import com.dtoropineiro.authservice.service.RecordService;
import com.dtoropineiro.authservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/private")
public class CalculateController {

	CalculateService calculateService;

	RecordRepository recordRepository;

	UserService userService;

	RecordService recordService;

	@GetMapping("/sum/{firstNumber}/{secondNumber}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String calculate(@PathVariable("firstNumber")int firstNumber, @PathVariable("secondNumber")int secondNumber) {
		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> userOptional = userService.getUser(currentUserName);

		if(userOptional.isPresent()){
			User user = userOptional.get();
			recordRepository.save(Record.builder()
					.timestamp(LocalDateTime.now())
					.user(user)
					.build());
		}
		return calculateService.sumNumbers(firstNumber,secondNumber);
	}

}
