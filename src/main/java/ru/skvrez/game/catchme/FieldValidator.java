package ru.skvrez.game.catchme;

import static ru.skvrez.game.catchme.PlayerName.FIRST_PLAYER;
import static ru.skvrez.game.catchme.PlayerName.SECOND_PLAYER;

public class FieldValidator {
	private GameField gameField;
	private PlayerName winPlayer;

	public FieldValidator(GameField gameField){
		this.gameField = gameField;
	}
	
	public PlayerName getWinPlayer() {
		return winPlayer;
	}
	
	public boolean isValidField(PlayerName currentPlayer) {
		
		boolean noMoveStep = gameField.getListGameChips()
		    .stream()
		    .filter(x->x.getPlayerName() == currentPlayer)
		    .allMatch(x->x.getNextMove().isEmpty());
		
		if(noMoveStep) {
			winPlayer = playerMirroring(currentPlayer);
			return false;
		}
		int maxRowFirstPlayerChips = gameField.getListGameChips()
		    .stream()
		    .filter(x->x.getPlayerName() == FIRST_PLAYER)
			.max((x,y) -> Integer.compare(x.getFieldAddress().getRow(), y.getFieldAddress().getRow()))
			.get()
			.getFieldAddress()
			.getRow();
		int rowSecondPlayerChip = gameField.returnChip(SECOND_PLAYER, 1).getFieldAddress().getRow();
		if(rowSecondPlayerChip > maxRowFirstPlayerChips) {
			winPlayer = SECOND_PLAYER;
			return false;
		}
		return true;
	}
	
	private PlayerName playerMirroring(PlayerName playerName) {
		if(playerName == FIRST_PLAYER) {
			return SECOND_PLAYER;
		} else {
			return FIRST_PLAYER;
		}
	}
}
