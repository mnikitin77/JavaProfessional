package com.mvnikitin.lesson2;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.*;

public class DBCreation {

    private Connection connection;
    private final static double MAX_PRICE = 999999999.9999;


    public DBCreation(String dbName) throws SQLException {
        try {
            Class.forName( "org.sqlite.JDBC" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
        connection.setAutoCommit(false);
    }

    public static void main(String[] args) {

        DBCreation db = null;

        try {
            db = new DBCreation("src\\main\\java\\com\\mvnikitin\\lesson2\\lesson2.db");
            // Задание 1. Сформировать таблицу товаров.
            db.createTable("products");
            db.commit();

            // Задание 2. заолнить таблицу товаров 10000 строками.
            db.populateTable("products", 10000);
            db.commit();
        } catch (SQLException e) {
            try {
                db.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            db.closeConnection();
        }
    }

    public void createTable (String tableName) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT * FROM sqlite_master WHERE type='table'" +
                        " AND name='" + tableName + "'");
        if(rs.next()) {
            statement.execute(
                    "DROP TABLE " + tableName);
        }

        statement.execute("CREATE TABLE " + tableName + " (" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "price DECIMAL(13,4) NOT NULL)");
    }

    public void populateTable(String tableName, int valuesCount)
            throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO products (name, price) VALUES (?, ?)");

        MathContext mc = new MathContext(4);

        long timeStart = System.currentTimeMillis();

        for (int i = 0; i < valuesCount; i++) {
            ps.setString(1, "Товар_" + i);
            BigDecimal price = new BigDecimal(Math.random() * MAX_PRICE, mc);
            ps.setBigDecimal(2, price);
            ps.addBatch();
        }

        ps.executeBatch();

        long timeSpent = System.currentTimeMillis() - timeStart;
        System.out.println("Длительность заполнения 10000 записей: " + timeSpent + "мс.");
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commit()
            throws SQLException {
        connection.commit();
    }

    public void rollback()
            throws SQLException {
        connection.rollback();
    }
}
