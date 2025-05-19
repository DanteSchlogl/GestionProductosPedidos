package com.gestion.servicios;

import com.gestion.modelo.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionProductos {
    private final List<Producto> productos;
    private final Scanner scanner;

    public GestionProductos(Scanner scanner) {
        this.productos = new ArrayList<>();
        this.scanner = scanner;
    }

    public void agregarProducto() {
        System.out.println("\n--- AGREGAR NUEVO PRODUCTO ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());

        System.out.print("Cantidad en stock: ");
        int stock = Integer.parseInt(scanner.nextLine());

        Producto producto = new Producto(nombre, precio, stock);
        productos.add(producto);
        System.out.println("Producto agregado con éxito! ID: " + producto.getId());
    }

    public void listarProductos() {
        System.out.println("\n--- LISTADO DE PRODUCTOS ---");
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        productos.forEach(System.out::println);
    }

    public Producto buscarProductoPorId() {
        System.out.print("\nIngrese el ID del producto: ");
        int id = Integer.parseInt(scanner.nextLine());

        return productos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void mostrarYActualizarProducto() {
        Producto producto = buscarProductoPorId();
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println("\n--- INFORMACIÓN DEL PRODUCTO ---");
        System.out.println(producto);

        System.out.println("\n¿Desea actualizar algún dato? (s/n)");
        String opcion = scanner.nextLine().toLowerCase();

        if (opcion.equals("s")) {
            System.out.println("¿Qué dato desea actualizar?");
            System.out.println("1. Nombre");
            System.out.println("2. Precio");
            System.out.println("3. Stock");
            System.out.print("Opción: ");
            int dato = Integer.parseInt(scanner.nextLine());

            switch (dato) {
                case 1:
                    System.out.print("Nuevo nombre: ");
                    producto.setNombre(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Nuevo precio: ");
                    producto.setPrecio(Double.parseDouble(scanner.nextLine()));
                    break;
                case 3:
                    System.out.print("Nueva cantidad en stock: ");
                    producto.setCantidadStock(Integer.parseInt(scanner.nextLine()));
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
            System.out.println("Producto actualizado con éxito!");
        }
    }

    public void eliminarProducto() {
        Producto producto = buscarProductoPorId();
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        productos.remove(producto);
        System.out.println("Producto eliminado con éxito!");
    }

    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }
}