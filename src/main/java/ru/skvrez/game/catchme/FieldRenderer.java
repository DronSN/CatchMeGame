package ru.skvrez.game.catchme;

import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Color.*;

import org.fusesource.jansi.AnsiConsole;

public class FieldRenderer {
	private final String EMPTY_CELL = " [ ] ";
//	private final String FIRST_PLAYER_CELL =  " [X] ";
//	private final String SECOND_PLAYER_CELL = " [*] ";
	private final String EMPTY_SPACE = "     ";
	private final String TAB_SPACE = "          ";
	
	
	private GameField gameField;
	
	public FieldRenderer(GameField gameField) {
	     this.gameField = gameField;	
	}
	
	public void show() {
	    AnsiConsole.systemInstall();
	    System.out.println(ansi().eraseScreen().reset());
	    for(int[] row:gameField.getField()) {
	    	System.out.print(TAB_SPACE);
	    	for(int cell:row) {
	    		if(cell == 9) {
	    			System.out.print(EMPTY_SPACE);
	    		}else if(cell == 1 ) {
	    			System.out.print(" [" +ansi().fg(YELLOW).bold().a("x").reset() + "] ");
	    		}else if(cell == 2) {
	    			System.out.print(" [" +ansi().fg(CYAN).bold().a("*").reset() + "] ");;
	    		} else {
	    			System.out.print(EMPTY_CELL);
	    		}
	    	}
	    	System.out.println();
	    }
	    AnsiConsole.systemUninstall();
	}

}
