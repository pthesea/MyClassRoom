package Model;

import View.Class;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClassDAO {
    private Connection connection;

    public ClassDAO() {
        connection = new ConnectionDatabase().getConnection();
    }

    public Object[] readAllClasseOwned(int userId) {
        ArrayList<Classes> classes = new ArrayList<Classes>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from class where fk_user = " + userId + "");
            while(result.next()) {
                Classes newClasses = new Classes();
                newClasses.id = result.getInt("id");
                newClasses.code = result.getString("code");
                newClasses.name = result.getString("name");
                newClasses.fk_user = result.getInt("fk_user");
                classes.add(newClasses);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes.toArray();
    }

    public Object[] readAllClassesEnrolled(int userId) {
        ArrayList<Classes> classes = new ArrayList<Classes>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from classes where id in (select fk_class from enrollement where fk_user = " + userId + "");
            while(result.next()) {
                Classes newClasses = new Classes();
                newClasses.id = result.getInt("id");
                newClasses.code = result.getString("code");
                newClasses.name = result.getString("name");
                newClasses.fk_user = result.getInt("fk_user");
                classes.add(newClasses);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes.toArray();
    }

    public int searchClass(String code) {
        Classes classes = new Classes();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select id from classes where code = " + code + "");

                classes.id = result.getInt("id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes.id;
    }

    public void insertClass(String code, String name, int fk_user) {
        try {
            String query = "insert into class (code, name, fk_user) values (" +
                    ", '" + code + "', '" + name + "', '" + fk_user + "')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAssignment(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
