package ru.skvrez.game.catchme;

import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Color.*;
import static ru.skvrez.game.catchme.PlayerName.FIRST_PLAYER;
import static ru.skvrez.game.catchme.PlayerName.SECOND_PLAYER;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import org.fusesource.jansi.AnsiConsole;

public class FieldRenderer {
	private final String EMPTY_CELL = " [ ] ";
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
	    		}else if(cell == 7 ) {
	    			System.out.print(" [" +ansi().fg(YELLOW).bold().a("x").reset() + "] ");
	    		}else if(cell == 8) {
	    			System.out.print(" [" +ansi().fg(CYAN).bold().a("*").reset() + "] ");
	    		} else {
	    			System.out.print(EMPTY_CELL);
	    		}
	    	}
	    	System.out.println();
	    }
	    AnsiConsole.systemUninstall();
	}

	private void showChipMoveFiled(int[][] chipField) {
	    AnsiConsole.systemInstall();
	    System.out.println(ansi().eraseScreen().reset());
	    for(int[] row:chipField) {
	    	System.out.print(TAB_SPACE);
	    	for(int cell:row) {
	    		if(cell == 9) {
	    			System.out.print(EMPTY_SPACE);
	    		}else if(cell == 7 ) {
	    			System.out.print(" [" +ansi().fg(YELLOW).bold().a("x").reset() + "] ");
	    		}else if(cell == 8) {
	    			System.out.print(" [" +ansi().fg(CYAN).bold().a("*").reset() + "] ");
	    		}else if(cell >= 1 && cell <= 4) {
	    			System.out.print(" [" +ansi().fg(MAGENTA ).bold().a(cell).reset() + "] ");
	    		} else {
	    			System.out.print(EMPTY_CELL);
	    		}
	    	}
	    	System.out.println();
	    }
	    AnsiConsole.systemUninstall();
	}
	
	private int chipNumberFirstPlayer(int row, int column) {
		int chipNumber = 0;
		for(GameChip gameChip:gameField.getListGameChips()) {
			if(gameChip.getPlayerName() == FIRST_PLAYER &&
			  gameChip.getFieldAddress().getRow() == row &&
			  gameChip.getFieldAddress().getColumn() == column) {
				chipNumber = gameChip.getChipNumber();
				break;
			}
		}
		return chipNumber;
	}
	
	
	public void showFirstPlayerChipsWithNumbers() {
	    AnsiConsole.systemInstall();
	    System.out.println(ansi().eraseScreen().reset());
	    for(int rowNumber = 0;rowNumber<gameField.getField().length;rowNumber++) {
	    	int[] row = gameField.getField()[rowNumber];
	    	System.out.print(TAB_SPACE);
	    	for(int columnNumber = 0;columnNumber < row.length;columnNumber++) {
	    		int cell = row[columnNumber];		
	    		if(cell == 9) {
	    			System.out.print(EMPTY_SPACE);
	    		}else if(cell == 7 ) {
	    			int chipNumber = chipNumberFirstPlayer(rowNumber, columnNumber);
	    			if(gameField.returnChip(FIRST_PLAYER, chipNumber).getNextMove().isEmpty()) {
	    				System.out.print(" [" +ansi().fg(YELLOW).bold().a("x").reset() + "] ");
	    			} else {
	    				System.out.print(" [" +ansi().fg(YELLOW).bold().a(chipNumber).reset() + "] ");	
	    			}
	    		}else if(cell == 8) {
	    			System.out.print(" [" +ansi().fg(CYAN).bold().a("*").reset() + "] ");
	    		}else if(cell >= 1 && cell <= 4) {
	    			System.out.print(" [" +ansi().fg(BLUE).bold().a(cell).reset() + "] ");
	    		} else {
	    			System.out.print(EMPTY_CELL);
	    		}
	    	}
	    	System.out.println();
	    }
	    AnsiConsole.systemUninstall();
	}
	
	public void showChipMove(PlayerName playerName, int chipNumber) {
		int[][] chipField  = new int[gameField.getField().length][gameField.getField()[0].length];
		int rowIndex = 0;
		for(int[] row:gameField.getField()) {
			chipField[rowIndex] = Arrays.copyOf(row, row.length);
			rowIndex++;
		}
		GameChip currentChip=null;
		for(GameChip gameChip:gameField.getListGameChips()) {
			if(gameChip.getPlayerName() == playerName && gameChip.getChipNumber() == chipNumber) {
				currentChip = gameChip;
				break;
			}
		}
		for(Map.Entry<Integer,FieldAddress> mapEntry:currentChip.getNextMove().entrySet() ) {
			chipField[mapEntry.getValue().getRow()][mapEntry.getValue().getColumn()] = mapEntry.getKey();
		}
		showChipMoveFiled(chipField);
	}
	
	public void playerGameStep(PlayerName playerName) {
		String notValidMessage = "";
		String input = "";
		boolean isValid;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));		
		do {
    		if(playerName == FIRST_PLAYER ) {
    			showFirstPlayerChipsWithNumbers();
        		System.out.println("FIRST PLAYER, type chip number and press Enter");
    			
    		} else {
        		showChipMove(SECOND_PLAYER, 1);
        		System.out.println("SECOND PLAYER, type field number and press Enter");
    		}
    		AnsiConsole.systemInstall();
    		System.out.println(ansi().fg(RED).bold().a(notValidMessage).reset());
    		AnsiConsole.systemUninstall();
    		try {
				input = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		isValid = InputValidator.isValid(gameField, playerName, input);
    		if(!isValid) {
    			notValidMessage = "Input is not valid, try again";
    		}else {
    			notValidMessage = "";
    		}
		}while(! isValid);
		gameField.moveChip(playerName, Integer.parseInt(input));
	}
	
	public void showWin(PlayerName playerName) {
		String message;
		if(playerName == FIRST_PLAYER ) {
			message = "PLAYER ONE is WINNER!!!";
		} else {
			message = "PLAYER TWO is WINNER!!!";
		}
		AnsiConsole.systemInstall();
		System.out.println();
		System.out.println(ansi().fg(GREEN).bold().a(message).reset());
		AnsiConsole.systemUninstall();
		
	}
}
