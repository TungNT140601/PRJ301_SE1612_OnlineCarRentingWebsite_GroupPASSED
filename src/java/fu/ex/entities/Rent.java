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
public class Rent implements Serializable{
    private String rentID;
    private float amount;
    private Customers customerID;

    public Rent() {
    }

    public Rent(String rentID, float amount, Customers customerID) {
        this.rentID = rentID;
        this.amount = amount;
        this.customerID = customerID;
    }

    public String getRentID() {
        return rentID;
    }

    public void setRentID(String rentID) {
        this.rentID = rentID;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Customers getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customers customerID) {
        this.customerID = customerID;
    }
     
}
