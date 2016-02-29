/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// developed by Pablo Jesus Espinosa Bermejo B2
package trabajofinal201516;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author espi_
 */
public class TrabajoFinal201516 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        // TODO code application logic here
        //DATABASE
        ArrayList<Customer> Customers = new ArrayList<Customer>();
        ArrayList<Product> Products = new ArrayList<Product>();
        ArrayList<Order> Orders = new ArrayList<Order>();
        //
        Customer c = new Customer();
        Product p = new Product();
        Order o = new Order();
        Bill b=new Bill();
        String option = new String();
        Scanner sc = new Scanner(System.in);
        while (!option.equalsIgnoreCase("S")) {
            System.out.println("|==============|");
            System.out.println("|MENU PRINCIPAL|");
            System.out.println("|==============|");
            System.out.println("Escoja una option de las siguiente:");
            System.out.println("1-Clientes");
            System.out.println("2-Pedidos");
            System.out.println("3-Almacen");
            System.out.println("S-Salir");
            option = sc.nextLine();
            option = option.toUpperCase();
            switch (option) {
                case "1":
                    //Clientes
                    String optionC = new String();
                    do {
                        System.out.println("|========|");
                        System.out.println("|CLIENTES|");
                        System.out.println("|========|");
                        System.out.println("Escoja una option de las siguiente:");
                        System.out.println("1-Crear clientes aleatorios");
                        System.out.println("2-Escribir clientes en archivo");
                        System.out.println("3-Leer clientes de archivo");
                        System.out.println("4-Dar de alta a un cliente");
                        System.out.println("5-Dar de baja a un cliente");
                        System.out.println("6-Modificar un cliente");
                        System.out.println("7-Listado de clientes");
                        System.out.println("S-Volver al MENU PRINCIPAL");
                        optionC = sc.nextLine();
                        optionC = optionC.toUpperCase();
                        switch (optionC) {
                            case "1":
                                //Crear clientes aleatorios
                                c.randomCustomers(Customers);
                                break;

                            case "2":
                                //Escribir
                                c.writeCustomers(Customers);
                                break;
                            case "3":
                                //Leer
                                c.readCustomers(Customers);
                                break;

                            case "4":
                                //Altas
                                c.newCustomer(Customers);
                                break;
                            case "5":
                                //Bajas
                                c.deleteCustomer(Customers);
                                break;

                            case "6":
                                //Modificaciones
                                c.updateCustomer(Customers);
                                break;
                            case "7":
                                //Listado
                                c.showCustomers(Customers);
                                break;

                            case "S":
                                break;

                            default:
                                System.out.println("OPCION NO VALIDA");
                                break;
                        }
                    } while (!optionC.equalsIgnoreCase("S"));
                    break;
                case "2":
                    //Pedidos
                    String optionP = new String();
                    if (Products.isEmpty()) {
                        System.out.println("Store is empty of products! Create some previously!(3->1)");
                        break;
                    }
                    if (Customers.isEmpty()) {
                        System.out.println("There arent customers! Create some previously!(1->1)");
                        break;
                    }
                    do {
                        System.out.println("|=======|");
                        System.out.println("|PEDIDOS|");
                        System.out.println("|=======|");
                        System.out.println("Escoja una option de las siguiente:");
                        System.out.println("1-Hacer pedido");
                        System.out.println("2-Eliminar pedido");
                        System.out.println("3-Generar pedidos");
                        System.out.println("4-Generar facturas");
                        System.out.println("S-Volver al MENU PRINCIPAL");
                        optionP = sc.nextLine();
                        optionP = optionP.toUpperCase();
                        switch (optionP) {
                            case "1":
                                //Hacer pedido
                                o.newOrder(Products, Customers, Orders);
                                break;
                            case "2":
                                //Eliminar pedido
                                if (Orders.isEmpty()) {
                                    System.out.println("There arent orders yet!");
                                    break;
                                }
                                o.deleteOrder(Products, Orders);
                                break;
                            case "3":
                                //Generar CSV
                                if (Orders.isEmpty()) {
                                    System.out.println("There arent orders yet!");
                                    break;
                                }
                                o.generateCSV(Orders);
                                break;

                            case "4":
                                if (Orders.isEmpty()) {
                                    System.out.println("There arent orders yet!");
                                    break;
                                }
                                //Altas
                                b.generateBill(Customers);
                                break;

                            case "S":
                                break;

                            default:
                                System.out.println("OPCION NO VALIDA");
                                break;
                        }
                    } while (!optionP.equalsIgnoreCase("S"));
                    break;
                case "3":
                    //Almacen
                    String optionA = new String();
                    do {
                        System.out.println("|=======|");
                        System.out.println("|ALMACÉN|");
                        System.out.println("|=======|");
                        System.out.println("Escoja una option de las siguiente:");
                        System.out.println("1-Crear articulos aleatorios");
                        System.out.println("2-Exportar encolumnado");
                        System.out.println("3-Importar encolumnado");
                        System.out.println("S-Volver al MENU PRINCIPAL");
                        optionA = sc.nextLine();
                        optionA = optionA.toUpperCase();
                        switch (optionA) {
                            case "1":
                                //Crear productos aleatorios
                                p.randomProducts(Products);
                                break;
                            case "2":
                                //Exportar encolumnado
                                p.exportColumns(Products);
                                break;
                            case "3":
                                //Importar encolumnado
                                p.importColumns(Products);
                                break;

                            case "S":
                                break;

                            default:
                                System.out.println("OPCION NO VALIDA");
                                break;
                        }
                    } while (!optionA.equalsIgnoreCase("S"));
                    break;
                case "S":
                    break;
                default:
                    System.out.println("OPCION NO VALIDA");

            }

        }
    }

}
