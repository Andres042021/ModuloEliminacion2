import javax.swing.*;
import java.util.ArrayList;

class Producto {
    private String codigo;
    private String nombre;
    private int anio;
    private double precio;

    public Producto(String codigo, String nombre, int anio, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.anio = anio;
        this.precio = precio;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getAnio() { return anio; }
    public double getPrecio() { return precio; }
}

public class ModuloEliminacion {

    // 🔹 Función para eliminar por código único
    public static void eliminarRegistro(ArrayList<Producto> lista) {
        try {
            String codigo = JOptionPane.showInputDialog("Ingrese el código del registro a eliminar:");
            
            if (codigo == null || codigo.trim().isEmpty()) {
                throw new NullPointerException("Código vacío o cancelado.");
            }

            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getCodigo().equals(codigo)) {
                    int confirmacion = JOptionPane.showConfirmDialog(null,
                        "¿Está seguro de eliminar el registro con código " + codigo + "?",
                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        lista.remove(i);
                        JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente.");
                    }
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "No se encontró el registro con ese código.");
            }

        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Acción cancelada o valor nulo.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
        }
    }

    // 🔹 Ejemplo de validaciones con try-catch
    public static void ingresarDatos(ArrayList<Producto> lista) {
        try {
            String codigo = JOptionPane.showInputDialog("Ingrese código único:");
            String nombre = JOptionPane.showInputDialog("Ingrese nombre:");

            int anio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese año:"));
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese precio:"));

            if (anio <= 0 || precio <= 0) {
                throw new IllegalArgumentException("Año y precio deben ser positivos.");
            }

            lista.add(new Producto(codigo, nombre, anio, precio));
            JOptionPane.showMessageDialog(null, "Registro agregado correctamente.");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: debe ingresar números válidos en año y precio.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Ingreso cancelado o valor nulo.");
        }
    }

    public static void main(String[] args) {
        ArrayList<Producto> lista = new ArrayList<>();

        // Solo para probar tu módulo
        ingresarDatos(lista);
        eliminarRegistro(lista);
    }
}
