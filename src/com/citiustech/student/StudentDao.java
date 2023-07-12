package com.citiustech.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.mysql.cj.protocol.Resultset;

public class StudentDao {

	public static boolean validateStudent(String email, String password) {
		// jdbc code..

		boolean flag = false;

		try {

			Connection con = ConnectionProvider.createConnection();
			String query = "select * from student where studentEmail =? and  studentPassword = ?";
			// System.out.println(query);
			// Statement stmt = con.createStatement();
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet set = pstmt.executeQuery();

			while (set.next()) {
				flag = true;
				StudentData.studentRollNumber = set.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	public static boolean insertStudentToDatabase(Student student) {

		boolean f = false;

		try {
			// jdbc code..
			Connection con = ConnectionProvider.createConnection();
			String query = "insert into Student(studentFirstName, studentLastName, studentGender, studentPassword, studentAddress, studentEmail, studentCourse) values(?,?,?,?,?,?,?)";

			// prepared statement
			PreparedStatement pstmt = con.prepareStatement(query);

			// set the value of parameters
			pstmt.setString(1, student.getStudentFirstName());
			pstmt.setString(2, student.getStudentLastName());
			pstmt.setString(3, student.getStudentGender());
			pstmt.setString(4, student.getStudentPassword());
			pstmt.setString(5, student.getStudentAddress());
			pstmt.setString(6, student.getStudentEmail());
			pstmt.setString(7, student.getStudentCourse());

			// execute
			pstmt.executeUpdate();
			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public static boolean deleteStudent(int studentRollNumber) {

		boolean f = false;

		try {
			// jdbc code..
			Connection con = ConnectionProvider.createConnection();
			String query = "delete from Student where studentRollNumber = ?";

			// prepared statement
			PreparedStatement pstmt = con.prepareStatement(query);

			// set the value of parameters
			pstmt.setInt(1, studentRollNumber);

			// execute
			pstmt.executeUpdate();
			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}

	public static void showAllStudents() {
		// TODO Auto-generated method stub
		boolean f = false;

		try {
			// jdbc code..
			Connection con = ConnectionProvider.createConnection();
			String query = "select * from Student";

			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery(query);

			while (set.next()) {
				int studentRollNumber = set.getInt(1);
				String studentFirstName = set.getString(2);
				String studentLastName = set.getString(3);
				String studentGender = set.getString(4);
				String studentPassword = set.getString(5);
				String studentAddress = set.getString(6);
				String studentEmail = set.getString(7);
				String studentCourse = set.getString(8);

				System.out.println("studentRollNumber : " + studentRollNumber);
				System.out.println("studentFirstName : " + studentFirstName);
				System.out.println("studentLastName : " + studentLastName);
				System.out.println("studentGender : " + studentGender);
				System.out.println("studentPassword : " + studentPassword);
				System.out.println("studentAddress : " + studentAddress);
				System.out.println("studentEmail : " + studentEmail);
				System.out.println("studentCourse : " + studentCourse);
				System.out.println("++++++++++++++++++++++++++++++++++++++++++");
			}
			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static boolean updateStudent(int stRollNo, String stFirstName, String stLastName, String stGender,
			String stPassword, String stAddress, String stEmail, String stCourse) {
		// TODO Auto-generated method stub
		boolean f = false;
		try {
			Connection con = ConnectionProvider.createConnection();
			String updateStudentQuery = "update Student SET studentFirstName = ?, studentLastName = ?, studentGender = ?, studentPassword = ?, studentAddress = ?, studentEmail = ?, studentCourse = ? WHERE studentRollNumber = ?";

			// prepared statement
			PreparedStatement pstmt = con.prepareStatement(updateStudentQuery);

			// set the value of parameters
			pstmt.setString(1, stFirstName);
			pstmt.setString(2, stLastName);
			pstmt.setString(3, stGender);
			pstmt.setString(4, stPassword);
			pstmt.setString(5, stAddress);
			pstmt.setString(6, stEmail);
			pstmt.setString(7, stCourse);
			pstmt.setInt(8, stRollNo);

			// execute
			pstmt.executeUpdate();

			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean getStudentByRollNo(int stdRollNo) {
		// TODO Auto-generated method stub
		try {
			boolean getStudentByRollNo = false;
			Connection con = ConnectionProvider.createConnection();
			String getStudentByID = "select studentRollNumber, studentFirstName, studentLastName, studentGender, studentPassword, studentAddress,studentEmail, studentCourse  from student where studentRollNumber = '"
					+ stdRollNo + "' ";

			// prepared statement
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery(getStudentByID);

			while (set.next()) {
				int studentRollNumber = set.getInt(1);
				String studentFirstName = set.getString(2);
				String studentLastName = set.getString(3);
				String studentGender = set.getString(4);
				String studentPassword = set.getString(5);
				String studentAddress = set.getString(6);
				String studentEmail = set.getString(7);
				String studentCourse = set.getString(8);

				System.out.println("studentRollNumber : " + studentRollNumber);
				System.out.println("studentFirstName : " + studentFirstName);
				System.out.println("studentLastName : " + studentLastName);
				System.out.println("studentGender : " + studentGender);
				System.out.println("studentPassword : " + studentPassword);
				System.out.println("studentAddress : " + studentAddress);
				System.out.println("studentEmail : " + studentEmail);
				System.out.println("studentCourse : " + studentCourse);
				System.out.println("++++++++++++++++++++++++++++++++++++++++++");
			}
			getStudentByRollNo = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void generateReportCard(int score) {
		try {

			// boolean generateReport= false;

			LocalDate currentDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			String formattedDate = currentDate.format(formatter);

			Connection con = ConnectionProvider.createConnection();
			String generateReportQuery = " insert into reportcard values(?,?,?,?)";

			// prepared statement
			PreparedStatement pstmt = con.prepareStatement(generateReportQuery);

			pstmt.setString(1, null);
			pstmt.setInt(2, StudentData.studentRollNumber);
			pstmt.setInt(3, score);
			pstmt.setString(4, formattedDate);

			pstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean getReportData(int studentRollNo) {
		// TODO Auto-generated method stub
		boolean getReportCard = false;

		try {
			Connection con = ConnectionProvider.createConnection();
			String getReportDataQuery = "select * from reportcard where studentRollNumber = ?";
			// System.out.println(getReportDataQuery);
			PreparedStatement stmt = con.prepareStatement(getReportDataQuery);
			stmt.setInt(1, studentRollNo);
			ResultSet set = stmt.executeQuery();

			while (set.next()) {
				int reportCardId = set.getInt(1);
				int studentRollNumber = set.getInt(2);
				int score = set.getInt(3);
				String dateOfExam = set.getString(4);

				System.out.println("reportCardId : " + reportCardId);
				System.out.println("studentRollNumber : " + studentRollNumber);
				System.out.println("score : " + score);
				System.out.println("dateOfExam : " + dateOfExam);

				System.out.println("++++++++++++++++++++++++++++++++++++++++++");
			}
			getReportCard = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public static boolean getReportDataById(int studentRollNo) {
		// TODO Auto-generated method stub
		boolean getReportCardById = false;

		try {
			Connection con = ConnectionProvider.createConnection();
			String getReportDataQuery = "select * from reportcard where studentRollNumber = ?";
			// System.out.println(getReportDataQuery);
			PreparedStatement stmt = con.prepareStatement(getReportDataQuery);
			stmt.setInt(1, studentRollNo);
			ResultSet set = stmt.executeQuery();

			while (set.next()) {
				int reportCardId = set.getInt(1);
				int studentRollNumber = set.getInt(2);
				int score = set.getInt(3);
				String dateOfExam = set.getString(4);

				System.out.println("reportCardId : " + reportCardId);
				System.out.println("studentRollNumber : " + studentRollNumber);
				System.out.println("score : " + score);
				System.out.println("dateOfExam : " + dateOfExam);

				System.out.println("++++++++++++++++++++++++++++++++++++++++++");
				getReportCardById = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getReportCardById;
	}

	public static void getAllReportCard() {
		// TODO Auto-generated method stub
		boolean f = false;

		try {
			// jdbc code..
			Connection con = ConnectionProvider.createConnection();
			String query = "select * from reportcard";

			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery(query);

			while (set.next()) {
				int reportCardId = set.getInt(1);
				int studentRollNumber = set.getInt(2);
				int score = set.getInt(3);
				String dateOfExam = set.getString(4);
				

				System.out.println("reportCardId : " + reportCardId);
				System.out.println("studentRollNumber : " + studentRollNumber);
				System.out.println("score : " + score);
				System.out.println("dateOfExam : " + dateOfExam);
				
				System.out.println("++++++++++++++++++++++++++++++++++++++++++");
			}
			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
