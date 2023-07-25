import java.util.*;

public class GestionProductos {
    public static void main(String[] args) {
        // Crear la lista para almacenar los productos
        List<Producto> listaProductos = new ArrayList<>();

        // Crear el mapa para organizar los productos por categoría
        Map<String, List<Producto>> productosPorCategoria = new HashMap<>();

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            // Mostrar el menú de opciones
            System.out.println("\n--- Menú de opciones ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Mostrar lista de productos");
            System.out.println("3. Agregar producto a categoría");
            System.out.println("4. Mostrar productos por categoría");
            System.out.println("5. Buscar producto por código");
            System.out.println("6. Actualizar stock de un producto");
            System.out.println("7. Eliminar producto");
            System.out.println("8. Calcular valor total del inventario");
            System.out.println("9. Mostrar producto con precio más alto y más bajo");
            System.out.println("10. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    // Agregar un nuevo producto a la lista
                    System.out.println("\n--- Agregar producto ---");
                    System.out.print("Código: ");
                    int codigo = sc.nextInt();
                    sc.nextLine(); // Limpiar el buffer del scanner
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Precio: $");
                    double precio = sc.nextDouble();
                    System.out.print("Stock: ");
                    int stock = sc.nextInt();

                    // Crear un nuevo objeto Producto y agregarlo a la lista
                    Producto producto = new Producto(codigo, nombre, precio, stock);
                    listaProductos.add(producto);
                    break;

                case 2:
                    // Mostrar la lista de productos registrados
                    System.out.println("\n--- Lista de productos registrados ---");
                    for (Producto prod : listaProductos) {
                        System.out.println(prod.informacionProductos());
                    }
                    break;

                case 3:
                    // Agregar un producto a una categoría específica
                    System.out.println("\n--- Agregar producto a categoría ---");
                    if (listaProductos.isEmpty()) {
                        System.out.println("No hay productos registrados.");
                        break;
                    }
                    System.out.println("Lista de productos:");
                    for (int i = 0; i < listaProductos.size(); i++) {
                        Producto p = listaProductos.get(i);
                        System.out.println((i + 1) + ". " + p.nombre);
                    }
                    System.out.print("Seleccione un producto (número): ");
                    int seleccion = sc.nextInt();
                    sc.nextLine(); // Limpiar el buffer del scanner
                    if (seleccion < 1 || seleccion > listaProductos.size()) {
                        System.out.println("Opción inválida.");
                        break;
                    }
                    System.out.print("Ingrese la categoría del producto: ");
                    String categoria = sc.nextLine();

                    // Obtener el producto seleccionado y agregarlo al mapa productosPorCategoria
                    Producto productoSeleccionado = listaProductos.get(seleccion - 1);
                    if (productosPorCategoria.containsKey(categoria)) {
                        productosPorCategoria.get(categoria).add(productoSeleccionado);
                    } else {
                        List<Producto> productosCategoria = new ArrayList<>();
                        productosCategoria.add(productoSeleccionado);
                        productosPorCategoria.put(categoria, productosCategoria);
                    }
                    System.out.println("Producto agregado a la categoría exitosamente.");
                    break;

                case 4:
                    // Mostrar productos por categoría
                    System.out.println("\n--- Productos por categoría ---");
                    for (String cat : productosPorCategoria.keySet()) {
                        System.out.println(cat + ":");
                        List<Producto> productosCategoria = productosPorCategoria.get(cat);
                        for (Producto prod : productosCategoria) {
                            System.out.println("  " + prod.informacionProductos());
                        }
                    }
                    break;

                case 5:
                    // Buscar producto por código
                    System.out.println("\n--- Buscar producto por código ---");
                    System.out.print("Ingrese el código del producto: ");
                    int codigoBuscar = sc.nextInt();

                    // Buscar el producto en la lista
                    boolean encontrado = false;
                    for (Producto prod : listaProductos) {
                        if (prod.codigo == codigoBuscar) {
                            System.out.println("Producto encontrado:");
                            System.out.println(prod.informacionProductos());
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case 6:
                    // Actualizar stock de un producto
                    System.out.println("\n--- Actualizar stock de un producto ---");
                    if (listaProductos.isEmpty()) {
                        System.out.println("No hay productos registrados.");
                        break;
                    }
                    System.out.println("Lista de productos:");
                    for (int i = 0; i < listaProductos.size(); i++) {
                        Producto p = listaProductos.get(i);
                        System.out.println((i + 1) + ". " + p.nombre);
                    }
                    System.out.print("Seleccione un producto (número): ");
                    seleccion = sc.nextInt();
                    sc.nextLine(); // Limpiar el buffer del scanner
                    if (seleccion < 1 || seleccion > listaProductos.size()) {
                        System.out.println("Opción inválida.");
                        break;
                    }
                    System.out.print("Ingrese la nueva cantidad de stock: ");
                    int nuevoStock = sc.nextInt();

                    // Actualizar el stock del producto seleccionado
                    Producto productoActualizarStock = listaProductos.get(seleccion - 1);
                    productoActualizarStock.stock = nuevoStock;
                    System.out.println("Stock actualizado exitosamente.");
                    break;

                case 7:
                    // Eliminar un producto de la lista
                    System.out.println("\n--- Eliminar producto ---");
                    if (listaProductos.isEmpty()) {
                        System.out.println("No hay productos registrados.");
                        break;
                    }
                    System.out.print("Ingrese el código del producto a eliminar: ");
                    int codigoEliminar = sc.nextInt();

                    // Buscar y eliminar el producto de la lista
                    for (Iterator<Producto> iterator = listaProductos.iterator(); iterator.hasNext(); ) {
                        Producto prod = iterator.next();
                        if (prod.codigo == codigoEliminar) {
                            iterator.remove();
                            // Eliminar el producto de todas las categorías en el mapa productosPorCategoria
                            for (List<Producto> productosCategoria : productosPorCategoria.values()) {
                                productosCategoria.remove(prod);
                            }
                            System.out.println("Producto eliminado exitosamente.");
                            break;
                        }
                    }
                    break;

                case 8:
                    // Calcular valor total del inventario
                    System.out.println("\n--- Valor total del inventario ---");
                    double valorTotalInventario = 0;
                    for (Producto prod : listaProductos) {
                        valorTotalInventario += prod.precio * prod.stock;
                    }
                    System.out.println("El valor total del inventario es: $" + valorTotalInventario);
                    break;

                case 9:
                    // Encontrar producto con precio más alto y más bajo
                    System.out.println("\n--- Producto con precio más alto y más bajo ---");
                    if (listaProductos.isEmpty()) {
                        System.out.println("No hay productos registrados.");
                        break;
                    }
                    Producto productoMasAlto = listaProductos.get(0);
                    Producto productoMasBajo = listaProductos.get(0);
                    for (Producto prod : listaProductos) {
                        if (prod.precio > productoMasAlto.precio) {
                            productoMasAlto = prod;
                        }
                        if (prod.precio < productoMasBajo.precio) {
                            productoMasBajo = prod;
                        }
                    }
                    System.out.println("Producto con precio más alto:");
                    System.out.println(productoMasAlto.informacionProductos());
                    System.out.println("Producto con precio más bajo:");
                    System.out.println(productoMasBajo.informacionProductos());
                    break;

                case 10:
                    // Salir del programa
                    System.out.println("\nSaliendo del programa...");
                    break;

                default:
                    System.out.println("\nOpción inválida. Por favor, ingrese una opción válida.");
            }

        } while (opcion != 10);

        sc.close();
    }
}
