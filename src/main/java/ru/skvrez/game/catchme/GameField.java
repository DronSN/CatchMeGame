package ru.skvrez.game.catchme;

public class GameField {
	
	private final int COLUMN_COUNT = 3;
	private final int ROWS_COUNT = 5;
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
	
	public GameField() {
		field = new int[ROWS_COUNT][COLUMN_COUNT];
		//first player is 1
		for(FieldAddress address:FIRST_PLAYER_ADDRESS) {
			field[address.getRow()][address.getColumn()] = 1;
		}
		//second player is 2
		field[SECOND_PLAYER_ADDRESS.getRow()][SECOND_PLAYER_ADDRESS.getColumn()] = 2;
		//unused space is 9
		for(FieldAddress address:UNUSED_AREA) {
			field[address.getRow()][address.getColumn()] = 9;
		}
	}

	public int[][] getField() {
		return field;
	}
}

class FieldAddress {
	 int column;
	 int row;
	 public FieldAddress(int row, int column) {
		 this.column = column;
		 this.row = row;
	 }
	public int getColumn() {
		return column;
	}
	public int getRow() {
		return row;
	}
}
