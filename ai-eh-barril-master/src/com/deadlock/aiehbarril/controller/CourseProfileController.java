package com.deadlock.aiehbarril.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.deadlock.aiehbarril.Main;
import com.deadlock.aiehbarril.model.Course;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class CourseProfileController implements Initializable {

	Course selectedCourse = null;

	@FXML
	private Label lbl_media_explanation;

	@FXML
	private Label lbl_media_examDificulty_text;

	@FXML
	private Label lbl_media_presence;

	@FXML
	private Label lbl_media_presence_text;

	@FXML
	private Label lbl_porcentagemEhBarril;

	@FXML
	private Label lbl_headAlias;

	@FXML
	private Label lbl_numVotos;

	@FXML
	private Label lbl_media_projects_text;

	@FXML
	private Label lbl_media_free_to_answers_text;

	@FXML
	private Label lbl_media_projects;

	@FXML
	private Label lbl_media_confidence;

	@FXML
	private Label lbl_media_explanation_text;

	@FXML
	private Label lbl_media_confidence_text;

	@FXML
	private Label lbl_media_examDificulty;

	@FXML
	private Label lbl_media_free_to_answers;

	@FXML
	private Label lbl_headProfessor;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
			@Override
			public void onScreenChanged(String newScreen, Object userData) {
				if (newScreen.equals("view/CourseProfile.fxml")) {
					if (userData != null) {
						selectedCourse = (Course) userData;
						lbl_headAlias.setText(selectedCourse.getAlias());
						lbl_headProfessor.setText(selectedCourse.getProfessor());
					}
					System.out.print(selectedCourse);

				}
			}
		});

	}

	@FXML
	private void handleEvaluate(ActionEvent event) {
		System.out.print("Evaluate\n");
		Main.changeScreen("view/RateCourse.fxml",selectedCourse);
	}

	@FXML
	private void handleSearchCourse(ActionEvent event) {
		System.out.print("SearchCourse\n");
		Main.changeScreen("view/SearchCourse.fxml");
	}

}
