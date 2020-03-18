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

public class SousCategorieTrie {
    
 private int id;
  private String name;
  private String description;
  private String img;
  private int idCat;
  
  public SousCategorieTrie() {}
  
  public SousCategorieTrie(String name, String description, String img, int idCat)
  {
    this.name = name;
    this.description = description;
    this.img = img;
    this.idCat = idCat; }
  
   public SousCategorieTrie(String name, String description, String img)
  {
    this.name = name;
    this.description = description;
    this.img = img;
    }
  
  public SousCategorieTrie(int id, String name, String description, String img, int idCat) { this.id = id;
    this.name = name;
    this.description = description;
    this.img = img;
    this.idCat = idCat;
  }
  
  public int getId() { return id; }
  
  public void setId(int id)
  {
    this.id = id;
  }
  
  public String getName() {
    return name;
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
  
  public String getImg() {
    return img;
  }
  
  public void setImg(String img) {
    this.img = img;
  }
  
  public int getIdCat() {
    return idCat;
  }
  
  public void setIdCat(int idCat) {
    this.idCat = idCat;
  }
  
  public String toString()
  {
    return "SousCategorieTrie{id=" + id + ", name=" + name + ", description=" + description + ", img=" + img + ", idCat=" + idCat + '}';
  }
  
  
  
  String model;
    public SousCategorieTrie(String model) {
        this.model = model;
    }

  
}
