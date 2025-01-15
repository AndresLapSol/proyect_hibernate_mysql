package org.example;

import org.example.todoDepartamento.menuDepartamento;
import org.example.todoEmpleado.menuEmpleado;
import org.example.todoEmpresa.menuEmpresa;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("###-SELECCION DE TABLA-###");
            System.out.println("¿CON QUE TABLA DESEA INTERACTUAR?");
            System.out.println("1| Empresa");
            System.out.println("2| Departamento");
            System.out.println("3| Empleado");
            System.out.println("0| Salir");

            try {
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine(); // Consumir el salto de línea después de nextInt()

                    switch (opcion) {
                        case 1:
                            menuEmpresa menuEmpresa = new menuEmpresa();
                            menuEmpresa.gestionar();
                            break;
                        case 2:
                            menuDepartamento menuDepartamento = new menuDepartamento();
                            menuDepartamento.gestionar();
                            break;
                        case 3:
                            menuEmpleado menuEmpleado = new menuEmpleado();
                            menuEmpleado.gestionar();
                            break;
                        case 0:
                            System.out.println("Saliendo del programa. ¡Hasta luego!");
                            break;
                        default:
                            System.out.println("Opción no válida. Intente de nuevo.");
                    }
                } else {
                    System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                    sc.next(); // Consumir entrada no válida
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                sc.next(); // Consumir entrada no válida
            }
        } while (opcion != 0);

        sc.close(); // Cerrar el Scanner solo aquí
    }
}