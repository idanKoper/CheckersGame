import java.util.Scanner;

public class Game {

    private boolean isFinish = false;
    private Board board;
    private String turn = "W";


    public Game() {
        System.out.println("Player 1 - White - Please enter your name");
        Scanner sc = new Scanner(System.in);
        String nameW = sc.nextLine();
        System.out.println("Player 2 - Black - Please enter your name");
        String nameB = sc.nextLine();
        System.out.println("Let the game begin");
        board = new Board();
        while (board.getScoreP1() != 0 || board.getScoreP2() != 0 || isFinish != true) {
            System.out.println("Please enter your soldier that you want to move, write 'Q' if there isn't any legal move");
            String answerUser = sc.nextLine();
            if (answerUser.equals("Q")) {    //     I am assume that the user will enter 'Q' if he dont have any any legal move.
                isFinish = true;
                break;
            }
            BoardItem soldierInput = calculatePlaceInBoard(answerUser);
            System.out.println("Please enter where you want to move your soldier");
            BoardItem placeToMove = calculatePlaceInBoard(sc.nextLine());
            if (board.isValidTurn(soldierInput, placeToMove) == true) {
                board.moveItem(soldierInput, placeToMove);
            } else {
                System.out.println("Please enter correct input to move the player");
                continue;
            }
            board.printBoard();
        }
    }

    private BoardItem calculatePlaceInBoard(String soldierInput) {
        int column = (int) soldierInput.charAt(0) - 64; // Get the ascii of the char and sub by 64 to get the place in the board.
        int row = (int) soldierInput.charAt(1) - 48;    // sub by 48 to get the place in the board.
        BoardItem solderToMove = board.getItemByXY(column, row);
        return solderToMove;

    }
}
