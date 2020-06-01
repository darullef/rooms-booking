package com.darullef.roomsbooking.api;

import com.darullef.roomsbooking.model.Booking;
import com.darullef.roomsbooking.service.BookingService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> addBooking(HttpEntity<String> httpEntity) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(httpEntity.getBody());
        JSONArray jsonRooms = (JSONArray) (json.get("rooms"));

        Timestamp startTime = Timestamp.valueOf(json.get("startTime").toString());
        Timestamp endTime = Timestamp.valueOf(json.get("endTime").toString());

        List<Long> rooms = new ArrayList<Long>();
        for (Object jsonRoom : jsonRooms) {
            rooms.add((Long) jsonRoom);
        }

        Booking booking = bookingService.createBooking(startTime, endTime, rooms);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable("id") Long id) {
        try {
            Booking booking = bookingService.getBookingById(id);
            return new ResponseEntity<Booking>(booking, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Booking with id " + id + " does not exist");
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable("id") Long id, @RequestBody Booking newBooking) {
        try {
            Booking booking = bookingService.getBookingById(id);
            bookingService.updateBooking(booking, newBooking);
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Booking with id " + id + " does not exist");
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable("id") Long id) {
        try {
            bookingService.deleteBooking(id);
            return new ResponseEntity<>("Booking deleted", HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Booking with id " + id + " does not exist");
        }
    }
}
