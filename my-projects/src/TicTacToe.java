import java.util.*;

public class TicTacToe {
    static List<Integer> personMoveTrack = new ArrayList<>();
    static  List<Integer> computerMoveTrack = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);
    static char[][] gameBoard = {
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '},

    };

    public static void main(String[] args) {
        printBoard(gameBoard);
       while(winCheck().length() == 0){
           personMove(gameBoard);
           System.out.println(winCheck());
           if(winCheck().length() > 0){
               return;
           }
           System.out.println("Computer moved");
           computerMove(gameBoard);
           System.out.println(winCheck());
       }
    }

    public static void printBoard(char [][] gameBoard){
        for (int i=0; i<gameBoard.length; i++){
            for (int j=0; j<gameBoard[0].length; j++){
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
    }

    public static void personMove(char [][] gameBoard){
        char symbol = 'X';
        System.out.println("Enter numbers from 1 to 9 to make a move: ");
        int personMove = scanner.nextInt();
        if(isSpaceAvailable(gameBoard,personMove)){
            placePosition("person",personMove);
            printBoard(gameBoard);
            personMoveTrack.add(personMove);
        }else{
            System.out.println("Position not empty, enter again: ");
            personMove(gameBoard);
        }
    }
    public static void computerMove(char [][] gameBoard) {
        char symbol = 'O';
        Random randomNumber = new Random();
        int computerMove = randomNumber.nextInt(9) + 1;
        if (isSpaceAvailable(gameBoard, computerMove)) {
                placePosition("computer",computerMove);
                printBoard(gameBoard);
                computerMoveTrack.add(computerMove);
        }else{
            computerMove(gameBoard);
        }
    }

    public static boolean isSpaceAvailable(char [][] gameBoard, int move){
        if(move>=1 && move<=9) {
            switch (move) {
                case 1 -> {
                    return gameBoard[0][0] == ' ';
                }
                case 2 -> {
                    return gameBoard[0][2] == ' ';
                }
                case 3 -> {
                    return gameBoard[0][4] == ' ';
                }
                case 4 -> {
                    return gameBoard[2][0] == ' ';
                }
                case 5 -> {
                    return gameBoard[2][2] == ' ';
                }
                case 6 -> {
                    return gameBoard[2][4] == ' ';
                }
                case 7 -> {
                    return gameBoard[4][0] == ' ';
                }
                case 8 -> {
                    return gameBoard[4][2] == ' ';
                }
                case 9 -> {
                    return gameBoard[4][4] == ' ';
                }
            }
        }
        return false;
    }
    public static void placePosition(String player,int move){
        char symbol = player.equals("person")? 'X':'O';
        switch(move) {
            case 1 -> {
                gameBoard[0][0] = symbol;
            }
            case 2 -> {
                gameBoard[0][2] = symbol;
            }
            case 3 -> {
                gameBoard[0][4] = symbol;
            }
            case 4 -> {
                gameBoard[2][0] = symbol;
            }
            case 5 -> {
                gameBoard[2][2] = symbol;
            }
            case 6 -> {
                gameBoard[2][4] = symbol;
            }
            case 7 -> {
                gameBoard[4][0] = symbol;
            }
            case 8 -> {
                gameBoard[4][2] = symbol;
            }
            case 9 -> {
                gameBoard[4][4] = symbol;
            }
        }
    }
    public static String winCheck(){

        List firstRow = Arrays.asList(1,2,3);
        List secondRow = Arrays.asList(4,5,6);
        List thirdRow = Arrays.asList(7,8,9);
        List firstCol = Arrays.asList(1,4,7);
        List secondCol = Arrays.asList(2,5,8);
        List thirdCol = Arrays.asList(3,6,9);
        List firstDiag = Arrays.asList(1,5,9);
        List secondDiag = Arrays.asList(3,5,7);

        List<List> fullList = new ArrayList<List>();

        fullList.add(firstRow);
        fullList.add(secondRow);
        fullList.add(thirdRow);
        fullList.add(firstCol);
        fullList.add(secondCol);
        fullList.add(thirdCol);
        fullList.add(firstDiag);
        fullList.add(secondDiag);

        for(List l : fullList){
            if(personMoveTrack.containsAll(l)){
                return "Person Won";
            }else if(computerMoveTrack.containsAll(l)){
                return "Computer wins";
            }else if (personMoveTrack.size() + computerMoveTrack.size() == 9){
                return "TIE";
            }
        }
       return "";
    }
}
