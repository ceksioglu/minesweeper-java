package minesweeper;

import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    // Gerçek mayın yerlerinin ve oyuncunun gördüğü haritanın tutulduğu diziler
    String[][] mineMap;
    String[][] displayMap;
    int rows, cols; // Oyunun satır ve sütun sayısı
    Scanner scanner = new Scanner(System.in); // Kullanıcı girişlerini almak için scanner

    // Oyun başlatıldığında satır ve sütun sayıları alınır ve haritalar başlatılır
    public MineSweeper(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        mineMap = new String[rows][cols];
        displayMap = new String[rows][cols];
        initializeMap();
    }

    // Başlangıç haritalarını oluşturur ve mayınları yerleştirir
    private void initializeMap() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                displayMap[i][j] = "-";
                mineMap[i][j] = "0"; // Başlangıçta tüm hücreler "0" olarak ayarlanır
            }
        }
        createMines();
        calculateAdjacent();
        debugMapPrint();
    }

    // DEBUG amaçlı mayın haritasını yazdırır ( * Cuma en son istenen kriter * )
    private void debugMapPrint() {
        System.out.println("----DEBUG MAYIN HARITASI----:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(mineMap[i][j] + " ");
            }
            System.out.println(); // Satırdaki tüm hücreler yazdırıldıktan sonra yeni satıra geçer
        }
        System.out.println();
    }

    // Rastgele mayın yerleştirme
    private void createMines() {
        int mineCount = (rows * cols) / 4; // Toplam mayın sayısı
        Random rng = new Random();
        while (mineCount > 0) {
            int row = rng.nextInt(rows);
            int col = rng.nextInt(cols);
            if (!mineMap[row][col].equals("*")) {
                mineMap[row][col] = "*"; // Eğer hücrede mayın yoksa mayın yerleştir
                mineCount--;
            }
        }
    }

    /* Komşu mayın sayılarını hesaplar. Matris işlemleri, genellikle hesaplama yoğun olduğu için &
     O(rows x cols) doğrusal zaman karmaşıklığında olduğundan ilk başta bir kere hesaplayıp her kullanıcı girişinde tekrardan hesaplanmasına gerek kalmadan kodumuz hızlanıyor.
     En son mineMap yeni değerlerle güncellenir.*/
    private void calculateAdjacent() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!mineMap[row][col].equals("*")) {
                    int mineCount = 0;
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            int nextRow = row + i;
                            int nextCol = col + j;
                            if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {
                                if (mineMap[nextRow][nextCol].equals("*")) {
                                    mineCount++; // Komşu hücrede mayın varsa sayacı arttır
                                }
                            }
                        }
                    }
                    mineMap[row][col] = String.valueOf(mineCount); // Komşu mayın sayısını kaydet
                }
            }
        }
    }

    // Oyunu oynama döngüsü
    public void playGame() {
        boolean currentlyPlaying = true;
        while (currentlyPlaying) {
            printBoard();
            System.out.println("Satır ve sütun girin: ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            if (row >= 0 && row < rows && col >= 0 && col < cols) {
                if (displayMap[row][col].equals("-")) {
                    displayMap[row][col] = mineMap[row][col];  // Hücrenin gerçek değerini mineMap'ten çeker.
                    if (mineMap[row][col].equals("*")) {
                        printBoard();
                        System.out.println("BOOM! Oyun bitti. RIP");
                        break;
                    } else if (checkWin()) {
                        System.out.println("Tebrikler, oyunu kazandınız!");
                        break;
                    }
                } else {
                    System.out.println("Bu hücre daha önce seçildi, başka koordinat girin.");
                }
            } else {
                System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
            }
        }
    }

    // Oyun tahtasını yazdırır
    private void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(displayMap[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Oyunun kazanılıp kazanılmadığını kontrol eder
    private boolean checkWin() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mineMap[i][j].equals("0") && displayMap[i][j].equals("-")) {
                    return false; // Eğer açılmamış ve mayınsız bir hücre varsa oyun devam eder
                }
            }
        }
        return true; // Tüm güvenli hücreler açıldıysa oyun kazanılmıştır
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
        newGame.playGame(); // Oyunu başlat
    }
}
