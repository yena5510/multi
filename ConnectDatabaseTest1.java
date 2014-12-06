package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// 임폴트 문 부를 때 같은 이름의 객체가 여러가지 있기 때문에 잘 못 부를 수 있다.
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
            System.out.println("드라이버 적재 성공 ... 데이터베이스에 연결중입니다.");
            con = DriverManager.getConnection(url, id, pw);
            System.out.println("데이터베이스 연결 성공");
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
            // 1. 연결 객체 만들기
            Connection con = makeConnection();// 만약 이 문장이 try문 밖에 있으면 makeConnection()이 실행은 되지만
            // Connection con에 저장하고. 아랫줄의 con.createStatement()에서 사용할 con과 연동이 되지 않을 수 있다!!(주의)
            // 2. statment 객체 만들기
            Statement stmt = con.createStatement();
            // 3. ResultSet 만들기
            String sql1 = "select * from student_db";
            ResultSet rs = stmt.executeQuery(sql1);//프로시저 랭귀지 sql. 쿼리문만 바꾸는 랭귀지 기법
            // 4. 데이터 추출하기.
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
