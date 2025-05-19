package com.gestion.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contadorId = 1;
    private final int id;
    private final List<LineaPedido> lineasPedido;

    public Pedido() {
        this.id = contadorId++;
        this.lineasPedido = new ArrayList<>();
    }

    public void agregarLineaPedido(Producto producto, int cantidad) {
        lineasPedido.add(new LineaPedido(producto, cantidad));
    }

    public double getTotal() {
        return lineasPedido.stream()
                .mapToDouble(LineaPedido::getSubtotal)
                .sum();
    }

    public void confirmarPedido() {
        for (LineaPedido linea : lineasPedido) {
            linea.getProducto().reducirStock(linea.getCantidad());
        }
    }

    // Getters
    public int getId() {
        return id;
    }

    public List<LineaPedido> getLineasPedido() {
        return new ArrayList<>(lineasPedido);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Pedido #%d\n", id));
        sb.append("----------------------------------------\n");
        for (LineaPedido linea : lineasPedido) {
            sb.append(linea).append("\n");
        }
        sb.append("----------------------------------------\n");
        sb.append(String.format("TOTAL: $%.2f\n", getTotal()));
        return sb.toString();
    }
}