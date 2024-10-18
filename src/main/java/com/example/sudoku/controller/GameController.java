package com.example.sudoku.controller;

import com.example.sudoku.model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GameController {

    private TextField[][] cajas = new TextField[6][6];
    @FXML
    private Label welcomeText;
    @FXML
    private TextField caja00;

    @FXML
    private TextField caja01;

    @FXML
    private TextField caja02;

    @FXML
    private TextField caja03;

    @FXML
    private TextField caja04;

    @FXML
    private TextField caja05;

    @FXML
    private TextField caja10;

    @FXML
    private TextField caja11;

    @FXML
    private TextField caja12;

    @FXML
    private TextField caja13;

    @FXML
    private TextField caja14;

    @FXML
    private TextField caja15;

    @FXML
    private TextField caja20;

    @FXML
    private TextField caja21;

    @FXML
    private TextField caja22;

    @FXML
    private TextField caja23;

    @FXML
    private TextField caja24;

    @FXML
    private TextField caja25;

    @FXML
    private TextField caja30;

    @FXML
    private TextField caja31;

    @FXML
    private TextField caja32;

    @FXML
    private TextField caja33;

    @FXML
    private TextField caja34;

    @FXML
    private TextField caja35;

    @FXML
    private TextField caja40;

    @FXML
    private TextField caja41;

    @FXML
    private TextField caja42;

    @FXML
    private TextField caja43;

    @FXML
    private TextField caja44;

    @FXML
    private TextField caja45;

    @FXML
    private TextField caja50;

    @FXML
    private TextField caja51;

    @FXML
    private TextField caja52;

    @FXML
    private TextField caja53;

    @FXML
    private TextField caja54;

    @FXML
    private TextField caja55;

    Game juego;

    @FXML
    void onclick(ActionEvent event) {

    }
    public GameController() {
        cajas[0][0] = caja00;
        cajas[0][1] = caja01;
        cajas[0][2] = caja02;
        cajas[0][3] = caja03;
        cajas[0][4] = caja04;
        cajas[0][5] = caja05;

        cajas[1][0] = caja10;
        cajas[1][1] = caja11;
        cajas[1][2] = caja12;
        cajas[1][3] = caja13;
        cajas[1][4] = caja14;
        cajas[1][5] = caja15;

        cajas[2][0] = caja20;
        cajas[2][1] = caja21;
        cajas[2][2] = caja22;
        cajas[2][3] = caja23;
        cajas[2][4] = caja24;
        cajas[2][5] = caja25;

        cajas[3][0] = caja30;
        cajas[3][1] = caja31;
        cajas[3][2] = caja32;
        cajas[3][3] = caja33;
        cajas[3][4] = caja34;
        cajas[3][5] = caja35;

        cajas[4][0] = caja40;
        cajas[4][1] = caja41;
        cajas[4][2] = caja42;
        cajas[4][3] = caja43;
        cajas[4][4] = caja44;
        cajas[4][5] = caja45;

        cajas[5][0] = caja50;
        cajas[5][1] = caja51;
        cajas[5][2] = caja52;
        cajas[5][3] = caja53;
        cajas[5][4] = caja54;
        cajas[5][5] = caja55;
    }

    public void botonreiniciar(ActionEvent actionEvent) {
    }

    public void botonayuda(ActionEvent actionEvent) {

    }



    public void botonvalidar(ActionEvent actionEvent) {

    }
}
