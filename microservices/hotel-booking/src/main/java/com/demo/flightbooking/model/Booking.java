package com.demo.flightbooking.model;

import javax.persistence.Table;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by cleophas on 2018/07/26.
 */
@Entity
@Table(name = "bookings")
public class Booking {

    public Booking() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="booking_id")
    private long id;

    private String email;
    private String status;
    private String correlationId;
    private Date DateBooked;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateBooked() {
        return DateBooked;
    }

    public void setDateBooked(Date dateBooked) {
        DateBooked = dateBooked;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
