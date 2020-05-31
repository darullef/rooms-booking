package com.darullef.roomsbooking.service;

import com.darullef.roomsbooking.dao.BookingDao;
import com.darullef.roomsbooking.model.Booking;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingDao bookingDao;

    public void createBooking(Booking booking) {
        booking.setStartTime(DateTime.parse(booking.getStartTime().toString()));
        booking.setEndTime(DateTime.parse(booking.getEndTime().toString()));
        bookingDao.save(booking);
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookingDao.findAll());
    }

    public Booking getBookingById(Long id) {
        return bookingDao.findById(id).get();
    }

    public void updateBooking(Booking booking, Booking newBooking) {
        booking.setStartTime(newBooking.getStartTime());
        booking.setEndTime(newBooking.getEndTime());
        bookingDao.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingDao.delete(bookingDao.findById(id).get());
    }

}
