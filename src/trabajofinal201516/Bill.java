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
public class Bill {

    private int billID;
    private String billEmail;
    private String orderID;
    private float billBase;
    private float billVAT;
    private float billTOTAL;

    public void generateBill(ArrayList<Customer> customers) throws IOException {
        Customer c = new Customer();
        Scanner sc = new Scanner(System.in);
        System.out.printf("Write the date of the bill you want to generate (dd-mm-yyyy): ");
        String date = sc.nextLine();
        String path = c.filePath("Pedidos");
        File f = new File(path);
        f.mkdir();
        Random rd = new Random();
        File[] listOfFiles = f.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                if (file.getName().contains(date)) {
                    String namefile = file.getName();
                    String[] filedata = namefile.split("_");
                    //System.out.println(filedata[1]);
                    int customerindex = c.searchCustomer(customers, filedata[1]);
                    if (customerindex != -1) {
                        int billnumber = rd.nextInt(99999) + 1;
                        String path1 = path + File.separator + Integer.toString(billnumber) + "_" + date+".txt";
                        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path1)));
                        pw.println("\t\tFACTURA DE TU PEDIDO");
                        pw.println("Nº de Factura: " + billnumber);
                        pw.println("Pedido nº: " + filedata[0]);
                        pw.println("Fecha: "+date);
                        pw.printf("\n\n");
                        pw.printf("Direccion de facturacion:\n");
                        pw.println(customers.get(customerindex).getName() + " " + customers.get(customerindex).getSurname1()
                                + " " + customers.get(customerindex).getSurname2());
                        pw.println(customers.get(customerindex).getAdress());
                        pw.println(customers.get(customerindex).getZipcode() + " " + customers.get(customerindex).getCity() + "("
                                + customers.get(customerindex).getRegion() + ")");
                        pw.println("Telefono: " + customers.get(customerindex).getTelephonenumber());
                        pw.println("");
                        pw.printf("%-7s%-35s%-10s%-10s%-7s%-15s\n", "Código", "Descripción", "Cantidad", "Base", "VAT", "Subtotal");

                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String currentline="";
                        Float basetotal=0f;
                        Float VATtotal=0f;
                        while ((currentline = br.readLine()) != null) {
                            String[]variables=currentline.split(";");
                            variables[2]=variables[2].replaceAll(",", ".");
                            variables[7]=variables[7].replaceAll(",", ".");
                            
                            Float subtotal=(Integer.parseInt(variables[3])*Float.parseFloat(variables[2]));
                            Float VAT=(subtotal/100)*Float.parseFloat(variables[7]);
                            VATtotal=VATtotal+VAT;
                            basetotal=basetotal+subtotal;
                            subtotal=subtotal+VAT;
                            pw.printf("%-7s%-35s%-10s%-10s%-7s%-15.2f\n",variables[0],variables[8],variables[3],variables[2],variables[7],subtotal);
                            pw.printf("%-7s%s %s\n", "",variables[6],variables[5]);
                        }
                        pw.println("");
                        pw.printf("Total base: %.2f\n",basetotal);
                        pw.printf("Total VAT: %.2f\n",VATtotal);
                        pw.printf("Total factura: %.2f\n", VATtotal+basetotal);
                        pw.close();
                        br.close();
                        System.out.println("Bill printed.");
                    }
                }
            }
        }
    }
}
