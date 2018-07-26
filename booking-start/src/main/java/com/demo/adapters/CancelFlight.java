package com.demo.adapters;

import com.demo.facades.BookingFacade;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

/**
 * Created by cleophas on 2018/07/23.
 */

@Named("cancelFlight")
public class CancelFlight implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String email = (String) execution.getVariable("email");
        String message = "Cancel Flight, Email: " + email;
        String correlationId = (String) execution.getVariable("correlationId");
        BookingFacade bookingFacade = new BookingFacade();
        bookingFacade.makeBooking("Cancelled Flight", email, correlationId, "flight");
        System.out.println(message);
    }
}
