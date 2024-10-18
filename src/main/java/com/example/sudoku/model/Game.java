package com.example.sudoku.model;

import com.example.sudoku.view.AlertBox.AlertBox;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private int dato_entrada;
    private ArrayList<Integer> numeros;
    private ArrayList<ArrayList<Integer>> sudokuinicio;
    private ArrayList<ArrayList<Integer>> bloque1;
    private ArrayList<ArrayList<Integer>> bloque2;
    private ArrayList<ArrayList<Integer>> bloque3;
    private ArrayList<ArrayList<Integer>> bloque4;

    private ArrayList<ArrayList<Integer>> bloque5;
    private ArrayList<ArrayList<Integer>> bloque6;

    public Game() {
        this.dato_entrada = 0;
        ArrayList<ArrayList<Integer>> sudokuinicio = new ArrayList<>();
        ArrayList<ArrayList<Integer>> bloque1 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> bloque2 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> bloque3 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> bloque4 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> bloque5 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> bloque6 = new ArrayList<>();
        numeros = new ArrayList<>();

        for(int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                sudokuinicio.get(i).set(j,0);
            }
        }
        for(int i=0; i<5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(sudokuinicio.get(i).get(j));
            }
            System.out.println();
        }
    }
    public void generersudoku(){
        //crea un array con numeros del 1 al 6
        ArrayList<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            numeros.add(i);
        }
        for(int fila= 0;fila<5;fila++){
            for(int columna =0;columna<5;columna++){

                Collections.shuffle(numeros); // Mezclar números
                int num = numeros.get(0); // Tomar el primer número
                sudokuinicio.get(fila).set(columna,num);
            }
        }
    }


    public int getDato_entrada(){
        return this.dato_entrada;
    }
    public void setDato_entrada(int dato_entrada){
        this.dato_entrada = dato_entrada;
    }
    public boolean verificacionnumero(){
        try {
            if (dato_entrada >= 1 && dato_entrada <= 6) {
                return true;
            } else {
                AlertBox alert = new AlertBox();
                alert.showMessage("Error",
                                "Número no válido",
                                "El número debe estar entre 1 y 6.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("La entrada no es un número válido.");
            return false;}
    }

    public boolean verificacionfila(int fil, int dato){
        for(int i=0;i<5;i++) {
            //recorrer la fila del arreglo

        }
        return false;
    }
    public boolean verificacioncolumna(){
        //recorrer columna del arreglo
        return false;
    }
    public boolean verificacionbloque(){
        //tomar un bloque de 2x3(2 filas y 3 columnas) y añadirlo a un arreglo
        return false;
    }



}
