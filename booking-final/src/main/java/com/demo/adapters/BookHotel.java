package com.demo.adapters;

import com.demo.facades.BookingFacade;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

/**
 * Created by cleophas on 2018/07/23.
 */

@Named("bookHotel")
public class BookHotel implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String email = (String) execution.getVariable("email");
        String correlationId = (String) execution.getVariable("correlationId");
        execution.setVariable("bookHotel", "Done");
        String message = "Book Hotel, Email: " + email;

        BookingFacade bookingFacade = new BookingFacade();
        bookingFacade.makeBooking("Hotel Booked", email, correlationId, "hotel");

        System.out.println(message);
    }
}
