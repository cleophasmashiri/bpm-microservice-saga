package com.demo.adapters;

import com.demo.facades.BookingFacade;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

/**
 * Created by cleophas on 2018/07/23.
 */

@Named("cancelHotel")
public class CancelHotel implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String email = (String) execution.getVariable("email");
        execution.setVariable("cancelHotel", "Done");
        String message = "Cancel Hotel, Email: " + email;
        String correlationId = (String) execution.getVariable("correlationId");
        BookingFacade bookingFacade = new BookingFacade();
        bookingFacade.makeBooking("Cancelled Hotel", email, correlationId, "hotel");
        System.out.println(message);
    }
}
