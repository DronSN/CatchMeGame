package ru.skvrez.game.catchme;

import static ru.skvrez.game.catchme.PlayerName.FIRST_PLAYER;
import java.util.HashMap;
import java.util.Map;

public class GameChip {
	private GameField gameField;
	private FieldAddress fieldAddress;
	private PlayerName playerName;
	private int chipNumber;
	private Map<Integer,FieldAddress> nextMove;
	
	public GameChip(FieldAddress fieldAddress, 
			        PlayerName   playerName,
			        int chipNumber,
			        GameField gameField) {
		this.fieldAddress = fieldAddress;
		this.playerName = playerName;
		this.chipNumber = chipNumber;
		this.gameField = gameField;
	}

	public PlayerName getPlayerName() {
		return playerName;
	}

	public int getChipNumber() {
		return chipNumber;
	}

	public FieldAddress getFieldAddress() {
		return fieldAddress;
	}

	public Map<Integer, FieldAddress> getNextMove() {
		return nextMove;
	}

	public void setFieldAddress(FieldAddress fieldAddress) {
		this.fieldAddress = fieldAddress;
	}

	public void updateNextMove() {
		if(playerName == FIRST_PLAYER) {
			updateNextMoveFirstPlayer();
		} else {
			updateNextMoveSecondPlayer();
		}
	}
	
	private void updateNextMoveFirstPlayer() {
		int currentRow = fieldAddress.getRow();
		int currentColumn = fieldAddress.getColumn();
		nextMove = new HashMap<>();
		int moveIndex = 1;
		if(currentRow-1>=0 && gameField.getField()[currentRow-1][currentColumn]==0) {
			nextMove.put(moveIndex, new FieldAddress(currentRow-1,currentColumn));
		} else if(currentRow-1>=0 && gameField.getField()[currentRow-1][currentColumn]==9) {
			if(gameField.getField()[0][1] == 0) {
				nextMove.put(moveIndex, new FieldAddress(0,1));
			}
		}
	}
	
	private void updateNextMoveSecondPlayer() {
		int currentRow = fieldAddress.getRow();
		int currentColumn = fieldAddress.getColumn();
		nextMove = new HashMap<>();
		int moveIndex = 1;
		if(currentRow == 0 && currentColumn == 1 && gameField.getField()[1][0]==0){
			nextMove.put(moveIndex, new FieldAddress(1,0));
			moveIndex++;
		}
		if(currentRow-1>=0 && gameField.getField()[currentRow-1][currentColumn]==0) {
			nextMove.put(moveIndex, new FieldAddress(currentRow-1,currentColumn));
			moveIndex++;
		} 
		if (currentRow+1<gameField.getField().length && gameField.getField()[currentRow+1][currentColumn]==0) {
			nextMove.put(moveIndex, new FieldAddress(currentRow+1,currentColumn));
			moveIndex++;
		}
		if(currentColumn-1>=0 && gameField.getField()[currentRow][currentColumn-1]==0) {
			nextMove.put(moveIndex, new FieldAddress(currentRow,currentColumn-1));
			moveIndex++;
		}
		if(currentColumn+1<gameField.getField()[currentRow].length && gameField.getField()[currentRow][currentColumn+1]==0) {
			nextMove.put(moveIndex, new FieldAddress(currentRow,currentColumn+1));
			moveIndex++;
		}
		if(currentRow == 0 && currentColumn == 1 && gameField.getField()[1][2]==0){
			nextMove.put(moveIndex, new FieldAddress(1,2));
			moveIndex++;
		}
	}
}
