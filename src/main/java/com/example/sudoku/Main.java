package com.example.sudoku;

import javafx.application.Application;
import javafx.stage.Stage;
import com.example.sudoku.view.GameStage;
import java.io.IOException;
public class Main extends Application  {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        GameStage.getInstance();
    }
}
