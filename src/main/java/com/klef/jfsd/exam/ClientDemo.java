package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

        // Insert Records
        insertClient(session, "Alice", "Female", 30, "New York", "alice@example.com", "1234567890");
        insertClient(session, "Bob", "Male", 25, "California", "bob@example.com", "0987654321");

        // Retrieve and Display Records
        retrieveAllClients(session);

        tx.commit();
        session.close();
        sf.close();
    }

    private static void insertClient(Session session, String name, String gender, int age, String location, String email, String mobile) {
        Client client = new Client();
        client.setName(name);
        client.setGender(gender);
        client.setAge(age);
        client.setLocation(location);
        client.setEmail(email);
        client.setMobile(mobile);

        session.save(client);
        System.out.println("Client inserted: " + name);
    }

    private static void retrieveAllClients(Session session) {
        List<Client> clients = session.createQuery("from Client", Client.class).getResultList();
        for (Client client : clients) {
            System.out.println("ID: " + client.getId() +
                    ", Name: " + client.getName() +
                    ", Gender: " + client.getGender() +
                    ", Age: " + client.getAge() +
                    ", Location: " + client.getLocation() +
                    ", Email: " + client.getEmail() +
                    ", Mobile: " + client.getMobile());
        }
    }
}
