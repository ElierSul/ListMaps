public class Empleado {
    String nombre;
    String apellido;
    int edad;
    String departamento;

    public Empleado(String nombre, String apellido, int edad, String departamento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.departamento = departamento;
    }

    public String obtenerInformacionEmpleado(){
        return "Nombre: " +nombre+ " | Apellido: " +apellido+ " | Edad: " +edad+ " | Departamento: " +
                departamento;
    }
}
