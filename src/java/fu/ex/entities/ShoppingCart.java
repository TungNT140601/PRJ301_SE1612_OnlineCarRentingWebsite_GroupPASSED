/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.entities;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class ShoppingCart implements Serializable{
    private Car car;
    private int quantity;
    private Rent rentID;
    private String pickupDate;
    private String returnDate;
    
    public ShoppingCart() {
    }

    public ShoppingCart(Car car, int quantity, Rent rentID, String pickupDate, String returnDate) {
        this.car = car;
        this.quantity = quantity;
        this.rentID = rentID;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Rent getRentID() {
        return rentID;
    }

    public void setRentID(Rent rentID) {
        this.rentID = rentID;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

   
 
}
