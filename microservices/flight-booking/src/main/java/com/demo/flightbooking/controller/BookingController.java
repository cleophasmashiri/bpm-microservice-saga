package com.demo.flightbooking.controller;

import java.util.Collection;
import java.util.Date;

import com.demo.flightbooking.model.Booking;
import com.demo.flightbooking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 * Created by cleophas on 2018/07/26.
 */

@RestController
@RequestMapping("/flight")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Collection<Booking>> getBookings() {
        return new ResponseEntity<>(bookingRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.GET, value="/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable long id) {
        Booking booking = bookingRepository.findOne(id);

        if (booking != null) {
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(booking, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addBooking(@RequestBody Booking booking) {
        booking.setDateBooked(new Date());
        return new ResponseEntity<>(bookingRepository.save(booking), HttpStatus.CREATED);
    }


}
