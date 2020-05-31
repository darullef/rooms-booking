package com.darullef.roomsbooking.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @OneToMany(targetEntity = Room.class)
    private List<Room> rooms = new ArrayList<>();

    public Booking() {}

    public Booking(Long id, Timestamp startTime, Timestamp endTime, List<Room> room) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.rooms = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public List<Room> getRoom() {
        return rooms;
    }

    public void setRoom(List<Room> room) {
        this.rooms = room;
    }
}
