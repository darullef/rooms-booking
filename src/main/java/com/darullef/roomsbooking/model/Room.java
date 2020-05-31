package com.darullef.roomsbooking.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "number")
    private String number;

    @NotNull
    @Column(name = "capacity")
    private int capacity;

    @Column(name = "has_projector")
    private boolean hasProjector;

    public Room() {}

    public Room(Long id, String number, int capacity, boolean hasProjector) {
        this.id = id;
        this.number = number;
        this.capacity = capacity;
        this.hasProjector = hasProjector;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }
}
