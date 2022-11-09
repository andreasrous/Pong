package scenes;

import static constants.Constants.BLACK;
import static constants.Constants.CYAN;
import static constants.Constants.GAME_HEIGHT;
import static constants.Constants.GAME_SCENE;
import static constants.Constants.GAME_WIDTH;
import static constants.Constants.TEXT_FONT;
import static constants.Constants.TILE_SIZE;
import static constants.Constants.WHITE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import entities.Rect;
import main.Game;

public class MenuScene {

	private Game game;
	private Font font;
	private Color color1, color2;
	private Rect titleRect, playRect, exitRect;
	private boolean firstTime;

	public MenuScene(Game game) {
		this.game = game;
		font = new Font(TEXT_FONT, Font.BOLD, (int) (TILE_SIZE * 1.25));
		color1 = WHITE;
		color2 = WHITE;
		firstTime = true;
	}

	public void update() {

	}

	public void render(Graphics g) {
		if (firstTime)
			initRect(g);
		g.setColor(BLACK);
		g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		g.setColor(WHITE);
		g.setFont(font);
		g.drawString("Ping Pong", (int) titleRect.x, (int) titleRect.y);
		g.setColor(color1);
		g.drawString("Play", (int) playRect.x, (int) playRect.y);
		g.setColor(color2);
		g.drawString("Exit", (int) exitRect.x, (int) exitRect.y);
	}

	public void initRect(Graphics g) {
		int stringWidth = g.getFontMetrics(font).stringWidth("Ping Pong");
		titleRect = new Rect(GAME_WIDTH / 2 - stringWidth / 2, (int) (GAME_HEIGHT / 2.5), stringWidth,
				(int) (TILE_SIZE * 1.25));

		stringWidth = g.getFontMetrics(font).stringWidth("Play");
		playRect = new Rect(GAME_WIDTH / 2 - stringWidth / 2, (int) (GAME_HEIGHT / 2.09), stringWidth,
				(int) (TILE_SIZE * 1.25));

		stringWidth = g.getFontMetrics(font).stringWidth("Exit");
		exitRect = new Rect(GAME_WIDTH / 2 - stringWidth / 2, (int) (GAME_HEIGHT / 1.8), stringWidth,
				(int) (TILE_SIZE * 1.25));

		firstTime = false;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getX() >= playRect.x && e.getX() <= playRect.x + playRect.width && e.getY() >= playRect.y - TILE_SIZE
				&& e.getY() <= playRect.y + playRect.height - TILE_SIZE) {

			if (game.getGameScene() == null)
				game.createGameScene();

			game.setState(GAME_SCENE);
		}

		if (e.getX() >= exitRect.x && e.getX() <= exitRect.x + exitRect.width && e.getY() >= exitRect.y - TILE_SIZE
				&& e.getY() <= exitRect.y + exitRect.height - TILE_SIZE)
			game.exitGame();
	}

	public void mouseMoved(MouseEvent e) {
		if (e.getX() >= playRect.x && e.getX() <= playRect.x + playRect.width && e.getY() >= playRect.y - TILE_SIZE
				&& e.getY() <= playRect.y + playRect.height - TILE_SIZE) {
			color1 = CYAN;
			color2 = WHITE;
		} else {
			color1 = WHITE;
			color2 = WHITE;
		}

		if (e.getX() >= exitRect.x && e.getX() <= exitRect.x + exitRect.width && e.getY() >= exitRect.y - TILE_SIZE
				&& e.getY() <= exitRect.y + exitRect.height - TILE_SIZE) {
			color1 = WHITE;
			color2 = CYAN;
		} else if (!(e.getX() >= playRect.x && e.getX() <= playRect.x + playRect.width
				&& e.getY() >= playRect.y - TILE_SIZE && e.getY() <= playRect.y + playRect.height - TILE_SIZE)) {
			color1 = WHITE;
			color2 = WHITE;
		}
	}
}
