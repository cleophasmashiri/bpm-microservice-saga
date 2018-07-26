package com.demo.adapters;

import com.demo.facades.BookingFacade;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

/**
 * Created by cleophas on 2018/07/23.
 */

@Named("cancelCar")
public class CancelCar implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String email = (String) execution.getVariable("email");
        String message = "Cancel Car, Email: " + email;
        String correlationId = (String) execution.getVariable("correlationId");
        BookingFacade bookingFacade = new BookingFacade();
        bookingFacade.makeBooking("Cancelled Car", email, correlationId, "car");
        System.out.println(message);
    }
}
