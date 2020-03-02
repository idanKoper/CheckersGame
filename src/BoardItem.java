public class BoardItem {

    private int x;
    private int y;
    private Type team;

    public BoardItem(int x, int y, String team) {
        this.x = x;
        this.y = y;
        switch (team) {
            case "P1":
                this.setTeam(Type.P1);
                break;
            case "P2":
                this.setTeam(Type.P2);
                break;
            case "W":
                this.setTeam(Type.W);
                break;
            case "B":
                this.setTeam(Type.B);
                break;
            default:
                this.setTeam(Type.NULL);

        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Type getTeam() {
        return team;
    }

    public void setTeam(Type team) {
        this.team = team;
    }


}
