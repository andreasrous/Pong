package inputs;

import static constants.Constants.MENU_SCENE;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game;

public class KeyHandler implements KeyListener {

	private Game game;

	public KeyHandler(Game game) {
		this.game = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			game.setState(MENU_SCENE);
		} else if (game.getGameScene() != null) {
			game.getGameScene().keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (game.getGameScene() != null)
			game.getGameScene().keyReleased(e);
	}

}
