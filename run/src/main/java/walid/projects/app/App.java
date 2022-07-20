package walid.projects.app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

import walid.projects.app.Board;
import walid.projects.app.Evaluator;
/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private Stage stage;
    private int mode = -1;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        scene = start_scene(stage);
        this.stage.setScene(scene);
        stage.show();
    }

    public Scene start_scene(Stage stage)
    {
        int height = 1;
        int width = 3;
        // int mode_human = 0;
        // int mode_easyai = 1;
        // int mode_hardai = 2;
        Board start = new Board(height, width);
        String[][] btn_names = {{"Human", "Easy", "Hard"}};
        EventHandler<ActionEvent>[][] events = new EventHandler[height][width];
        for(int i = 0; i<events[0].length; i++)
        {
            final int pos = i;
            events[0][i] = new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent arg0) {
                    // TODO Auto-generated method stub
                    System.out.println("Mode was: " + mode);
                    mode = pos;
                    ai game_ai = mode == 0 ? null : mode == 1 ? new easyai() : new hardai(); 
                    System.out.println("Mode now is: " + mode);
                    stage.setScene(game_scene(game_ai));
                }
                
            };
        }
        start.initBoard(btn_names, events);
        BorderPane layout = start.getBoard();
        Scene scene = new Scene(layout, 640, 480);
        return scene;
    }

    public Scene game_scene(ai game_ai)
    {
        int height = 4;
        int width = 4;
        Evaluator game_eval = new Evaluator(width);
        Board tic_tac_toe = new Board(height, width);
        String[][] btn_names = new String[height][width];
        for(int r = 0; r<btn_names.length; r++)
        {
            for(int c = 0; c<btn_names[r].length; c++)
            {
                btn_names[r][c] = " ";
            }
        }
        /**----------------TEST------------------------- */

        EventHandler<ActionEvent>[][] events = new EventHandler[height][width];
        for(int r = 0; r<height; r++)
        {
            for(int c = 0; c<width; c++)
            {
                final int pos_r = r;
                final int pos_c = c;
                events[r][c] = new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent arg0) {
                        // TODO Auto-generated method stub
                        if(tic_tac_toe.getCell(pos_r, pos_c).charAt(0) == ' ' && (tic_tac_toe.turn == 0) || game_ai == null) // turn 0 = human
                        {
                            tic_tac_toe.setBoardBtn(pos_r, pos_c, tic_tac_toe.turn == 0 ? "O" : "X");
                            if(game_ai != null)
                            {
                                tic_tac_toe.turn = tic_tac_toe.turn == 0 ? 1 : 0;
                                game_ai.nextMove(tic_tac_toe, "X");
                            }
                            tic_tac_toe.turn = tic_tac_toe.turn == 0 ? 1 : 0;
                            String winner = game_eval.game_over(tic_tac_toe);
                            if(winner != null)
                            {
                                System.out.println(winner + " WINS! Thanks for Playing :]");
                                System.exit(0);
                            }
                        }
                        // System.out.println(
                        //     "Row Major: " + Integer.toBinaryString(game_eval.convert_board(tic_tac_toe, "O")[0]) + "\n" +
                        //     "Col Major: " + Integer.toBinaryString(game_eval.convert_board(tic_tac_toe, "O")[1]));

                    }
                };
            }
        }
        /**--------------END TEST------------------------- */
        tic_tac_toe.initBoard(btn_names, events);
        BorderPane layout = tic_tac_toe.getBoard();
        Scene scene = new Scene(layout, 640, 480);
        return scene;
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
        //Evaluator test = new Evaluator(4);
        //int[] eval = test.eval;
        //System.out.println(Arrays.toString(eval));
    }

}