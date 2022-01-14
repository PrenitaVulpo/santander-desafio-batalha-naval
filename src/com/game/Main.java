package com.game;


import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;


public class Main {

    private static final String[] letters = {"A","B","C","D","E","F","G","H","I","J"};

    private static final String[][] tablePlayer1 = {
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

    private static final String[][] tablePlayer2 = {
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

    private static String tablePlayer1ASCII =
            "-------------------------------------------------------\n" +
            "                        JOGADOR                        \n" +
            "-------------------------------------------------------\n";

    private static int turn = 2;
    private static String playerName = "";
    private static int player1Ships = 0;
    private static int player2Ships = 0;
    static Random generateRandon = new Random();
    private static final Scanner scanner = new Scanner(System.in);
    private static final String[] letterArray = {"A", "B", "C", "D" , "E", "F", "G", "H", "I", "J"};


    public static void main(String[] args) {

        System.out.println("----------------------------------------------------------------------------");
        System.out.println("|                       Bem vindo à Batalha Naval ☠️                     |");
        System.out.println("----------------------------------------------------------------------------");

        System.out.print("informe o seu nome: ");
        playerName = scanner.nextLine();

        startGame();

    }

    private static void startGame() {
        //Posicionamento das peças no tabuleiro

        System.out.println("- Inicialmente, iremos posicionar as embarações -");

        while (player1Ships <= 1 && player2Ships <= 1) {

            placeShips();

            if (player1Ships == 1 && player2Ships == 1) {
                break;
            }

        }
            startCombat();

    }

    private static void buildASCII(String[][] table){
        StringBuilder tempString = new StringBuilder();

        for (String[] strings : table) {
            for (String string : strings) {
                if (!Objects.equals(string, ",")) {
                    tempString.append(string);
                }
            }

            tablePlayer1ASCII = tablePlayer1ASCII + tempString + "\n";

            tempString = new StringBuilder();
        }
    }

    private static void resetASCII(){
        tablePlayer1ASCII =
                "-------------------------------------------------------\n" +
                "                        JOGADOR                        \n" +
                "-------------------------------------------------------\n";
    }

    private static void showTable() {

        if (turn%2==0){
            buildASCII(tablePlayer1);
            System.out.println(tablePlayer1ASCII);
            resetASCII();
        } else {
            for (String[] strings : tablePlayer2) {
                System.out.println(Arrays.toString(strings));
            }
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static void placeShips() {
        String line;
        int[] coordinates = new int[2];
        if (turn%2==0){
            System.out.println("Vez de "+playerName);
            System.out.print("Digite uma letra de 'A' a 'J': ");
            line = scanner.next().toUpperCase();
            if (!Arrays.asList(letters).contains(line)){
                System.out.println("entrada inválida");
                placeShips();
            }
            coordinates[0] = Arrays.asList(letterArray).indexOf(line) + 1;
            System.out.print("Digite um número de '0' a '9': ");
            String input = scanner.next() ;
            if (!isNumeric(input)){
                System.out.println("entrada inválida, informe um número");
                placeShips();
            }
            if(Integer.parseInt(input) + 1 < 11 && Integer.parseInt(input) + 1 > 0){
                coordinates[1] = Integer.parseInt(input) + 1;
            }
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
            if (Objects.equals(tablePlayer1[coordinates[0]][coordinates[1]], "|   |")){
                tablePlayer1[coordinates[0]][coordinates[1]] = "| N |";
                player1Ships++;
                turn++;
            }else {
                System.out.println("Esta posição esta ocupada");
            }

        }else {
            int a = generateRandon.nextInt(9);
            int b = generateRandon.nextInt(9);
//            System.out.println(a);
//            System.out.println(b);
            tablePlayer2[a][b] = "| N |";
            player2Ships++;
            turn++;
        }
    }

    public static boolean shoot(int[] coordinates) {

        if(turn%2==0){

            if (Objects.equals(tablePlayer2[coordinates[0]][coordinates[1]], "|   |")){
                //Tiro na agua
                tablePlayer2[coordinates[0]][coordinates[1]] = "| - |";
                System.out.println("Tiro na agua!");
                return true;
            } else if (Objects.equals(tablePlayer2[coordinates[0]][coordinates[1]], "| N |")){
                //Tiro certeiro
                tablePlayer2[coordinates[0]][coordinates[1]] = "| * |";
                System.out.println("Tiro certeiro!");
                player2Ships--;
                return true;
            }

        } else {

            if (Objects.equals(tablePlayer1[coordinates[0]][coordinates[1]], "|   |")){
                //Tiro na agua
                tablePlayer1[coordinates[0]][coordinates[1]] = "| - |";
                System.out.println("O bot deu um tiro na agua!");
                return true;
            } else if (Objects.equals(tablePlayer1[coordinates[0]][coordinates[1]], "| N |")){
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
        System.out.println("|                        COMBATE COMEÇOU  ☠️                             |");
        System.out.println("----------------------------------------------------------------------------");
        String line;
        while (Objects.equals(checkWinner(), "")) {

            if(turn%2==0){
                System.out.println("Jogada de "+playerName);

                showTable();

                int[] coordinates= new int[2];

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

            } else {

                System.out.println("Jogada do Bot");

                int[] coordinates = new int[2];
                coordinates[0] = generateRandon.nextInt(2) ; //Para testar é só reduzir o range do bot
                coordinates[1] = generateRandon.nextInt(2) ;//Para testar é só reduzir o range do bot

                while(!shoot(coordinates)) {

                    coordinates[0] = generateRandon.nextInt(2);
                    coordinates[1] = generateRandon.nextInt(2);

                }

            }
            turn ++;
            System.out.println("navios do bot: "+player2Ships);
        }

        System.out.println("O vencedor é o(a): "+checkWinner());


    }

    private static String checkWinner(){
        if (player1Ships == 0 ) {
            return "Bot";
        }

        if (player2Ships == 0) {
            return playerName;
        }

        return "";
    }
}