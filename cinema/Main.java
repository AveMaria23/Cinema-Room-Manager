package com.cinema;

import java.text.DecimalFormat;
import java.util.Scanner;

class Main {
    static int purchasedTickets = 0;
    static int allSeats;
    static int currentIncome;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seat = scanner.nextInt();
        allSeats = row*seat;

        char[][] hall = new char[row+1][seat+1];
        hall[0][0] = ' ';
        for (int i = 1; i < row + 1; i++) {
            hall[i][0] = (char)(i + '0');
        }
        for (int i = 1; i < seat + 1; i++) {
            hall[0][i] = (char)(i + '0');
        }
        for (int i = 1; i < row + 1; i++) {
            for (int j = 1; j < seat + 1; j++) {
                hall[i][j] = 'S';
            }
        }


        boolean menu = true;

        while (menu == true) {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            if (choice == 0 || choice == 1 || choice ==2 || choice == 3) {
                switch (choice) {
                    case 1:
                        show(row, seat, hall);
                        break;
                    case 2:
                        buy(row, seat, hall, scanner);
                        break;
                    case 3:
                        statistics(row, seat);
                        break;
                    case 0:
                    default:
                        menu = false;
                        break;
                }
            }
            else {
                System.out.println("Wrong input!");
            }
        }
    }


    public static void show(int row, int seat, char[][] hall) {
        System.out.println();
        System.out.println("Cinema:");

        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= seat; j++) {
                System.out.print(hall[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void buy(int row, int seat, char[][] hall, Scanner scanner) {
        int row1;
        int seat1;
        boolean buying = true;
        while (buying) {
            System.out.println();
            System.out.println("Enter a row number:");
            row1 = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seat1 = scanner.nextInt();
            if (row1 > row || seat1 > seat) {
                System.out.println("Wrong input!");
            } else if (hall[row1][seat1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                buying = false;
                ++purchasedTickets;
                System.out.println();
                int price;
                if (seat * row <= 60) {
                    price = 10;
                } else {
                    if (row % 2 == 0) {
                        if (row1 <= row / 2) {
                            price = 10;
                        } else {
                            price = 8;
                        }
                    } else {
                        if (row1 < row / 2 + 1) {
                            price = 10;
                        } else {
                            price = 8;
                        }
                    }
                }
                currentIncome += price;
                System.out.println("Ticket price: $" + price);
                System.out.println("");
                hall[row1][seat1] = 'B';

            }
        }
    }

    public static void statistics(int row, int seat){
        double percentage = ((double)purchasedTickets/(double)allSeats)*(double)100;
        DecimalFormat df = new DecimalFormat("0.00");
        String result = df.format(percentage);
        int totalIncome;
        if (allSeats <= 60){
            totalIncome = allSeats*10;
        }
        else {
            if (row % 2 != 0){
                totalIncome = ((row/2)*seat)*10 + ((row/2 + 1)*seat)*8;
            }
            else {
                totalIncome = (row*seat/2)*10 + (row*seat/2)*8;
            }
        }

        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.println("Percentage: " + result + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

}