public class Producto {
    int codigo;
    String nombre;
    double precio;
    int stock;

    public Producto(int codigo, String nombre, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String informacionProductos(){
        return "Codigo: " +codigo+ " | Producto: " +nombre+ " | Precio: " +precio+ " | Stock: " +stock;
    }
}
