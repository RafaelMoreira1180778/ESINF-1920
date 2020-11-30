/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf;

import java.util.Objects;

/**
 *
 * @author DEI-ISEP
 */
public class Product implements Comparable<Product> {

    private String identification;
    private int quantity;
    private long price;

    Product(String identification, int quantity, long price) {
        this.identification = identification;
        this.quantity = quantity;
        this.price = price;
    }

    Product(String identification) {
        this(identification, 0, 0);
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return identification.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.identification, other.identification)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Product o) {
        return this.getIdentification().compareToIgnoreCase(o.getIdentification());
    }
}
