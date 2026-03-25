import java.util.ArrayList;
import java.util.Scanner;

public class BookManagement {
    private static ArrayList<Book> books = new ArrayList<>();
    private static int nextId = 1;   // Tự động tăng ID

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n========== MENU QUAN LY SACH ==========");
            System.out.println("1. Them 1 cuon sach");
            System.out.println("2. Xoa 1 cuon sach");
            System.out.println("3. Thay doi (sua) 1 cuon sach");
            System.out.println("4. Xuat thong tin tat ca cac cuon sach");
            System.out.println("5. Tim cuon sach co tua de chua chu \"Lap trinh\" (khong phan biet hoa thuong)");
            System.out.println("6. Lay sach: Nhap vao 1 so K va gia P. Lay toi da K cuon sach co gia <= P");
            System.out.println("7. Nhap vao 1 danh sach cac tac gia tu ban phim. Cho biet tat ca cuon sach cua nhung tac gia nay");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            choice = sc.nextInt();
            sc.nextLine(); // Xóa dòng new line

            switch (choice) {
                case 1:
                    addBook(sc);
                    break;
                case 2:
                    deleteBook(sc);
                    break;
                case 3:
                    updateBook(sc);
                    break;
                case 4:
                    displayAllBooks();
                    break;
                case 5:
                    searchByTitleContainsLapTrinh();
                    break;
                case 6:
                    getBooksByPriceLimit(sc);
                    break;
                case 7:
                    searchBooksByAuthors(sc);
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh. Tam biet!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai.");
            }
        } while (choice != 0);

        sc.close();
    }

    // 1. Them sach
    private static void addBook(Scanner sc) {
        System.out.print("Nhap ten sach: ");
        String title = sc.nextLine();
        System.out.print("Nhap tac gia: ");
        String author = sc.nextLine();
        double price = 0;
        while (true) {
            try {
                System.out.print("Nhap don gia: ");
                price = sc.nextDouble();
                sc.nextLine(); // Consume newline
                break;
            } catch (Exception e) {
                System.out.println("Gia khong hop le! Vui long nhap so.");
                sc.nextLine(); // Clear invalid input
            }
        }

        Book book = new Book(nextId++, title, author, price);
        books.add(book);
        System.out.println("Them sach thanh cong! Ma sach: " + book.getId());
    }

    // 2. Xoa sach theo ID
    private static void deleteBook(Scanner sc) {
        int id = 0;
        while (true) {
            try {
                System.out.print("Nhap ma sach can xoa: ");
                id = sc.nextInt();
                sc.nextLine(); // Consume newline
                break;
            } catch (Exception e) {
                System.out.println("Ma sach khong hop le! Vui long nhap so nguyen.");
                sc.nextLine(); // Clear invalid input
            }
        }
        boolean found = false;

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.remove(i);
                System.out.println("Xoa sach thanh cong!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay sach co ma " + id);
        }
    }

    // 3. Sua sach
    private static void updateBook(Scanner sc) {
        int id = 0;
        while (true) {
            try {
                System.out.print("Nhap ma sach can sua: ");
                id = sc.nextInt();
                sc.nextLine(); // Consume newline
                break;
            } catch (Exception e) {
                System.out.println("Ma sach khong hop le! Vui long nhap so nguyen.");
                sc.nextLine(); // Clear invalid input
            }
        }

        for (Book book : books) {
            if (book.getId() == id) {
                System.out.print("Nhap ten sach moi: ");
                book.setTitle(sc.nextLine());
                System.out.print("Nhap tac gia moi: ");
                book.setAuthor(sc.nextLine());
                double price = 0;
                while (true) {
                    try {
                        System.out.print("Nhap don gia moi: ");
                        price = sc.nextDouble();
                        sc.nextLine(); // Consume newline
                        break;
                    } catch (Exception e) {
                        System.out.println("Gia khong hop le! Vui long nhap so.");
                        sc.nextLine(); // Clear invalid input
                    }
                }
                book.setPrice(price);
                System.out.println("Cap nhat sach thanh cong!");
                return;
            }
        }
        System.out.println("Khong tim thay sach co ma " + id);
    }

    // 4. Hien thi tat ca sach
    private static void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("Danh sach sach trong!");
            return;
        }
        System.out.println("\nDanh sach tat ca sach:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // 5. Tim sach chua "Lap trinh" (khong phan biet hoa thuong)
    private static void searchByTitleContainsLapTrinh() {
        boolean found = false;
        System.out.println("\nCac sach co tua de chua \"Lap trinh\":");

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains("lap trinh")) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay sach nao chua \"Lap trinh\" trong ten.");
        }
    }

    // 6. Lay toi da K sach co gia <= P
    private static void getBooksByPriceLimit(Scanner sc) {
        int K = 0;
        while (true) {
            try {
                System.out.print("Nhap so luong toi da K: ");
                K = sc.nextInt();
                sc.nextLine(); // Consume newline
                break;
            } catch (Exception e) {
                System.out.println("K khong hop le! Vui long nhap so nguyen.");
                sc.nextLine(); // Clear invalid input
            }
        }
        double P = 0;
        while (true) {
            try {
                System.out.print("Nhap gia P: ");
                P = sc.nextDouble();
                sc.nextLine(); // Consume newline
                break;
            } catch (Exception e) {
                System.out.println("P khong hop le! Vui long nhap so.");
                sc.nextLine(); // Clear invalid input
            }
        }

        System.out.println("\nDanh sach toi da " + K + " cuon sach co gia <= " + P + ":");

        int count = 0;
        for (Book book : books) {
            if (book.getPrice() <= P) {
                System.out.println(book);
                count++;
                if (count == K) break;
            }
        }
        if (count == 0) {
            System.out.println("Khong co sach nao thoa man dieu kien.");
        }
    }

    // 7. Tim sach theo danh sach tac gia nhap tu ban phim
    private static void searchBooksByAuthors(Scanner sc) {
        System.out.print("Nhap danh sach tac gia (cach nhau bang dau phay): ");
        String input = sc.nextLine();
        String[] authors = input.split(",");

        // Trim khoang trang cho tung tac gia
        for (int i = 0; i < authors.length; i++) {
            authors[i] = authors[i].trim();
        }

        System.out.println("\nCac sach cua nhung tac gia tren:");

        boolean found = false;
        for (Book book : books) {
            for (String author : authors) {
                if (book.getAuthor().equalsIgnoreCase(author)) {
                    System.out.println(book);
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("Khong tim thay sach nao cua cac tac gia nay.");
        }
    }
}