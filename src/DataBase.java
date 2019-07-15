import java.sql.*;

public class DataBase {
    public static Connection connection;
    public static Statement statement;

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:Users.db");
            statement = connection.createStatement();
            System.out.println("База подключена");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createTable() throws SQLException {
        statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS 'Humans'" +
                "('id' INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT," +
                "'login' text UNIQUE," +
                "'password' text," +
                "'nickname' text UNIQUE " +
                ")");
        System.out.println("Таблица создана или уже существует");
    }
    public static void addToDB(String log, String pass, String nick) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO 'Humans'" +
                        "(login,password,nickname) " +
                        "VALUES (?,?,?)");
        ps.setString(1,log);
        ps.setString(2,pass);
        ps.setString(3,nick);
        ps.execute();

    }
    public static void deleteFromDB(String nick) throws SQLException {
        String sql = String.format("DELETE FROM Humans WHERE nickname = '%s'",nick);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.execute();
    }
    public static void updateNick(String exNick, String newNick) throws SQLException {
        String sql = String.format("UPDATE Humans SET nickname = '%s' WHERE nickname = '%s' ", newNick,exNick);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.execute();
    }
    public static boolean isUsed(String where, String value)
    {

        String sql = null;
        if(where.equalsIgnoreCase("login")){
            sql = String.format("SELECT nickname FROM Humans where login = '%s'", value);
        }
        else if(where.equalsIgnoreCase("nickname")){
        //else{
            sql = String.format("SELECT login FROM Humans where nickname = '%s'", value);
        }
        try {
            // оправка запроса и получение ответа
            ResultSet rs = statement.executeQuery(sql);
            // если есть строка возвращаем результат если нет то вернеться null
            if(rs.next()) {
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
    public static void ReadDB() throws ClassNotFoundException, SQLException
    {
        ResultSet rs = statement.executeQuery("SELECT * FROM Humans");

        while(rs.next())
        {
            int id = rs.getInt("id");
            String  login = rs.getString("login");
            String  nickname = rs.getString("nickname");
            System.out.println( "ID = " + id );
            System.out.println( "name = " + login );
            System.out.println( "phone = " + nickname );
            System.out.println();
        }

        System.out.println("Таблица выведена");
    }

}
