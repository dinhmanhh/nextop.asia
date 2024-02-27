package utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLTestConnection {

    public static Connection getMyConnection() throws SQLException, ClassNotFoundException {
        return MySQLConnUtils.getMySQLConnection();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Get connection... ");

        // Lấy ra đối tượng Connection kết nối vào database.
        Connection conn = MySQLTestConnection.getMyConnection();

        System.out.println("Opened connection: " + conn);

//        Statement statement = conn.createStatement();

        String sql = "SELECT emp_number, employee_id, emp_firstname, emp_middle_name, emp_lastname FROM hs_hr_employee;";
        String sqlParam = "SELECT emp_number, employee_id, emp_firstname, emp_middle_name, emp_lastname FROM hs_hr_employee WHERE employee_id = ?;";

        PreparedStatement pstm = conn.prepareStatement(sqlParam);
        pstm.setString(1, "0003");

        ResultSet rs = pstm.executeQuery();


        // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
        //  ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            int emp_number = rs.getInt("emp_number");
            String employee_id = rs.getString("employee_id");
            String emp_firstname = rs.getString("emp_firstname");
            String emp_middle_name = rs.getString("emp_middle_name");
            String emp_lastname = rs.getString("emp_lastname");

            System.out.println("--------------------");
            System.out.println("Emp Number: " + emp_number);
            System.out.println("Emp ID: " + employee_id);
            System.out.println("Emp Firstname: " + emp_firstname);
            System.out.println("Emp Middle name: " + emp_middle_name);
            System.out.println("Emp Lastname: " + emp_lastname);
        }
        conn.close();
        System.out.println("---------- Closed connection ----------");
    }
}
