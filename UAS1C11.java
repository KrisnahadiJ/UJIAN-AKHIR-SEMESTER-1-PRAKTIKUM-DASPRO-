import java.util.Scanner;

public class UAS1C11 {
    static String[] namaTim11;
    static int[][] skorTim11;
    static int[] totalskor11;
    static int jumlahTim11;
    static final int BONUS_BUFF = 11;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan;

        do {
            tampilkanMenu();
            pilihan = validasiInputAngka(input, 1, 4);

            switch (pilihan) {
                case 1:
                    inputDataSkor(input);
                    break;
                case 2:
                    tampilkanTabelSkor();
                    break;
                case 3:
                    tentukanJuara();
                    break;
                case 4:
                    System.out.println("Terima kasih! Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 4);

        input.close();
    }

    static void tampilkanMenu() {
        System.out.println("11_Krisnahadi Jayawardana_SIB 1C_");
        System.out.println("============================================================");
        System.out.println("======================== MENU UTAMA ========================");
        System.out.println("============================================================");
        System.out.println("1. Input Data Skor Tim");
        System.out.println("2. Tampilkan Tabel Skor");
        System.out.println("3. Tentukan Juara");
        System.out.println("4. Keluar");
        System.out.print("Pilih menu (1-4): ");
    }

    static void inputDataSkor(Scanner input) {
        int duaDigitTerakhirNIM = 1;
        jumlahTim11 = (duaDigitTerakhirNIM % 3) + 4;
        System.out.println("Jumlah tim adalah: " + jumlahTim11);

        namaTim11 = new String[jumlahTim11];
        skorTim11 = new int[jumlahTim11][2];
        totalskor11 = new int[jumlahTim11];

        for (int i = 0; i < jumlahTim11; i++) {
            System.out.print("Masukkan nama tim ke-" + (i + 1) + ": ");
            namaTim11[i] = input.next();
            for (int j = 0; j < 2; j++) {
                System.out.print("Masukkan skor " + namaTim11[i] + " untuk Level " + (j + 1) + ": ");
                skorTim11[i][j] = validasiInputAngka(input, 0, 100);
                if (j == 0 && skorTim11[i][j] < 35) {
                    skorTim11[i][j] = 0;
                    System.out.println("Skor Level 1 kurang dari 35, dianggap 0.");
                }
            }

            totalskor11[i] = skorTim11[i][0] + skorTim11[i][1];

            if (skorTim11[i][0] > 50 && skorTim11[i][1] > 50) {
                totalskor11[i] += BONUS_BUFF;
                System.out.println("Bonus Buff Kemenangan +" + BONUS_BUFF + " ditambahkan!");
            }

            if (totalskor11[i] % 2 == 0) {
                totalskor11[i] -= 15;
                System.out.println("Total skor genap, dikurangi 15 poin.");
            }
        }
    }

    static void tampilkanTabelSkor() {
        if (namaTim11 == null) {
            System.out.println("Data belum dimasukkan!");
            return;
        }
    
        System.out.println("=========================================================");
        System.out.println("================== TABEL SKOR TURNAMEN ==================");
        System.out.println("=========================================================");
        System.out.printf("%-20s%-15s%-15s%-15s\n", "Nama Tim", "Level 1", "Level 2", "Total Skor");
        System.out.println("---------------------------------------------------------");
    
        for (int i = 0; i < jumlahTim11; i++) {
            if (skorTim11[i][0] < 35) skorTim11[i][0] = 0;
    
            totalskor11[i] = skorTim11[i][0] + skorTim11[i][1];
    
            if (totalskor11[i] % 2 == 0) totalskor11[i] -= 15;
    
            if (skorTim11[i][0] > 50 && skorTim11[i][1] > 50) {
                totalskor11[i] += BONUS_BUFF; // Bonus buff
            }
    
            // Tampilkan data dengan format rapi
            System.out.printf("%-20s%-15d%-15d%-15d\n", 
                              namaTim11[i], 
                              skorTim11[i][0], 
                              skorTim11[i][1], 
                              totalskor11[i]);
        }
    }

    static void tentukanJuara() {
        if (namaTim11 == null) {
            System.out.println("Tidak ada data yang bisa ditampilkan.");
            return;
        }

        int skorTertinggi = totalskor11[0];
        String juara = namaTim11[0];
        boolean seri = false;

        for (int i = 1; i < jumlahTim11; i++) {
            if (totalskor11[i] > skorTertinggi) {
                skorTertinggi = totalskor11[i];
                juara = namaTim11[i];
                seri = false;
            } else if (totalskor11[i] == skorTertinggi) {
                seri = true;
            }
        }

        if (seri) {
            System.out.println("\nTurnamen berakhir seri.");
            System.out.println("Tim terbaik adalah Krisna");
        } else {
            System.out.println("Selamat kepada Tim " + juara + " yang telah memenangkan kompetisi!");
        }
    }

    static int validasiInputAngka(Scanner input, int min, int max) {
        int nilai;
        while (true) {
            if (input.hasNextInt()) {
                nilai = input.nextInt();
                if (nilai >= min && nilai <= max) {
                    return nilai;
                }
            }
            System.out.print("Input tidak valid! Masukkan angka antara " + min + " - " + max + ": ");
            input.nextLine();
        }
    }
}