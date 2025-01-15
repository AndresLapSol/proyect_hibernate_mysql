package org.example.todoEmpleado;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.example.todoDepartamento.Departamento;

import java.util.List;
import java.util.Scanner;

public class repositoryEmpleado {

    private SessionFactory factory;
    private Scanner scanner;

    public repositoryEmpleado() {
        // Inicializar SessionFactory una sola vez
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Empleado.class)
                .addAnnotatedClass(Departamento.class)
                .buildSessionFactory();
        scanner = new Scanner(System.in); // Instancia compartida de Scanner
    }

    public void insertarEmpleado() {
        try (Session session = factory.getCurrentSession()) {
            System.out.println("###-> AÑADIR EMPLEADO <-###");
            System.out.print("Introduzca el NOMBRE del empleado: ");
            String nombre = scanner.nextLine();
            System.out.print("Introduzca el APELLIDO del empleado: ");
            String apellido = scanner.nextLine();
            System.out.print("Introduzca el PUESTO del empleado: ");
            String puesto = scanner.nextLine();
            System.out.print("Introduzca el ID del DEPARTAMENTO asociado: ");
            int departamentoId = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            session.beginTransaction();
            Departamento departamento = session.get(Departamento.class, departamentoId);

            if (departamento != null) {
                Empleado nuevoEmpleado = new Empleado(nombre, apellido, puesto);
                nuevoEmpleado.setDepartamento(departamento);
                session.save(nuevoEmpleado);
                session.getTransaction().commit();

                System.out.println("Empleado guardado con éxito: " + nuevoEmpleado);
            } else {
                System.out.println("No se encontró el departamento con ID: " + departamentoId);
                session.getTransaction().rollback();
            }
        } catch (Exception e) {
            System.err.println("Error al insertar el empleado: " + e.getMessage());
        }
    }

    public void buscarEmpleado() {
        try (Session session = factory.getCurrentSession()) {
            System.out.println("###-> VER EMPLEADO <-###");
            System.out.print("Introduzca el ID del empleado: ");
            int empleadoId = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            session.beginTransaction();
            Empleado empleado = session.get(Empleado.class, empleadoId);
            session.getTransaction().commit();

            if (empleado != null) {
                System.out.println(empleado);
            } else {
                System.out.println("No se encontró el empleado con ID: " + empleadoId);
            }
        } catch (Exception e) {
            System.err.println("Error al buscar el empleado: " + e.getMessage());
        }
    }

    public void actualizarEmpleado() {
        try (Session session = factory.getCurrentSession()) {
            System.out.println("###-> ACTUALIZAR EMPLEADO <-###");
            System.out.print("Introduzca el ID del empleado que desea ACTUALIZAR: ");
            int empleadoId = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            session.beginTransaction();
            Empleado empleado = session.get(Empleado.class, empleadoId);

            if (empleado != null) {
                System.out.print("Introduzca el NUEVO NOMBRE del empleado: ");
                String nuevoNombre = scanner.nextLine();
                System.out.print("Introduzca el NUEVO APELLIDO del empleado: ");
                String nuevoApellido = scanner.nextLine();
                System.out.print("Introduzca el NUEVO PUESTO del empleado: ");
                String nuevoPuesto = scanner.nextLine();
                System.out.print("Introduzca el ID del NUEVO DEPARTAMENTO asociado: ");
                int nuevoDepartamentoId = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del scanner

                Departamento nuevoDepartamento = session.get(Departamento.class, nuevoDepartamentoId);

                if (nuevoDepartamento != null) {
                    empleado.setNombre(nuevoNombre);
                    empleado.setApellido(nuevoApellido);
                    empleado.setPuesto(nuevoPuesto);
                    empleado.setDepartamento(nuevoDepartamento);
                    session.update(empleado);

                    session.getTransaction().commit();
                    System.out.println("Empleado actualizado correctamente.");
                } else {
                    System.out.println("No se encontró el departamento con ID: " + nuevoDepartamentoId);
                    session.getTransaction().rollback();
                }
            } else {
                System.out.println("No se encontró el empleado con ID: " + empleadoId);
                session.getTransaction().rollback();
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar el empleado: " + e.getMessage());
        }
    }

    public void eliminarEmpleado() {
        try (Session session = factory.getCurrentSession()) {
            System.out.println("###-> ELIMINAR EMPLEADO <-###");
            System.out.print("Introduzca el ID del empleado que desea ELIMINAR: ");
            int empleadoId = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            session.beginTransaction();
            Empleado empleado = session.get(Empleado.class, empleadoId);

            if (empleado != null) {
                session.delete(empleado);
                session.getTransaction().commit();
                System.out.println("Empleado eliminado correctamente.");
            } else {
                System.out.println("No se encontró el empleado con ID: " + empleadoId);
                session.getTransaction().rollback();
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar el empleado: " + e.getMessage());
        }
    }

    public void listarEmpleados() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Empleado> empleados = session.createQuery("from Empleado", Empleado.class).getResultList();
            session.getTransaction().commit();

            if (!empleados.isEmpty()) {
                System.out.println("###-> LISTADO DE EMPLEADOS <-###");
                for (Empleado empleado : empleados) {
                    System.out.println(empleado);
                }
            } else {
                System.out.println("No hay empleados registrados.");
            }
        } catch (Exception e) {
            System.err.println("Error al listar los empleados: " + e.getMessage());
        }
    }

    public void consultarEmpleadosPorDepartamento() {
        try (Session session = factory.getCurrentSession()) {
            System.out.println("###-> CONSULTAR EMPLEADOS POR DEPARTAMENTO <-###");
            System.out.print("Introduzca el ID del DEPARTAMENTO: ");
            int departamentoId = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            session.beginTransaction();
            Departamento departamento = session.get(Departamento.class, departamentoId);

            if (departamento != null) {
                // Obtener empleados del departamento
                List<Empleado> empleados = session.createQuery(
                                "from Empleado where departamento.id = :departamentoId", Empleado.class)
                        .setParameter("departamentoId", departamentoId)
                        .getResultList();

                if (!empleados.isEmpty()) {
                    System.out.println("###-> EMPLEADOS DEL DEPARTAMENTO: " + departamento.getNombre() + " <-###");
                    for (Empleado empleado : empleados) {
                        System.out.println(empleado);
                    }
                } else {
                    System.out.println("No hay empleados en este departamento.");
                }
            } else {
                System.out.println("No se encontró el departamento con ID: " + departamentoId);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error al consultar empleados por departamento: " + e.getMessage());
        }
    }

    public void cerrarRecursos() {
        if (factory != null) {
            factory.close();
        }
        if (scanner != null) {
            scanner.close();
        }
    }
}
