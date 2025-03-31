package exam_ignateva_231;

import java.sql.*;

public class DataBase {
    private final String host = "localhost";
    private final String port = "5432";
    private final String dbName = "spotify";
    private final String login = "divanstrawberry";
    private final String password = "";

    private Connection dbCon;

    private Connection getDbConnection() {
        String str = "jdbc:postgresql://" + host + ":"
                + port + "/" + dbName;
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Соединение установлено");
        } catch (ClassNotFoundException e) {
            System.out.println("соединение не установлено");
        }
        try {
            dbCon = DriverManager.getConnection(str, login, password);
        } catch (SQLException e) {
            System.out.println("неверный путь (логин и пароль)");
        }
        return dbCon;
    }

    public void isConnection() throws SQLException, ClassNotFoundException {
        dbCon = getDbConnection();
        System.out.println(dbCon.isValid(1000));
    }


    public void createTableSeans(String seans) throws ClassNotFoundException, SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS " + seans + " (id INT PRIMARY KEY, filmname VARCHAR(60), status VARCHAR(60));";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql);
    }

    public void addValuesSeans(String table_name, int id, String filmname, String status) throws SQLException {
        String sql = "INSERT INTO " + table_name + " VALUES(?, ?, ?);";
        PreparedStatement preparedStatements = getDbConnection().prepareStatement(sql);
        preparedStatements.setInt(1, id);
        preparedStatements.setString(2, filmname);
        preparedStatements.setString(3, status);
        preparedStatements.executeUpdate();
    }

    public void selectTableSeans(String seans) throws SQLException {
        Statement statement = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from " + seans);
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String filmname = resultSet.getString(2);
            String status = resultSet.getString(3);
            System.out.printf("%d. %s. %s. \n", id, filmname, status);
        }
    }

    public void searchTableSeans(String seans) throws SQLException {
        Statement statement = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM seans WHERE status = 'забронировано'");
        while (resultSet.next()) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String filmname = resultSet.getString(2);
                String status = resultSet.getString(3);
                System.out.printf("%d. %s. %s. \n", id, filmname, status);
            }
        }
    }

    public void searchTableSeans2(String seans) throws SQLException {
        Statement statement = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM seans WHERE status = 'свободно'");
        while (resultSet.next()) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String filmname = resultSet.getString(2);
                String status = resultSet.getString(3);
                System.out.printf("%d. %s. %s. \n", id, filmname, status);
            }
        }
    }
}