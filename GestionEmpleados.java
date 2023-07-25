import java.util.*;

public class GestionEmpleados {
    public static void main(String[] args) {

        List<Empleado> listaempleados = new ArrayList<>();
        Map<String, List<Empleado>> empleadosDepartamento = new HashMap<>();

        Scanner scan = new Scanner(System.in);
        int opc;

        do{
            System.out.println("\n MENU DE OPCIONES \n" +
                    "1. Agregar Empleado \n" +
                    "2. Mostrar lista de empleados \n" +
                    "3. Mostrar empleados por departamento \n" +
                    "4. Buscar empleado por nombre y apellido \n" +
                    "5. Eliminar empleado \n" +
                    "0. Salir");
            System.out.print("Ingrese una opcion: ");
            opc = scan.nextInt();
            scan.nextLine();

            switch (opc){
                case 1:
                    System.out.println("\n AGREGAR EMPLEADO \n");
                    System.out.print("Nombre: ");
                    String nombre = scan.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = scan.nextLine();
                    System.out.print("Edad: ");
                    int edad = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Departamento: ");
                    String departamento = scan.nextLine();

                    Empleado empleado = new Empleado(nombre, apellido, edad, departamento);
                    listaempleados.add(empleado);

                    if (empleadosDepartamento.containsKey(departamento)) {
                        empleadosDepartamento.get(departamento).add(empleado);
                    }else{
                        List<Empleado> empleados = new ArrayList<>();
                        empleados.add(empleado);
                        empleadosDepartamento.put(departamento, empleados);
                    }
                    break;

                case 2:
                    if (listaempleados.isEmpty()) {
                        System.out.println("No hay empleados en la lista");
                    }
                    System.out.println("\n LISTA DE EMPLEADOS \n");
                    for (Empleado emp: listaempleados) {
                        System.out.println(emp.obtenerInformacionEmpleado());
                    }
                    break;

                case 3:
                    if (empleadosDepartamento.isEmpty()) {
                        System.out.println("Aun no hay empleados registrados \n");
                    }
                    System.out.println("\n EMPLEADOS POR DEPARTAMENTO \n");
                    for (String dep : empleadosDepartamento.keySet()) {
                        System.out.println(dep + ":");
                        List<Empleado> empleadosDep = empleadosDepartamento.get(dep);
                        for (Empleado emp : empleadosDep) {
                            System.out.println(" " + emp.obtenerInformacionEmpleado());
                        }
                    }
                    break;

                case 4:
                    System.out.println("\n BUSCAR EMPLEADO POR NOMBRE Y APELLIDO \n");
                    System.out.print("Ingrese el nombre del empleado: ");
                    String nombreBusca = scan.nextLine();
                    System.out.print("Ingresa el apellido del empleado: ");
                    String apellidoBusca = scan.nextLine();

                    boolean encontrado = false;

                    for (Empleado emp : listaempleados) {
                        if (emp.nombre.equalsIgnoreCase(nombreBusca) && emp.apellido.equalsIgnoreCase(apellidoBusca)) {
                            System.out.println("Empleado encontrado");
                            System.out.println(emp.obtenerInformacionEmpleado());
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("El empleado no se encuentra registrado en la compañia");
                    }
                    break;

                case 5:
                    System.out.println("\n ELIMINAR EMPLEADO \n");
                    System.out.print("Ingrese el nombre del empleado que desea eliminar: ");
                    String nombreEliminar = scan.nextLine();
                    System.out.print("Ingresa el apellido del empleado que desea eliminar: ");
                    String apellidoEliminar = scan.nextLine();

                    for (Iterator<Empleado> iterator = listaempleados.iterator(); iterator.hasNext(); ) {
                        Empleado emp = iterator.next();
                        if (emp.nombre.equalsIgnoreCase(nombreEliminar) && emp.apellido.equalsIgnoreCase(apellidoEliminar)) {
                            iterator.remove();
                            // Eliminar el empleado del mapa empleadosPorDepartamento
                            List<Empleado> empleadosDep = empleadosDepartamento.get(emp.departamento);
                            empleadosDep.remove(emp);
                            if (empleadosDep.isEmpty()) {
                                empleadosDepartamento.remove(emp.departamento);
                            }
                            System.out.println("Empleado eliminado exitosamente.");
                            break;
                        }
                    }
                    break;

                case 0:
                    System.out.println("\n SALIENDO DEL PROGRAMA \n");
                    break;

                default:
                    System.out.println("\n OPCION INVALIDA \n");
            }

        }while(opc != 0);
        scan.close();
    }
}
