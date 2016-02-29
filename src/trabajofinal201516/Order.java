/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// developed by Pablo Jesus Espinosa Bermejo B2
package trabajofinal201516;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import trabajofinal201516.*;

/**
 *
 * @author espi_
 */
public class Order {

    private String orderID;
    private String orderDate; //It could be Date
    private String orderMail;
    ArrayList<Product> orderProducts;

    public Order() {
        this.orderProducts = new ArrayList<Product>();
    }

    public void newOrder(ArrayList<Product> p, ArrayList<Customer> cust, ArrayList<Order> orders) {

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Product pr = new Product();
        Scanner sc = new Scanner(System.in);
        String r = "";
        Customer cu = new Customer();
        Order o = new Order();
        Random rand = new Random();
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUWXYZ".toCharArray();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            char c = chars[rand.nextInt(chars.length)];
            sb.append(c);
        }

        o.setOrderID(sb.toString());
        System.out.println("Write the mail of a Customer");
        cu.showCustomers(cust);
        String mail = "";

        do {
            System.out.printf("Write an Email from the list: ");
            mail = sc.nextLine();

        } while (-1 == cu.searchCustomer(cust, mail));

        o.setOrderMail(mail);
        o.setOrderDate(df.format(Calendar.getInstance().getTime()));
        int i;
        System.out.println("How do you want the files to be sorted by?(press enter for default):");
        System.out.println("1-ProductID");
        System.out.println("2-Sport");
        System.out.println("3-Category");
        System.out.println("4-Colour");
        System.out.println("5-Size");
        System.out.println("6-Units");
        System.out.println("7-Price");
        System.out.println("8-VAT");
        System.out.printf("Option: ");
        String sort=sc.nextLine();
        if(sort.equalsIgnoreCase("1")){
            Comparator<Product>comp=new ComparatorID();
            Collections.sort(p, comp);
        }else if(sort.equalsIgnoreCase("2")){
            
            Comparator<Product>comp=new ComparatorSport();
            Collections.sort(p, comp);
        }else if(sort.equalsIgnoreCase("3")){
            Comparator<Product>comp=new ComparatorCategory();
            Collections.sort(p, comp);
            
        }else if(sort.equalsIgnoreCase("4")){
            Comparator<Product>comp=new ComparatorColour();
            Collections.sort(p, comp);
            
        }else if(sort.equalsIgnoreCase("5")){
            Comparator<Product>comp=new ComparatorSize();
            Collections.sort(p, comp);
            
        }else if(sort.equalsIgnoreCase("6")){
            Comparator<Product>comp=new ComparatorUnits();
            Collections.sort(p, comp);
            
        }else if(sort.equalsIgnoreCase("7")){
            Comparator<Product>comp=new ComparatorPrice();
            Collections.sort(p, comp);
            
        }else if(sort.equalsIgnoreCase("8")){
            Comparator<Product>comp=new ComparatorVAT();
            Collections.sort(p, comp);
            
        }else{
            System.out.println("Default sorted.");
        }

        System.out.println("These are the products availables:");
        pr.showProducts(p);
        do {
            System.out.println("Write exit to close the order.");
            System.out.println("Write end to finish your order.");
            System.out.printf("Choose your product(ID): ");
            r = sc.nextLine();
            if (r.equalsIgnoreCase("exit")) {
                orders.add(o);
                o.returnProducts(p, o);
                System.out.println("Order Canceled!");
                break;
            } else if (r.equalsIgnoreCase("end")) {
                orders.add(o);
                System.out.println("Order " + o.getOrderID() + " completed!");
                break;
            } else {
                i = pr.searchProduct(p, r);
            }
            if (i >= 0) {

                System.out.printf("How many units do you want: ");
                r = sc.nextLine();
                if (r.equalsIgnoreCase("exit")) {
                    orders.add(o);
                    o.returnProducts(p, o);
                    System.out.println("Order Canceled!");
                    break;
                } else if (r.equalsIgnoreCase("end")) {
                    orders.add(o);
                    System.out.println("Order " + o.getOrderID() + " completed!");
                    break;
                } else {

                    int ud = p.get(i).getUnits();
                    if ((Integer.parseInt(r)) <= ud) {

                        String category = p.get(i).getCategory();
                        String colour = p.get(i).getColour();
                        String description = p.get(i).getDescription();
                        Float price = p.get(i).getPrice();
                        Float VAT = p.get(i).getVAT();
                        int ID = p.get(i).getProductID();
                        String size = p.get(i).getSize();
                        String sport = p.get(i).getSport();

                        int aux1 = Integer.parseInt(r);

                        Product prod = new Product();
                        prod.setCategory(category);
                        prod.setColour(colour);
                        prod.setPrice(price);
                        prod.setDescription(description);
                        prod.setVAT(VAT);
                        prod.setProductID(ID);
                        prod.setSize(size);
                        prod.setSport(sport);
                        prod.setUnits(aux1);
                        o.orderProducts.add(prod);

                        int aux2 = (ud - aux1);
                        p.get(i).setUnits(aux2);
                        System.out.println("There are " + aux2 + " units of the product left.");
                    } else {
                        System.out.println("There arent enough units of the product, units availables: " + ud);
                    }
                }
            }
        } while (!r.equalsIgnoreCase("exit") && !r.equalsIgnoreCase("end"));

    }

    public void deleteOrder(ArrayList<Product> p, ArrayList<Order> o) {
        Scanner sc = new Scanner(System.in);
        Order or = new Order();
        System.out.printf("Write the order ID that you want to delete: ");
        String ID = sc.nextLine();
        or = searchOrder(o, ID);
        returnProducts(p, or);
    }

    public Order searchOrder(ArrayList<Order> o, String id) {
        if (o.isEmpty()) {
            System.out.println("There arent orders.");
        } else {
            for (int i = 0; i < o.size(); i++) {
                String aux = o.get(i).getOrderID();
                if (aux.equals(id)) {
                    System.out.println("Order " + o.get(i).getOrderID() + " found!");
                    ArrayList<Product> er = o.get(i).orderProducts;
                    System.out.println(er.get(0).getUnits());
                    return o.get(i);
                }
            }
            System.out.println("No order was found.");

        }
        return null;

    }

    public void returnProducts(ArrayList<Product> p, Order o) {
        ArrayList<Product> pr = o.getOrderProducts();
        Product x = new Product();
        for (int i = 0; i < pr.size(); i++) {
            int r = x.searchProduct(p, Integer.toString(pr.get(i).getProductID()));
            int sum = pr.get(i).getUnits();
            System.out.println(sum);
            int sum1 = p.get(r).getUnits();
            System.out.println(sum1);
            sum = sum + sum1;
            p.get(r).setUnits(sum);
        }
        o.orderProducts.clear();
        System.out.println("Products of the order " + o.getOrderID() + " were succesfully returned.");
        o.setOrderID("RETURNED");
    }

    public void generateCSV(ArrayList<Order> o) throws IOException {
        Customer c = new Customer();
        String path = c.filePath("Pedidos");
        String path1="";
        File f = new File(path);
        f.mkdir();
        for (int i = 0; i < o.size(); i++) {
            Order or = o.get(i);
            String ID = or.getOrderID();
            if (!ID.equalsIgnoreCase("RETURNED")) {//We only print no returned orders
                String customer = or.getOrderMail();
                String date = or.getOrderDate();
                ArrayList<Product> p = or.getOrderProducts();
                path1 = path + File.separator + ID + "_" + customer + "_" + date + ".csv";
//                try {
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path1)));
                //pw.printf("ID;Sport;Price;Units;Category;Size;Colour;VAT;Description\n"); Commented because Excel detects its as an error
                for (int u = 0; u < p.size(); u++) {
                    //PRODUCT ID,SPORT
                    pw.printf("%s;%s;%.2f;%d;%s;%s;%s;%.2f;%s\n", Integer.toString(p.get(u).getProductID()), p.get(u).getSport(), p.get(u).getPrice(),
                            p.get(u).getUnits(), p.get(u).getCategory(), p.get(u).getSize(), p.get(u).getColour(), p.get(u).getVAT(),
                            p.get(u).getDescription());
//                        pw.println(Integer.toString(p.get(u).getProductID())+";"+p.get(u).getSport()+";"+p.get(u).getPrice()+";"+p.get(u).getUnits()
//                        +";"+p.get(u).getCategory()+";"+p.get(u).getSize()+";"+p.get(u).getColour()+";"+p.get(u).getVAT()+";"+p.get(u).getDescription());
                }
                System.out.println("CSV SUCCESSFULLY EXPORTED!");

                pw.close();
//                } catch (Exception e) {
//                    System.out.println("Fail while writting on file!");
//                }

            }

        }

    }



    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderMail() {
        return orderMail;
    }

    public void setOrderMail(String orderMail) {
        this.orderMail = orderMail;
    }

    public ArrayList<Product> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(ArrayList<Product> orderProducts) {
        this.orderProducts = orderProducts;
    }

}
