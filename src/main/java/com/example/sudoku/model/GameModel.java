package com.example.sudoku.model;

import com.example.sudoku.view.AlertBox.AlertBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameModel {
    private static ArrayList<ArrayList<Integer>> sudokuinicio;
    private static int[][] sudoku1;
    private static int[][] sudoku2;
    private static int[][] sudoku3;
    private static int[][] sudoku4;
    private static int[][] sudoku5;

    public GameModel() {
        this.sudokuinicio = new ArrayList<>();
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

        for (int i = 0; i < 6; i++) {
            ArrayList<Integer> fila = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                fila.add(0); // Inicializar la matriz con ceros (vacía)
            }
            this.sudokuinicio.add(fila);
        }
        selectRandomSudoku();
        imprimirSudoku();
    }
    private static void selectRandomSudoku() {
        Random random = new Random();
        int selectedSudokuIndex = random.nextInt(5); // Genera un índice aleatorio entre 0 y 4

        // Convertir el Sudoku seleccionado a ArrayList<ArrayList<Integer>>
        sudokuinicio = new ArrayList<>();
        int[][] selectedSudoku = switch (selectedSudokuIndex) {
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
    }
    private void imprimirSudoku() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(sudokuinicio.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    private boolean verificacioncolumna(int column, int dato) {
        for (int i = 0; i < 6; i++) {
            if (sudokuinicio.get(i).get(column) == dato) {
                return false;
            }
        }
        return true;
    }

    private boolean verificacionbloque(int fila, int columna, int dato) {
        return true;
    }

    private static ArrayList<ArrayList<Integer>> getSudokuInicio() {
        return sudokuinicio;
    }
}
