// import pustaka
import java.util.Random; //menghasilkan angka acak, dipakai untuk icon
import java.util.Scanner; //menerima input pengguna

// deklarasi properti yang  informasi permainan seperti ukuran map, posisi pemain, status kesehatan. 
public class dietgame {
    static final int SIZE = 20;
    static char[][] map = new char[SIZE][SIZE]; 
    static int playerX = 7; 
    static int playerY = 7;
    static int muscleMass = 0;
    static int weight = 55;
    static int sugar = 0;
    static int fat = 0;
    static int langkah = 0;

    //Method utama yang menjalankan permainan
    public static void main(String[] args) {
        initializeMap();
        printMap();

        //scanner untuk input arah menggunakan W A S D , dan menampilkan keterangan icon
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println (" ======================= value tiap icon =======================");  
            System.out.println (" icon R = Run ; muscle mass + 1 weight -1 sugar + 0 fat +0 ");
            System.out.println( " icon S = Sport ; muscle mass + 2 weight -1 sugar + 0 fat -1  ");
            System.out.println (" icon V = Vegetable and fruit  ; muscle mass + 0 weight +0 sugar -1 fat -1");
            System.out.println (" icon D = Sweet drink  ; muscle mass + 0 weight +2 sugar +2 fat +0");
            System.out.println (" icon F = Fast food ; muscle mass + 0 weight +0 sugar +3 fat +2 ");
            System.out.println ("Enter direction (W/A/S/D): ");
            char direction = scanner.next().charAt(0);

            movePlayer(direction);
            checkIcon();
            checkStatus();
            printMap();

            //logika pengkodian untuk menentukan menang dan kalah
            if (sugar > 5) {
                System.out.println("you have diabetes !! because your sugar levels are too high! let's live healthy from now, dear 𐙚₊˚⊹♡");
                break;
            } else if (fat > 5) {
                System.out.println("you have cholesterol !! because your fat are too high! let's live healthy from now, dear 𐙚₊˚⊹♡");
                break;
            } else if (weight > 60) {
                System.out.println("you have obesytas !!  because your weight too fat! let's live healthy from now, dear 𐙚₊˚⊹♡");
                break;
            } else if (weight <= 50 && muscleMass == 10 ) {
                System.out.println("YOU WIN! because you has diet and has healty!");
                break;
            }
        }
        scanner.close();
    }

//method untuk meninialisasi map dengan ".' dan size x size yang telah ditentukan
    static void initializeMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = '.';
            }
        }
        // untuk  menempatkan / memunculkan tiga ikon acak ('S', 'R', 'V', 'D', 'F') pada lokasi acak di peta
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int iconX = random.nextInt(SIZE);
            int iconY = random.nextInt(SIZE);
            char icon = getRandomIcon();
            map[iconY][iconX] = icon;
        }
    }
    // method i mengembalikan karakter acak dari array karakter ikon yang telah ditentukan.
    static char getRandomIcon() {
        char[] icons = {'S', 'R', 'V', 'D', 'F'};
        Random random = new Random();
        return icons[random.nextInt(icons.length)];
    }
    //method untuk mencetak peta dalam bentuk matriks, 
    //menampilkan posisi user/player dengan karakter '&' dan ikon lainnya sesuai dengan lokasi mereka di peta.
    static void printMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == playerY && j == playerX) {
                    System.out.print('&' + " ");
                } else {
                    System.out.print(map[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    //method untuk menempatkan icon secara acak ketika muncul
    static void munculDitempatRandom(){
         Random random = new Random();
        
            int iconX = random.nextInt(SIZE);
            int iconY = random.nextInt(SIZE);
            char icon = getRandomIcon();
            map[iconY][iconX] = icon;
    }
    //method untuk arah gerak user
    static void movePlayer(char direction) {
        switch (direction) {
            case 'W':
                if (playerY > 0) {
                    playerY--;
                }
                break;
            case 'A':
                if (playerX > 0) {
                    playerX--;
                }
                break;
            case 'S':
                if (playerY < SIZE - 1) {
                    playerY++;
                }
                break;
            case 'D':
                if (playerX < SIZE - 1) {
                    playerX++;
                }
                break;
        }
        //Setiap langkah ke-3, ikon baru muncul di tempat acak
        langkah++;
        if(langkah%3 == 0){
            munculDitempatRandom();
        }
    }
    //method mengecek ikon yang ditemui oleh pemain dan mengubah status kesehatan (muscleMass, weight, sugar, fat) berdasarkan ikon yang diambil
    //ini logic mathematic
    static void checkIcon() {
        char icon = map[playerY][playerX];
        switch (icon) {
            case 'S':
                muscleMass += 2;
                weight -= 1;
                fat -= 1;
                break;
            case 'R':
                muscleMass += 1;
                weight -= 1;
                // No effect on fat and sugar
                break;
            case 'V':
                fat -= 1;
                sugar -=1;
                // No effect on muscle mass and weight
                break;
            case 'D':
                weight += 2;
                sugar += 2;
                // No effect on muscle mass and fat
                break;
            case 'F':
                fat += 3;
                sugar += 2;
                // No effect on muscle mass and weight
                break;
        }
        map[playerY][playerX] = '.';
    }
    //method untum meriksa  status kesehatan saat ini dan menampilkannya ke layar
    static void checkStatus() {
        if (sugar > 5 || fat > 5 || weight > 60) {
            return;
        }
        System.out.println("Status:");
        System.out.println("Muscle Mass: " + muscleMass);
        System.out.println("Weight: " + weight);
        System.out.println("Sugar: " + sugar);
        System.out.println("Fat: " + fat);
    }
}
