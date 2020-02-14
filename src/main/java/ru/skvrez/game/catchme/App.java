package ru.skvrez.game.catchme;

public class App 
{
    public static void main( String[] args )
    {
        GameField gameField = new GameField();
        FieldRenderer fieldRenderer = new FieldRenderer(gameField);
        fieldRenderer.show();
    }
}
