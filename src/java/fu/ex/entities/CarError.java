/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.entities;

/**
 *
 * @author Tung Nguyen
 */
public class CarError {
    private String idError, nameError, descriptionError, purchaseError, quantityError, imgURLError, priceError;

    public CarError() {
        this.idError = "";
        this.nameError = "";
        this.descriptionError = "";
        this.purchaseError = "";
        this.quantityError = "";
        this.imgURLError = "";
        this.priceError = "";
    }

    public CarError(String idError, String nameError, String descriptionError, String purchaseError, String quantityError, String imgURLError, String priceError) {
        this.idError = idError;
        this.nameError = nameError;
        this.descriptionError = descriptionError;
        this.purchaseError = purchaseError;
        this.quantityError = quantityError;
        this.imgURLError = imgURLError;
        this.priceError = priceError;
    }

    public String getIdError() {
        return idError;
    }

    public void setIdError(String idError) {
        this.idError = idError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public String getPurchaseError() {
        return purchaseError;
    }

    public void setPurchaseError(String purchaseError) {
        this.purchaseError = purchaseError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getImgURLError() {
        return imgURLError;
    }

    public void setImgURLError(String imgURLError) {
        this.imgURLError = imgURLError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }
    
}
