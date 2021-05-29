package com.student.registration.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.UUID;
import java.io.UnsupportedEncodingException;

import com.student.registration.model.Logins;
import com.student.registration.model.Student;

public class StudentDao {

public int registerStudent(Logins login) throws ClassNotFoundException {
		
	    ConnectionFactory connectionFactory=new ConnectionFactory();
	    Connection connection=connectionFactory.getConnection();
		String INSERT_LOGINS_SQL="INSERT INTO Logins"+
		"(username,email,password) VALUES"+
				"(?,?,?)";
		int result=0;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(INSERT_LOGINS_SQL);
			String encriptedPass=Base64.getEncoder().encodeToString(login.getPassword().getBytes("utf-8"));
			System.out.println("actualPass="+login.getPassword());
			System.out.println("encryptedPass="+encriptedPass);
			preparedStatement.setString(1, login.getUsername());
			preparedStatement.setString(2, login.getUseremail());
			preparedStatement.setString(3, encriptedPass);
			System.out.println(preparedStatement);
		    result=preparedStatement.executeUpdate();   
			preparedStatement.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connection.close();
			System.out.println("connection closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
    }

        public int updateStudentDetails(Student student) {
	           // TODO Auto-generated method stub
        	
        	ConnectionFactory connectionFactory=new ConnectionFactory();
    	    Connection connection=connectionFactory.getConnection();
    	    String INSERT_STUDENTS_SQL ="INSERT INTO students"+"(FIRST_NAME,LAST_NAME,EMAIL,DOB,CONTACT_NO,SUBJECT,LOGIN_ID) values"
    	        	+"(?,?,?,?,?,?,?)";
    	    int result=0;
    		try {
    			PreparedStatement preparedStatement=connection.prepareStatement(INSERT_STUDENTS_SQL);
    			preparedStatement.setString(1, student.getFirstname());
    			preparedStatement.setString(2, student.getLastname());
    			preparedStatement.setString(3, student.getEmail());
    			preparedStatement.setString(4, student.getDOB());
    			preparedStatement.setString(5, student.getContact());
    			preparedStatement.setString(6, student.getSubject());
    			preparedStatement.setInt(7, student.getLoginId());
    			System.out.println(preparedStatement);
    		    result=preparedStatement.executeUpdate();
    		    preparedStatement.close();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		try {
    			connection.close();
    			System.out.println("connection closed");
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	return result;
     }	
        
        public Logins getlogin(String email, String pass){
        	
        	ConnectionFactory connectionFactory=new ConnectionFactory();
    	    Connection connection=connectionFactory.getConnection();
            Logins usr=null;
            String encriptedPass="";
            try {
				   encriptedPass=Base64.getEncoder().encodeToString(pass.getBytes("utf-8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}           
            String FETCH_USER ="select * from logins where email=? and password=?";
            try{
                
                PreparedStatement preparedStatement=connection.prepareStatement(FETCH_USER); 
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, encriptedPass);
                
                ResultSet rs = preparedStatement.executeQuery();
                
                if(rs.next()){
                    usr = new Logins();
                    usr.setLoginId(rs.getInt("LOGIN_ID"));
                    usr.setUsername(rs.getString("USERNAME"));
                    usr.setUseremail(rs.getString("EMAIL"));
                    usr.setPassword(rs.getString("password"));
                    
                }
                
            }catch(Exception e){
                e.printStackTrace();
            }
            try {
    			connection.close();
    			System.out.println("connection closed");
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            return usr;
        }
        
       public Student getStudent(int loginId){
        	
        	ConnectionFactory connectionFactory=new ConnectionFactory();
    	    Connection connection=connectionFactory.getConnection();
            Student student=null;          
            String FETCH_STUDENT ="select * from students where login_id=?";
            try{
                
                PreparedStatement preparedStatement=connection.prepareStatement(FETCH_STUDENT); 
                preparedStatement.setInt(1, loginId);                            
                ResultSet rs = preparedStatement.executeQuery();               
                if(rs.next()){
                	student = new Student();
                	student.setFirstname(rs.getString("FIRST_NAME"));
            		student.setLastname(rs.getString("LAST_NAME"));
            		student.setEmail(rs.getString("EMAIL"));
            		student.setDOB(rs.getString("DOB"));
            		student.setContact(rs.getString("CONTACT_NO"));		
            		student.setSubject(rs.getString("SUBJECT"));
            		student.setStudentId(rs.getInt("STUDENT_ID"));
                    
                }
                
            }catch(Exception e){
                e.printStackTrace();
            }
            try {
    			connection.close();
    			System.out.println("connection closed");
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            return student;
        }

	public int getloginId(Logins login) {
		// TODO Auto-generated method stub
		ConnectionFactory connectionFactory=new ConnectionFactory();
	    Connection connection=connectionFactory.getConnection();
		String FETCH_LOGIN_ID ="select LOGIN_ID from logins where email=? and password=?";
	try {	
		 PreparedStatement preparedStatement1=connection.prepareStatement(FETCH_LOGIN_ID);
		    preparedStatement1.setString(1, login.getUseremail());
			preparedStatement1.setString(2, Base64.getEncoder().encodeToString(login.getPassword().getBytes("utf-8")));
			ResultSet rs = preparedStatement1.executeQuery();
			if(rs.next())
			return (int)rs.getInt("LOGIN_ID");
	   }
	       catch (Exception e) {
		      e.printStackTrace();
	    }
	try {
		   connection.close();
		   System.out.println("connection closed");
	  } catch (SQLException e) {
		// TODO Auto-generated catch block
		    e.printStackTrace();
	}
		return 0;
	}
}
