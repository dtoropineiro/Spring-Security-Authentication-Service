package com.dtoropineiro.authservice.service;

import org.springframework.stereotype.Service;

@Service
public class CalculateServiceImpl implements CalculateService{
    @Override
    public String sumNumbers(int firstNumber, int secondNumber) {
        final int sum = firstNumber + secondNumber;
        return firstNumber + " + " + secondNumber + " = " + sum;
    }
}
