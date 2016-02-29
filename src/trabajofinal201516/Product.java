/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// developed by Pablo Jesus Espinosa Bermejo B2
package trabajofinal201516;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author espi_
 */
public class Product {

    private int productID;
    private String description;
    private String sport;
    private String category;
    private String colour;
    private String size;
    private int units;
    private float price;
    private float VAT;

    public Product() {
        Random r = new Random();
        this.productID = r.nextInt(50000);
        this.units = r.nextInt(50) + 25;
        this.price = r.nextFloat() * 125;
        this.VAT = (float) 21;
        String[] descriptions = {"Muy confortable", "Perfecto para el deporte", "Valido para todos los publicos",
            "Te quedara como un guante", "Mejora tu golpeo"};
        this.description = descriptions[r.nextInt(descriptions.length)];
        String[] sports = {"Rugby", "Basketball", "Football", "Handball", "Tennis", "Athletism", "Table Tennis", "Padel", "Cycling"};
        this.sport = sports[r.nextInt(sports.length)];
        String[] categories = {"Accesories", "Shoes", "Facilities", "T-Shirts", "Shorts", "Underwear", "Vitamins"};
        this.category = categories[r.nextInt(categories.length)];
        String[] colours = {"Red", "Orange", "Pink", "Blue", "Black", "White", "Yellow", "Green", "Purple", "Magenta"};
        this.colour = colours[r.nextInt(colours.length)];
        String[] sizes = {"XS", "S", "M", "L", "XL", "XXL", "UNI-SIZE"};
        this.size = sizes[r.nextInt(sizes.length)];
    }

    public void randomProducts(ArrayList<Product> p) {
        int x = 50;
        Scanner sc = new Scanner(System.in);

        System.out.printf("Number of products to create(push enter for default):");
        String aux = sc.nextLine();
        if (!aux.isEmpty()) {

            x = Integer.parseInt(aux);
        }
        for (int i = 0; i < x; i++) {
            Product y = new Product();
            p.add(y);
        }
        System.out.println("PRODUCTS WERE SUCCESSFULLY CREATED!");
    }

    public void exportColumns(ArrayList<Product> p) throws IOException {
        Scanner sc = new Scanner(System.in);
        Customer c = new Customer();
        String path;
        System.out.printf("Write the name of the file to export: ");
        String filename = sc.nextLine();
        path = c.filePath("Articulos");
        File f = new File(path);
        f.mkdir();
        path = path + File.separator + filename;
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path)));
            for (int i = 0; i < p.size(); i++) {
                //PRODUCT ID,SPORT
                pw.printf("%-7s%-14s%-10.2f%-4d%-12s%-10s%-10s%-6.2f%-35s\n", Integer.toString(p.get(i).productID), p.get(i).sport, p.get(i).price,
                        p.get(i).units, p.get(i).getCategory(), p.get(i).getSize(), p.get(i).getColour(), p.get(i).getVAT(),
                        p.get(i).getDescription());
            }
            pw.printf("7\t14\t10\t4\t12\t10\t10\t6\t35");
            System.out.println("SUCCESSFULLY EXPORTED!");
            pw.close();
        } catch (Exception e) {
            System.out.println("Fail while writting on file!");
        }

    }

    public void showProducts(ArrayList<Product> p) {

        System.out.println("ID     Sport         Price     n   Category    Size      Colour    VAT   Description");
        for (int i = 0; i < p.size(); i++) {
            //PRODUCT ID,SPORT
            System.out.printf("%-7s%-14s%-10.2f%-4d%-12s%-10s%-10s%-6.2f%-35s\n", Integer.toString(p.get(i).productID), p.get(i).sport, p.get(i).price,
                    p.get(i).units, p.get(i).getCategory(), p.get(i).getSize(), p.get(i).getColour(), p.get(i).getVAT(),
                    p.get(i).getDescription());
        }
    }
    
    public int searchProduct(ArrayList<Product> c,String ID) {
        //Search product
        if (c.isEmpty()) {
            System.out.println("There's no product in the store.");
        } else {
            for (int i = 0; i < c.size(); i++) {
                int aux=c.get(i).getProductID();
                if (aux==Integer.parseInt(ID)) {
                    return i;
                }
            }
            System.out.println("No product was found.");

        }
        return -1;
    }
    
    public void importColumns(ArrayList<Product> p) throws IOException {
        Scanner sc = new Scanner(System.in);
        Customer c = new Customer();
        String path;
        System.out.printf("Write the name of the file to import: ");
        String filename = sc.nextLine();
        path = c.filePath("Articulos");
        path = path + File.separator + filename;

        try {
            String lastline = "";
            String currentline = "";
            int x = 1;
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((currentline = br.readLine()) != null) {
                lastline = currentline;
                x++;
            }
            String[] numbers = lastline.split("\t");
            br.close();

            BufferedReader br1 = new BufferedReader(new FileReader(path));
            System.out.println("Previous data its going to be deleted.");
            p.clear();
            for (int i = 0; i < x - 2; i++) {
                int ini = 0;
                String aux = br1.readLine();

                String productIDs = aux.substring(ini, Integer.parseInt(numbers[0]));
                productIDs = productIDs.replaceAll("\\s", "");
                int productID = Integer.parseInt(productIDs);
                ini = Integer.parseInt(numbers[0]);
//                System.out.println(productID);

                String sport = aux.substring(ini, ini + Integer.parseInt(numbers[1]));
                ini = ini + Integer.parseInt(numbers[1]);
//                System.out.println(sport);

                String prices = aux.substring(ini, ini + Integer.parseInt(numbers[2]));
                prices = prices.replaceAll("\\s", "");
                prices = prices.replaceAll(",", ".");
                float price = Float.valueOf(prices);
                ini = ini + Integer.parseInt(numbers[2]);
//                System.out.println(price);

                String unitss = aux.substring(ini, ini + Integer.parseInt(numbers[3]));
                unitss = unitss.replaceAll("\\s", "");
                int units = Integer.parseInt(unitss);
                ini = ini + Integer.parseInt(numbers[3]);
//                System.out.println(units);

                String category = aux.substring(ini, ini + Integer.parseInt(numbers[4]));
                ini = ini + Integer.parseInt(numbers[4]);
//                System.out.println(category);

                String size = aux.substring(ini, ini + Integer.parseInt(numbers[5]));
                ini = ini + Integer.parseInt(numbers[5]);
//                System.out.println(size);

                String colour = aux.substring(ini, ini + Integer.parseInt(numbers[6]));
                ini = ini + Integer.parseInt(numbers[6]);
//                System.out.println(colour);

                String VATs = aux.substring(ini, ini + Integer.parseInt(numbers[7]));
                VATs = VATs.replaceAll("\\s", "");
                VATs = VATs.replaceAll(",", ".");
                float VAT = Float.parseFloat(VATs);
                ini = ini + Integer.parseInt(numbers[7]);

                String description = aux.substring(ini, ini + Integer.parseInt(numbers[8]));

                Product pr = new Product();
                pr.setProductID(productID);
                pr.setCategory(category);
                pr.setColour(colour);
                pr.setPrice(price);
                pr.setDescription(description);
                pr.setVAT(VAT);
                pr.setUnits(units);
                pr.setSport(sport);
                pr.setSize(size);
                p.add(pr);

            }
            br1.close();
            System.out.println((x - 2) + " Products where successfully imported!");
//            int[] col={Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]), 
//            Integer.parseInt(numbers[2]), Integer.parseInt(numbers[3]), 
//            Integer.parseInt(numbers[4]), Integer.parseInt(numbers[5]), 
//            Integer.parseInt(numbers[6]), Integer.parseInt(numbers[7]), 
//            Integer.parseInt(numbers[8]), Integer.parseInt(numbers[9])};
////            for(int i=0;i<9;i++){
////                
////            System.out.println(col[i]);
////            }
        } catch (Exception e) {
            System.out.println("Fail while reading the file!");
        }
    }
    //Getters and Setters

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getVAT() {
        return VAT;
    }

    public void setVAT(float VAT) {
        this.VAT = VAT;
    }

}
