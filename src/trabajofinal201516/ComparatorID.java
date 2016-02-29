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
public class ComparatorID implements Comparator<Product>  {

    @Override
    public int compare(Product o1, Product o2) {
        int id1 = o1.getProductID();
        int id2 = o2.getProductID();
        return Integer.compare(id1, id2);
    }
    
}
