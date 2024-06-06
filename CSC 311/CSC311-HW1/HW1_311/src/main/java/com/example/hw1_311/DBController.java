package com.example.hw1_311;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * This class contains methods to handle interactions between
 * json files and the database, and the Database and AppController.
 * @author Amaurys De Los Santos Mendez
 */
public class DBController {
    /**
     * Creates a database file and a table.
     */
    public static void createDBAndTable(){
        String dbFilePath = ".//GradeBook.accdb";
        String databaseURL = "jdbc:ucanaccess://" + dbFilePath;
        File dbFile = new File(dbFilePath);

        if (!dbFile.exists()) {
            try (Database db =
                         DatabaseBuilder.create(Database.FileFormat.V2010, new File(dbFilePath))) {
                System.out.println("The database file has been created.");
            } catch (IOException ioe) {
                ioe.printStackTrace(System.err);
            }
        }
        //
        // Create Table
        //
        try {

            Connection conn = DriverManager.getConnection(databaseURL);
            String sql;
            sql = "CREATE TABLE Grades (Name nvarchar(255), Category nvarchar(255), Score INT)";
            Statement createTableStatement = conn.createStatement();
            createTableStatement.execute(sql);
            conn.commit();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Inserts a {@link Grade} into the database.
     * @param conn the database connection.
     * @param grade the grade to be inserted.
     */
    public static void insertDataToDB(Connection conn, Grade grade){
        String sql = "INSERT INTO Grades (Name, Category, Score) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, grade.getName());
            preparedStatement.setString(2, grade.getCategory());
            preparedStatement.setInt(3, grade.getScore());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Populates a json file with the information from a database.
     * @param file The json file.
     * @param conn Connection to the database.
     */
    public static void fillJSONfromDB(File file, Connection conn){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String jsonString = gson.toJson(getData(conn));

        PrintStream ps = null;

        try {
            ps = new PrintStream(file);
            ps.println(jsonString);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Populates the database with the information from a json file.
     *
     * @param conn Connection to the database.
     * @param file The json file.
     */
    public static void fillDBfromJSON(Connection conn, File file){

        DBController.clearAllDBData(conn);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileReader fr = null;

        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Grade> temp = gson.fromJson(fr, new TypeToken<ArrayList<Grade>>(){}.getType());
        DBController.insertListOfData(conn, temp);

    }

    /**
     * Fills the database with a list of items.
     * @param conn Connection to the database.
     * @param list List of grades to be added to the database.
     */

    public static void insertListOfData(Connection conn, List<Grade> list){
        if(list == null){
            return;
        }

        clearAllDBData(conn);
        list.forEach(e->{
            insertDataToDB(conn,e);
        });
    }

    /**
     * Prints the data from the database into the terminal.
     * @param conn Connection to the database.
     */
    public static void showData(Connection conn){
        try {
            String tableName = "Grades";
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select * from " + tableName);
            while (result.next()) {
                String name = result.getString("Name");
                String category = result.getString("Category");
                int salary = result.getInt("Score");

                System.out.printf("%s %s %d\n", name, category, salary);
            }
        } catch (SQLException except) {
            except.printStackTrace();
        }

    }

    /**
     * Retrieves the information from the database and returns it as a linked-list.
     * @param conn Connection to the database.
     * @return LinkedList<Grade> A list of grades from the database.
     */
    public static LinkedList<Grade> getData(Connection conn){
        ResultSet result = null;
        LinkedList<Grade> listOfGrades = new LinkedList<>();
        try {
            String tableName = "Grades";
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("select * from " + tableName);
        } catch (SQLException except) {
            except.printStackTrace();
        }

        while (true) {
            try {
                if (!result.next()) break;

                String name = result.getString("Name");
                String category = result.getString("Category");
                int salary = result.getInt("Score");

                Grade temp = new Grade(name, category, salary);
                listOfGrades.add(temp);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return listOfGrades;

    }

    /**
     * Clears all data from the database without effecting the json file.
     * @param conn Connection to the database.
     */
    public static void clearAllDBData(Connection conn){

        String sql = "DELETE FROM Grades";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            int rowsDeleted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
