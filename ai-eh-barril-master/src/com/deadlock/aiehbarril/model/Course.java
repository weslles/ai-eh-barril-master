package com.deadlock.aiehbarril.model;

import java.util.List;

import com.deadlock.aiehbarril.model.sqlite.CourseSQLite;

//import java.time.LocalDate;

//import javafx.beans.property.IntegerProperty;
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.property.ObjectProperty;
//import javafx.beans.property.SimpleObjectProperty;


/**
 * Classe Model para uma Course (disciplina).
 */
public class Course {

	private int _id;
	private Integer id = new Integer(_id);


    private String alias;
    private String professor;

    public Course(String alias, String professor) {
    	this.alias = alias;
        this.professor = professor;
    }
    /**
     * @param alias nome da disciplina.
     * @param professor professor que leciona a disciplina.
     */
    public Course(int _id, String alias, String professor) {
    	this._id = _id;
        this.alias = alias;
        this.professor = professor;

    }

    public int getId() {
		return _id;
	}

    public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String toString(){
    	return alias + " - " + professor;
    }

	/** ____________DAO______________*/
	private static CourseSQLite dao = new CourseSQLite();
	public void save(){
		if(id != null && dao.find(_id) != null)
			dao.update(this);
		else{
			dao.create(this);
			this._id = dao.find()._id;
		}

	}

	public void delete(){
		if(dao.find(_id) != null)
			dao.delete(this);
	}

	public static List<Course> all(){
		return dao.all();
	}

	public static Course find(int pk){
		return dao.find(pk);
	}

	public static List<Course> whereAlias(String alias){
		return dao.whereAlias(alias);
	}

	public static List<Course> whereProfessor(String professor){
		return dao.whereProfessor(professor);
	}
}


