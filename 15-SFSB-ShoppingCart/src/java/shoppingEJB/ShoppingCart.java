/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingEJB;

import jakarta.ejb.Local;



/**
 *
 * @author Reiner
 */
@Local
public interface ShoppingCart {
    /**
     * Adds a number of items to the shopping cart.
     * @param id - ID of the item to be added
     * @param quantity - number of items to be added
     */
    public void addItem(String id, int quantity);
    /**
     * Removes a number items from the shopping cart. If quantity exceeds
     * the number of present items, number is set to 0.
     * @param id - ID of the item to be removed
     * @param quantity - number of items to be removed. 
     */
    public void removeItem(String id, int quantity);
    /**
     * Proceed with the checkout by asking for billing information. Checkout 
     * will terminate the current session for the EJB.
     * @return Message indicating result of checkout.
     */
    public String checkout();
    /**
     * Cancels the current ordering process. Cancel will terminate the current 
     * session for the EJB.
     */
    public void cancel();
    
    /**
     * Returns a string representing content of shopping cart
     * @return 
     */
    public String getItemList();    
}
