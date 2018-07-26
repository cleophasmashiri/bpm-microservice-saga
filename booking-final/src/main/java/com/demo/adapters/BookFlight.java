package com.demo.adapters;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

/**
 * Created by cleophas on 2018/07/23.
 */

@Named("bookFlight")
public class BookFlight implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
//        String email = (String) execution.getVariable("email");
//        String message = "Booked Flight, Email: " + email;
//        System.out.println(message);
        throw new RuntimeException("message");
    }
}
