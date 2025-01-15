package org.example.todoEmpleado;

import java.util.Scanner;

public class menuEmpleado {
    public void gestionar() {
        repositoryEmpleado repository = new repositoryEmpleado();
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("###-> EMPLEADO <-###");
            System.out.println("¿QUE OPERACION DESEA HACER?");
            System.out.println("1| Insertar datos.");
            System.out.println("2| Buscar Empleado por su ID.");
            System.out.println("3| Actualizar Empleado.");
            System.out.println("4| Eliminar Empleado.");
            System.out.println("5| Ver todos los registros.");
            System.out.println("6| Consultar Empleado por departamento.");
            System.out.println("0| Regresar al menú principal."); // Opción para regresar

            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine(); // Consumir el salto de línea después de nextInt()

                switch (opcion) {
                    case 1:
                        repository.insertarEmpleado();
                        break;
                    case 2:
                        repository.buscarEmpleado();
                        break;
                    case 3:
                        repository.actualizarEmpleado();
                        break;
                    case 4:
                        repository.eliminarEmpleado();
                        break;
                    case 5:
                        repository.listarEmpleados();
                        break;
                    case 6:
                        repository.consultarEmpleadosPorDepartamento();
                        break; // Agregar break aquí
                    case 0:
                        System.out.println("Regresando al menú principal...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                sc.next(); // Consumir entrada no válida
            }
        } while (opcion != 0);

        // No cerrar el Scanner aquí
        // sc.close(); // Elimina esta línea
    }
}