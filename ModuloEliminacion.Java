import javax.swing.*;
import java.util.ArrayList;

class Vehiculo {
    private String codigo;
    private String marca;
    private String modelo;
    private int anio;
    private double precio;

    public Vehiculo(String codigo, String marca, String modelo, int anio, double precio) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.precio = precio;
    }

    public String getCodigo() { return codigo; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAnio() { return anio; }
    public double getPrecio() { return precio; }
}

public class ModuloEliminacion {
    public static void eliminarRegistro(ArrayList<Vehiculo> lista) {
        try {
            String codigo = JOptionPane.showInputDialog("Ingrese el código único del vehículo a eliminar:");

            if (codigo == null || codigo.trim().isEmpty()) {
                throw new NullPointerException("Código vacío o cancelado.");
            }

            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                    int confirmacion = JOptionPane.showConfirmDialog(null,
                        "¿Está seguro de eliminar el vehículo con código " + codigo + "?",
                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        lista.remove(i);
                        JOptionPane.showMessageDialog(null, "Vehículo eliminado exitosamente.");
                    }
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "No se encontró el vehículo con ese código.");
            }

        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Acción cancelada o valor nulo.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
        }
    }

    public static void ingresarDatos(ArrayList<Vehiculo> lista) {
        try {
            String codigo = JOptionPane.showInputDialog("Ingrese código único:");
            String marca = JOptionPane.showInputDialog("Ingrese marca:");
            String modelo = JOptionPane.showInputDialog("Ingrese modelo:");

            int anio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese año:"));
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese precio:"));

            if (anio <= 0 || precio <= 0) {
                throw new IllegalArgumentException("Año y precio deben ser positivos.");
            }

            lista.add(new Vehiculo(codigo, marca, modelo, anio, precio));
            JOptionPane.showMessageDialog(null, "Vehículo registrado correctamente.");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: debe ingresar números válidos en año y precio.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Ingreso cancelado o valor nulo.");
        }
    }

    public static void main(String[] args) {
        ArrayList<Vehiculo> lista = new ArrayList<>();

        ingresarDatos(lista);
        eliminarRegistro(lista);
    }
}
