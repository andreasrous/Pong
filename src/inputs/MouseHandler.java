package inputs;

import static constants.Constants.EXIT_SCENE;
import static constants.Constants.MENU_SCENE;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.Game;

public class MouseHandler implements MouseListener, MouseMotionListener {

	private Game game;

	public MouseHandler(Game game) {
		this.game = game;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (game.getState() == EXIT_SCENE)
			game.getExitScene().mouseMoved(e);
		else if (game.getState() == MENU_SCENE)
			game.getMenuScene().mouseMoved(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (game.getState() == EXIT_SCENE)
			game.getExitScene().mouseClicked(e);
		else if (game.getState() == MENU_SCENE)
			game.getMenuScene().mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
