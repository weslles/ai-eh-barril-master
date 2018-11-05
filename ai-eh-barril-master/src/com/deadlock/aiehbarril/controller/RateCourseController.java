package com.deadlock.aiehbarril.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.deadlock.aiehbarril.Main;
import com.deadlock.aiehbarril.model.Course;
import com.deadlock.aiehbarril.model.Rating;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class RateCourseController implements Initializable {

	Course selectedCourse = null;
	Rating currentRate = new Rating();

	@FXML
    private Label headAlias;

    @FXML
    private Label headProfessor;

    @FXML
    private AnchorPane optionTwo;

    @FXML
    private AnchorPane optionOne;

    @FXML
    private ToggleGroup q1;

    @FXML
    private ToggleGroup q2;

    @FXML
    private ToggleGroup q3;

    @FXML
    private ToggleGroup q4;

    @FXML
    private ToggleGroup q5;

    @FXML
    private ToggleGroup q6;

    @FXML
    private ToggleButton a1;

    @FXML
    private ToggleButton a2;

    @FXML
    private ToggleButton a3;

    @FXML
    private ToggleButton a4;

    @FXML
    private ToggleButton a5;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
			@Override
			public void onScreenChanged(String newScreen, Object userData) {
				if (newScreen.equals("view/RateCourse.fxml")) {
					if (userData != null) {
						selectedCourse = (Course) userData;
						headAlias.setText(selectedCourse.getAlias());
						headProfessor.setText(selectedCourse.getProfessor());
						currentRate.setCourseID(selectedCourse.getId());
					}
				}
				if (newScreen.equals("view/LastStepRating.fxml")) {
					if (userData != null)
						currentRate = (Rating) userData;
				}
			}
		});

	}

	@FXML
	private void handleLastStepRating(ActionEvent event) {
		currentRate.setRequires_presence(setRateQuestion(q1));
		currentRate.setConfidence(setRateQuestion(q2));
		currentRate.setExam_dificulty(setRateQuestion(q3));
		currentRate.setExplanation(setRateQuestion(q4));
		currentRate.setFree_to_answers(setRateQuestion(q5));
		currentRate.setProjects(setRateQuestion(q6));

		System.out.println(selectedCourse);
		System.out.print(currentRate);
		System.out.print("LastStepRating\n");
		Main.changeScreen("view/LastStepRating.fxml", currentRate);
	}

	private int setRateQuestion(ToggleGroup q){
		ToggleButton sel = (ToggleButton) q.getSelectedToggle();
		if (q.getSelectedToggle() != null)
			return getValue(sel.getId());
		return 0;
	}

	private int getValue(String id){
		switch(id){
		case "a1": return 1;
		case "a2": return 2;
		case "a3": return 3;
		case "a4": return 4;
		case "a5": return 5;
		}
		return 0;
	}

	@FXML
	private void handleCourseProfile(ActionEvent event) {
		System.out.print("CourseProfile\n");
		Main.changeScreen("view/CourseProfile.fxml");
	}

	@FXML
	private void selectOne(ActionEvent event){
		System.out.print("Aí eh Barril\n");
		optionOne.setStyle("-fx-background-color:#c1c1c1;");
		optionTwo.setStyle("-fx-background-color: #f8f8f8;");
		currentRate.setEh_barril((short) 1);

	}

	@FXML
	private void selectTwo(ActionEvent event){
		System.out.print("Eh de boa\n");
		optionTwo.setStyle("-fx-background-color:#c1c1c1;");
		optionOne.setStyle("-fx-background-color: #f8f8f8;");
		currentRate.setEh_barril((short) 0);

	}

	@FXML
	private void handleRatingFeedback(ActionEvent event) {
		System.out.print("RatingFeedback\n");
		System.out.print(currentRate);
		currentRate.save();
		Main.changeScreen("view/RatingFeedback.fxml");
	}

	@FXML
	private void handleEvaluate(ActionEvent event) {
//		System.out.print("Evaluate\n");
		Main.changeScreen("view/RateCourse.fxml");
	}

	@FXML
	private void handleSearchCourse(ActionEvent event) {
		System.out.print(Rating.all());
		System.out.print("\nSearchCourse\n");
		Main.changeScreen("view/SearchCourse.fxml");
	}

}
