package walid.projects.app;
import java.util.Arrays;
public class easyai implements ai{

    @Override
    public void nextMove(Board board, String delim) {
        // TODO Auto-generated method stub
        System.out.println("AI MOVE!");
        int height = board.getHeight();
        int width = board.getWidth();
        ai.printMove(board, "X", 0, 0);
        return;
    }
}