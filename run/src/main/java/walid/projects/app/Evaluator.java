package walid.projects.app;

public class Evaluator {
    public int eval[];
    public int diag_eval[];
    public Evaluator(int n) {
        eval = new int[4];
        diag_eval = new int[2];
        int base = (1<<n) - 1;
        int left_diag_base = 1; 
        for(int i = 0; i<n; i++)
        {
            eval[i] = base;
            base<<=n;
            left_diag_base = (left_diag_base<<n-1) + 1;
        }
        diag_eval[0] = left_diag_base - 1;
        diag_eval[1] = 4680;
    }
    public int[] convert_board(Board input_board, String delim)
    {
        String[][] board = input_board.getBtnLabels();
        int[] output = new int[2];
        int row_major = 0;
        int col_major = 0;
        for(int r = 0; r<board.length; r++) {
            for(int c = 0; c<board[r].length; c++) {
                if(board[r][c].equals(delim))
                    row_major+=1;
                
                if(board[c][r].equals(delim))
                    col_major+=1;

                row_major <<= 1;
                col_major <<= 1;
            }
        }
        output[0] = row_major >> 1;
        output[1] = col_major >> 1;
        return output;
    }
    public String game_over(Board board) {
        return "O";
    }
}
