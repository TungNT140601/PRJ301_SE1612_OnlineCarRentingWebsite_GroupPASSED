/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.entities;

import java.io.Serializable;

/**
 *
 * @author Tung Nguyen
 */
public class Car implements Serializable{
    private String carID;
    private Car_type typeID;
    private String carName;
    private Car_brand brandID;
    private String description;
    private String purchaseDate;
    private int quantity;
    private String imgURL;
    private float price;

    public Car() {
        this.carID = "";
        this.typeID = null;
        this.carName = "";
        this.brandID = null;
        this.description = "";
        this.purchaseDate = "";
        this.quantity = 0;
        this.imgURL = "";
        this.price = 0;
    }

    public Car(String carID, Car_type typeID, String carName, Car_brand brandID, String description, String purchaseDate, int quantity, String imgURL, float price) {
        this.carID = carID;
        this.typeID = typeID;
        this.carName = carName;
        this.brandID = brandID;
        this.description = description;
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
        this.imgURL = imgURL;
        this.price = price;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Car_type getTypeID() {
        return typeID;
    }

    public void setTypeID(Car_type typeID) {
        this.typeID = typeID;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Car_brand getBrandID() {
        return brandID;
    }

    public void setBrandID(Car_brand brandID) {
        this.brandID = brandID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
}
