public class Board {

    private BoardItem[][] board = new BoardItem[9][9];
    private char[] letterConversion = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    private int scoreP1 = 12, scoreP2 = 12;
    private Type turn;
    private GameMode gameMode;

    public Board() {
        createBoard();
    }

    private void createBoard() {
        String letterToPrint;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                letterToPrint = (x + y) % 2 == 0 ? "W" : "B";
                if (y == 0 && x == 0)
                    letterToPrint = "";
                else if (y == 0)
                    letterToPrint = String.valueOf(letterConversion[x - 1]);
                else if (x == 0)
                    letterToPrint = String.valueOf(y);
                if (y > 0 && y < 4 && letterToPrint == "B")
                    letterToPrint = "P1";
                if (y > 5 && y < board.length && letterToPrint == "B")
                    letterToPrint = "P2";

                System.out.print(letterToPrint + "\t");
                board[x][y] = new BoardItem(x, y, letterToPrint);
            }
            System.out.println();
        }
        turn = Type.P1; //P1 is the first player to play.
        gameMode = GameMode.CREATING; //Its Only for function if i will want to improve this project.
    }

    public void printBoard() {
        String letterToPrint;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                if (y == 0 && x == 0)
                    letterToPrint = "";
                else if (y == 0)
                    letterToPrint = String.valueOf(letterConversion[x - 1]);
                else if (x == 0)
                    letterToPrint = String.valueOf(y);
                else letterToPrint = (getItemByXY(x, y).getTeam().toString());
                System.out.print(letterToPrint + "\t");
            }
            System.out.println();
        }
    }

    public BoardItem getItemByXY(int x, int y) {
        return board[x][y];
    }

    public void changeItemsByStatus(BoardItem soldierInput, BoardItem placeToMove, GameMode gameMode) {
        switch (gameMode) {
            case MOVE:
                placeToMove.setTeam(soldierInput.getTeam());
                soldierInput.setTeam((soldierInput.getX() + soldierInput.getY()) % 2 == 0 ? Type.W : Type.B);
                break;
            case EATING:
                changeStatusForEatenItem(soldierInput, placeToMove);
                placeToMove.setTeam(soldierInput.getTeam());
                soldierInput.setTeam((soldierInput.getX() + soldierInput.getY()) % 2 == 0 ? Type.W : Type.B);
                break;
        }
    }

    public boolean isValidTurn(BoardItem source, BoardItem dest) {
        if (dest.getX() <= 8 && dest.getX() >= 1) { //Check if the player is within the limits of the game
            if (source.getTeam() == Type.P1 && dest.getTeam() == Type.B && dest.getY() > source.getY() && dest.getY() <= 8) {
                if (dest.getX() == (source.getX() + 1) && dest.getY() == (source.getY() + 1)) return true;    //P1-Right
                if (dest.getX() == (source.getX() - 1) && dest.getY() == (source.getY() + 1)) return true;    //P1-Left
                if (dest.getX() == (source.getX() - 2) && dest.getY() == (source.getY() + 2))
                    return true;    //P1-Eating Left
                if (dest.getX() == (source.getX() + 2) && dest.getY() == (source.getY() + 2))
                    return true;    //P1-Eating Right
                return false;
            } else if (source.getTeam() == Type.P2 && dest.getTeam() == Type.B && dest.getY() < source.getY() && dest.getY() >= 1) {
                if (dest.getX() == (source.getX() - 1) && (dest.getY() + 1) == source.getY()) return true;    //P2-Left
                if (dest.getX() == (source.getX() + 1) && (dest.getY() + 1) == source.getY()) return true;    //P2-Right
                if (dest.getX() == (source.getX() - 2) && dest.getY() == (source.getY() - 2))
                    return true;    //P2-Eating Left
                if (dest.getX() == (source.getX() + 2) && dest.getY() == (source.getY() - 2))
                    return true;    //P2-Eating Right
                return false;
            }
        }
        return false;
    }

    public void moveItem(BoardItem soldierInput, BoardItem placeToMove) {
        switch (Math.abs(placeToMove.getY() - soldierInput.getY())) {
            case 1:         //Moving Mode
                this.gameMode = GameMode.MOVE;
                break;
            case 2:         //Eating Mode
                gameMode = GameMode.EATING;
                break;
        }
        changeItemsByStatus(soldierInput, placeToMove, gameMode);
    }


    public boolean cheackDoubleEating(BoardItem placeToMove) {          //todo: Need to implement.
        return true;
    }

    public void changeStatusForEatenItem(BoardItem soldierInput, BoardItem placeToMove) {
        int eatenItemX = 0, eatenItemY = 0;
        if (soldierInput.getTeam() == Type.P1) {
            this.scoreP2--;
            if (placeToMove.getX() == (soldierInput.getX() - 2) && placeToMove.getY() == (soldierInput.getY() + 2)) //The user P1 ate left
            {
                eatenItemX = soldierInput.getX() - 1;
                eatenItemY = soldierInput.getY() + 1;
            }
            if (placeToMove.getX() == (soldierInput.getX() + 2) && placeToMove.getY() == (soldierInput.getY() + 2)) //The user P1 ate right
            {
                eatenItemX = soldierInput.getX() + 1;
                eatenItemY = soldierInput.getY() + 1;
            }
        } else {
            this.scoreP1--;
            if (placeToMove.getX() == (soldierInput.getX() - 2) && placeToMove.getY() == (soldierInput.getY() - 2)) //The user P2 ate left
            {
                eatenItemX = soldierInput.getX() - 1;
                eatenItemY = soldierInput.getY() - 1;
            }
            if (placeToMove.getX() == (soldierInput.getX() + 2) && placeToMove.getY() == (soldierInput.getY() - 2)) //The user P2 ate right
            {
                eatenItemX = soldierInput.getX() + 1;
                eatenItemY = soldierInput.getY() - 1;
            }
        }
        BoardItem eatenItem = getItemByXY(eatenItemX, eatenItemY);
        eatenItem.setTeam((eatenItem.getX() + eatenItem.getY()) % 2 == 0 ? Type.W : Type.B);
    }

    public int getScoreP2() {
        return scoreP2;
    }

    public int getScoreP1() {
        return scoreP1;
    }
}
