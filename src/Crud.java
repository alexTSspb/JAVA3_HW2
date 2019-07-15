import java.sql.SQLException;

public class Crud {
    private static DataBase db;

    public static void main(String[] args) {
        try {
            DataBase.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            DataBase.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            DataBase.ReadDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DataBase.disconnect();
//        try {
//            if(!db.isUsed("login","login2")) {
//                db.addToDB("login2", "password2", "nick2");
//            }else {
//                System.out.println("Логин уже используется");
//            }
//        } catch (SQLException e) {
//            System.out.println("/add_fail не добавлено");
//            //e.printStackTrace();
//        }
//        try {
//            db.updateNick("nick2", "nick2_new");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


        }

    }

