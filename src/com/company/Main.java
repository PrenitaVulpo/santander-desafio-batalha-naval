package com.company;

//A fazer

//Função para distribuir os submarinos nas tabelas de cada jogador
//Função de entrada de cada jogador
//Exibir o mapa  de tiros além do mapa próprio dos submarinos do jogador

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static String[][] tablePlayer1 = {
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "}
    };
    private static String[][] tablePlayer2 = {
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "}
    };
    private static int turn = 2;
    private static String player = "";
    private static int player1Ships = 8;
    private static int player2Ships = 0;
    static Random generateRandon = new Random();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //Posicionamento das peças no tabuleiro
        while (player1Ships <= 10 && player2Ships <= 10) {

            placeShips();
            showTable();

        }

        startCombat();

    }

    private static void showTable() {

        if (turn%2==0){
            for (String[] strings : tablePlayer1) {
                System.out.println(Arrays.toString(strings));
            }
        } else {
            /*for (String[] strings : tablePlayer2) {
                System.out.println(Arrays.toString(strings));
            }*/
        }
    }

    private static void placeShips() {
        int coordinates[] = new int[2];
        if (turn%2==0){
            player = "Jogador 1";
            System.out.println("Vez do "+player);
            System.out.println("Digite a linha: ");
            coordinates[0] = scanner.nextInt();
            System.out.println("Digite a coluna: ");
            coordinates[1] = scanner.nextInt();
            setPosition(coordinates);
            if (player1Ships <= 10){
                //showTable();
                System.out.println("------------------------------------");
                turn ++;
            }
        } else {
            player = "Jogador 2";
            System.out.println("Vez do "+player);
            setPosition(coordinates);
            if (player2Ships >= 10){
                showTable();
                System.out.println("------------------------------------");
                turn ++;

            }
        }
    }

    public static void setPosition(int[] coordinates){
        if(turn%2==0){
            if (tablePlayer1[coordinates[0]][coordinates[1]] == " "){
                tablePlayer1[coordinates[0]][coordinates[1]] = "N";
                player1Ships++;
            }else {
                System.out.println("Esta posição esta ocupada");
            }

        }else {
            int a = generateRandon.nextInt(9);
            int b = generateRandon.nextInt(9);
            System.out.println(a);
            System.out.println(b);
            tablePlayer2[a][b] = "N";
            player2Ships++;
        }
    }

    public static void shoot(int[] coordinates) {

        if(turn%2==0){

            //Jogada do BOT

        } else {

            if (tablePlayer2[coordinates[0]][coordinates[1]] == " "){
                //Tiro na agua
                tablePlayer2[coordinates[0]][coordinates[1]] = "-";
                System.out.println("Tiro na agua!");
            }else if (tablePlayer2[coordinates[0]][coordinates[1]] == "-"  || tablePlayer2[coordinates[0]][coordinates[1]] == "*"){
                //Tiro em local onde já houve tiro
                System.out.println("Você já realizou um disparo nesta posição");
            } else if (tablePlayer2[coordinates[0]][coordinates[1]] == "N"){
                //Tiro certeiro
                tablePlayer2[coordinates[0]][coordinates[1]] = "*";
                System.out.println("Tiro certeiro!");
            }

        }

    }

    public static void startCombat() {

        System.out.println("O combate começou!");

        while (Objects.equals(checkWinner(), "")) {

            if(turn%2==0){
                System.out.println("Jogada do Bot");

                turn ++;
            } else {
                System.out.println("Jogada do Player");

                int coordinates[] = new int[2];

                System.out.println("Digite a linha: ");
                coordinates[0] = scanner.nextInt();
                System.out.println("Digite a coluna: ");
                coordinates[1] = scanner.nextInt();

                shoot(coordinates);

            }

        }

        System.out.println("O vencedor é "+checkWinner());

    }

    private static String checkWinner(){
        /*if (player1Ships == 0 && turn > 4) {
            return "Jogador 2";
        }

        if (player2Ships == 0 && turn > 4) {
            return "Jogador 1";
        }*/

        return "";
    }
}