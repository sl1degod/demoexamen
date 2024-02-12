package ru.sl1degod.demoexamen.database;
import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.postgresql.Driver;
import ru.sl1degod.demoexamen.models.App_form;
import ru.sl1degod.demoexamen.models.Equipments;
import ru.sl1degod.demoexamen.models.TypeOfFault;
import ru.sl1degod.demoexamen.models.User;
import ru.sl1degod.demoexamen.utils.State;

public class DataBase {
    public Connection connect_to_db() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + "test", "postgres", "123");
            if (connection != null) {
                System.out.println("Connection successful");
            } else {
                System.out.println("Connection failed");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public int check_login(String login) {
        try {
            String query = String.format("select * from users where login = '%s'", login);
            Statement statement = connect_to_db().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.last();
            if (resultSet.getRow() >= 1) {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 404;
        }
        return 201;
    }

    public int signIn(String login, String password) {
        try {
            String query = String.format("select users.id, users.firstname, users.secondname, users.lastname, roles.name as role, users.login, users.password from users, roles where users.roles_id = roles.id and login = '%s' and password = '%s'", login, password);
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
                return 0;
            }
            State.getInstance().setRole(resultSet.getString("role"));
            State.getInstance().setUser_id(resultSet.getString("id"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 404;
        }
        return 201;
    }

    public ObservableList<App_form> getApp_form() {
        ObservableList<App_form> appForms = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connect_to_db().createStatement().executeQuery("SELECT app_form.id as id, equipments.name as equipments, type_of_fault.name as type_of_fault, app_form.date_create, app_form.description as description, status_app.name as status_app, app_form.comments, priority.name as priority, app_form.users_id FROM app_form JOIN status_app ON status_app.id = app_form.status_id JOIN equipments ON equipments.id = app_form.equip_id JOIN type_of_fault ON type_of_fault.id = app_form.fault_id JOIN priority ON priority.id = app_form.priority_id;");
            while (resultSet.next()) {
                appForms.add(new App_form(
                        resultSet.getString("id"),
                        resultSet.getString("equipments"),
                        resultSet.getString("type_of_fault"),
                        resultSet.getString("date_create"),
                        resultSet.getString("description"),
                        resultSet.getString("status_app"),
                        resultSet.getString("comments"),
                        resultSet.getString("priority"),
                        resultSet.getString("users_id")
                ));
            }
            return appForms;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return appForms;
        }
    }

    public ObservableList<App_form> getUserApp_form(String id) {
        ObservableList<App_form> appForms = FXCollections.observableArrayList();
        try {
            String query = String.format("SELECT app_form.id as id, equipments.name as equipments, type_of_fault.name as type_of_fault, app_form.date_create, app_form.description as description, status_app.name as status_app, app_form.comments, priority.name as priority, app_form.users_id FROM app_form JOIN status_app ON status_app.id = app_form.status_id JOIN equipments ON equipments.id = app_form.equip_id JOIN type_of_fault ON type_of_fault.id = app_form.fault_id JOIN priority ON priority.id = app_form.priority_id where app_form.users_id = '%s';", id);
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                appForms.add(new App_form(
                        resultSet.getString("id"),
                        resultSet.getString("equipments"),
                        resultSet.getString("type_of_fault"),
                        resultSet.getString("date_create"),
                        resultSet.getString("description"),
                        resultSet.getString("status_app"),
                        resultSet.getString("comments"),
                        resultSet.getString("priority"),
                        resultSet.getString("users_id")
                ));
            }
            return appForms;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return appForms;
        }
    }

    public void createApp_form(String equip, String typeOfFault, String desc, String user_id) {
        try {
            String query = String.format("insert into app_form (users_id, equip_id, fault_id, date_create, description, status_id, comments, priority_id) values ('%s','%s', '%s', now(), '%s', 1, '', 2)", user_id, equip, typeOfFault, desc, user_id);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            System.out.println("Data created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<User> getUser(String id) {
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            String query = String.format("select users.id, users.firstname, users.secondname, users.lastname, roles.name as role, users.login, users.password from users, roles where users.roles_id = roles.id and users.id = '%s'", id);
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setSecondName(resultSet.getString("secondName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setRoles_id(resultSet.getString("role"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return users;
        }
    }

    public ObservableList<Equipments> getEquipments() {
        ObservableList<Equipments> equipments = FXCollections.observableArrayList();
        ObservableList<String> names = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connect_to_db().createStatement().executeQuery("select * from Equipments");
            while (resultSet.next()) {
                equipments.add(new Equipments(
                        resultSet.getString("id"),
                        resultSet.getString("serial_number"),
                        resultSet.getString("name")
                ));
                names.add(resultSet.getString("name"));
            }
            State.getInstance().setEquipNames(names);
            return equipments;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return equipments;
        }
    }

    public ObservableList<TypeOfFault> getFaults() {
        ObservableList<TypeOfFault> typeOfFaults = FXCollections.observableArrayList();
        ObservableList<String> names = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connect_to_db().createStatement().executeQuery("select * from type_of_fault");
            while (resultSet.next()) {
                typeOfFaults.add(new TypeOfFault(
                        resultSet.getString("id"),
                        resultSet.getString("name")
                ));
                names.add(resultSet.getString("name"));
            }
            State.getInstance().setFaultNames(names);

            return typeOfFaults;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return typeOfFaults;
        }
    }
}
