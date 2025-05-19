import java.util.ArrayList;
import java.util.Scanner;

// Clase principal del programa
public class Main {
    // Lista que almacena objetos de tipo Articulo
    static ArrayList<Articulo> lista = new ArrayList<>();
    // se usaria para agregar una nueva lista de pedidos
    // static ArrayList<Pedido> pedidos = new ArrayList<>();

    // Scanner para entrada de datos por consola
    static Scanner sc = new Scanner(System.in);

    public static boolean esEntero(String str) {
        return str.matches("\\d+"); // solo números positivos
    }

    public static boolean esDecimal(String str) {
        return str.matches("\\d+(\\.\\d+)?"); // acepta decimales como 12.5
    }

    // Método principal
    public static void main(String[] args) {
        int opcion;
        // Bucle principal del programa con menú interactivo
        do {
            System.out.println("\n--- Menú de Artículos ---");
            System.out.println("1. Crear artículo");
            System.out.println("2. Listar artículos");
            System.out.println("3. Modificar artículo");
            System.out.println("4. Eliminar artículo");
            System.out.println("5. Salir");
            // System.out.print("Seleccione una opción: ");
            // opcion = sc.nextInt(); // Leer opción del usuario
            // sc.nextLine(); // Limpiar buffer del scanner
            while (true) {
                System.out.print("Seleccione una opción (1-5): ");
                String input = sc.nextLine().trim();

                if (!esEntero(input)) {
                    System.out.println("Entrada inválida. Debe ingresar un número.");
                    continue;
                }

                opcion = Integer.parseInt(input);

                if (opcion >= 1 && opcion <= 5) {
                    break;
                } else {
                    System.out.println("Debe ingresar un número entre 1 y 5.");
                }

            }
            // Estructura switch para manejar las opciones

            switch (opcion) {
                case 1:
                    crearArticulo();
                    break;
                case 2:
                    listarArticulos();
                    break;
                case 3:
                    modificarArticulo();
                    break;
                case 4:
                    eliminarArticulo();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 5); // Repetir hasta que el usuario elija salir
    }

    // Método para crear un nuevo artículo
    public static void crearArticulo() {
        int id = -1;
        while (true) {
            System.out.print("ID: ");
            String input = sc.nextLine();

            if (esEntero(input)) {
                id = Integer.parseInt(input);
                final int idFinal = id;
                boolean existe = lista.stream().anyMatch(a -> a.getId() == idFinal);
                if (!existe) {
                    break;
                } else {
                    System.out.println("Ya existe un artículo con ese ID.");
                }
            } else {
                System.out.println("ID inválido. Debe ser un número entero positivo.");
            }
        }

        String nombre = "";
        while (true) {
            System.out.print("Nombre: ");
            nombre = sc.nextLine().trim();
            if (!nombre.isEmpty())
                break;
            System.out.println("El nombre no puede estar vacío.");
        }

        double precio = 0;
        while (true) {
            System.out.print("Precio: ");
            String input = sc.nextLine();
            if (esDecimal(input)) {
                precio = Double.parseDouble(input);
                break;

            } else {
                System.out.println("Precio inválido. Debe ser un número decimal positivo.");
            }
        }

        Articulo nuevo = new Articulo(id, nombre, precio);
        lista.add(nuevo);
        System.out.println("Artículo agregado.");

    }

    // Método para mostrar todos los artículos de la lista
    public static void listarArticulos() {

        if (lista.isEmpty()) {
            System.out.println("No hay artículos cargados.");
        } else {
            for (Articulo a : lista) {
                a.mostrar(); // Llamada polimórfica al método mostrar()
            }
        }
    }

    // Método para modificar un artículo existente
    public static void modificarArticulo() {
        System.out.print("ID del artículo a modificar: ");
        String inputId = sc.nextLine().trim();

        if (inputId.isEmpty() || !esEntero(inputId)) {
            System.out.println("ID inválido. Debe ser un número entero positivo.");
            return;
        }

        int id = Integer.parseInt(inputId);

        // Buscar artículo con ese ID
        Articulo articuloEncontrado = null;
        for (Articulo articulo : lista) {
            if (articulo.getId() == id) {
                articuloEncontrado = articulo;
                break;
            }
        }

        // Si no se encontró el artículo
        if (articuloEncontrado == null) {
            System.out.println("No existe un artículo con el ID especificado.");
            return;
        }

        // Validar nuevo nombre
        String nuevoNombre;
        while (true) {
            System.out.print("Nuevo nombre: ");
            nuevoNombre = sc.nextLine().trim();
            if (!nuevoNombre.isEmpty()) {
                articuloEncontrado.setNombre(nuevoNombre);
                break;
            }
            System.out.println("El nombre no puede estar vacío.");
        }

        // Validar nuevo precio
        while (true) {
            System.out.print("Nuevo precio: ");
            String inputPrecio = sc.nextLine().trim();
            if (esDecimal(inputPrecio)) {
                double nuevoPrecio = Double.parseDouble(inputPrecio);
                articuloEncontrado.setPrecio(nuevoPrecio);
                break;
            }
            System.out.println("Precio inválido. Debe ser un número decimal positivo.");
        }

        System.out.println("Artículo actualizado.");

        // System.out.println("Artículo no encontrado.");
    }

    // Método para eliminar un artículo por ID
    public static void eliminarArticulo() {
        System.out.print("ID del artículo a eliminar: ");
        String inputId = sc.nextLine().trim();

        if (inputId.isEmpty() || !esEntero(inputId)) {
            System.out.println("ID inválido. Debe ser un número entero positivo.");
            return;
        }

        int id = Integer.parseInt(inputId);

        // Verificar si el artículo existe
        boolean existe = lista.stream().anyMatch(a -> a.getId() == id);

        if (!existe) {
            System.out.println("No existe un artículo con ese ID.");
            return;
        }

        // Eliminar el artículo
        lista.removeIf(a -> a.getId() == id);
        System.out.println("Artículo eliminado correctamente.");
    }

}
