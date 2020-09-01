package com.challenge.service;

import com.challenge.repository.memory.DateRepositoryMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateServiceImpl implements DateService{

    @Autowired
    private DateRepositoryMem dateRepository;

    @Override
    public LocalDate getActualDate() {
        return dateRepository.getActualDate();
    }

    @Override
    public LocalDate setActualDate(LocalDate date) {
        return dateRepository.setActualDate(date);
    }

    @Override
    public LocalDate getPromoDate() {
        return dateRepository.getPromoDate();
    }

    @Override
    public LocalDate setPromoDate(LocalDate date) {
        return dateRepository.setPromoDate(date);
    }

    @Override
    public LocalDate nextDay() {
        LocalDate actualDate = dateRepository.getActualDate();
        actualDate = actualDate.plusDays(1);
        return dateRepository.setActualDate(actualDate);
    }

    @Override
    public Boolean isPromoDay() {
        LocalDate actualDate = dateRepository.getActualDate();
        LocalDate promoDate = dateRepository.getPromoDate();
        return actualDate.compareTo(promoDate) == 0;
    }

}
