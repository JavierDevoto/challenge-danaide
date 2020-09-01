package com.challenge.service;

import java.time.LocalDate;

public interface DateService {

    LocalDate getActualDate();

    LocalDate setActualDate(LocalDate date);

    LocalDate getPromoDate();

    LocalDate setPromoDate(LocalDate date);

    LocalDate nextDay();

    Boolean isPromoDay();

}
