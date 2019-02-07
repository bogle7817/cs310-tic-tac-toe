package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToeController implements ActionListener{

    public final TicTacToeModel model;
    private final TicTacToeView view;
    
    /* CONSTRUCTOR */

    public TicTacToeController(int width) {
        
        /* Initialize model, view, and width */

        model = new TicTacToeModel(width);
        view = new TicTacToeView(this,width);
        
    }

    public String getMarkAsString(int row, int col) {        
        return (model.getMark(row, col).toString());        
    }
    
    public TicTacToeView getView() {        
        return view;        
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        if (event.getSource() instanceof JButton) {
            
            JButton button = (JButton) (event.getSource());

            String fullButtonName = button.getName();
            String replaceBN = fullButtonName.replaceFirst("Square", "");
            String[] getRowCol = replaceBN.split("");

            boolean gameOver = false;
            int row, col;
            row = Integer.parseInt(getRowCol[0]);
            col = Integer.parseInt(getRowCol[1]);

            model.makeMark(row,col);
            
            view.updateSquares();

            if (model.isGameover() == true) {
                gameOver = true;
                view.disableSquares();
                view.showResult(model.getResult().toString());
            }
        }


    }
}
