/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// developed by Pablo Jesus Espinosa Bermejo B2
package trabajofinal201516;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author espi_
 */
public class Customer implements Serializable {

    private String mail;
    private String name;
    private String surname1;
    private String surname2;
    private String city;
    private String region;
    private int zipcode;
    private int telephonenumber;
    private String password;
    private String bankaccount;
    private String adress;

    public Customer() {
        Random rand = new Random();
        //MAIL
        //Creates a String with random characters from the character array
        //chars always with a 12 size
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUWXYZ".toCharArray();
        String[] servers = {"gmail.com", "outlook.com", "yahoo.com", "apple.com"};
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            char c = chars[rand.nextInt(chars.length)];
            sb.append(c);
        }
        String id = sb.toString();
        String server = servers[rand.nextInt(servers.length)];
        this.mail = id + "@" + server;

        //NAME AND SURNAME
        //Random Name and two surnames(spanish)
        String[] names = {"Juan", "Pablo", "Miguel", "Francisco", "Juan Carlos", "Jose Miguel",
            "Jose Miguel", "Fernando", "Alex", "Raul", "Rafa", "Rodrigo"};
        String[] surnames = {"Sanchez", "Perez", "Garcia", "Fernandez", "Hernandez", "Martin",
            "Gomez", "Diaz", "Manzano", "Rodriguez", "Pajares", "Benito", "Gonzalez"};
        this.name = names[rand.nextInt(names.length)];
        this.surname1 = surnames[rand.nextInt(surnames.length)];
        this.surname2 = surnames[rand.nextInt(surnames.length)];

        //City, Region and ZIPCode
        String[] cities = {"Sydney", "Bangkok", "Kuala Lumpur", "London", "Warsaw", "Berlin",
            "Paris", "Madrid", "Lisbon", "New York", "Los Angeles", "Moskow", "Rome", "Athens"};
        String[] regions = {"NW", "N", "NE", "Central", "West", "East", "SW", "S", "SE"};
        this.city = cities[rand.nextInt(cities.length)];
        this.region = regions[rand.nextInt(regions.length)];
        this.zipcode = rand.nextInt(1000) + 37000;

        //PASSWORD
        //Creates a random password of 6 cifers
        String psw = new String();
        for (int i = 0; i < 6; i++) {
            char p = chars[rand.nextInt(chars.length)];
            psw = psw + p;
        }
        this.password = psw;
        //TELEPHONE NUMBER
        String snumber = new String();
        snumber = "6";
        for (int i = 0; i < 8; i++) {
            snumber = snumber + Integer.toString(rand.nextInt(10));
        }
        this.telephonenumber = Integer.valueOf(snumber);
        //BANK ACCOUNT
        //Creates a random BANK ACCOUNT NUMBER with two first letters and 20 numbers
        char[] bnkchar = "ABCDEFGHIJKLMNOPQRSTUWXYZ".toCharArray();
        String bnkacc = new String();
        for (int i = 0; i < 2; i++) {
            char b = bnkchar[rand.nextInt(bnkchar.length)];
            bnkacc = bnkacc + b;
        }
        for (int i = 0; i < 20; i++) {
            int bnk = rand.nextInt(10);
            bnkacc = bnkacc + Integer.toString(bnk);
        }
        this.bankaccount = bnkacc;

        //ADRESS
        //Generate a random adress with numbers from 1 to 500
        String[] places = {"Avenue", "Square", "Street", "Boulevard", "Road", "Lane"};
        String[] stnames = {"Franklin Thomason", "Trafalgar", "Bermondsey", "HighCliffe", "Abbey", "George Ferdinand", "St. James", "Riley"};
        String placeaux = places[rand.nextInt(places.length)];
        String namesaux = stnames[rand.nextInt(stnames.length)];
        this.adress = placeaux + " " + namesaux + " " + Integer.toString(rand.nextInt(500) + 1);
    }

    public void newCustomer(ArrayList<Customer> c) {
        Scanner sc = new Scanner(System.in);
        System.out.println("+-----------+");
        System.out.println("|NEW ACCOUNT|");
        System.out.println("+-----------+");
        System.out.printf("Write your email adress: ");
        this.mail = sc.nextLine();
        System.out.printf("Write your password: ");
        this.password = sc.nextLine();
        System.out.printf("Write your bank account number: ");
        this.bankaccount = sc.nextLine();
        System.out.printf("Write your billing adress: ");
        this.adress = sc.nextLine();
        System.out.printf("Write your City: ");
        this.city = sc.nextLine();
        System.out.printf("Write your Region: ");
        this.region = sc.nextLine();
        System.out.printf("Write your ZIP Code: ");
        this.zipcode = Integer.valueOf(sc.nextLine());
        System.out.printf("Write your Name: ");
        this.name = sc.nextLine();
        System.out.printf("Write your Surname(1): ");
        this.surname1 = sc.nextLine();
        System.out.printf("Write your Surname(2): ");
        this.surname2 = sc.nextLine();
        System.out.printf("Write your Telephone Number: ");
        this.telephonenumber = Integer.valueOf(sc.nextLine());
        c.add(this);

    }

    public void updateCustomer(ArrayList<Customer> c) {
        Scanner sc = new Scanner(System.in);
        Customer a = new Customer();
        //Search costumer
        int indx = searchCustomer(c);
        if (0 > indx) {
            System.out.println("It's not possible to make the costumer update.");
        } else {
            //Fill the data
            System.out.println("UPDATE PROCESS");
            System.out.println("Enter the new values for the customer.");
            System.out.printf("Write email adress: ");
            a.mail = sc.nextLine();
            System.out.printf("Write password: ");
            a.password = sc.nextLine();
            System.out.printf("Write bank account number: ");
            a.bankaccount = sc.nextLine();
            System.out.printf("Write billing adress: ");
            a.adress = sc.nextLine();
            System.out.printf("Write City: ");
            a.city = sc.nextLine();
            System.out.printf("Write Region: ");
            a.region = sc.nextLine();
            System.out.printf("Write ZIP Code: ");
            a.zipcode = Integer.valueOf(sc.nextLine());
            System.out.printf("Write Name: ");
            a.name = sc.nextLine();
            System.out.printf("Write Surname(1): ");
            a.surname1 = sc.nextLine();
            System.out.printf("Write Surname(2): ");
            a.surname2 = sc.nextLine();
            System.out.printf("Write Telephone Number: ");
            a.telephonenumber = Integer.valueOf(sc.nextLine());

            //Update arraylist
            c.set(indx, a);
            System.out.println("UPDATE SUCCESSFUL!");
        }
    }

    public void deleteCustomer(ArrayList<Customer> c) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Do you want to delete all Customers? YES/NO");
        String r = sc.nextLine();
        if (r.equalsIgnoreCase("YES")) {
            c.clear();
            System.out.println("ALL CUSTOMERS WERE REMOVED!");
        } else if(r.equalsIgnoreCase("NO")){
            int indx = searchCustomer(c);
            if (0 > indx) {
                System.out.println("It's not possible to make the costumer removal.");
            } else {
                c.remove(indx);
                System.out.println("DELETED CUSTOMER S1"
                        + "UCCESSFULLY!");
            }
        }else{
            System.out.println("Wrong input.");
        }
    }

    public int searchCustomer(ArrayList<Customer> c) {
        Scanner sc = new Scanner(System.in);
        String mail;
        //Search costumer
        if (c.isEmpty()) {
            System.out.println("There's no customer registrated.");
        } else {

            System.out.printf("Write customer e-Mail: ");
            mail = sc.nextLine();
            for (int i = 0; i < c.size(); i++) {
                if (c.get(i).mail.equalsIgnoreCase(mail)) {
                    return i;
                }
            }
            System.out.println("No customer was found.");

        }
        return -1;
    }
    
    public int searchCustomer(ArrayList<Customer> c,String mail) {
        //Search costumer
        if (c.isEmpty()) {
            System.out.println("There's no customer registrated.");
        } else {

            for (int i = 0; i < c.size(); i++) {
                if (c.get(i).mail.equalsIgnoreCase(mail)) {
                    return i;
                }
            }
            //System.out.println("No customer was found.");

        }
        return -1;
    }

    public void randomCustomers(ArrayList<Customer> c) {
        int x=25;
        Scanner sc = new Scanner(System.in);

        System.out.printf("Number of customers to create(push enter for default):");
        String aux=sc.nextLine();
        if(!aux.isEmpty()){
            
        x = Integer.parseInt(aux);
        }
        for (int i = 0; i < x; i++) {
            Customer y = new Customer();
            c.add(y);
        }
        System.out.println("CUSTOMERS WERE SUCCESSFUL CREATED!");
    }

    public void showCustomers(ArrayList<Customer> c) {
        if (c.isEmpty()) {
            System.out.println("There arent customers registrated.");
        } else {
            String name,ape1,ape2,mail;
            System.out.printf("=======================================================\n");
            System.out.printf("NAME                SURNAME-1      SURNAME-2      EMAIL\n");
            System.out.printf("=======================================================\n");
            
            for (int i = 0; i < c.size(); i++) {
                name=c.get(i).name;
                mail=c.get(i).mail;
                ape1=c.get(i).surname1;
                ape2=c.get(i).surname2;
                System.out.printf("%-20s%-15s%-15s%-20s\n",name,ape1,ape2,mail);//http://www.homeandlearn.co.uk/java/java_formatted_strings.html
            }
        }
    }

    public void writeCustomers(ArrayList<Customer> c) throws FileNotFoundException, IOException {

        FileOutputStream fos = new FileOutputStream(filePath("customers.bin"));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        int x = 0;
        for (int i = 0; i < c.size(); i++) {
            oos.writeObject(c.get(i));
            x++;
        }
        oos.close();
        System.out.println(x + " Customers were exported SUCCESSFULLY!");
    }

    public void readCustomers(ArrayList<Customer> c) throws FileNotFoundException, IOException, ClassNotFoundException {
        
        try {
            FileInputStream fis = new FileInputStream(filePath("customers.bin"));

            ObjectInputStream ois = new ObjectInputStream(fis);
            int i = 0;
            c.clear();
            Object aux = ois.readObject();
            while (aux != null) {
                if (aux instanceof Customer) {
                    c.add((Customer) aux);
                }
                i++;
                try {
                    aux = ois.readObject();
                } catch (Exception e) {
                    break;
                }
            }

            System.out.println(i + " Customers were imported SUCCESSFULLY!");
            ois.close();
        } catch (Exception e) {
            System.out.println("It wasnt possible to read the file!");

        }
    }

    public String filePath(String n) {
        String filepath;
        if (!System.getProperty("os.name").toLowerCase().contains("linux")) {
            filepath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "tienda" + File.separator + n;
        } else {
            filepath = System.getProperty("user.home") + File.separator + "Escritorio" + File.separator + "tienda" + File.separator + n;
        }
        String x = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "tienda";
        File f = new File(x);
        f.mkdir();
        return filepath;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public int getTelephonenumber() {
        return telephonenumber;
    }

    public void setTelephonenumber(int telephonenumber) {
        this.telephonenumber = telephonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

}
