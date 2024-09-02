package com.demo.jdbctest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test {

	static Connection con = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	static CallableStatement cs = null;

	static void insertValues(String name, int id) {

		try {
			String url = "jdbc:mysql://localhost:3306";
			con = DriverManager.getConnection(url, "root", "7980784725");
			System.out.println("connected");
			ps = con.prepareStatement("insert into Test.Exam values(?,?)");
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("values inserted");
			ps.close();
			con.close();
		} catch (Exception e) {
			System.out.println("Not connected ");
			e.printStackTrace();
		}

	}

	static void display() {

		try {
			String url = "jdbc:mysql://localhost:3306/Test";
			con = DriverManager.getConnection(url, "root", "7980784725");
			System.out.println("connected");
			cs = con.prepareCall("{call new_procedure()}");
			rs = cs.executeQuery();
			while (rs.next()) {
				System.out.println("Name : " + rs.getString("name"));
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Not connected ");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.gc();
		while (true) {
			System.out.println("enter 1 for insert");
			System.out.println("enter 2 for display");
			System.out.println("enter 3 for exit");
			System.out.print("Enter the value:	");
			int choice = Integer.parseInt(in.readLine());
			System.out.println("\n======================================================");
			switch (choice) {
			case 1:
				System.out.print("\nenter name:	");
				String name = in.readLine();
				System.out.print("\nenter id:	");
				int id = Integer.parseInt(in.readLine());
				insertValues(name, id);
				System.out.println("\n======================================================");
				break;
			case 2:
				display();
				System.out.println("\n======================================================");
				break;
			case 3:
				System.out.println("tata");
				System.out.println("\n======================================================");
				System.exit(0);
				break;
			default:
				System.out.println("WRIONG CHOICE");
			}
		}
		
	}

}
