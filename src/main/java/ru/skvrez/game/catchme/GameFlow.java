package ru.skvrez.game.catchme;

import static ru.skvrez.game.catchme.PlayerName.FIRST_PLAYER;
import static ru.skvrez.game.catchme.PlayerName.SECOND_PLAYER;

public class GameFlow {
	
    private FieldRenderer fieldRenderer;
    private FieldValidator fieldValidator;
	
    public GameFlow(FieldRenderer fieldRenderer, FieldValidator fieldValidator) {
    	this.fieldRenderer = fieldRenderer;
    	this.fieldValidator = fieldValidator;
    }
    
    public void startGame() {
        boolean firstPlayer = true;
        while(true) {
        	if (firstPlayer) {
        		if(isGameComplite(FIRST_PLAYER)) {
        			break;
        		}
        		firstPlayer = false;
        	} else {
        		if(isGameComplite(SECOND_PLAYER)) {
        			break;
        		}
        		firstPlayer = true;
        	}
        }
    }
    
    public boolean isGameComplite(PlayerName playerName) {
		if(!fieldValidator.isValidField(playerName)) {
			fieldRenderer.show();
			fieldRenderer.showWin(fieldValidator.getWinPlayer());        			
			return true;
		}
		fieldRenderer.playerGameStep(playerName);
		return false;
    }
}
