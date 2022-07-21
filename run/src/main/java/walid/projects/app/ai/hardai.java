package walid.projects.app.ai;

import walid.projects.app.Board;
import walid.projects.app.Evaluator;

public class hardai implements ai {

    public class State {
        private double r;
        public State()
        {
            this.r = 0; //initial state reward = 0
        }
        public double get_reward() {
            return r;
        }
        public void set_reward_by_state(String agent_delim, Board table) {
            Evaluator eval = new Evaluator(table.board.length);
            String winner = eval.game_over(table);
            if(winner != null)
            {
                if(winner.equals(agent_delim) == false)
                    this.r = -1; //lost
                else
                    this.r = 1; //won
            }
        }   
    }
    public class StateAction {
        private State state;
        private int[] action;
        public StateAction(State init_state, int[] init_action) {
            state = init_state;
            action = init_action;
        }
        public State getState()
        {
            return state;
        }
        public int[] getAction()
        {
            return action;
        }
        public void setState(State newState)
        {
            this.state = newState;
        }
        public void setAction(int[] newAction)
        {
            this.action = newAction;
        }
    }
    public class Agent {
        private StateAction[][] q_table; //index by row byte representation of table
        private Evaluator eval;
        public Agent(int q_height, int q_width)
        {
            eval = new Evaluator(q_height);
            q_table = new State[q_height][q_width];
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
