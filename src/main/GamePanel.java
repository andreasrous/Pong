package main;

import static constants.Constants.BLACK;
import static constants.Constants.GAME_HEIGHT;
import static constants.Constants.GAME_WIDTH;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyHandler;
import inputs.MouseHandler;

public class GamePanel extends JPanel {

	private Game game;
	private KeyHandler keyHandler;
	private MouseHandler mouseHandler;

	public GamePanel(Game game) {
		this.game = game;
		keyHandler = new KeyHandler(game);
		mouseHandler = new MouseHandler(game);
		this.addKeyListener(keyHandler);
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		this.setBackground(BLACK);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.requestFocus();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
	}

	public Game getGame() {
		return game;
	}
}
