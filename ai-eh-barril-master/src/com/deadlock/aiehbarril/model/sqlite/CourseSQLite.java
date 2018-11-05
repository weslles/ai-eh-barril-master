package com.deadlock.aiehbarril.model.sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.deadlock.aiehbarril.model.Course;

public class CourseSQLite extends SQLiteBase {

	public CourseSQLite(){
		open();
		try{

			PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Courses ("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"alias TEXT,"+
					"professor TEXT);");
			stm.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void create(Course c){//usefull
		open();
		try{
			PreparedStatement stm = conn.prepareStatement("INSERT INTO Courses VALUES(?,?,?);");
			stm.setString(2, c.getAlias());
			stm.setString(3, c.getProfessor());

			stm.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public List<Course> all(){
		ArrayList<Course> result = new ArrayList<>();
		open();
		try{
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM Courses ORDER BY id ASC;");

			ResultSet rs = stm.executeQuery();

			while(rs.next()){
				Course c = new Course(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3));
				result.add(c);
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public void update(Course c){
		conn = open();

		try{

			PreparedStatement stm = conn.prepareStatement("UPDATE Courses SET "+
					"alias = ?, "+
					"professor = ? "+
					"WHERE id = ?;");

			stm.setString(1, c.getAlias());
			stm.setString(2, c.getProfessor());
			stm.setInt(3, c.getId());

			stm.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void delete(Course c){
		conn = open();

		try{

			PreparedStatement stm = conn.prepareStatement("DELETE FROM Courses WHERE id = ?;");

			stm.setInt(1, c.getId());

			stm.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			close();
		}
	}

//	SELECT *
//	FROM TABLE
//	WHERE ID = (SELECT MAX(ID)  FROM TABLE);

	public Course find(){
		Course result = null;
		conn = open();
		try{
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM Courses WHERE id = (SELECT MAX(id)  FROM Courses);");

			ResultSet rs = stm.executeQuery();

			if(rs.next()){
				Course c = new Course(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3));
				result = c;
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public Course find(int PK){
		Course result = null;
		conn = open();
		try{
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM Courses WHERE id = ?;");

			stm.setInt(1, PK);
			ResultSet rs = stm.executeQuery();

			if(rs.next()){
				Course c = new Course(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3));
				result = c;
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public List<Course> whereAlias(String alias){//usefull
		ArrayList<Course> result = new ArrayList<>();
		conn = open();
		try{
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM Courses WHERE alias LIKE ? ORDER BY alias DESC;");

			stm.setString(1, "%"+alias+"%"); //alterar para "%"+alias+"%"
			ResultSet rs = stm.executeQuery();

			while(rs.next()){
				Course c = new Course(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3));
				result.add(c);
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public List<Course> whereProfessor(String professor){//usefull
		ArrayList<Course> result = new ArrayList<>();
		conn = open();
		try{
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM Courses WHERE professor LIKE ? ORDER BY professor DESC;");

			stm.setString(1, "%"+professor+"%");
			ResultSet rs = stm.executeQuery();

			while(rs.next()){
				Course c = new Course(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3));
				result.add(c);
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
}
