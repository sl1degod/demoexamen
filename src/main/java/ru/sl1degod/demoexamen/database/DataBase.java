package ru.sl1degod.demoexamen.database;
import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import org.postgresql.Driver;
import ru.sl1degod.demoexamen.models.*;
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
            ResultSet resultSet = connect_to_db().createStatement().executeQuery("SELECT app_form.id as id, equipments.name as equipments, type_of_fault.name as type_of_fault, app_form.date_create, app_form.description as description, status_app.name as status_app, app_form.comments, priority.name as priority, app_form.users_id FROM app_form JOIN status_app ON status_app.id = app_form.status_id JOIN equipments ON equipments.id = app_form.equip_id JOIN type_of_fault ON type_of_fault.id = app_form.fault_id JOIN priority ON priority.id = app_form.priority_id order by app_form.id desc;");
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

    public void getCauses() {
        ObservableList<String> causes = FXCollections.observableArrayList();
        try {
            String query = String.format("select * from cause_of_fault");
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                causes.add(resultSet.getString("name"));
            }
            State.getInstance().setCauses(causes);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getRepairUsers(String id) {
        ObservableList<String> repairUsers = FXCollections.observableArrayList();
        ObservableList<String> repairApp_form = FXCollections.observableArrayList();
        try {
            String query = String.format("select app_form.id, app_form.users_id from app_form join repair on repair.app_id = app_form.id where repair.status_repair_id = 1 and app_form.users_id = '%s'", id);
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                repairApp_form.add(resultSet.getString("id"));
                repairUsers.add(resultSet.getString("users_id"));
            }
            State.getInstance().setRepair_user_id(repairUsers);
            State.getInstance().setApp_form_ids(repairApp_form);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getStatusRepairData() {
        ObservableList<String> status_repair = FXCollections.observableArrayList();
        try {
            String query = String.format("select * from status_repair");
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                status_repair.add(resultSet.getString("name"));
            }
            State.getInstance().setStatus_repair(status_repair);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getStatusApp_form() {
        ObservableList<String> status_repair = FXCollections.observableArrayList();
        try {
            String query = String.format("select * from status_app");
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                status_repair.add(resultSet.getString("name"));
            }
            State.getInstance().setStatus_app_form(status_repair);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAdminsData() {
        ObservableList<String> admins = FXCollections.observableArrayList();
        try {
            String query = String.format("select concat(firstname, ' ', LEFT(secondname, 1), '. ', LEFT(lastname, 1), '.') as FIO from users where roles_id = 2");
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                admins.add(resultSet.getString("fio"));
            }
            State.getInstance().setAdmins(admins);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getMaterials() {
        ObservableList<String> materials = FXCollections.observableArrayList();
        try {
            String query = String.format("select * from materials");
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                materials.add(resultSet.getString("name"));
            }
            State.getInstance().setMaterials(materials);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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

    public ObservableList<Repair> getRepairs(String id) {
        ObservableList<Repair> repairs = FXCollections.observableArrayList();
        try {
            String query = String.format("SELECT repair.app_id as app_form_id, repair.user_id as admin, repair.time_repair as time, repair.price as price, cause_of_fault.name as cause, repair.assistance as assistance, status_repair.name as status_repair, repair.rating as rating FROM repair JOIN cause_of_fault ON cause_of_fault.id = repair.cause_id JOIN status_repair ON status_repair.id = repair.status_repair_id JOIN app_form ON app_form.id = repair.app_id JOIN users ON users.id = app_form.users_id where app_form.users_id = '%s'", id);
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                repairs.add(new Repair(
                        resultSet.getString("app_form_id"),
                        resultSet.getString("admin"),
                        resultSet.getString("time"),
                        resultSet.getString("price"),
                        resultSet.getString("cause"),
                        resultSet.getString("assistance"),
                        resultSet.getString("status_repair"),
                        resultSet.getString("rating")
                ));
            }
            return repairs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return repairs;
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

    public void updateApp_form(String statusApp, String desc, String comments, String id) {
        try {
            String query = String.format("update app_form set status_id = '%s', comments = '%s', description = '%s' where id = '%s'", statusApp, comments, desc, id);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            System.out.println("Data created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createRepair(String app_id, String admin_id, Integer time_repair, String price, String cause_id, String assistance, String status_repair_id, String rating) {
        try {
            String query = String.format("insert into repair (app_id, user_id, time_repair, price, cause_id, assistance, status_repair_id, rating) values ('%s','%s', '%s', '%s', '%s', '%s', '%s', '%s') RETURNING *", app_id, admin_id, time_repair, price, cause_id, assistance, status_repair_id, rating);
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
              State.getInstance().setApp_form_id(resultSet.getString("id"));
                System.out.println(State.getInstance().getApp_form_id());
            }
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

    public ObservableList<Stats> getStats() {
        ObservableList<Stats> stats = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connect_to_db().createStatement().executeQuery("select count(app_form.id) as count, avg(repair.time_repair), cause_of_fault.name from app_form, status_app, repair, cause_of_fault where app_form.status_id = 3 and app_form.status_id = status_app.id and repair.app_id = app_form.id and cause_of_fault.id = repair.cause_id group by cause_of_fault.name ORDER BY COUNT(*) DESC LIMIT 1");
            while (resultSet.next()) {
                stats.add(new Stats(
                        resultSet.getString("count"),
                        resultSet.getString("avg"),
                        resultSet.getString("name")
                ));
            }
            return stats;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return stats;
        }
    }
}
