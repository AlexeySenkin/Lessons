package lesson4;


import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;


public class TicTakToe {
    private static final String AI_WIN_MSG = "Победил компьютер!";
    private static final String HUMAN_WIN_MSG = "Победил человек!";
    private static final String DRAW_MSG = "Ничья!";

    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static final char DOT_EMPTY = '•';

    private static final int SIZE = 5;
    private static final int WINCOUNT = 5;
    private static final boolean AI_ON = true;

    private static Scanner scanner;
    private static char[][] map;
    private static Random random;


    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        random = new Random();

        initMap();
        printMap();
        startGameLoop();
    }

    private static void startGameLoop() {
        while (true) {
            humanTurn();
            printMap();
            if (checkEndGame(DOT_X)) {
                break;
            }

            aiTurn();
            printMap();
            if (checkEndGame(DOT_O)) {
                break;
            }
        }
    }

    private static boolean checkEndGame(char symbol) {
        if (isMapFull()) {
            System.out.println(DRAW_MSG);
            return true;
        }

        if (isWin(symbol)) {
            System.out.println(getWinMessageBy(symbol));
            return true;
        }

        return false;
    }

    private static String getWinMessageBy(char symbol) {
/*
        if (symbol == DOT_X) {
            return HUMAN_WIN_MSG;
        } else {
            return AI_WIN_MSG;
        }

*/
        return symbol == DOT_X ? HUMAN_WIN_MSG : AI_WIN_MSG;
    }

    private static boolean isWin(char symbol) {
        int countRow = 0;
        int countCol = 0;
        int countDiagOne = 0;
        int countDiagTwo = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == symbol) {
                    countRow++;
                } else {
                    countRow = 0;
                }
                if (map[j][i] == symbol) {
                    countCol++;
                } else {
                    countCol = 0;
                }
                if (countRow == WINCOUNT || countCol == WINCOUNT) {
                    System.out.println("col || row");
                    return true;
                }
            }
            countRow = 0;
            countCol = 0;
        }
        for (int i = 0; i < SIZE; i++) {
            if (map[i][i] == symbol) {
                countDiagOne++;
            } else {
                countDiagOne = 0;
            }
            if (map[i][SIZE - 1 - i] == symbol) {
                countDiagTwo++;
            } else {
                countDiagTwo = 0;
            }
            if (countDiagOne == WINCOUNT || countDiagTwo == WINCOUNT) {
                System.out.println("diagonal");
                return true;
            }
        }
        return false;
    }

    private static boolean isMapFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (isEmptyCell(row, col)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void printArray(int[][] arr) {
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    public static int getSumArray(int[] arr) {
        int result = 0;
        for (int v : arr) {
            result += v;
        }
        return result;
    }

    private static void aiTurn() {
        int row = 0;
        int col = 0;
        int[] userDotCountRow = new int[SIZE];
        int[] userDotCountCol = new int[SIZE];
        int[][] userDotCountDiag = new int[2][1];

        int maxUserDotRow = 0;
        int maxUserDotCol = 0;
        int maxUserDiag = 0;
        int maxUserDotRowValue = 0;
        int maxUserDotColValue = 0;
        int maxUserDiagValue = 0;

        if (AI_ON) {
            System.out.println("Ai ON");

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (map[i][j] == DOT_X) {
                        userDotCountRow[i]++;
                    } else if (map[i][j] == DOT_O) {
                        userDotCountRow[i] = 0;
                        break;
                    }
                }
                for (int j = 0; j < SIZE; j++) {
                    if (map[j][i] == DOT_X) {
                        userDotCountCol[i]++;
                    } else if (map[j][i] == DOT_O) {
                        userDotCountCol[i] = 0;
                        break;
                    }
                }
            }
            for (int i = 0; i < SIZE; i++) {
                if (map[i][i] == DOT_X) {
                    userDotCountDiag[0][0]++;
                }
                if (map[i][SIZE - 1 - i] == DOT_X) {
                    userDotCountDiag[1][0]++;
                }
            }
            for (int i = 0; i < SIZE; i++) {
                if (i == 0) {
                    maxUserDotRow = i;
                    maxUserDotRowValue = userDotCountRow[i];
                } else if (userDotCountRow[i] > userDotCountRow[i - 1]) {
                    maxUserDotRow = i;
                    maxUserDotRowValue = userDotCountRow[i];
                }
                if (i == 0) {
                    maxUserDotCol = i;
                    maxUserDotColValue = userDotCountCol[i];
                } else if (userDotCountCol[i] > userDotCountCol[i - 1]) {
                    maxUserDotCol = i;
                    maxUserDotColValue = userDotCountCol[i];
                }
            }

            if (userDotCountDiag[0][0] < userDotCountDiag[1][0]) {
                maxUserDiag = 0;
            } else {
                maxUserDiag = 1;
            }

        } else {
            System.out.println("Ai OFF");
        }
        int repeatCount = 0;
        do {
            if (AI_ON) {
                if (repeatCount <= SIZE) {
                    if (maxUserDotRowValue >= maxUserDotColValue) {
                        if (maxUserDotRowValue >= userDotCountDiag[maxUserDiag][0]) {
                            row = maxUserDotRow;
                            col = random.nextInt(SIZE);
                        } else {
                            //TODO блокировка по диагонали
                            if (maxUserDiag == 0) {
                                row = maxUserDotRow;
                                col = row;
                            } else {

                            }
                        }
                    } else {
                        if (maxUserDotColValue >= userDotCountDiag[maxUserDiag][0]) {
                            row = random.nextInt(SIZE);
                            col = maxUserDotCol;
                        } else {
                            //TODO блокировка по диагонали
                        }
                    }
                    repeatCount++;
                } else {
                    repeatCount = 0;
                    row = random.nextInt(SIZE);
                    col = random.nextInt(SIZE);
                }
            } else {
                row = random.nextInt(SIZE);
                col = random.nextInt(SIZE);
            }
        } while (!isEmptyCell(row, col));

        System.out.println("Ход компьютера: row= " + (row + 1) + ", col= " + (col + 1));
        map[row][col] = DOT_O;

    }

    private static void humanTurn() {
        System.out.println("Введите координаты row col: ");
        int row = 0;
        int col = 0;
        do {
            row = readIndex();
            col = readIndex();


            if (!checkRange(row) || !checkRange(col)) {
                System.out.println("координаты должны быть в диапазоне от 1 до " + SIZE);
                continue;
            }

            if (isEmptyCell(row - 1, col - 1)) {
                break;
            } else {
                System.out.println("Клетка уже занята!");
            }
        } while (true);

        map[row - 1][col - 1] = DOT_X;
    }

    private static boolean isEmptyCell(int row, int col) {
        return map[row][col] == DOT_EMPTY;
    }

    private static int readIndex() {
        while (!scanner.hasNextInt()) {
            System.out.println("Координаты должны иметь целочисленное значение!");
            scanner.next();
        }

        return scanner.nextInt();
    }

    private static boolean checkRange(int index) {
        return index >= 1 && index <= SIZE;
    }

    private static void initMap() {
        map = new char[SIZE][SIZE];

        for (int row = 0; row < map.length; row++) {
            Arrays.fill(map[row], DOT_EMPTY);
        }
    }

    private static void printMap() {
        printMapHeader();
        printMapState();
        System.out.println();
    }

    private static void printMapState() {
        for (int row = 0; row < map.length; row++) {
            printRowNumber(row);
            printRow(map[row]);
            System.out.println();
        }
    }

    private static void printRow(char[] chars) {
        for (int col = 0; col < chars.length; col++) {
            System.out.print(chars[col] + " ");
        }
    }

    private static void printRowNumber(int rowNumber) {
        System.out.print((rowNumber + 1) + " ");
    }

    private static void printMapHeader() {
        for (int col = 0; col <= SIZE; col++) {
            System.out.print(col + " ");
        }
        System.out.println();
    }
}
