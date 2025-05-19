package com.gestion.servicios;

import com.gestion.excepciones.StockInsuficienteException;
import com.gestion.modelo.Pedido;
import com.gestion.modelo.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionPedidos {
    private final List<Pedido> pedidos;
    private final GestionProductos gestionProductos;
    private final Scanner scanner;

    public GestionPedidos(GestionProductos gestionProductos, Scanner scanner) {
        this.pedidos = new ArrayList<>();
        this.gestionProductos = gestionProductos;
        this.scanner = scanner;
    }

    public void crearPedido() {
        if (gestionProductos.getProductos().isEmpty()) {
            System.out.println("No hay productos disponibles para crear un pedido.");
            return;
        }

        Pedido nuevoPedido = new Pedido();
        boolean continuar = true;

        System.out.println("\n--- CREAR NUEVO PEDIDO ---");

        while (continuar) {
            gestionProductos.listarProductos();
            System.out.print("\nIngrese el ID del producto a agregar al pedido: ");
            int idProducto = Integer.parseInt(scanner.nextLine());

            Producto producto = gestionProductos.getProductos().stream()
                    .filter(p -> p.getId() == idProducto)
                    .findFirst()
                    .orElse(null);

            if (producto == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            System.out.print("Cantidad deseada: ");
            int cantidad = Integer.parseInt(scanner.nextLine());

            try {
                if (cantidad > producto.getCantidadStock()) {
                    throw new StockInsuficienteException(
                            "Stock insuficiente. Disponible: " + producto.getCantidadStock());
                }

                nuevoPedido.agregarLineaPedido(producto, cantidad);
                System.out.println("Producto agregado al pedido.");
            } catch (StockInsuficienteException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("¿Desea agregar otro producto? (s/n)");
            String opcion = scanner.nextLine().toLowerCase();
            continuar = opcion.equals("s");
        }

        if (!nuevoPedido.getLineasPedido().isEmpty()) {
            System.out.println("\n--- RESUMEN DEL PEDIDO ---");
            System.out.println(nuevoPedido);

            System.out.println("¿Confirmar pedido? (s/n)");
            String confirmacion = scanner.nextLine().toLowerCase();

            if (confirmacion.equals("s")) {
                nuevoPedido.confirmarPedido();
                pedidos.add(nuevoPedido);
                System.out.println("Pedido confirmado con éxito! Número de pedido: " + nuevoPedido.getId());
            } else {
                System.out.println("Pedido cancelado.");
            }
        } else {
            System.out.println("Pedido vacío. No se ha creado ningún pedido.");
        }
    }

    public void listarPedidos() {
        System.out.println("\n--- LISTADO DE PEDIDOS ---");
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }

        pedidos.forEach(System.out::println);
    }
}