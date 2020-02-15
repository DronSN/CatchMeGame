package ru.skvrez.game.catchme;

public class FieldAddress {
	 private int column;
	 private int row;
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
