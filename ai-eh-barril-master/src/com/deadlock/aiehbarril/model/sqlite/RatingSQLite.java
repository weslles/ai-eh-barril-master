package com.deadlock.aiehbarril.model.sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.deadlock.aiehbarril.model.Rating;

/*OBS.: não foi testado*/

public class RatingSQLite extends SQLiteBase {

	public RatingSQLite(){
		open();
		try{

			PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Ratings ("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"explanation INTEGER,"+
					"requires_presence INTEGER,"+
					"exam_dificulty INTEGER,"+
					"free_to_answers INTEGER,"+
					"projects INTEGER,"+
					"confidence INTEGER,"+
					"eh_barril NUMERIC,"+
					"fk_courseID INTEGER, "+
					"FOREIGN KEY(fk_courseID) REFERENCES Courses(id));");
			stm.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void create(Rating c){//usefull
		open();
		try{
			PreparedStatement stm = conn.prepareStatement("INSERT INTO Ratings VALUES(?,?,?,?,?,?,?,?,?);");
			stm.setInt(2, c.getExplanation());
			stm.setInt(3, c.getRequires_presence());
			stm.setInt(4, c.getExam_dificulty());
			stm.setInt(5, c.getFree_to_answers());
			stm.setInt(6, c.getProjects());
			stm.setInt(7, c.getConfidence());
			stm.setShort(8, c.isEh_barril());
			stm.setInt(9, c.getCourseID());

			stm.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public List<Rating> all(){
		ArrayList<Rating> result = new ArrayList<>();
		open();
		try{
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM Ratings ORDER BY id ASC;");

			ResultSet rs = stm.executeQuery();

			while(rs.next()){
				Rating c = new Rating(
						rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getShort(8),
						rs.getInt(9)
						);
				result.add(c);
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public void update(Rating c){
		conn = open();

		try{

			PreparedStatement stm = conn.prepareStatement("UPDATE Ratings SET "+
					"explanation = ?, "+
					"requires_presence = ?, "+
					"exam_dificulty = ?, "+
					"free_to_answers = ?, "+
					"projects = ?, "+
					"confidence = ?, "+
					"eh_barril = ?, "+
					"fk_courseID = ? "+
					"WHERE id = ?;");

			stm.setInt(1, c.getExplanation());
			stm.setInt(2, c.getRequires_presence());
			stm.setInt(3, c.getExam_dificulty());
			stm.setInt(4, c.getFree_to_answers());
			stm.setInt(5, c.getProjects());
			stm.setInt(6, c.getConfidence());
			stm.setShort(7, c.isEh_barril());
			stm.setInt(8, c.getCourseID());
			stm.setInt(3, c.getId());

			stm.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void delete(Rating c){
		conn = open();

		try{

			PreparedStatement stm = conn.prepareStatement("DELETE FROM Ratings WHERE id = ?;");

			stm.setInt(1, c.getId());

			stm.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public Rating find(int PK){
		Rating result = null;
		conn = open();
		try{
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM Ratings WHERE id = ?;");

			stm.setInt(1, PK);
			ResultSet rs = stm.executeQuery();

			if(rs.next()){
				Rating c = new Rating(
						rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getShort(8),
						rs.getInt(9)
						);
				result = c;
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public List<Rating> whereCourse(int FK){//usefull
		ArrayList<Rating> result = new ArrayList<>();
		conn = open();
		try{
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM Ratings WHERE fk_courseID = ?;");

			stm.setInt(1, FK);
			ResultSet rs = stm.executeQuery();

			while(rs.next()){
				Rating c = new Rating(
						rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getShort(8),
						rs.getInt(9)
						);
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
