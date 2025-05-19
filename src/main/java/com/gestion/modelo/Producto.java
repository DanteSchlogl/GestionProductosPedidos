package com.gestion.modelo;

public class Producto {
    private static int contadorId = 1;
    private final int id;
    private String nombre;
    private double precio;
    private int cantidadStock;

    public Producto(String nombre, double precio, int cantidadStock) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        if (cantidadStock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        this.cantidadStock = cantidadStock;
    }

    public void reducirStock(int cantidad) {
        if (cantidad > this.cantidadStock) {
            throw new IllegalArgumentException("No hay suficiente stock");
        }
        this.cantidadStock -= cantidad;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %-20s | Precio: $%.2f | Stock: %d",
                id, nombre, precio, cantidadStock);
    }
}