package org.example.todoDepartamento;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.example.todoEmpresa.Empresa;

import java.util.List;
import java.util.Scanner;

public class repositoryDepartamento {
    private SessionFactory factory;
    private Scanner scanner;

    public repositoryDepartamento() {
        // Inicializar SessionFactory una sola vez
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Departamento.class)
                .addAnnotatedClass(Empresa.class)
                .buildSessionFactory();
        scanner = new Scanner(System.in); // Instancia compartida de Scanner
    }

    public void insertarDepartamento() {
        try (Session session = factory.getCurrentSession()) {
            System.out.println("###-> AÑADIR DEPARTAMENTO <-###");
            System.out.print("Introduzca el NOMBRE del departamento: ");
            String nombre = scanner.nextLine();
            System.out.print("Introduzca el ID de la empresa asociada: ");
            int empresaId = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            session.beginTransaction();
            Empresa empresa = session.get(Empresa.class, empresaId);

            if (empresa != null) {
                Departamento nuevoDepartamento = new Departamento(nombre);
                nuevoDepartamento.setEmpresa(empresa);
                session.save(nuevoDepartamento);
                session.getTransaction().commit();

                System.out.println("Departamento guardado con éxito: " + nuevoDepartamento);
            } else {
                System.out.println("No se encontró la empresa con ID: " + empresaId);
                session.getTransaction().rollback();
            }
        } catch (Exception e) {
            System.err.println("Error al insertar el departamento: " + e.getMessage());
        }
    }

    public void buscarDepartamento() {
        try (Session session = factory.getCurrentSession()) {
            System.out.println("###-> VER DEPARTAMENTO <-###");
            System.out.print("Introduzca el ID del departamento: ");
            int departamentoId = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            session.beginTransaction();
            Departamento departamento = session.get(Departamento.class, departamentoId);
            session.getTransaction().commit();

            if (departamento != null) {
                System.out.println(departamento);
            } else {
                System.out.println("No se encontró el departamento con ID: " + departamentoId);
            }
        } catch (Exception e) {
            System.err.println("Error al buscar el departamento: " + e.getMessage());
        }
    }

    public void actualizarDepartamento() {
        try (Session session = factory.getCurrentSession()) {
            System.out.println("###-> ACTUALIZAR DEPARTAMENTO <-###");
            System.out.print("Introduzca el ID del departamento que desea ACTUALIZAR: ");
            int departamentoId = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            session.beginTransaction();
            Departamento departamento = session.get(Departamento.class, departamentoId);

            if (departamento != null) {
                System.out.print("Introduzca el NUEVO NOMBRE del departamento: ");
                String nuevoNombre = scanner.nextLine();
                System.out.print("Introduzca el ID de la NUEVA EMPRESA asociada: ");
                int nuevoEmpresaId = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del scanner

                Empresa nuevaEmpresa = session.get(Empresa.class, nuevoEmpresaId);

                if (nuevaEmpresa != null) {
                    departamento.setNombre(nuevoNombre);
                    departamento.setEmpresa(nuevaEmpresa);
                    session.update(departamento);

                    session.getTransaction().commit();
                    System.out.println("Departamento actualizado correctamente.");
                } else {
                    System.out.println("No se encontró la empresa con ID: " + nuevoEmpresaId);
                    session.getTransaction().rollback();
                }
            } else {
                System.out.println("No se encontró el departamento con ID: " + departamentoId);
                session.getTransaction().rollback();
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar el departamento: " + e.getMessage());
        }
    }

    public void eliminarDepartamento() {
        try (Session session = factory.getCurrentSession()) {
            System.out.println("###-> ELIMINAR DEPARTAMENTO <-###");
            System.out.print("Introduzca el ID del departamento que desea ELIMINAR: ");
            int departamentoId = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            session.beginTransaction();
            Departamento departamento = session.get(Departamento.class, departamentoId);

            if (departamento != null) {
                session.delete(departamento);
                session.getTransaction().commit();
                System.out.println("Departamento eliminado correctamente.");
            } else {
                System.out.println("No se encontró el departamento con ID: " + departamentoId);
                session.getTransaction().rollback();
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar el departamento: " + e.getMessage());
        }
    }

    public void listarDepartamentos() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Departamento> departamentos = session.createQuery("from Departamento", Departamento.class).getResultList();
            session.getTransaction().commit();

            if (!departamentos.isEmpty()) {
                System.out.println("###-> LISTADO DE DEPARTAMENTOS <-###");
                for (Departamento departamento : departamentos) {
                    System.out.println(departamento);
                }
            } else {
                System.out.println("No hay departamentos registrados.");
            }
        } catch (Exception e) {
            System.err.println("Error al listar los departamentos: " + e.getMessage());
        }
    }

    public void mostrarDepartamentosConNumeroDeEmpleados() {
        try (Session session = factory.getCurrentSession()) {
            System.out.println("###-> MOSTRAR DEPARTAMENTOS DE UNA EMPRESA <-###");
            System.out.print("Introduzca el ID de la EMPRESA: ");
            int empresaId = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            session.beginTransaction();
            Empresa empresa = session.get(Empresa.class, empresaId);

            if (empresa != null) {
                // Consulta para obtener departamentos y el número de empleados
                List<Object[]> resultados = session.createQuery(
                                "select d.nombre, count(e.id) " +
                                        "from Departamento d " +
                                        "left join Empleado e on d.id = e.departamento.id " +
                                        "where d.empresa.id = :empresaId " +
                                        "group by d.id, d.nombre", Object[].class)
                        .setParameter("empresaId", empresaId)
                        .getResultList();

                System.out.println("###-> DEPARTAMENTOS DE LA EMPRESA: " + empresa.getNombre() + " <-###");

                if (!resultados.isEmpty()) {
                    for (Object[] fila : resultados) {
                        String nombreDepartamento = (String) fila[0];
                        Long numeroEmpleados = (Long) fila[1];
                        System.out.println("Departamento: " + nombreDepartamento + " | Número de empleados: " + numeroEmpleados);
                    }
                } else {
                    System.out.println("La empresa no tiene departamentos registrados.");
                }
            } else {
                System.out.println("No se encontró la empresa con ID: " + empresaId);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error al mostrar los departamentos de la empresa: " + e.getMessage());
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
