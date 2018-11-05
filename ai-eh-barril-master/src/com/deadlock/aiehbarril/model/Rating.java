package com.deadlock.aiehbarril.model;

import java.util.List;

import com.deadlock.aiehbarril.model.sqlite.RatingSQLite;

public class Rating {
	private int _id;
	private Integer id = new Integer(_id);

	private int explanation;
	private int requires_presence;
	private int exam_dificulty;
	private int free_to_answers;
	private int projects;
	private int confidence;
	private short eh_barril;

	private int fk_courseID;


	public Rating() {
		this.explanation = 0;
		this.requires_presence = 0;
		this.exam_dificulty = 0;
		this.free_to_answers = 0;
		this.projects = 0;
		this.confidence = 0;
		this.eh_barril = 0;
	}



	public Rating(int explanation, int requires_presence, int exam_dificulty, int free_to_answers, int projects,
			int confidence, short eh_barril, int fk_courseID) {
		this.explanation = explanation;
		this.requires_presence = requires_presence;
		this.exam_dificulty = exam_dificulty;
		this.free_to_answers = free_to_answers;
		this.projects = projects;
		this.confidence = confidence;
		this.eh_barril = eh_barril;
		this.fk_courseID = fk_courseID;
	}

	public Rating(int _id, int explanation, int requires_presence, int exam_dificulty, int free_to_answers,
			int projects, int confidence, short eh_barril, int fk_courseID) {
		this._id = _id;
		this.explanation = explanation;
		this.requires_presence = requires_presence;
		this.exam_dificulty = exam_dificulty;
		this.free_to_answers = free_to_answers;
		this.projects = projects;
		this.confidence = confidence;
		this.eh_barril = eh_barril;
		this.fk_courseID = fk_courseID;
	}



	public int getExplanation() {
		return explanation;
	}
	public void setExplanation(int explanation) {
		this.explanation = explanation;
	}
	public int getRequires_presence() {
		return requires_presence;
	}
	public void setRequires_presence(int requires_presence) {
		this.requires_presence = requires_presence;
	}
	public int getExam_dificulty() {
		return exam_dificulty;
	}
	public void setExam_dificulty(int exam_dificulty) {
		this.exam_dificulty = exam_dificulty;
	}
	public int getFree_to_answers() {
		return free_to_answers;
	}
	public void setFree_to_answers(int free_to_answers) {
		this.free_to_answers = free_to_answers;
	}
	public int getProjects() {
		return projects;
	}
	public void setProjects(int projects) {
		this.projects = projects;
	}
	public int getConfidence() {
		return confidence;
	}
	public void setConfidence(int confidence) {
		this.confidence = confidence;
	}
	public short isEh_barril() {
		return eh_barril;
	}
	public void setEh_barril(short eh_barril) {
		this.eh_barril = eh_barril;
	}

	public int getId() {
		return _id;
	}

	public int getCourseID() {
		return fk_courseID;
	}

	public void setCourseID(int courseID) {
		this.fk_courseID = courseID;
	}

	public String toString(){
    	return "id:"+ _id+"| eh_barril? "+ eh_barril +" |"+"| CourseID? "+ fk_courseID +" |"+explanation+","+requires_presence+","+exam_dificulty+","+free_to_answers+
    	","+projects+","+confidence+" |\n";
    }

	/** ____________DAO______________*/
	private static RatingSQLite dao = new RatingSQLite();
	public void save(){
		if(id != null && dao.find(_id) != null)
			dao.update(this);
		else
			dao.create(this);
	}

	public void delete(){
		if(dao.find(_id) != null)
			dao.delete(this);
	}

	public static List<Rating> all(){
		return dao.all();
	}

	public static Rating find(int pk){
		return dao.find(pk);
	}

	public static List<Rating> whereCourse(int FK){
		return dao.whereCourse(FK);
	}

}
