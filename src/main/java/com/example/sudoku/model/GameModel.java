package com.example.sudoku.model;

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

        for (int i = 0; i < 6; i++) {
            ArrayList<Integer> fila = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                fila.add(0); // Inicializar la matriz con ceros (vacía)
            }
            this.sudokuinicio.add(fila);
        }
        selectRandomSudoku();
        llenarBloques();
        imprimirSudoku();
        imprimirBloques();
    }
    private static void selectRandomSudoku() {
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
    }
    public void generarSudoku() {
        Random random = new Random();
        int[][] sudoku = new int[6][6];

        // Generar el Sudoku inicial (este es solo un ejemplo)
        for (int bloque = 0; bloque < 6; bloque++) {
            int contadorF = (bloque / 2) * 2; // Filas (0, 2, 4)
            int contadorC = (bloque % 2) * 3; // Columnas (0, 3)

            boolean[] numerosUsados = new boolean[7]; // Para números del 1 al 6

            for (int i = 0; i < 2; i++) {
                int fila, columna;
                int numeroAleatorio;

                do {
                    fila = random.nextInt(2) + contadorF;
                    columna = random.nextInt(3) + contadorC;
                    numeroAleatorio = random.nextInt(6) + 1; // Genera número entre 1 y 6
                } while (numerosUsados[numeroAleatorio] || sudoku[fila][columna] != 0);

                sudoku[fila][columna] = numeroAleatorio;
                numerosUsados[numeroAleatorio] = true;
            }
        }

        // Agregar el Sudoku inicial al ArrayList
        sudokuInicial.add(sudoku);
    }
    public void llenarBloques() {
        // Llenar bloque 1 (filas 0-1, columnas 0-2)
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                bloque1[i][j] = sudokuinicio.get(i).get(j);
            }
        }

        // Llenar bloque 2 (filas 0-1, columnas 3-5)
        for (int i = 0; i < 2; i++) {
            for (int j = 3; j < 6; j++) {
                bloque2[i][j - 3] = sudokuinicio.get(i).get(j);
            }
        }

        // Llenar bloque 3 (filas 2-3, columnas 0-2)
        for (int i = 2; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                bloque3[i - 2][j] = sudokuinicio.get(i).get(j);
            }
        }

        // Llenar bloque 4 (filas 2-3, columnas 3-5)
        for (int i = 2; i < 4; i++) {
            for (int j = 3; j < 6; j++) {
                bloque4[i - 2][j - 3] = sudokuinicio.get(i).get(j);
            }
        }

        // Llenar bloque 5 (filas 4-5, columnas 0-2)
        for (int i = 4; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                bloque5[i - 4][j] = sudokuinicio.get(i).get(j);
            }
        }

        // Llenar bloque 6 (filas 4-5, columnas 3-5)
        for (int i = 4; i < 6; i++) {
            for (int j = 3; j < 6; j++) {
                bloque6[i - 4][j - 3] = sudokuinicio.get(i).get(j);
            }
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

    public boolean verificacionFila(int fila, int dato) {
        for (int i = 0; i < 6; i++) {
            if (sudokuinicio.get(fila).get(i) == dato) {
                return false; // Si el número ya está en la fila, no es válido
            }
        }
        return true; // Si no se repite, es válido
    }
    public boolean verificacionColumna(int columna, int dato) {
        for (int i = 0; i < 6; i++) {
            if (sudokuinicio.get(i).get(columna) == dato) {
                return false; // Si el número ya está en la columna, no es válido
            }
        }
        return true; // Si no se repite, es válido
    }

    public boolean verificacionBloque(int fila, int columna, int dato) {
        int inicioFila = (fila / 2) * 2; // Determina el inicio del bloque en filas
        int inicioColumna = (columna / 3) * 3; // Determina el inicio del bloque en columnas

        for (int i = 0; i < 2; i++) { // Recorrer las 2 filas del bloque
            for (int j = 0; j < 3; j++) { // Recorrer las 3 columnas del bloque
                if (sudokuinicio.get(inicioFila + i).get(inicioColumna + j) == dato) {
                    return false; // Si el número ya está en el bloque, no es válido
                }
            }
        }
        return true; // Si no se repite, es válido
    }

    public static int [][] getSudokuInicio() {
        return selectedSudoku;
    }
    public ArrayList<int[][]> getSudokuInicial() {
        return sudokuInicial;
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
