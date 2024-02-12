package ru.sl1degod.demoexamen.models;

import java.util.List;

public class Equipments {
    private String id;
    private String serial_number;
    private String name;
    public Equipments(String id, String serial_number, String name) {
        this.id = id;
        this.serial_number = serial_number;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
