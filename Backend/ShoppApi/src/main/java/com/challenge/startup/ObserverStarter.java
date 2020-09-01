package com.challenge.startup;

import com.challenge.repository.memory.DateRepositoryMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ObserverStarter {

    @Autowired
    private DateRepositoryMem dateRepositoryMem;

    @Autowired
    private Observer observer;

    @PostConstruct
    public void startDaemon() {
        dateRepositoryMem.addPropertyChangeListener(observer);
    }

}
