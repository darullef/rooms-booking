package com.darullef.roomsbooking.service;

import com.darullef.roomsbooking.dao.BookingDao;
import com.darullef.roomsbooking.dao.RoomDao;
import com.darullef.roomsbooking.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookingService {

    @Autowired
    private BookingDao bookingDao;
    @Autowired
    private RoomDao roomDao;

    public Booking createBooking(Timestamp startTime, Timestamp endTime, List<Long> rooms) {
        Booking booking = new Booking();
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        Long roomIdForException = (long) 0;

        try {
            for(Long room_id : rooms) {
                roomIdForException = room_id;
                booking.getRoom().add(roomDao.findById(room_id).get());
            }
        } catch (NoSuchElementException ex ) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Room with id " + roomIdForException + " does not exist");
        }

        bookingDao.save(booking);
        return booking;
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
