package com.gestion;

import com.gestion.servicios.GestionPedidos;
import com.gestion.servicios.GestionProductos;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestionProductos gestionProductos = new GestionProductos(scanner);
        GestionPedidos gestionPedidos = new GestionPedidos(gestionProductos, scanner);

        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    gestionProductos.agregarProducto();
                    break;
                case 2:
                    gestionProductos.listarProductos();
                    break;
                case 3:
                    gestionProductos.mostrarYActualizarProducto();
                    break;
                case 4:
                    gestionProductos.eliminarProducto();
                    break;
                case 5:
                    gestionPedidos.crearPedido();
                    break;
                case 6:
                    gestionPedidos.listarPedidos();
                    break;
                case 7:
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }

            if (!salir) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GESTIÓN DE PRODUCTOS Y PEDIDOS ===");
        System.out.println("1. Agregar producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Buscar/Actualizar producto");
        System.out.println("4. Eliminar producto");
        System.out.println("5. Crear un pedido");
        System.out.println("6. Listar pedidos");
        System.out.println("7. Salir");
    }
}
