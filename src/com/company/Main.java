package com.company;

//A fazer

//Função para distribuir os submarinos nas tabelas de cada jogador
//Função de entrada de cada jogador
//Exibir o mapa  de tiros além do mapa próprio dos submarinos do jogador

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static String[][] tablePlayer1 = {{" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "}};
    private static String[][] tablePlayer2 = {{" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "}};
    private static int turn = 2;
    private static String player = "";
    private static int player1Ships = 0;
    private static int player2Ships = 10;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        while (Objects.equals(checkWinner(), "")) {
            showTable();
            turnAssistant();
        }
        showTable();
        System.out.println("O vencedor é "+checkWinner());

    }

    private static void showTable() {

        if (turn%2==0){
            for (String[] strings : tablePlayer1) {
                System.out.println(Arrays.toString(strings));
            }
        } else {
            for (String[] strings : tablePlayer2) {
                System.out.println(Arrays.toString(strings));
            }
        }
    }

    private static void turnAssistant() {
        int coordinates[] = new int[2];
        if (turn%2==0){
            player = "Jogador 1";
            System.out.println("Vez do "+player);
            System.out.println("Digite a linha: ");
            coordinates[0] = scanner.nextInt();
            System.out.println("Digite a coluna: ");
            coordinates[1] = scanner.nextInt();
            setPosition(coordinates);
            if (player1Ships >= 10){
                showTable();
                System.out.println("-------------------------------------");
                turn ++;
            }
        } else {
            player = "Jogador 2";
            System.out.println("Vez do "+player);
            System.out.println("Digite a linha: ");
            coordinates[0] = scanner.nextInt();
            System.out.println("Digite a coluna: ");
            coordinates[1] = scanner.nextInt();
            turn ++;
        }
    }

    public static void setPosition(int[] coordinates){
        if (tablePlayer1[coordinates[0]][coordinates[1]] == " "){
            tablePlayer1[coordinates[0]][coordinates[1]] = " N ";
            player1Ships++;
        }else {
            System.out.println("Esta posição esta ocupada");
        }
    }


    private static String checkWinner(){
        if (player1Ships == 0 && turn > 4) {
            return "Jogador 2";
        }

        if (player2Ships == 0 && turn > 4) {
            return "Jogador 1";
        }

        return "";
    }
}