package com.deadlock.aiehbarril;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

//import java.io.IOException;

//import javafx.fxml.FXML;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

import java.util.ArrayList;

import com.deadlock.aiehbarril.model.Course;

public class Main extends Application {

	private static Stage currentStage;

	private static Scene searchCourse;
	private static Scene about;
	private static Scene registerCourse;
	private static Scene courseProfile;
	private static Scene rateCourse;
	private static Scene lastStepRating;
	private static Scene ratingFeedback;

//    private BorderPane rootLayout;

    /** Os dados como uma observable list de Courses.	*/
//    private ObservableList<Course> courseData = FXCollections.observableArrayList();

    /** Construtor  */
    public Main() {
        // Add some sample data
    	int i = 0;

    	Course A = new Course(++i,"Cálculo III", "Edson");
    	A.save();
    	A = new Course(++i,"Eng. de Software I", "Ricardo");
    	A.save();
    	A = new Course(++i,"Banco de Dados I", "Godoy");
    	A.save();
    	A = new Course(++i,"Redes de computadores I", "Fábio");
    	A.save();
    	A = new Course(++i,"Física II", "Paulo");
    	A.save();
    	A = new Course(++i,"Física I", "Wagner");
    	A.save();
    	A = new Course(++i,"Cálculo Numérico", "Jorge");
    	A.save();
    	A = new Course(++i,"Cálculo II", "Beto");
    	A.save();
    	A = new Course(++i,"Estatística", "Hugo");
    	A.save();

    }

    /**
     * Retorna os dados como uma observable list de Courses.
     * @return
     */
//    public ObservableList<Course> getCourseData() {
//        return courseData;
//    }

    @Override
	public void start(Stage primaryStage) throws Exception {

//    	System.exit(0);

    	currentStage = primaryStage;

    	Parent fxmlSearchCourse = FXMLLoader.load(getClass().getResource("view/SearchCourse.fxml"));
    	searchCourse = new Scene(fxmlSearchCourse);

    	Parent fxmlAbout = FXMLLoader.load(getClass().getResource("view/About.fxml"));
    	about = new Scene(fxmlAbout);

    	Parent fxmlRegisterCourse = FXMLLoader.load(getClass().getResource("view/RegisterCourse.fxml"));
    	registerCourse = new Scene(fxmlRegisterCourse);

    	Parent fxmlCourseProfile = FXMLLoader.load(getClass().getResource("view/CourseProfile.fxml"));
    	courseProfile = new Scene(fxmlCourseProfile);

    	Parent fxmlRateCourse = FXMLLoader.load(getClass().getResource("view/RateCourse.fxml"));
    	rateCourse = new Scene(fxmlRateCourse);

    	Parent fxmlLastStepRating = FXMLLoader.load(getClass().getResource("view/LastStepRating.fxml"));
    	lastStepRating = new Scene(fxmlLastStepRating);

    	Parent fxmlRatingFeedback = FXMLLoader.load(getClass().getResource("view/RatingFeedback.fxml"));
    	ratingFeedback = new Scene(fxmlRatingFeedback);

    	primaryStage.setScene(searchCourse);
    	primaryStage.show();

	}

    public static void changeScreen(String url, Object userData) {
    	switch (url){
    	case "view/SearchCourse.fxml":
    		currentStage.setScene(searchCourse);
    		notifyAllListeners("view/SearchCourse.fxml",userData);
    		break;
    	case "view/About.fxml":
    		currentStage.setScene(about);
    		notifyAllListeners("view/About.fxml",userData);
    		break;
    	case "view/RegisterCourse.fxml":
    		currentStage.setScene(registerCourse);
    		notifyAllListeners("view/RegisterCourse.fxml",userData);
    		break;
    	case "view/CourseProfile.fxml":
    		currentStage.setScene(courseProfile);
    		notifyAllListeners("view/CourseProfile.fxml",userData);
    		break;
    	case "view/RateCourse.fxml":
    		currentStage.setScene(rateCourse);
    		notifyAllListeners("view/RateCourse.fxml",userData);
    		break;
    	case "view/LastStepRating.fxml":
    		currentStage.setScene(lastStepRating);
    		notifyAllListeners("view/LastStepRating.fxml",userData);
    		break;
    	case "view/RatingFeedback.fxml":
    		currentStage.setScene(ratingFeedback);
    		notifyAllListeners("view/RatingFeedback.fxml",userData);
    		break;
    	}
    }

    public static void changeScreen(String url) {
    	changeScreen(url,null);
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    //-------------------

    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    public static interface OnChangeScreen{
    	void onScreenChanged(String newScreen, Object userData);
    }

    public static void addOnChangeScreenListener(OnChangeScreen newListener) {
    	listeners.add(newListener);
    }


    private static void notifyAllListeners(String newScreen, Object userData) {
    	for(OnChangeScreen l : listeners) {
    		l.onScreenChanged(newScreen, userData);
    	}
    }

}
