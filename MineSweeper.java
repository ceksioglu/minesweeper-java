package minesweeper;

import java.util.Scanner;

public class MineSweeper {
    // Mayınların bulunduğu gerçek harita ve kullanıcının gördüğü harita
    String[][] mineMap;
    String[][] displayMap;
    int rows, cols; // Haritanın satır ve sütun sayısı
    Scanner scanner = new Scanner(System.in); // Kullanıcıdan giriş almak için scanner nesnesi

    // Constructor: Oyun başlatılırken satır ve sütun sayıları alınır ve haritalar initialize edilir
    public MineSweeper(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        mineMap = new String[rows][cols];
        displayMap = new String[rows][cols];
        initializeMap();
    }

    private void initializeMap() {
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Matris boyutunu girin: ");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        if (rows < 2 || cols < 2) { // Eğer girilen boyutlar 2x2'den küçükse, uyarı ver ve programı sonlandır
            System.out.println("Matris boyutu en az 2x2 olmalıdır.");
            return;
        }
        MineSweeper newGame = new MineSweeper(rows, cols);
        //newGame.playGame(); // Oyunu başlat
    }



}
