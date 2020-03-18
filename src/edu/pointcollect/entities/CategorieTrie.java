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
public class CategorieTrie {
    
private int id;
  private String name;
  private String description;
  private String img;
  private List<SousCategorieTrie> l;
  
  public CategorieTrie() {}
  
  public CategorieTrie(int id, String name, String description, String img)
  {
    this.id = id;
    this.name = name;
    this.description = description;
    this.img = img;
  }
  
  public CategorieTrie(String name, String description, String img)
  {
    this.name = name;
    this.description = description;
    this.img = img;
  }
    public CategorieTrie(String name, String description)
  {
    this.name = name;
    this.description = description;
   
  }
  
  
  public CategorieTrie(String name, String description, String img, List<SousCategorieTrie> l)
  {
    this.name = name;
    this.description = description;
    this.img = img;
    this.l = new ArrayList();
  }

    public CategorieTrie(String name) {
        this.name = name;
    }
  
      public CategorieTrie(int  id) {
        this.id = id;
    }

  public int getId()
  {
    return id;
  }
  
  public void setId(int id) {
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
  
  public List<SousCategorieTrie> getL() {
    return l;
  }
  
  public void setL(List<SousCategorieTrie> l) {
    this.l = l;
  }
  
  public String toString()
  {
    return "CategorieTrie{id=" + id + ", name=" + name + ", description=" + description + ", img=" + img + '}';
  }
}