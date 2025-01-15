package org.example.todoDepartamento;

import java.util.Scanner;

public class menuDepartamento {
    public void gestionar() {
        repositoryDepartamento repository = new repositoryDepartamento();
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("###-DEPARTAMENTO-###");
            System.out.println("¿QUE OPERACION DESEA HACER?");
            System.out.println("1| Insertar datos.");
            System.out.println("2| Buscar Departamento por su ID.");
            System.out.println("3| Actualizar Departamento.");
            System.out.println("4| Eliminar un Departamento.");
            System.out.println("5| Ver todos los registros.");
            System.out.println("6| Mostrar los departamentos de una empresa y el número de empleados en cada uno.");
            System.out.println("0| Regresar al menú principal.");

            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine(); // Consumir el salto de línea después de nextInt()

                switch (opcion) {
                    case 1:
                        repository.insertarDepartamento();
                        break;
                    case 2:
                        repository.buscarDepartamento();
                        break;
                    case 3:
                        repository.actualizarDepartamento();
                        break;
                    case 4:
                        repository.eliminarDepartamento();
                        break;
                    case 5:
                        repository.listarDepartamentos();
                        break;
                    case 6:
                        repository.mostrarDepartamentosConNumeroDeEmpleados();
                        break;
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