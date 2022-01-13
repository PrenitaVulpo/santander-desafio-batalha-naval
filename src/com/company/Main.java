package com.company;

//A fazer

//Função para distribuir os submarinos nas tabelas de cada jogador
//Função de entrada de cada jogador
//Exibir o mapa  de tiros além do mapa próprio dos submarinos do jogador

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {

    private static final String[][] gridTitle = {             {"----------------------------------------------------------------------------"},             {"                                 JOGADOR                                    "},             {"----------------------------------------------------------------------------"}};
    private static String[][] tablePlayer1 = {
            {"|   |","| 0 |","| 1 |","| 2 |","| 3 |","| 4 |","| 5 |","| 6 |","| 7 |","| 8 |","| 9 |"},
            {"| A |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |"},
            {"| B |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |"},
            {"| C |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |"},
            {"| D |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |"},
            {"| E |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |"},
            {"| F |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |"},
            {"| G |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |"},
            {"| H |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |"},
            {"| I |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |"},
            {"| J |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |"}};
    private static String[][] tablePlayer2 = {
            {"|   |","| 0 |","| 1 |","| 2 |","| 3 |","| 4 |","| 5 |","| 6 |","| 7 |","| 8 |","| 9 |"},
            {"| A |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |"},
            {"| B |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |"},
            {"| C |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |"},
            {"| D |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |"},
            {"| E |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |"},
            {"| F |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |"},
            {"| G |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |"},
            {"| H |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |"},
            {"| I |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |"},
            {"| J |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |","|   |"}};
    private static int turn = 2;
    private static String player = "";
    private static int player1Ships = 8;
    private static int player2Ships = 0;
    static Random generateRandon = new Random();
    private static Scanner scanner = new Scanner(System.in);
    private static final String[] letterArray = {"A", "B", "C", "D" , "E", "F", "G", "H", "I", "J"};


    public static void main(String[] args) {

        System.out.println("----------------------------------------------------------------------------");
        System.out.println("|                       Bem vindo a Batalha Naval ☠️                     |");
        System.out.println("----------------------------------------------------------------------------");
        if (turn == 2){
            showTable();
        }
        //inserir scanner para nome do usuario

        startGame();

    }

    private static void startGame() {
        //Posicionamento das peças no tabuleiro

        System.out.println("- Inicialmente, iremos posicionar as embarações -");

        while (player1Ships <= 10 && player2Ships <= 10) {

            placeShips();
            //showTable();

            if (player1Ships == 10 && player2Ships == 10) {
                break;
            }

        }

        startCombat();
    }

    private static void showTable() {

        if (turn%2==0){
            for (String[] title: gridTitle){
                System.out.println(Arrays.toString(title));
            }
            for (String[] strings : tablePlayer1) {
                System.out.println(Arrays.toString(strings));
            }
        } else {
            for (String[] strings : tablePlayer2) {
                System.out.println(Arrays.toString(strings));
            }
        }
    }

    private static void placeShips() {
        String line = "";
        int coordinates[] = new int[2];
        if (turn%2==0){
            player = "Jogador 1";
            System.out.println("Vez do "+player);
            System.out.println("Digite uma letra de 'A' a 'J': ");
            line = scanner.next().toUpperCase();
            coordinates[0] = Arrays.asList(letterArray).indexOf(line) +1;
            System.out.println("Digite um numero de '0' a '9': ");
            coordinates[1] = scanner.nextInt() + 1 ;
            setPosition(coordinates);
            showTable();
            if (player1Ships == 10){
                //showTable();
                System.out.println("------------------------------------");
                turn ++;
            }
        } else {
            player = "Jogador 2";
            System.out.println("Vez do "+player);
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
            if (tablePlayer1[coordinates[0]][coordinates[1]] == "|   |"){
                tablePlayer1[coordinates[0]][coordinates[1]] = "| N |";
                player1Ships++;
            }else {
                System.out.println("Esta posição esta ocupada");
            }

        }else {
            int a = generateRandon.nextInt(9);
            int b = generateRandon.nextInt(9);
            System.out.println(a);
            System.out.println(b);
            tablePlayer2[a][b] = "| N |";
            player2Ships++;
        }
    }

    public static boolean shoot(int[] coordinates) {

        if(turn%2==0){

            if (tablePlayer2[coordinates[0]][coordinates[1]] == "|   |"){
                //Tiro na agua
                tablePlayer2[coordinates[0]][coordinates[1]] = "| - |";
                System.out.println("Tiro na agua!");
                return true;
            } else if (tablePlayer2[coordinates[0]][coordinates[1]] == "| N |"){
                //Tiro certeiro
                tablePlayer2[coordinates[0]][coordinates[1]] = "| * |";
                System.out.println("Tiro certeiro!");
                player2Ships--;
                return true;
            }

        } else {

            if (tablePlayer1[coordinates[0]][coordinates[1]] == "|   |"){
                //Tiro na agua
                tablePlayer1[coordinates[0]][coordinates[1]] = "| - |";
                System.out.println("O bot deu um tiro na agua!");
                return true;
            } else if (tablePlayer1[coordinates[0]][coordinates[1]] == "| N |"){
                //Tiro certeiro
                tablePlayer1[coordinates[0]][coordinates[1]] = "| * |";
                System.out.println("Tiro certeiro!");
                player1Ships--;
                return true;
            }

        }

        return false;

    }

    public static void startCombat() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("|                        COMBATE COMEÇÕU  ☠️                             |");
        System.out.println("----------------------------------------------------------------------------");
        String line = "";
        while (Objects.equals(checkWinner(), "")) {

            if(turn%2==0){
                System.out.println("Jogada do Player");

                showTable();

                int coordinates[] = new int[2];

                System.out.println("Digite uma letra de 'A' a 'J': ");
                line = scanner.next().toUpperCase();
                coordinates[0] = Arrays.asList(letterArray).indexOf(line) +1;
                System.out.println("Digite um numero de '0' a '9': ");
                coordinates[1] = scanner.nextInt() + 1 ;

                while(!shoot(coordinates)) {

                    System.out.println("Você ja realizou um disparo nessa posição! Jogue novamente :)");

                    System.out.println("Digite uma letra de 'A' a 'J': ");
                    line = scanner.next().toUpperCase();
                    coordinates[0] = Arrays.asList(letterArray).indexOf(line) +1;
                    System.out.println("Digite um numero de '0' a '9': ");
                    coordinates[1] = scanner.nextInt() + 1 ;

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
            return "Jogador 1";
        }

        return "";
    }
}