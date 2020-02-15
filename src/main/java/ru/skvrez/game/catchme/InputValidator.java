package ru.skvrez.game.catchme;

import java.util.HashSet;
import java.util.Set;
import static ru.skvrez.game.catchme.PlayerName.FIRST_PLAYER;
import static ru.skvrez.game.catchme.PlayerName.SECOND_PLAYER;

public class InputValidator {
	public static boolean isValid(GameField gameField, PlayerName playerName, String input) {
		Set<Integer> validSet = new HashSet<>();
		boolean isValid = false;
		int intMove = 0;
	    try { 
	    	intMove = Integer.parseInt(input); 
	    } catch(NumberFormatException e) { 
	        return isValid; 
	    }
		if(playerName == FIRST_PLAYER) {
			gameField.getListGameChips()
			.stream()
			.filter(x->x.getPlayerName() == FIRST_PLAYER  &&
			           (! x.getNextMove().isEmpty()) )
			.forEach(x->validSet.add(x.getChipNumber()));
		} else {
			gameField.getListGameChips()
			.stream()
			.filter(x->x.getPlayerName() == SECOND_PLAYER  &&
			           (! x.getNextMove().isEmpty()) )
			.forEach(x->validSet.addAll(x.getNextMove().keySet()));
		}
		return validSet.contains(intMove);
	}
}
