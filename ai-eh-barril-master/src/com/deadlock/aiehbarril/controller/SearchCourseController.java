package com.deadlock.aiehbarril.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.deadlock.aiehbarril.Main;
import com.deadlock.aiehbarril.model.Course;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;

public class SearchCourseController implements Initializable {

	@FXML
	private TextField search;

	@FXML
	protected ListView<Course> drop_results;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		drop_results.setVisible(false);

		Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
			@Override
			public void onScreenChanged(String newScreen, Object userData) {
				if(newScreen.equals("view/SearchCourse.fxml")) {
					search.setText("");
					System.out.print("OLHE ELA!!!!");
				}
			}
		});

		// Listen for TextField text changes
		search.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {

//		    	System.out.println("changeSearch "+ newValue);
		    	drop_results.getItems().clear();
		    	if( search.getText().length() > 3 ){
					List<Course> courses;
//					System.out.println("vou ver no estoque...");
					courses = Course.whereAlias(search.getText());
					if(!courses.isEmpty()) {
						/* Popula a lista */
						drop_results.setVisible(true);
						int i = 0;
						for(Course c:courses){
							if( i > 4 )	break;
							drop_results.getItems().add(c);
//							System.out.println(c.getAlias());
							i++;
						}
					} else{
						drop_results.setVisible(false);
					}
				} else{
					drop_results.setVisible(false);
				}
		    }
		});

		drop_results.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {
		    @Override
		    public void changed(ObservableValue<? extends Course> observable,
		            Course oldValue, Course newValue) {

		    	ObservableList<Course> ol = drop_results.getSelectionModel().getSelectedItems();
				if(!ol.isEmpty()) {
					Course sel = ol.get(0);
					System.out.println("VAI PARA PROX. TELA");
					System.out.println(sel);
					Main.changeScreen("view/CourseProfile.fxml",sel);
				}
		    }
		});
	}

	@FXML
	private void handleAbout(ActionEvent event) {
		System.out.print("About\n");
		Main.changeScreen("view/About.fxml");
	}

	@FXML
	private void handleCourseProfile(ActionEvent event) {
		System.out.print("CourseProfile\n");
		Main.changeScreen("view/CourseProfile.fxml");
	}

	@FXML
	private void handleRegisterCourse(ActionEvent event) {
		System.out.print("RegisterCourse\n");
		Main.changeScreen("view/RegisterCourse.fxml");
	}

	@FXML
	private void handleSearch(ActionEvent event) {
		List<Course> course;
		course = Course.whereAlias(search.getText());
		if(course.isEmpty()) {
			 Alert alert = new Alert(AlertType.ERROR);
             alert.setTitle("Erro");
             alert.setContentText("Disciplina não encontrada!");
             alert.showAndWait();
		} else {
			Main.changeScreen("view/CourseProfile.fxml",course.get(0));
		}

	}

}
