package com.darullef.roomsbooking.api;

import com.darullef.roomsbooking.model.Room;
import com.darullef.roomsbooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

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
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable("id") Long id) {
        try {
            Room room = roomService.getRoomById(id);
            return new ResponseEntity<Room>(room, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Room with id " + id + " does not exist");
        }
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
