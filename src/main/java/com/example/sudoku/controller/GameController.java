package com.example.sudoku.controller;

import com.example.sudoku.model.GameModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.TextFormatter;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.function.UnaryOperator;

public class GameController {

    @FXML
    private Label welcomeText;

    @FXML
    private TextField caja00, caja01, caja02, caja03, caja04, caja05;
    @FXML
    private TextField caja10, caja11, caja12, caja13, caja14, caja15;
    @FXML
    private TextField caja20, caja21, caja22, caja23, caja24, caja25;
    @FXML
    private TextField caja30, caja31, caja32, caja33, caja34, caja35;
    @FXML
    private TextField caja40, caja41, caja42, caja43, caja44, caja45;
    @FXML
    private TextField caja50, caja51, caja52, caja53, caja54, caja55;

    private GameModel juego;
    private GridPane grid;
    private Pane gridContainer;
    private TextField[][] cajas;

    public GameController() {
        this.juego = new GameModel();
        this.grid = new GridPane();
        this.gridContainer = new Pane();
        this.cajas = new TextField[6][6];
    }

    @FXML
    public void initialize() {
        // Inicializar el array de cajas
        cajas[0][0] = caja00; cajas[0][1] = caja01; cajas[0][2] = caja02; cajas[0][3] = caja03; cajas[0][4] = caja04; cajas[0][5] = caja05;
        cajas[1][0] = caja10; cajas[1][1] = caja11; cajas[1][2] = caja12; cajas[1][3] = caja13; cajas[1][4] = caja14; cajas[1][5] = caja15;
        cajas[2][0] = caja20; cajas[2][1] = caja21; cajas[2][2] = caja22; cajas[2][3] = caja23; cajas[2][4] = caja24; cajas[2][5] = caja25;
        cajas[3][0] = caja30; cajas[3][1] = caja31; cajas[3][2] = caja32; cajas[3][3] = caja33; cajas[3][4] = caja34; cajas[3][5] = caja35;
        cajas[4][0] = caja40; cajas[4][1] = caja41; cajas[4][2] = caja42; cajas[4][3] = caja43; cajas[4][4] = caja44; cajas[4][5] = caja45;
        cajas[5][0] = caja50; cajas[5][1] = caja51; cajas[5][2] = caja52; cajas[5][3] = caja53; cajas[5][4] = caja54; cajas[5][5] = caja55;

        // Aplicar formato y filtros
        configurarTextFields();
    }

    public void configurarTextFields() {
        // Crear un filtro para limitar la entrada a solo un dígito (1-9)
        UnaryOperator<TextFormatter.Change> digitFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[1-9]?")) { // Permite solo un dígito (1-9)
                return change;
            }
            return null; // Bloquea cualquier otro cambio
        };

        for (TextField[] row : cajas) {
            for (TextField textField : row) {
                // Aplicar el filtro a cada TextField
                textField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), null, digitFilter));
                textField.setStyle("-fx-background-color: lightgray;"); // Color por defecto

                // Comportamiento de cursor
                textField.setOnMouseEntered(this::cursorsobre);
                textField.setOnMouseExited(this::cursorfuera);
            }
        }
    }

    // Método para validar la entrada del TextField al hacer clic
    public void validarEntrada(MouseEvent event) {
        TextField textField = (TextField) event.getSource();
        String valor = textField.getText();

        // Comprobar si el valor es un dígito válido
        if (!valor.matches("[1-9]")) {
            textField.clear();
        }
    }

    @FXML
    void onclick(ActionEvent event) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                String valor = cajas[i][j].getText();
                if (valor.matches("[1-9]")) { // Solo un dígito válido entre 1 y 9
                    System.out.println("Valor válido en caja[" + i + "][" + j + "]: " + valor);
                } else {
                    System.out.println("Error: Valor no válido en caja[" + i + "][" + j + "]: " + valor);
                }
            }
        }
    }

    public void botonreiniciar(ActionEvent actionEvent) {

    }
    public void botonayuda(ActionEvent actionEvent) {}
    public void botonvalidar(ActionEvent actionEvent) {}
    public void iniciarjuego(ActionEvent actionEvent) {
        llenarsudoku();
    }
    public void ayuda(ActionEvent actionEvent) {}

    @FXML
    public void cursorsobre(@NotNull MouseEvent mouseEvent) {
        TextField textField = (TextField) mouseEvent.getSource();
        textField.setStyle("-fx-background-color: yellow;");
    }

    @FXML
    public void cursorfuera(@NotNull MouseEvent mouseEvent) {
        TextField textField = (TextField) mouseEvent.getSource();
        textField.setStyle("-fx-background-color: lightgray;");
    }

    public void clickcelda(MouseEvent mouseEvent) {
    }
    public void llenarsudoku() {
        Random random = new Random();
        boolean[] numerosUsados = new boolean[7]; // Para los números del 1 al 6 (índices 1 a 6)

        for (int bloque = 0; bloque < 6; bloque++) {
            int contadorF = (bloque / 2) * 2; // Filas (0, 2, 4)
            int contadorC = (bloque % 2) * 3; // Columnas (0, 3)

            // Rellenar celdas aleatorias dentro del bloque
            for (int i = 0; i < 2; i++) { // Cambia 2 por el número de celdas que deseas llenar
                int fila, columna;
                int numerobloque = 0;


                ;
                int numerousado=0;
                do {
                    fila = random.nextInt(2) + contadorF; // Seleccionar fila aleatoria
                    columna = random.nextInt(3) + contadorC; // Seleccionar columna aleatoria

                    // Obtener el número del bloque
                    int[][] bloqueExtraido = juego.getbloque(bloque + 1);
                    numerobloque = bloqueExtraido[fila - contadorF][columna - contadorC];

                } while (numerousado != 0 && numerosUsados[numerobloque]); // Repetir si el número ya fue usado

                // Establecer el número en el TextField
                cajas[fila][columna].setText(String.valueOf(numerobloque));
                numerosUsados[numerobloque] = true; // Marcar el número como usado
            }
        }
    }


}
