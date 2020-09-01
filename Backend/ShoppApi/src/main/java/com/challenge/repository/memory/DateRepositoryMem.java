package com.challenge.repository.memory;

import org.springframework.stereotype.Repository;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DateRepositoryMem {

    private LocalDate actualDate = LocalDate.now();
    private LocalDate promoDate = LocalDate.now();

    private PropertyChangeSupport support;

    public DateRepositoryMem() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public LocalDate getActualDate() {
        return actualDate;
    }

    public LocalDate setActualDate(LocalDate date) {
        support.firePropertyChange("actualDate", actualDate, date);
        actualDate = date;
        return actualDate;
    }

    public LocalDate getPromoDate() {
        return promoDate;
    }

    public LocalDate setPromoDate(LocalDate date) {
        promoDate = date;
        return promoDate;
    }

}
