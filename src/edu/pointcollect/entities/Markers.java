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
public class Markers {
    
 int id;
  
  int typePointCollect;
  
  String name;
  
  String address;
  
  Double lat;
  
  Double ing;
  
  String slug;
  
  String typeName;

  public Markers() {}
  

  public Markers(int id, int typePointCollect, String name, String address, Double lat, Double ing, String slug)
  {
    this.id = id;
    this.typePointCollect = typePointCollect;
    this.name = name;
    this.address = address;
    this.lat = lat;
    this.ing = ing;
    this.slug = slug;
  }

    public Markers(int typePointCollect, String name, String address, Double lat, Double ing, String slug) {
        this.typePointCollect = typePointCollect;
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.ing = ing;
        this.slug = slug;
    }

    public Markers(int id, int typePointCollect, String name, String address, Double lat, Double ing, String slug, String typeName) {
        this.id = id;
        this.typePointCollect = typePointCollect;
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.ing = ing;
        this.slug = slug;
        this.typeName = typeName;
    }

    public Markers(String address, Double lat, Double ing) {
        this.address = address;
        this.lat = lat;
        this.ing = ing;
    }

    public Markers(String name, String address, Double lat, Double ing) {
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.ing = ing;
    }

    public Markers(int id) {
        this.id = id;
    }

    public Markers(String name, String address, Double lat, Double ing, String slug) {
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.ing = ing;
        this.slug = slug;
    }
  
 
  


  public int getId()
  {
    return id;
  }
  
  public int getTypePointCollect() {
    return typePointCollect;
  }
  
  public String getName() {
    return name;
  }
  
  public String getAddress() {
    return address;
  }
  
  public Double getLat() {
    return lat;
  }
  
  public Double getIng() {
    return ing;
  }
  
  public String getSlug() {
    return slug;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public void setTypePointCollect(int typePointCollect) {
    this.typePointCollect = typePointCollect;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setAddress(String address) {
    this.address = address;
  }
  
  public void setLat(Double lat) {
    this.lat = lat;
  }
  
  public void setIng(Double ing) {
    this.ing = ing;
  }
  
  public void setSlug(String slug) {
    this.slug = slug;
  }
  
  public String toString()
  {
    return "Markers{id=" + id + ", typePointCollect=" + typePointCollect + ", name=" + name + ", address=" + address + ", lat=" + lat + ", ing=" + ing + ", slug=" + slug + '}';
  }
}
