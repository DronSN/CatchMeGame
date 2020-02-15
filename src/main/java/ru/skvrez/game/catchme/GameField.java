package ru.skvrez.game.catchme;

import static ru.skvrez.game.catchme.PlayerName.FIRST_PLAYER;
import static ru.skvrez.game.catchme.PlayerName.SECOND_PLAYER;
import java.util.ArrayList;
import java.util.List;

public class GameField {
	
	private final int COLUMN_COUNT = 3;
	private final int ROWS_COUNT = 5;
	private final int FIRST_PLAYER_SYMBOL = 7;
	private final int SECOND_PLAYER_SYMBOL = 8;
	private final int UNUSED_SPACE_SYMBOL = 9;
	private final FieldAddress[] FIRST_PLAYER_ADDRESS = new FieldAddress[] 
			{new FieldAddress(3,0),
			 new FieldAddress(4,1),
			 new FieldAddress(3,2)};
	private final FieldAddress   SECOND_PLAYER_ADDRESS  = new FieldAddress(2,1);	
	private final FieldAddress[] UNUSED_AREA = new FieldAddress[]
			{new FieldAddress(0,0),
    		 new FieldAddress(0,2),
			 new FieldAddress(4,0),
			 new FieldAddress(4,2),};
			
	private int[][] field;
	private List<GameChip> listGameChips = new ArrayList<GameChip>();
	
	public GameField() {
		field = new int[ROWS_COUNT][COLUMN_COUNT];
		int chipNumber = 1;
		//first player
		for(FieldAddress address:FIRST_PLAYER_ADDRESS) {
			field[address.getRow()][address.getColumn()] = FIRST_PLAYER_SYMBOL;
			listGameChips.add(new GameChip(address, 
					          FIRST_PLAYER, 
					          chipNumber,
					          this));
			chipNumber++;
		}
		//second player
		field[SECOND_PLAYER_ADDRESS.getRow()][SECOND_PLAYER_ADDRESS.getColumn()] = SECOND_PLAYER_SYMBOL;
		listGameChips.add(new GameChip(SECOND_PLAYER_ADDRESS,
				          SECOND_PLAYER,
				          1,
				          this));
		//unused space
		for(FieldAddress address:UNUSED_AREA) {
			field[address.getRow()][address.getColumn()] = UNUSED_SPACE_SYMBOL;
		}
		updateChipsNextMove();
		System.out.println();
	}

	public int[][] getField() {
		return field;
	}

	public List<GameChip> getListGameChips() {
		return listGameChips;
	}
	
	public GameChip returnChip(PlayerName playerName, int chipNumber) {
		 GameChip currentGameChip = null;
			for(GameChip gameChip:listGameChips) {
				if(gameChip.getPlayerName() == playerName && 
				   gameChip.getChipNumber() == chipNumber) {
					currentGameChip = gameChip;					
					break;
				}
			}
		 return currentGameChip;
	}
	
	public void updateChipPosition(PlayerName playerName, int chipNumber, FieldAddress fieldAddress) {
		FieldAddress oldFieldAddress = null;
		for(GameChip gameChip:listGameChips) {
			if(gameChip.getPlayerName() == playerName && 
			   gameChip.getChipNumber() == chipNumber) {
				oldFieldAddress = gameChip.getFieldAddress();
				gameChip.setFieldAddress(fieldAddress);
				break;
			}
		}
		field[oldFieldAddress.getRow()][oldFieldAddress.getColumn()] = 0;
		int fieldValue = 0;
		if(playerName == FIRST_PLAYER) {
			fieldValue = FIRST_PLAYER_SYMBOL;
		} else {
			fieldValue = SECOND_PLAYER_SYMBOL;
		}
		field[fieldAddress.getRow()][fieldAddress.getColumn()] = fieldValue;
		updateChipsNextMove();
	}
	
	public void updateChipsNextMove() {
		
		listGameChips.forEach(x->x.updateNextMove());
	}
	
	public void moveChip(PlayerName playerName, int chipNumber) {

		int moveNumber;
		if (playerName == FIRST_PLAYER) {
			moveNumber = 1;
		} else {
			moveNumber = chipNumber;
			chipNumber = 1;
		}
		GameChip gameChip = returnChip(playerName, chipNumber);
		int newRow = gameChip.getNextMove().get(moveNumber).getRow();
		int newColumn = gameChip.getNextMove().get(moveNumber).getColumn();
		updateChipPosition(playerName, chipNumber, new FieldAddress(newRow,newColumn));
	}
}


