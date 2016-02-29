/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// developed by Pablo Jesus Espinosa Bermejo B2
package trabajofinal201516;

import java.util.Comparator;

/**
 *
 * @author espi_
 */
public class ComparatorCategory implements Comparator<Product>  {

    @Override
    public int compare(Product o1, Product o2) {
        String id1 = o1.getCategory();
        String id2 = o2.getCategory();
        return id1.compareTo(id2);
    }
    
}