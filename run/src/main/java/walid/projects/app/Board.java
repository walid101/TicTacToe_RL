package walid.projects.app;
// import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public class Board {
    private BorderPane layout;
    private GridPane pane;
    private Button[][] board;
    private String[][] btn_labels;
    public int turn = 0;
    public Board(int Height, int Width)
    {
        layout = new BorderPane();
        pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setAlignment(Pos.CENTER);
        board = new Button[Height][Width];
        layout.setCenter(pane);
    }
    public boolean initBoard(String[][] btn_names, EventHandler<ActionEvent>[][] btn_actions)
    {
        try
        {
            btn_labels = btn_names;
            if(btn_names.length != board.length || btn_names[0].length != board[0].length)
            {
                System.out.println("Btn Names String Array Must Be Same Size As Board Btns in initBoard");
                return false;
            }
            for(int i = 0; i<btn_names.length; i++)
            {
                for(int j = 0; j<btn_names[i].length; j++)
                {
                    Button btn = new Button(btn_names[i][j]);
                    btn.setFont(Font.font("Monospaced", 40));
                    pane.add(btn, j, i);
                    board[i][j] = btn;
                    if(btn_actions != null && btn_actions[i] != null && btn_actions[i][j] != null)
                        btn.setOnAction(btn_actions[i][j]);
                    else
                        btn.setOnAction(e -> {
                            System.out.println("Button Is Not Wired");
                        });
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error Encountered at initBoard: " + e.getMessage());
        }
        return true;
    }
    
    public boolean setBoardBtn(int row, int col, String new_name) 
    {
        try
        {
            //assertFalse(new_name == null, "new name not null?");
            board[row][col].setText(new_name);
            btn_labels[row][col] = new_name;
        }
        catch(Exception e)
        {
            System.out.println("Exception Encountered in setBoardBtn: " + e.getMessage());
            return false;
        }
        return true;
    }
    public BorderPane getBoard()
    {
        return layout;
    }
    public String getCell(int r, int c)
    {
        return btn_labels[r][c];
    }
    public String[][] getBtnLabels()
    {
        return btn_labels;
    }
    public int getHeight()
    {
        return board.length;
    }
    public int getWidth() 
    {
        return board[0].length;
    }
}
