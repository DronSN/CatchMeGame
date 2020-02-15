package ru.skvrez.game.catchme;

import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        GameField gameField = new GameField();
        FieldRenderer fieldRenderer = new FieldRenderer(gameField);
        FieldValidator fieldValidator = new FieldValidator(gameField);
        GameFlow gameFlow = new GameFlow(fieldRenderer, fieldValidator);
        gameFlow.startGame();
    }
}
