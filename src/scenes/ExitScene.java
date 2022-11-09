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

public class ExitScene {

	private Game game;
	private Font font;
	private String text;
	private Color color1, color2;
	private Rect winnerRect, playRect, quitRect;
	private boolean firstTime;

	public ExitScene(Game game) {
		this.game = game;
		font = new Font(TEXT_FONT, Font.BOLD, (int) (TILE_SIZE * 1.25));
		text = "Player wins";
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
		g.setFont(font);
		g.setColor(WHITE);
		g.drawString(text, (int) winnerRect.x, (int) winnerRect.y);
		g.setColor(color1);
		g.drawString("Play again", (int) playRect.x, (int) playRect.y);
		g.setColor(color2);
		g.drawString("Quit", (int) quitRect.x, (int) quitRect.y);
	}

	public void initRect(Graphics g) {
		int stringWidth = g.getFontMetrics(font).stringWidth(text);
		winnerRect = new Rect(GAME_WIDTH / 2 - stringWidth / 2, (int) (GAME_HEIGHT / 2.5), stringWidth,
				(int) (TILE_SIZE * 1.25));

		stringWidth = g.getFontMetrics(font).stringWidth("Play again");
		playRect = new Rect(GAME_WIDTH / 2 - stringWidth / 2, (int) (GAME_HEIGHT / 2.09), stringWidth,
				(int) (TILE_SIZE * 1.25));

		stringWidth = g.getFontMetrics(font).stringWidth("Quit");
		quitRect = new Rect(GAME_WIDTH / 2 - stringWidth / 2, (int) (GAME_HEIGHT / 1.8), stringWidth,
				(int) (TILE_SIZE * 1.25));

		firstTime = false;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getX() >= playRect.x && e.getX() <= playRect.x + playRect.width && e.getY() >= playRect.y - TILE_SIZE
				&& e.getY() <= playRect.y + playRect.height - TILE_SIZE) {
			game.deleteGameScene();
			game.createGameScene();
			game.setState(GAME_SCENE);
		}

		if (e.getX() >= quitRect.x && e.getX() <= quitRect.x + quitRect.width && e.getY() >= quitRect.y - TILE_SIZE
				&& e.getY() <= quitRect.y + quitRect.height - TILE_SIZE)
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

		if (e.getX() >= quitRect.x && e.getX() <= quitRect.x + quitRect.width && e.getY() >= quitRect.y - TILE_SIZE
				&& e.getY() <= quitRect.y + quitRect.height - TILE_SIZE) {
			color1 = WHITE;
			color2 = CYAN;
		} else if (!(e.getX() >= playRect.x && e.getX() <= playRect.x + playRect.width
				&& e.getY() >= playRect.y - TILE_SIZE && e.getY() <= playRect.y + playRect.height - TILE_SIZE)) {
			color1 = WHITE;
			color2 = WHITE;
		}
	}

	public void setText(String text) {
		this.text = text;
	}
}
