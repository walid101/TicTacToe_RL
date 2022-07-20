package walid.projects.app;
import walid.projects.app.Board;

public interface ai {
    public int mode = -1;
    public static void printMove(Board board, String name, int row, int col)
    {
        board.setBoardBtn(row, col, name);
    } 
    public static int getMode()
    {
        return mode;
    }
    public abstract void nextMove(Board board, String delim);  
}
