package com.company;

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
    private static int player1Ships = 0;
    private static int player2Ships = 0;
    private static String playerName = "";
    static Random generateRandon = new Random();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("----------------------------------");
        System.out.println("| Bem vindo ao Batalha Naval ☠️|");
        System.out.println("----------------------------------");

        //inserir scanner para nome do usuario

        System.out.println("Qual é o seu nome: ");
        playerName = scanner.nextLine();

        startGame();

    }

    private static void startGame() {
        //Posicionamento das peças no tabuleiro

        System.out.println("- Inicialmente, iremos posicionar as embarações -");

        while (player1Ships <= 10 && player2Ships <= 10) {

            placeShips();

            if (player1Ships == 10 && player2Ships == 10) {
                break;
            }

        }

        startCombat();
    }

    private static void showTable() {

        if (turn%2==0){
            System.out.println("Tabuleiro do player");
            for (String[] strings : tablePlayer1) {
                System.out.println(Arrays.toString(strings));
            }
        } else {
            System.out.println("Tabuleiro do bot");
            for (String[] strings : tablePlayer2) {
                System.out.println(Arrays.toString(strings));
            }
        }
    }

    private static void placeShips() {

        int coordinates[] = new int[2];
        if (turn%2==0){
            System.out.println("Vez de "+playerName);
            System.out.println("Digite a linha: ");
            coordinates[0] = scanner.nextInt();
            System.out.println("Digite a coluna: ");
            coordinates[1] = scanner.nextInt();
            setPosition(coordinates);
            showTable();
            if (player1Ships == 10){
                //showTable();
                System.out.println("------------------------------------");
                turn ++;
            }
        } else {
            System.out.println("Vez do Bot");
            setPosition(coordinates);
            showTable();
            if (player2Ships == 10){
                //showTable();
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

    public static boolean shoot(int[] coordinates) {

        if(turn%2==0){

            if (tablePlayer2[coordinates[0]][coordinates[1]] == " "){
                //Tiro na agua
                tablePlayer2[coordinates[0]][coordinates[1]] = "-";
                System.out.println("Tiro na agua!");
                return true;
            } else if (tablePlayer2[coordinates[0]][coordinates[1]] == "N"){
                //Tiro certeiro
                tablePlayer2[coordinates[0]][coordinates[1]] = "*";
                System.out.println("Tiro certeiro!");
                player2Ships--;
                return true;
            }

        } else {

            if (tablePlayer1[coordinates[0]][coordinates[1]] == " "){
                //Tiro na agua
                tablePlayer1[coordinates[0]][coordinates[1]] = "-";
                System.out.println("O bot deu um tiro na agua!");
                return true;
            } else if (tablePlayer1[coordinates[0]][coordinates[1]] == "N"){
                //Tiro certeiro
                tablePlayer1[coordinates[0]][coordinates[1]] = "*";
                System.out.println("Tiro certeiro!");
                player1Ships--;
                return true;
            }

        }

        return false;

    }

    public static void startCombat() {

        System.out.println("---> O combate começou! <---");

        while (Objects.equals(checkWinner(), "")) {

            if(turn%2==0){
                System.out.println("Jogada de " + playerName);

                showTable();

                int coordinates[] = new int[2];

                System.out.println("Digite a linha: ");
                coordinates[0] = scanner.nextInt();
                System.out.println("Digite a coluna: ");
                coordinates[1] = scanner.nextInt();

                while(!shoot(coordinates)) {

                    System.out.println("Você ja realizou um disparo nessa posição! Jogue novamente :)");

                    System.out.println("Digite a linha: ");
                    coordinates[0] = scanner.nextInt();
                    System.out.println("Digite a coluna: ");
                    coordinates[1] = scanner.nextInt();

                }

                turn ++;
            } else {

                System.out.println("Jogada do Bot");

                int coordinates[] = new int[2];
                coordinates[0] = generateRandon.nextInt(9); //Para testar é só reduzir o range do bot
                coordinates[1] = generateRandon.nextInt(9);//Para testar é só reduzir o range do bot
                //System.out.println("Cordenada do bot -> " + coordinates[0] + ", " + coordinates[1]);

                while(!shoot(coordinates)) {

                    coordinates[0] = generateRandon.nextInt(9);
                    coordinates[1] = generateRandon.nextInt(9);
                    //System.out.println("Cordenada do bot -> " + coordinates[0] + ", " + coordinates[1]);

                }

                turn ++;

            }

        }

        System.out.println("O vencedor é o(a): "+checkWinner());

    }

    private static String checkWinner(){
        if (player1Ships == 0 && turn > 4) {
            return "Bot";
        }

        if (player2Ships == 0 && turn > 4) {
            return playerName;
        }

        return "";
    }
}