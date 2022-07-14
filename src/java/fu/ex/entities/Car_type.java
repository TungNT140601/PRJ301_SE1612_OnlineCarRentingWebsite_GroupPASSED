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
public class Car_type  implements Serializable{
    private String typeID;
    private String typeType;

    public Car_type(String typeID, String typeType) {
        this.typeID = typeID;
        this.typeType = typeType;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getTypeType() {
        return typeType;
    }

    public void setTypeType(String typeType) {
        this.typeType = typeType;
    }
    
}
