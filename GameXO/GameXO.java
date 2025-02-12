import java.util.Scanner;

public class GameXO {
    Scanner sc = new Scanner(System.in);

    String name;
    String newName;
    int parol;

    final String X = " x ";
    final String O = " o ";

    int index;
    int count = 9;

    boolean isX = true;
    boolean isError = false;
    boolean isNotEmpty = false;;
    boolean isMultiPlayer = true;
    boolean gameStatus = true;

    final String[][] board = {
            { "   ", "|", "   ", "|", "   " }, // 0- 0 2 4
            { "---", "+", "---", "+", "---" },
            { "   ", "|", "   ", "|", "   " }, // 2- 0 2 4
            { "---", "+", "---", "+", "---" },
            { "   ", "|", "   ", "|", "   " }, // 4- 0 2 4

    };

    public GameXO() {
        System.out.println("XO oyununa xos gelmisiniz.");
        System.out.print("Adiniz:");
        name = sc.nextLine();
        System.out.print("Parol:");
        parol = sc.nextInt();
        Clear();
        Menu();
    }

    public void Clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void Menu() {
        System.out.println(name + " xos gelmisiniz.");
        System.out.println("Istifadeci adinizi ve parolunuzu qeyd edin:");
        System.out.print("AD:");
        sc.nextLine();
        String nameCheck = sc.nextLine();
        System.out.print("PAROL:");
        int password = sc.nextInt();
        

        if (name.equals(nameCheck) && parol == password) {
            Clear();
            System.out.println(name + "" + " xos geldiniz!");
            StartMenu();
        } else {
            System.out.println("Yanlis ad veya parol. Tekrar cehd edin.");
            Menu();
        }
    }

    public void StartMenu() {
        System.out.println("   Menu:\n");
        System.out.println("1)Adinizi deyisin\n2)Start\n0)Exit");
        System.out.print("Menu secin:");
        int menu = sc.nextInt();
        SelectedMenu(menu);
    }

    public void SelectedMenu(int menu) {
        switch (menu) {
            case 1:
                Clear();
                NameChange();
                Menu();
                break;
            case 2:
                Start();
                break;
            case 0:
                Exit();
        }
    }

    public void Exit() {
        Clear();
        System.out.println("Cixmaq istediyinize eminsiniz?\nYES! OR NO!");
        // sc.nextLine();
        String yn = sc.next().trim();
        if (yn.equalsIgnoreCase("Yes")) {
            System.out.println("Exit...");
            System.exit(0);
        } else if (yn.equalsIgnoreCase("No")) {
            Clear();
            StartMenu();
        }
    }

    public void NameChange() { // yoxla gor problem var?
        System.out.println("Yeni adinizi yazin!");
        sc.nextLine();
        newName = sc.nextLine();
        name = newName;
        System.out.println(newName + " xos geldiniz!");
    }

    public void Table() {
        System.out.println("***********");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println("***********");
    }

    public void Start() {
        Clear();
        System.out.println("1)TwoPlayer\n2)OnePlayer\n0)Back");
        System.out.print("\nSecim edin:");
        int gamer = sc.nextInt();

        switch (gamer) {
            case 1:
                isMultiPlayer=true;
                RunMultiPlayer();
                break;
            case 2:
                isMultiPlayer = false;
                RunOnePlayer();
                break;
            default:
                Clear();
                StartMenu();
                break;
        }

    }

    public void RunMultiPlayer() {
        Clear();
        Table();
        SelectTable();
    }

    public void RunOnePlayer() {
        Clear();
        Table();
        
        if (checkWinner(isX ? "X" : "O")) {
            GameOver();
        } else if (isEnd()) {
            End();
        }
        if(!isX){
            System.out.println("Secim edin:");
            index = sc.nextInt();
            SelectIndex();
        }else{
            RandomIndex();
            SelectIndex();
        }    
            isX = !isX;
            RunOnePlayer();
    
}
    
    
    public void RandomIndex() {
        do {
            index=  (int) (1 + Math.random() * 9);
        } while (!SelectRandomIndex(index).equals("   "));
    }

    public void SelectTable() { // BURA baxaq ne duzgun getmir?
        System.out.println();
        if (isError) {
            System.out.println("Yanlis secim! Yalniz 1-9 araliginda secin!");
            isError = false;
        }
        if (isNotEmpty) {
            System.out.println("Bos damani secin!");
            isNotEmpty = false;
        }
        System.out.print(isX ? "X oyuncusu (1-9) arasi dama secin:" : "O oyuncusu (1-9) arasi dama secin:");
        index =sc.nextInt();

        SelectIndex();
        isX = !isX;

        if (checkWinner(isX ? "X" : "O")) {
            GameOver();
        } else if (isEnd()) {
            End();
        } else
            // Start();
            RunMultiPlayer();
    }
    


    public String SelectRandomIndex(int index) {
        switch (index) {
            case 1:
                return board[0][0]; 
            case 2:
                return board[0][2];
            case 3:
                return board[0][4];
            case 4:
                return board[2][0];
            case 5:
                return board[2][2];
            case 6:
                return board[2][4];
            case 7:
                return board[4][0];
            case 8:
                return board[4][2];
            case 9:
                return board[4][4];
            default:
            return "";
            
        }
    }

    public void SelectIndex() {
        switch (index) {
            case 1:
                if (board[0][0].equals("   ")) {
                    board[0][0] = isX ? X : O;
                    count--;
                } else {
                    isX = !isX;
                    isNotEmpty = true;
                }
                break;
            case 2:
                if (board[0][2].equals("   ")) {
                    board[0][2] = isX ? X : O;
                    count--;
                } else {
                    isX = !isX;
                    isNotEmpty = true;
                }
                break;
            case 3:
                if (board[0][4].equals("   ")) {
                    board[0][4] = isX ? X : O;
                    count--;
                } else {
                    isX = !isX;
                    isNotEmpty = true;
                }
                break;
            case 4:
                if (board[2][0].equals("   ")) {
                    board[2][0] = isX ? X : O;
                    count--;
                } else {
                    isX = !isX;
                    isNotEmpty = true;
                }
                break;
            case 5:
                if (board[2][2].equals("   ")) {
                    board[2][2] = isX ? X : O;
                    count--;
                } else {
                    isX = !isX;
                    isNotEmpty = true;
                }
                break;
            case 6:
                if (board[2][4].equals("   ")) {
                    board[2][4] = isX ? X : O;
                    count--;
                } else {
                    isX = !isX;
                    isNotEmpty = true;
                }
                break;
            case 7:
                if (board[4][0].equals("   ")) {
                    board[4][0] = isX ? X : O;
                    count--;
                } else {
                    isX = !isX;
                    isNotEmpty = true;
                }
                break;
            case 8:
                if (board[4][2].equals("   ")) {
                    board[4][2] = isX ? X : O;
                    count--;
                } else {
                    isX = !isX;
                    isNotEmpty = true;
                }
                break;
            case 9:
                if (board[4][4].equals("   ")) {
                    board[4][4] = isX ? X : O;
                    count--;
                } else {
                    isX = !isX;
                    isNotEmpty = true;
                }
                break;
            default:
                isX = !isX;
                isError = true;
                break;
        }
    }

    public boolean checkWinner(String player) {
        if (!board[0][0].equals("   ") && (board[0][0].equals(board[0][2]) && board[0][2].equals(board[0][4]))) {
            return true;
        } else if (!board[2][0].equals("   ") && (board[2][0].equals(board[2][2]) && board[2][2].equals(board[2][4]))) {
            return true;
        } else if (!board[4][0].equals("   ") && (board[4][0].equals(board[4][2]) && board[4][2].equals(board[4][4]))) {
            return true;
        } else if (!board[0][0].equals("   ") && (board[0][0].equals(board[2][0]) && board[2][0].equals(board[4][0]))) {
            return true;
        } else if (!board[0][2].equals("   ") && (board[0][2].equals(board[2][2]) && board[2][2].equals(board[4][2]))) {
            return true;
        } else if (!board[0][4].equals("   ") && (board[0][4].equals(board[2][4]) && board[2][4].equals(board[4][4]))) {
            return true;
        } else if (!board[0][0].equals("   ") && (board[0][0].equals(board[2][2]) && board[2][2].equals(board[4][4]))) {
            return true;
        } else if (!board[4][0].equals("   ") && (board[4][0].equals(board[2][2]) && board[2][2].equals(board[0][4]))) {
            return true;
        }
        return false;
    }

    public void GameOver() {
        Clear();
        Table();
        isX = !isX;
        System.out.println(isX ? "X oyuncusu qalib geldi" : "O oyuncusu qalib geldi");
        // System.exit(0);
    }

    public boolean isEnd() {
        return count <= 0 ? true : false;
    }

    public void End() {
        Clear();
        Table();
        System.out.println("Oyun bitdi.Hec bir oyuncu qalib gelmedi.");
    }
}
