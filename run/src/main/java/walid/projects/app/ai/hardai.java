package walid.projects.app.ai;

import java.util.ArrayList;
import java.util.Optional;

import walid.projects.app.Board;
import walid.projects.app.Evaluator;

public class hardai implements ai {
    public class QState {
        public int board;
        public int q_val;
        public QState(int init_board, Optional<Integer> init_q_val)
        {
            this.board = init_board;
            this.q_val = init_q_val.isPresent() ? init_q_val.get() : 0;
        }
    }
    public class Agent {
        private ArrayList<ArrayList<QState>> q_table; //row indices are all the STATES, column indeces are all the next possible
        private Evaluator eval;
        private char delim;
        public Agent(int q_height, int q_width, Optional<Character> delim)
        {
            eval = new Evaluator(q_height);
            this.q_table = new ArrayList<ArrayList<QState>>((int)Math.pow(2, q_height * q_width));
            if(delim.isPresent())
                this.delim = delim.get();
            else
                this.delim = 'O';
            // for(int i = 0; i<this.q_table.size(); i++)
            // {
            //     int[] states = this.eval.nextStates(board, delim)
            //     ArrayList<QState> q_states = new ArrayList<>();
            // }
        }
        public void setQValue(int value, Board state, Board postAction)
        {
            int row = eval.convert_board(state, this.delim + "")[0];
            int col = eval.convert_board(postAction,this.delim + "")[0];
            try {
                this.q_table.get(row).set(col, new QState(col, Optional.of(value)));
            } catch (Exception e) {};
        }
        public int stateReward(Board state)
        {
            String winning_delim = eval.game_over(state);
            return winning_delim != null ? winning_delim.charAt(0) != this.delim ? -1 : 1: 0;
        }
        public int[] move(Board table)
        {
            return null;   
        }
    }

    @Override
    public void nextMove(Board board, String delim) {
        // TODO Auto-generated method stub
        // Trained with Q-learning RL
        System.out.println("Not Implemented Yet!");
    }
    
}
