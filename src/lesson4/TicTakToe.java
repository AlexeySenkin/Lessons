package lesson4;


import java.util.Arrays;

public class TicTakToe {

    public static final int SIZE = 3;
    public static final char DOT_X = 'X';
    public static final char DOT_O = '0';
    public static final char DOT_EMPTY = '*';

    public static char[][] map ;


    public static void main(String[] args) {
        map = new char[SIZE][SIZE];
        for (char[] chars : map) {
            Arrays.fill(chars, DOT_EMPTY);
        }

        for (char[] chars : map) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");

            }
            System.out.println();

        }
    }
}
