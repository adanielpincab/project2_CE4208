/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingEJB;

import jakarta.ejb.Remove;
import jakarta.ejb.Stateful;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Reiner
 */
@Stateful
public class ShoppingCartBean
        implements ShoppingCart {

    private HashMap<String, Integer> items = new HashMap<>();
    
    @Override
    public void addItem(String id, int quantity) {
        // obtain current number of items in cart
        Integer orderQuantity = items.get(id);
        if (orderQuantity == null) {
            orderQuantity = 0;
        }
        // adjust quantity and put back to cart
        orderQuantity += quantity;
        items.put(id, orderQuantity);
    }

    @Override
    public void removeItem(String id, int quantity) {
        // obtain current number of items in cart
        Integer orderQuantity = items.get(id);
        if (orderQuantity == null) {
            orderQuantity = 0;
        }
        // adjust quantity and put back to cart
        orderQuantity -= quantity;
        if (orderQuantity <= 0) {
            // final quantity less equal 0 - remove from cart
            items.remove(id);
        } else {
            // final quantity > 0 - adjust quantity
            items.put(id, orderQuantity);
        }
        
    }

    @Override
    @Remove
    public String checkout() {
        // dummy checkout method that just returns message for successful 
        // checkout
        String message = "You checked out the following items:\n<br>" + getItemList();
        // empty storage
        items.clear();
        return message;
    }

    @Override
    @Remove
    public void cancel() {
        // no action required - annotation @Remove indicates
        // that calling this method should remove the EJB which will
        // automatically destroy instance variables
        // empty storage
        items.clear();
    }

    @Override
    public String getItemList() {
        String message = "";
        Set<String> keys = items.keySet();
        Iterator<String> it = keys.iterator();
        String k;
        while (it.hasNext()) {
            k = it.next();
            message += k + ", quantity: " + items.get(k) + "; \n<br>";
        }
        return message;
    }
}
