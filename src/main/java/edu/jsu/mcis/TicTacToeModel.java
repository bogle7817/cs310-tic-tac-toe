package edu.jsu.mcis;

public class TicTacToeModel {
    
    private Mark[][] board; /* Game board */
    private boolean xTurn;  /* True if X is current player */
    private int width;      /* Size of game board */
    
    /* ENUM TYPE DEFINITIONS */
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("TIE"), 
        NONE("NONE");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create board (width x width) as a 2D Mark array */
        
        board = new Mark[width][width];

        /* Initialize board by filling every square with empty marks */
        
        for(int row = 0; row < width; row++ ){
                
            for(int col = 0; col < width; col++){
                
            board[row][col] = Mark.EMPTY;
                   
            }    
        }
    }
	
    public boolean makeMark(int row, int col) { 
        
        /* Use "isValidSquare()" to check if the specified location is in range,
           and use "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, make a mark for the current player, then
           toggle "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, return FALSE. */
        
        if (isValidSquare(row, col) == true) {
            if (isSquareMarked(row, col) == false){
                
                if (xTurn) {
                    board[row][col] = Mark.X;
                    xTurn = false;
                }
                else if (!xTurn) {
                    board[row][col] = Mark.O;
                    xTurn = true;
                }
                
                return true;
            }
            else {
                return false;
            }
        }
        
        else {
            return false;
        } 
       
    }
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return TRUE if the specified location is within the bounds of the board */
        
        if ((row > width -1) || (col > width - 1) || (row < 0) || (col < 0)) {
            return false;
        }
        else {
            return true;
        }
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return TRUE if the square at specified location is marked */

        if (board[row][col] != Mark.EMPTY){
            return true;
        }
        else {
            return false;
        }
            
        
            
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return the mark from the square at the specified location */
        
        return board[row][col];
        
            
    }
	
    public Result getResult() {
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */
        
        if (isTie()){
           return Result.TIE;
       }
        else if (isMarkWin(Mark.X) == true) {
           return Result.X;
       }
        else if (isMarkWin(Mark.O) == true) {
           return Result.O;
       }
        else {
           return Result.NONE;
       }
    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
        
        boolean winRow = false;
        for (int i = 0; i < width; i++) {
            boolean isWinning = true;
            for (int j = 0; j < width; j++) {
                if (board[j][i] != mark) 
                    isWinning = false;
                
            }
            if (isWinning == true) 
                    winRow = true;
                
      }

        boolean winCol = false;
        for (int i = 0; i < width; i++) {
            boolean isWinning = true;
            for ( int j = 0; j < width; j++) {
                if (board[i][j] != mark)
                    isWinning = false;
            }
            if (isWinning == true) 
                    winCol = true;
      }
      
        boolean diagLR = true;
        for (int i = 0; i < width; i++) {
            if (board[i][i] != mark)
                diagLR = false;
 
        }

        boolean diagRL = true;
        for (int i = 0; i < width; i++) {
            int dec = width - 1 - i;
            if (board[dec][i] != mark)
                diagRL = false;

        }

        if (winRow == true)
            return true;
        else if (winCol == true)
            return true;
        else if (diagLR == true)
            return true;
        else if (diagRL == true)
            return true;
        else{
            return false;
        }
    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */

        int counter = 0;
        
        for(int i = 0; i < width; ++i) {
            for(int j = 0; j < width; ++j) {
                if(board[i][j] == Mark.EMPTY) {
                    ++counter;
                }
            }
        }
        if (counter == 0){
            return true;
        }
        else{
            return false;
        }
        
    }

    public boolean isGameover() {
        
        /* Return TRUE if the game is over */
        
        return (Result.NONE != getResult());
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
    @Override
    public String toString() {
        
        StringBuilder output = new StringBuilder("  ");
        
        /* Output the board contents as a string (see examples) */

        for ( int k = 0; k < getWidth(); k++) {
            output.append(k);
        }
        output.append("\n");
        int mar = -1;
        for (int h = 0;h < getWidth(); h++) {
            mar++;
            output.append( mar + " ");
            for (int m = 0; m < getWidth(); m++) {
               output.append(board[h][m]); 
            }
            output.append("\n");
        }

        return output.toString();
        
    }
    
}
