package com.example.sudoku.view;

import com.example.sudoku.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStage extends Stage {
    private GameController gameController;

    public GameStage() throws IOException {
        // Asegúrate de que la ruta del FXML sea correcta
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sudoku/sudoku-view.fxml"));
        Parent root = loader.load();
        gameController = loader.getController();
        Scene scene = new Scene(root);
        setTitle("Nivel Dios");
        setResizable(false);
        setScene(scene);
        show();
    }

    public GameController getGameController() {
        return gameController;
    }

    public static GameStage getInstance() throws IOException {
        if (GameStageHolder.INSTANCE == null) {
            GameStageHolder.INSTANCE = new GameStage();
        }
        return GameStageHolder.INSTANCE;
    }

    public static void deleteInstance() {
        if (GameStageHolder.INSTANCE != null) {
            GameStageHolder.INSTANCE.close();
            GameStageHolder.INSTANCE = null;
        }
    }

    private static class GameStageHolder {
        private static GameStage INSTANCE;
    }
}
