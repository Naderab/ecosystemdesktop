/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pointcollect.entities;

/**
 *
 * @author Khouloud
 */
import java.util.ArrayList;
import java.util.List;

public class TypePoint {

    int id;
    String name;
    String description;
    private List<Markers> address = new ArrayList();

    public TypePoint() {
    }

    public TypePoint(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public TypePoint(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public TypePoint(String name) {
        this.name = name;
    }

    public TypePoint(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "TypePoint{id=" + id + ", name=" + name + ", description=" + description + '}';
    }

}
