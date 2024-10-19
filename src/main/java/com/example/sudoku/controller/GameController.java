package com.example.sudoku.controller;

import com.example.sudoku.model.GameModel;
import com.example.sudoku.view.AlertBox.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.TextFormatter;
import org.jetbrains.annotations.NotNull;


import java.util.Arrays;
import java.util.Random;
import java.util.function.UnaryOperator;

public class GameController {
    @FXML
    Button btniniciar;

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



    private final TextField[][] cajas;
    private int[][] sudoku;
    private GameModel juego;
    AlertBox advertencia;

    public GameController() {
        this.cajas = new TextField[6][6];
        this.sudoku = new int[6][6];
        this.juego = new GameModel();
        this.advertencia = new AlertBox();

    }

    @FXML
    public void initialize() {
        // Inicializar el array de cajas
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

        // Aplicar formato y filtros
        initializeCajas();
        configurarTextFields();

    }

    public void configurarTextFields() {
        // limitar entrada 1-6
        UnaryOperator<TextFormatter.Change> digitFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[1-6]?")) { // solo 6 numeros
                return change;
            }
            return null; // Bloquea cualquier otro cambio
        };
        //recorrer los textfiel
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

    public void iniciarjuego(ActionEvent actionEvent) {
        llenarsudoku();

        btniniciar.setVisible(false); // desaparecer boton

    }

    public void ayuda(ActionEvent actionEvent){
        int[][] sudoku5 = juego.getSudokuInicio();
        for (int bloqueFila = 0; bloqueFila < 2; bloqueFila++) { // Para los bloques en las filas
            for (int bloqueCol = 0; bloqueCol < 3; bloqueCol++) { // Para los bloques en las columnas
                // Generar una posición aleatoria dentro del bloque 2x3
                int posFila = bloqueFila * 2 + (int) (Math.random() * 2);
                int posCol = bloqueCol * 3 + (int) (Math.random() * 3);

                // Verificar que no esté vacío antes de llenar
                TextField text = obtenerTextField(posFila, posCol);
                if (text.getText().isEmpty()) {
                    // Asignar el valor del bloque correspondiente de sudoku5
                    text.setText(String.valueOf(sudoku5[posFila][posCol]));
                }
            }
        }

    }

    private TextField obtenerTextField(int fila, int columna) {
        return this.cajas[fila][columna];
    }


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
        printSudoku();
    }

    public void llenarsudoku() {
        Random random = new Random();
        boolean[] numerosUsados = new boolean[7]; // Para los números del 1 al 6 (índices 1 a 6)

        for (int bloque = 0; bloque < 6; bloque++) {
            int contadorF = (bloque / 2) * 2; // Filas (0, 2, 4)
            int contadorC = (bloque % 2) * 3; // Columnas (0, 3)

            // Reiniciar numerosUsados para cada bloque
            // Reiniciar el uso de números al inicio de cada bloque
            Arrays.fill(numerosUsados, false);

            // Rellenar celdas aleatorias dentro del bloque
            for (int i = 0; i < 2; i++) { // Cambia 2 por el número de celdas que deseas llenar
                int fila, columna;
                int numerobloque = 0;
                int numerousado = 0;
                boolean encontrado = false; // Para determinar si encontramos un número válido

                do {
                    fila = random.nextInt(2) + contadorF; // Seleccionar fila aleatoria
                    columna = random.nextInt(3) + contadorC; // Seleccionar columna aleatoria

                    // Obtener el número del bloque
                    int[][] bloqueExtraido = juego.getbloque(bloque + 1);
                    numerobloque = bloqueExtraido[fila - contadorF][columna - contadorC];

                    // Verificar si el número ya ha sido usado
                    if (numerobloque >= 1 && numerobloque <= 6 && !numerosUsados[numerobloque]) {
                        // Si el número no ha sido usado, podemos salir del bucle
                        numerousado = numerobloque; // Asignar el número a numerousado
                        encontrado = true; // Marcamos que encontramos un número válido
                    }

                } while (!encontrado); // Repetir hasta que se encuentre un número no usado

                // Establecer el número en el TextField
                cajas[fila][columna].setText(String.valueOf(numerousado));
                numerosUsados[numerousado] = true; // Marca el número como usado
            }
        }
    }


    public void initializeCajas() {
        for (int fila = 0; fila < 6; fila++) {
            {
                final int f = fila; // Para la captura de la fila
                final int c = 0; // Para la captura de la columna

                cajas[f][c].textProperty().addListener((observable, oldValue, newValue) ->
                        validarEntrada(f, c, newValue));

            }
            {
                final int f = fila; // Para la captura de la fila
                final int c = 1; // Para la captura de la columna

                cajas[f][c].textProperty().addListener((observable, oldValue, newValue) -> validarEntrada(f, c, newValue));
            }
            {
                final int f = fila; // Para la captura de la fila
                final int c = 2; // Para la captura de la columna

                cajas[f][c].textProperty().addListener((observable, oldValue, newValue) -> validarEntrada(f, c, newValue));
            }
            {
                final int f = fila; // Para la captura de la fila
                final int c = 3; // Para la captura de la columna

                cajas[f][c].textProperty().addListener((observable, oldValue, newValue) -> validarEntrada(f, c, newValue));
            }
            {
                final int f = fila; // Para la captura de la fila
                final int c = 4; // Para la captura de la columna

                cajas[f][c].textProperty().addListener((observable, oldValue, newValue) -> validarEntrada(f, c, newValue));
            }
            {
                final int f = fila; // Para la captura de la fila
                final int c = 5; // Para la captura de la columna

                cajas[f][c].textProperty().addListener((observable, oldValue, newValue) -> validarEntrada(f, c, newValue));
            }
        }
    }

    public void validarEntrada(int fila, int columna, @NotNull String entrada) {
        // Si la entrada está vacía, pinta la caja de blanco y retorna

        if (verificarvalores()) {

            advertencia.showMessage("Eres un campeon", "Usuario","lograste llenar el sudoku");
        }

        if (entrada.isEmpty()) {
            pintarTextField(cajas[fila][columna], false); // Se pinta de blanco
            return;
        }

        try {
            // Intenta convertir la entrada a un número
            int dato = Integer.parseInt(entrada); // Convierte la entrada a un número
            sudoku[fila][columna] = dato;



                // Validar fila
                if (!juego.verificacionFila(fila, columna, dato)) {
                    pintarTextField(cajas[fila][columna], false);
                    return;
                }

                // Validar columna
                if (!juego.verificacionColumna(fila, columna, dato)) {
                    pintarTextField(cajas[fila][columna], false);
                    return;
                }

                // Validar bloque
                if (!juego.verificacionBloque(fila, columna, dato)) {
                    pintarTextField(cajas[fila][columna], false);
                    return;
                }

                // Si todas las verificaciones son correctas, actualizar el sudoku y pintar el TextField en blanco

                pintarTextField(cajas[fila][columna], true);

            } catch(NumberFormatException e){
                pintarTextField(cajas[fila][columna], false); // Si no es un número, pinta de rojo
            } catch(ArrayIndexOutOfBoundsException e){
            System.err.println("indice fuera de límites: " + e.getMessage());
                pintarTextField(cajas[fila][columna], false); // Manejo adicional si es necesario
            } catch(NullPointerException e){
                System.err.println("Referencia nula: " + e.getMessage());
                // Manejo adicional si es necesario
            } catch(Exception e){
                System.err.println("Error inesperado: " + e.getMessage());
                // Manejo adicional si es necesario
            }
        }

    private void pintarTextField (TextField textField,boolean esValido){
        if (esValido) {
            textField.setStyle("-fx-background-color: white;"); // Color por defecto
        } else {
            textField.setStyle("-fx-background-color: red;"); // Color de error
        }
    }public TextField getCaja ( int fila, int columna){
        return cajas[fila][columna]; // Retorna el TextField en la posición especificada
    }
    private void printSudoku () {
        System.out.println("Estado actual del Sudoku:");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                String value = String.valueOf(sudoku[i][j]);
                System.out.print((value.isEmpty() ? "0" : value) + " "); // Imprime 0 si está vacío
            }
            System.out.println();
        }
        System.out.println("----------------------------");
    }


    public boolean verificarvalores() {
        int[][] sudoku5 = juego.getSudokuInicio();  // Obtén el sudoku de inicio
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                // Convierte el valor del TextField a entero
                int valorCaja = Integer.parseInt(cajas[i][j].getText());

                // Compara el valor de cajas convertido con el valor de sudoku5
                if (valorCaja != sudoku5[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}




