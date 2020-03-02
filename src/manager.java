public class manager {
    Board board = new Board();

    public void doMove(int currentX, int currentY, int destX, int destY){
        if(destX >= 1 && destX <=8 && destY >= 1 && destY <=8)
        {
            BoardItem currentItem = board.getItemByXY(currentX, currentY);
            BoardItem destinationItem = board.getItemByXY(destX, destY);


        }
    }
    public static void main(String [] args){
        Game game = new Game();


    }
}
