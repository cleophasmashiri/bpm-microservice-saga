package com.demo.flightbooking.repository;

import com.demo.flightbooking.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by cleophas on 2018/07/26.
 */

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    Collection<Booking> findAll();
}
