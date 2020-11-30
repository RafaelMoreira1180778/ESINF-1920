package esinf;

import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author DEI-ISEP
 */
public class Supermarket {

    Map<Invoice, Set<Product>> m;

    Supermarket() {
        m = new HashMap<>();
    }

    // Reads invoices from a list of String
    void getInvoices(List<String> l) {
        Invoice inv = null;
        Set<Product> sp = null;
        for (String l1 : l) {
            String[] temp = l1.split(",");
            if (l1.charAt(0) == 'I') {
                if (inv != null) {
                    m.put(inv, sp);
                }
                inv = new Invoice(temp[1], temp[2]);
                sp = new TreeSet<Product>();
            } else {
                sp.add(new Product(temp[1], Integer.parseInt(temp[2]), Long.parseLong(temp[3])));
            }
        }
        if (inv != null) {
            m.put(inv, sp);
        }
    }

    // returns a set in which each number is the number of products in the r
    // invoice 
    Map<Invoice, Integer> numberOfProductsPerInvoice() {
        Map<Invoice, Integer> numProducts = new HashMap<>();
        for (Invoice i : m.keySet()) {
            int setSize = m.get(i).size();
            numProducts.put(i, setSize);
        }
        return numProducts;
    }

    // returns a Set of invoices in which each date is >d1 and <d2
    Set<Invoice> betweenDates(LocalDate d1, LocalDate d2) {
        Set<Invoice> invSet = new TreeSet<>();
        for (Invoice i : m.keySet()) {
            if (i.getDate().isAfter(d1) && i.getDate().isBefore(d2)) {
                invSet.add(i);
            }
        }
        return invSet;
    }

    // returns the sum of the price of the product in all the invoices
    long totalOfProduct(String productId) {
        long totalSum = 0;
        for (Invoice i : m.keySet()) {
            for (Product prd : m.get(i)) {
                if (prd.getIdentification().equalsIgnoreCase(productId)) {
                    totalSum += prd.getPrice() * prd.getQuantity();
                }
            }
        }
        return totalSum;
    }

    // converts a map of invoices and troducts to a map which key is a product 
    // identification and the values are a set of the invoice references 
    // in which it appearss
    Map<String, Set<Invoice>> convertInvoices() {
        Map<String, Set<Invoice>> fm = new HashMap<>();

        for (Map.Entry<Invoice, Set<Product>> entry : m.entrySet()) {
            for (Product prd : entry.getValue()) {
                Set<Invoice> sp = fm.get(prd.getIdentification());
                if (fm.get(prd.getIdentification()) == null) {
                    sp = new TreeSet<>();
                    sp.add(entry.getKey());
                    fm.put(prd.getIdentification(), sp);
                } else {
                    sp.add(entry.getKey());
                }
            }
        }
        return fm;
    }
}
