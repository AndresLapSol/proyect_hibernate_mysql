package org.example.todoEmpresa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class repositoryEmpresa {

    private SessionFactory factory;
    private Scanner scanner;

    public repositoryEmpresa() {
        // Inicializar SessionFactory una sola vez
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Empresa.class)
                .buildSessionFactory();
        scanner = new Scanner(System.in); // Instancia compartida de Scanner
    }

    public void insertarEmpresa() {
        try (Session session = factory.getCurrentSession()) {
            System.out.println("###-> AÑADIR EMPRESA <-###");
            System.out.print("Introduzca el NOMBRE de la empresa: ");
            String nombre = scanner.nextLine();
            System.out.print("Introduzca la INDUSTRIA de la empresa: ");
            String industria = scanner.nextLine();

            Empresa nuevaEmpresa = new Empresa(nombre, industria);

            session.beginTransaction();
            session.save(nuevaEmpresa);
            session.getTransaction().commit();

            System.out.println("Empresa guardada con éxito: " + nuevaEmpresa);
        } catch (Exception e) {
            System.err.println("Error al insertar la empresa: " + e.getMessage());
        }
    }

    public void buscarEmpresa() {
        try (Session session = factory.getCurrentSession()) {
            System.out.println("###-> VER EMPRESA <-###");
            System.out.print("Introduzca el ID de la empresa: ");
            int empresaId = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            session.beginTransaction();
            Empresa empresa = session.get(Empresa.class, empresaId);
            session.getTransaction().commit();

            if (empresa != null) {
                System.out.println(empresa);
            } else {
                System.out.println("No se encontró la empresa con ID: " + empresaId);
            }
        } catch (Exception e) {
            System.err.println("Error al buscar la empresa: " + e.getMessage());
        }
    }

    public void actualizarEmpresa() {
        try (Session session = factory.getCurrentSession()) {
            System.out.println("###-> ACTUALIZAR EMPRESA <-###");
            System.out.print("Introduzca el ID de la empresa que desea ACTUALIZAR: ");
            int empresaId = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            session.beginTransaction();
            Empresa empresa = session.get(Empresa.class, empresaId);

            if (empresa != null) {
                System.out.print("Introduzca el NUEVO NOMBRE de la empresa: ");
                String nuevoNombre = scanner.nextLine();
                System.out.print("Introduzca la NUEVA INDUSTRIA de la empresa: ");
                String nuevaIndustria = scanner.nextLine();

                empresa.setNombre(nuevoNombre);
                empresa.setIndustria(nuevaIndustria);
                session.update(empresa);

                session.getTransaction().commit();
                System.out.println("Empresa actualizada correctamente.");
            } else {
                System.out.println("No se encontró la empresa con ID: " + empresaId);
                session.getTransaction().rollback();
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar la empresa: " + e.getMessage());
        }
    }

    public void eliminarEmpresa() {
        try (Session session = factory.getCurrentSession()) {
            System.out.println("###-> ELIMINAR EMPRESA <-###");
            System.out.print("Introduzca el ID de la empresa que desea ELIMINAR: ");
            int empresaId = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            session.beginTransaction();
            Empresa empresa = session.get(Empresa.class, empresaId);

            if (empresa != null) {
                session.delete(empresa);
                session.getTransaction().commit();
                System.out.println("Empresa eliminada correctamente.");
            } else {
                System.out.println("No se encontró la empresa con ID: " + empresaId);
                session.getTransaction().rollback();
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar la empresa: " + e.getMessage());
        }
    }

    public void listarEmpresas() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Empresa> empresas = session.createQuery("from Empresa", Empresa.class).getResultList();
            session.getTransaction().commit();

            if (!empresas.isEmpty()) {
                System.out.println("###-> LISTADO DE EMPRESAS <-###");
                for (Empresa empresa : empresas) {
                    System.out.println(empresa);
                }
            } else {
                System.out.println("No hay empresas registradas.");
            }
        } catch (Exception e) {
            System.err.println("Error al listar las empresas: " + e.getMessage());
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
