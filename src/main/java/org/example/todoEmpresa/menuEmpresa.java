package org.example.todoEmpresa;

import java.util.Scanner;

public class menuEmpresa {
    public void gestionar() {
        repositoryEmpresa repository = new repositoryEmpresa();
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("###-> EMPRESA <-###");
            System.out.println("¿QUE OPERACION DESEA HACER?");
            System.out.println("1| Insertar datos.");
            System.out.println("2| Buscar empresa por su ID.");
            System.out.println("3| Actualizar Empresa.");
            System.out.println("4| Eliminar una empresa.");
            System.out.println("5| Ver todos los registros.");
            System.out.println("0| Regresar al menú principal."); // Opción para regresar

            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine(); // Consumir el salto de línea después de nextInt()

                switch (opcion) {
                    case 1:
                        repository.insertarEmpresa();
                        break;
                    case 2:
                        repository.buscarEmpresa();
                        break;
                    case 3:
                        repository.actualizarEmpresa();
                        break;
                    case 4:
                        repository.eliminarEmpresa();
                        break;
                    case 5:
                        repository.listarEmpresas();
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