package com.deadlock.aiehbarril.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

//import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.deadlock.aiehbarril.Main;
//import com.deadlock.aiehbarril.model.Course;
import com.deadlock.aiehbarril.model.Course;

public class RegisterCourseController implements Initializable {

	@FXML
	private TextField alias;
	@FXML
	private TextField professor;

	private boolean registerClicked = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public boolean isRegisterClicked() {
        return registerClicked;
    }

	@FXML
	private void alertLog() {
		Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("");
	    alert.setHeaderText("Hey");
	    alert.setContentText("You find me");
	    alert.showAndWait();
	}

	/**
     * Chamado quando o usuário clica Cadastrar.
     */
    @FXML
    private void handleRegister(ActionEvent event) {

        if (isInputValid()) {

            registerClicked = true;

            Course newCourse = new Course(alias.getText(),professor.getText());
            newCourse.save();

            // vai pra a próxima tela
            Main.changeScreen("view/CourseProfile.fxml",newCourse);
        }
    }

    /**
     * Valida a entrada do usuário nos campos de texto.
     *
     * @return true se a entrada é válida
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (alias.getText() == null || alias.getText().length() == 0) {
            errorMessage += "Insira o nome da disciplina\n";
        }
        if (professor.getText() == null || professor.getText().length() == 0) {
            errorMessage += "Insira o nome do professor\n";
        }
        // TO DO - Se já tiver cadastrada retorna um erro


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
            Alert alert = new Alert(AlertType.ERROR);
                      alert.setTitle("Erro");
                      alert.setContentText(errorMessage);
                alert.showAndWait();

            return false;
        }
    }

}
