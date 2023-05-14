package com.example.carpet.Repositories;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.carpet.Models.Contact;
import com.example.carpet.Models.ContactDTO;
import com.example.carpet.Services.ConnectionService;

@Repository
public class ContactsRepositoryImpl implements ContactsRepository {

    private ConnectionService connectionService;

    public ContactsRepositoryImpl(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @Override
    public Optional<Contact> Get(int id) throws SQLException {
        Connection conn = null;

        try {
            conn = connectionService.GetConnection();
            var query = """
                    SELECT *
                    FROM contacts c
                    INNER JOIN cities ci
                        ON c.cityId = ci.id
                    INNER JOIN states s
                        ON ci.stateId = s.id
                    WHERE c.id = ?
                        """;
            ;
            var stmt = conn.prepareStatement(query);

            stmt.setObject(1, id);

            var rs = stmt.executeQuery();
            Contact contact = null;

            if (rs.next()) {
                contact = new Contact(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("streetAddress"),
                        rs.getInt("streetNumber"),
                        rs.getString("phoneNumber"),
                        rs.getString("cityName"),
                        rs.getString("stateName"),
                        rs.getInt("userId"));

            }
            return Optional.ofNullable(contact);

        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    @Override
    public ArrayList<Contact> GetAll() throws SQLException {
        Connection conn = null;
        var contacts = new ArrayList<Contact>();

        try {
            conn = connectionService.GetConnection();
            var query = """
                    SELECT *
                    FROM contacts c
                    INNER JOIN cities ci
                        ON c.cityId = ci.id
                    INNER JOIN states s
                        ON ci.stateId = s.id
                        """;
            ;
            var stmt = conn.prepareStatement(query);

            var rs = stmt.executeQuery();

            while (rs.next()) {
                var contact = new Contact(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("streetAddress"),
                        rs.getInt("streetNumber"),
                        rs.getString("phoneNumber"),
                        rs.getString("cityName"),
                        rs.getString("stateName"),
                        rs.getInt("userId"));

                contacts.add(contact);
            }

            return contacts;

        } finally {

            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public boolean Create(ContactDTO contact) throws SQLException {
        Connection conn = null;

        try {
            conn = connectionService.GetConnection();
            var query = """
                    INSERT INTO contacts (firstName, lastName, email, streetAddress, streetNumber, phoneNumber, cityId, userId)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                        """;
            ;
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, contact.getFirstName());
            stmt.setString(2, contact.getLastName());
            stmt.setString(3, contact.getEmail());
            stmt.setString(4, contact.getStreetAddress());
            stmt.setInt(5, contact.getStreetNumber());
            stmt.setString(6, contact.getPhoneNumber());
            stmt.setInt(7, contact.getCityId());
            stmt.setInt(8, 2);
            return stmt.executeUpdate() > 0;

        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public boolean Update(int id, ContactDTO payload) throws SQLException {
        Connection conn = null;

        try {
            conn = connectionService.GetConnection();
            var query = """
                    UPDATE contacts
                    SET
                        firstName = COALESCE(?, firstName),
                        lastName = COALESCE(?, lastName),
                        email = COALESCE(?, email),
                        streetAddress = COALESCE(?, streetAddress),
                        streetNumber = COALESCE(?, streetNumber),
                        phoneNumber = COALESCE(?, phoneNumber),
                        cityId = ?
                    WHERE id = ?
                        """;
            ;
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, payload.getFirstName());
            stmt.setString(2, payload.getLastName());
            stmt.setString(3, payload.getEmail());
            stmt.setString(4, payload.getStreetAddress());
            stmt.setInt(5, payload.getStreetNumber());
            stmt.setString(6, payload.getPhoneNumber());
            stmt.setInt(7, payload.getCityId());
            stmt.setInt(8, id);

            return stmt.executeUpdate() > 0;

        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public boolean Delete(int id) throws SQLException {
        Connection conn = null;

        try {
            conn = connectionService.GetConnection();
            var query = "DELETE FROM contacts WHERE id = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

}
