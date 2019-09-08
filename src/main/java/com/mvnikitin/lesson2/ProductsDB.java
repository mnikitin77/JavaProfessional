package com.mvnikitin.lesson2;

import java.sql.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductsDB {

    private Connection connection;

    public ProductsDB(String dbName) throws SQLException {
        try {
            Class.forName( "org.sqlite.JDBC" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
    }

    public static void main(String[] args) {

        ProductsDB.printInfo();
        ProductsDB productsDB = null;
        Scanner scanner = null;

        try {
            productsDB = new ProductsDB(
                    "src\\main\\java\\com\\mvnikitin\\lesson2\\lesson2.db");
            scanner = new Scanner(System.in);

            boolean isExit = false;
            while(scanner.hasNextLine()) {

                String userInput = scanner.nextLine();

                String[] commandString = userInput.split(" ", 2);

                switch (commandString[0]) {
                    case "/выход":
                        isExit = true;
                        break;
                    // Задание 3. /цена <название_товара_в_базе>.
                    case "/цена":
                        if(!productsDB.validateElementsNumber(
                                userInput.split(" "), 2)) {
                            break;
                        }
                        productsDB.getPriceByName(commandString[1]);
                        break;
                    // Задание 4.
                    // /сменитьцену <название_товара_в_базе> <новая_цена>.
                    case "/сменитьцену":
                        if(!productsDB.validateElementsNumber(
                                userInput.split(" "), 3)) {
                            break;
                        }

                        String[] tokens = commandString[1].split(" ");
                        double d = Double.parseDouble(tokens[1]);
                        if(!productsDB.isNumber(tokens[1])) {
                            System.out.println("Некорректное значение цены.");
                            break;
                        }

                        productsDB.changePrice(tokens[0], tokens[1]);
                        break;
                    // Задание 5. /товарыпоцене <цена_от> <цена_до>.
                    case "/товарыпоцене":
                        if(!productsDB.validateElementsNumber(
                                userInput.split(" "), 3)) {
                            break;
                        }

                        tokens = commandString[1].split(" ");
                        if(!productsDB.isNumber(tokens[0]) ||
                                !productsDB.isNumber(tokens[1])) {
                            System.out.println("Некорректное значение цены.");
                            break;
                        }

                        productsDB.getProductsInPriceRange(tokens[0], tokens[1]);
                        break;
                    default:
                        System.out.println("Неверная команда.");
                }

                if (isExit)
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(productsDB != null) productsDB.closeConnection();
            if(scanner != null) scanner.close();
        }
    }

    public void getPriceByName(String productName)
            throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT price FROM products WHERE name ='" +
                        productName + "'");
        if(rs.next()) {
            System.out.println(rs.getBigDecimal(1));
        } else {
            System.out.println("Такого товара нет.");
        }
    }

    public void changePrice(String productName, String newPrice)
            throws SQLException {

        Statement statement = connection.createStatement();
        int res = statement.executeUpdate(
                "UPDATE products SET price=" + newPrice +
                        " WHERE name='" + productName + "'");
        if(res > 0) {
            System.out.println("Изменение цены выполнено успешно.");
        } else {
            System.out.println("Такого товара нет.");
        }
    }

    public void getProductsInPriceRange(String priceFrom, String priceTo)
            throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT name, price FROM products WHERE price>=" +
                        priceFrom + " AND price <=" + priceTo);
        if(rs.next()) {
            do {
                System.out.println(rs.getString(1) + " " +
                        rs.getBigDecimal(2));
            } while (rs.next());
        } else {
            System.out.println("Товары, соответствующие заданному критерию," +
                    " отсутствуют.");
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean validateElementsNumber(
            String[] commandStrings, int elementsNumber) {
        boolean res = true;
        if(commandStrings.length < elementsNumber) {
            System.out.println("неверный формат команды " + commandStrings[0]);
            res = false;
        }
        return res;
    }

    private boolean isNumber(String value) {
        boolean res = false;
        //Pattern p = Pattern.compile("[0-9]+.[0-9]+");
        Pattern p = Pattern.compile("^(0|[1-9]\\d*)(\\.\\d+)?$");
        Matcher m = p.matcher(value);
        if(m.matches()){
            res = true;
        }
        return res;
    }

    private static void printInfo() {
        System.out.println("" +
                "********************************************************************************\n" +
                "* Команды для работы с базой данных товаров:                                   *\n" +
                "* /цена <название_товара_в_базе> - запрос цены товара                          *\n" +
                "* /сменитьцену <название_товара_в_базе> <новая_цена> - " +
                "заменить цену товара    *\n" +
                "* /товарыпоцене <цена_от> <цена_до> - вывод на экран товаров в интервале цен   *\n" +
                "* /выход - выход из программы.                                                 *\n" +
                "********************************************************************************\n\n");
    }
}
