package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// ����Ʈ �� �θ� �� ���� �̸��� ��ü�� �������� �ֱ� ������ �� �� �θ� �� �ִ�.
// I think i can do it!!!!!!!
//go for it!!
public class ConnectDatabaseTest1 {
    public static Connection makeConnection(){
        String url = "jdbc:mysql://localhost:3307/booktest";
        String id = "root";
        String pw = "gina1768";
        Connection con = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("����̹� ���� ���� ... �����ͺ��̽��� �������Դϴ�.");
            con = DriverManager.getConnection(url, id, pw);
            System.out.println("�����ͺ��̽� ���� ����");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return con;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            // 1. ���� ��ü �����
            Connection con = makeConnection();// ���� �� ������ try�� �ۿ� ������ makeConnection()�� ������ ������
            // Connection con�� �����ϰ�. �Ʒ����� con.createStatement()���� ����� con�� ������ ���� ���� �� �ִ�!!(����)
            // 2. statment ��ü �����
            Statement stmt = con.createStatement();
            // 3. ResultSet �����
            String sql1 = "select * from student_db";
            ResultSet rs = stmt.executeQuery(sql1);//���ν��� ������ sql. �������� �ٲٴ� ������ ���
            // 4. ������ �����ϱ�.
            while(rs.next()){
                String st_id = rs.getString("studentid");
                String st_pw = rs.getString("studentpw");
                String st_name = rs.getString("studentname");
                String st_major = rs.getString("studentmajor");
                
                
                System.out.println(st_id+"\t"+st_pw+"\t"+st_name+"\t"+st_major);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
