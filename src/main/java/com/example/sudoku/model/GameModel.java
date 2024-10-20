package com.example.sudoku.model;

import com.example.sudoku.controller.GameController;

import java.util.ArrayList;
import java.util.Random;


public class GameModel {
    private ArrayList<int[][]> sudokuInicial;
    private static int[][] selectedSudoku;
    private static ArrayList<ArrayList<Integer>> sudokuinicio;
    private static int[][] sudoku1;
    private static int[][] sudoku2;
    private static int[][] sudoku3;
    private static int[][] sudoku4;
    private static int[][] sudoku5;
    private static int[][] bloque1;
    private static int[][] bloque2;
    private static int[][] bloque3;
    private static int[][] bloque4;
    private static int[][] bloque5;
    private static int[][] bloque6;
    private static int[][] bloqueextraido;
    private GameController controlador;
    private static int selectedSudokuIndex;




    public GameModel() {
        this.sudokuinicio = new ArrayList<>();
        this.bloque1 = new int[2][3];
        this.bloque2 = new int[2][3];
        this.bloque3 = new int[2][3];
        this.bloque4 = new int[2][3];
        this.bloque5 = new int[2][3];
        this.bloque6 = new int[2][3];
        this.sudokuInicial = new ArrayList<>();
        this.selectedSudoku = new int[6][6];


        sudoku1 = new int[][]{
                {1, 2, 3, 4, 5, 6},
                {4, 5, 6, 1, 2, 3},
                {2, 3, 1, 6, 4, 5},
                {6, 4, 5, 2, 3, 1},
                {3, 1, 2, 5, 6, 4},
                {5, 6, 4, 3, 1, 2}
        };

        sudoku2 = new int[][]{
                {1, 3, 5, 2, 4, 6},
                {2, 4, 6, 1, 3, 5},
                {3, 5, 1, 4, 6, 2},
                {4, 6, 2, 5, 1, 3},
                {5, 1, 3, 6, 2, 4},
                {6, 2, 4, 3, 5, 1}
        };

        sudoku3 = new int[][]{
                {1, 2, 3, 4, 5, 6},
                {4, 5, 6, 1, 2, 3},
                {5, 6, 4, 3, 1, 2},
                {2, 3, 1, 6, 4, 5},
                {6, 4, 5, 2, 3, 1},
                {3, 1, 2, 5, 6, 4}
        };

        sudoku4 = new int[][]{
                {1, 2, 3, 4, 5, 6},
                {4, 5, 6, 1, 2, 3},
                {2, 3, 1, 6, 4, 5},
                {5, 1, 3, 6, 2, 4},
                {6, 4, 5, 2, 3, 1},
                {3, 1, 2, 5, 6, 4}
        };

        sudoku5 = new int[][]{
                {1, 3, 5, 2, 4, 6},
                {2, 4, 6, 1, 3, 5},
                {3, 5, 1, 4, 6, 2},
                {4, 6, 2, 5, 1, 3},
                {5, 1, 3, 6, 2, 4},
                {6, 2, 4, 3, 5, 1}
        };
        llenarBloques();
        imprimirSudoku();



    }

    /*public void selectRandomSudoku() {

        for (int i = 0; i < 6; i++) {
            ArrayList<Integer> fila = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                fila.add(0); // Inicializar la matriz con ceros (vacía)
            }
            this.sudokuinicio.add(fila);
        }

        Random random = new Random();
        int selectedSudokuIndex = random.nextInt(5); // Genera un índice aleatorio entre 0 y 4

        // Convertir el Sudoku seleccionado a ArrayList<ArrayList<Integer>>
        sudokuinicio = new ArrayList<>();
        selectedSudoku = switch (selectedSudokuIndex) {
            case 0 -> sudoku1;
            case 1 -> sudoku2;
            case 2 -> sudoku3;
            case 3 -> sudoku4;
            case 4 -> sudoku5;
            default -> throw new IllegalStateException("Unexpected value: " + selectedSudokuIndex);
        };

        // Llenar el ArrayList con los valores del Sudoku seleccionado
        for (int[] row : selectedSudoku) {
            ArrayList<Integer> rowList = new ArrayList<>();
            for (int num : row) {
                rowList.add(num);
            }
            sudokuinicio.add(rowList);
        }

        System.out.println("aqui cree otro");
        llenarBloques();
        imprimirSudoku();

    }*/

    public void llenarBloques() {
        // Llenar bloque 1
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                bloque1[i][j] = sudoku5[i][j];
            }
        }

        // Llenar bloque 2
        for (int i = 0; i < 2; i++) {
            for (int j = 3; j < 6; j++) {
                bloque2[i][j - 3] = sudoku5[i][j];
            }
        }

        // Llenar bloque 3
        for (int i = 2; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                bloque3[i - 2][j] = sudoku5[i][j];
            }
        }

        // Llenar bloque 4
        for (int i = 2; i < 4; i++) {
            for (int j = 3; j < 6; j++) {
                bloque4[i - 2][j - 3] = sudoku5[i][j];
            }
        }

        // Llenar bloque 5
        for (int i = 4; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                bloque5[i - 4][j] = sudoku5[i][j];
            }
        }

        // Llenar bloque 6
        for (int i = 4; i < 6; i++) {
            for (int j = 3; j < 6; j++) {
                bloque6[i - 4][j - 3] = sudoku5[i][j];
            }
        }
    }
    private void imprimirSudoku() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(sudoku5[i][j]+ " ");
            }
            System.out.println();
        }
    }
    public void imprimirBloques() {
        // Imprimir bloque 1
        System.out.println("Bloque 1:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(bloque1[i][j] + " ");
            }
            System.out.println();
        }

        // Imprimir bloque 2
        System.out.println("Bloque 2:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(bloque2[i][j] + " ");
            }
            System.out.println();
        }

        // Imprimir bloque 3
        System.out.println("Bloque 3:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(bloque3[i][j] + " ");
            }
            System.out.println();
        }

        // Imprimir bloque 4
        System.out.println("Bloque 4:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(bloque4[i][j] + " ");
            }
            System.out.println();
        }

        // Imprimir bloque 5
        System.out.println("Bloque 5:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(bloque5[i][j] + " ");
            }
            System.out.println();
        }

        // Imprimir bloque 6
        System.out.println("Bloque 6:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(bloque6[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean verificacionFila(int fila, int columna, int dato) {
        controlador = new GameController();
        for (int i = 0; i < 6; i++) {
            String valorCaja = String.valueOf(controlador.getCaja(fila, i)); // Obtener valor
            if (!valorCaja.isEmpty()) { // Si la caja no está vacía
                int valor = Integer.parseInt(valorCaja); // Convertir el valor en entero
                if (valor == dato) {
                    return false; // si no esta en la fila
                }
            }
        }
        return true;
    }

    public boolean verificacionColumna(int fila, int columna, int dato) {
        controlador = new GameController();
        for (int i = 0; i < 6; i++) {
            String valorCaja = String.valueOf(controlador.getCaja(i, columna)); // Obtener el valor
            if (!valorCaja.isEmpty()) { // Si la caja no está vacía
                int valor = Integer.parseInt(valorCaja); // Convertir el valor a int
                if (valor == dato) {
                    return false; // Si el número ya está en la columna, no es válido
                }
            }
        }
        return true;
    }

    public boolean verificacionBloque(int fila, int columna, int dato) {
        controlador = new GameController();
        // Determina el inicio del bloque en filas y columnas
        int inicioFila = (fila / 2) * 2;
        int inicioColumna = (columna / 3) * 3;

        for (int i = 0; i < 2; i++) { // Recorrer las 2 filas del bloque
            for (int j = 0; j < 3; j++) { // Recorrer las 3 columnas del bloque
                // Obtener el valor del TextField correspondiente en el bloque
                String valorCaja = controlador.getCaja(inicioFila + i, inicioColumna + j).getText(); // Obtener el valor del TextField

                if (!valorCaja.isEmpty()) { // Si la caja no está vacía
                    int valor = Integer.parseInt(valorCaja); // Convertir el valor a int
                    if (valor == dato) {
                        return false; // Si el número ya está en el bloque, no es válido
                    }
                }
            }
        }
        return true; // Si no se repite, es válido
    }

    public int[][] getSudokuInicio() {
        return sudoku5;
    }

    public int[][] getbloque(int opc){

        switch (opc){
            case 1:
                bloqueextraido= bloque1;
                break;
            case 2:
                bloqueextraido= bloque2;
            break;
            case 3:
                bloqueextraido= bloque3;
            break;
            case 4:
                bloqueextraido= bloque4;
            break;
            case 5:
                bloqueextraido= bloque5;
            break;
            case 6:
                bloqueextraido= bloque6;
            break;
            default:
                break;

        }return bloqueextraido;
    }

}
