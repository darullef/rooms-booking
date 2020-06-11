package com.darullef.roomsbooking.api;

import com.darullef.roomsbooking.model.Room;
import com.darullef.roomsbooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<?> addRoom(@Valid @RequestBody Room room) {
        roomService.createRoom(room);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllRooms() {
        try {
            List<Room> rooms = roomService.getAllRooms();
            return new ResponseEntity<>(rooms, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No rooms exist");
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable("id") Long id) {
        try {
            Room room = roomService.getRoomById(id);
            return new ResponseEntity<>(room, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Room with id " + id + " does not exist");
        }
    }

    @GetMapping(path = "/booked")
    public ResponseEntity<?> getAllBookedRooms() {
        List<Room> rooms= roomService.getAllBookedRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping(path = "/f")
    public ResponseEntity<?> filterRooms(@RequestParam Map<String,String> allParams) {
        List<Room> rooms = new ArrayList<>();

        //capacity: from, to;   date: start, end;   projector: has
        if(allParams.containsKey("has")) {
            boolean x = Boolean.parseBoolean(allParams.get("has"));
            rooms =  Stream.concat(rooms.stream(), roomService.getAllRoomsWithProjector(x).stream())
                    .collect(Collectors.toList());
        }
        if(allParams.containsKey("from") || allParams.containsKey("to")) {
            int from = Integer.parseInt(allParams.get("from"));
            int to = Integer.parseInt(allParams.get("to"));
            rooms =  Stream.concat(rooms.stream(), roomService.getAllRoomsWhereCapacityBetween(from, to).stream())
                    .collect(Collectors.toList());
        }
        if(allParams.containsKey("start") || allParams.containsKey("end")) {
            Timestamp start = Timestamp.valueOf(allParams.get("start"));
            Timestamp end = Timestamp.valueOf(allParams.get("end"));
            rooms =  Stream.concat(rooms.stream(), roomService.getAllFreeRoomsInTime(start, end).stream())
                    .collect(Collectors.toList());
        }

        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping(path = "/fr")
    public ResponseEntity<?> getAllFreeRoomsInTime(@RequestParam Timestamp start, @RequestParam Timestamp end) {
        List<Room> rooms = roomService.getAllFreeRoomsInTime(start, end);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable("id") Long id, @RequestBody Room newRoom) {
        try {
            Room room = roomService.getRoomById(id);
            roomService.updateRoom(room, newRoom);
            return new ResponseEntity<>(room, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Room with id " + id + " does not exist");
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable("id") Long id) {
        try {
            roomService.deleteRoom(id);
            return new ResponseEntity<>("Room deleted", HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Room with id " + id + " does not exist");
        }
    }
}
