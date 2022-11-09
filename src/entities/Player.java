package entities;

import static constants.Constants.GAME_HEIGHT;
import static constants.Constants.GAME_WIDTH;
import static constants.Constants.GREEN;
import static constants.Constants.PADDLE_HEIGHT;
import static constants.Constants.PADDLE_SPEED;
import static constants.Constants.PADDLE_WIDTH;
import static constants.Constants.SCORE_FONT;
import static constants.Constants.TILE_SIZE;
import static constants.Constants.WHITE;
import static constants.Constants.WINNING_SCORE;

import java.awt.Font;
import java.awt.Graphics;

import main.Game;

public class Player {

	protected Game game;
	protected Rect rect;
	protected int score;
	protected long lastCheck;
	protected boolean up, down;

	public Player(Game game) {
		this.game = game;
		rect = new Rect(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT);
		score = 0;
	}

	public void update() {
		move();
	}

	public void render(Graphics g) {
		drawPaddle(g);
		if (score != WINNING_SCORE)
			drawScore(g);
	}

	public void move() {
		if (!(up && down)) {
			if (up && rect.y - PADDLE_SPEED > 0)
				rect.y -= PADDLE_SPEED;
			else if (up && rect.y - PADDLE_SPEED <= 0)
				rect.y = 0;
			else if (down && rect.y + rect.height + PADDLE_SPEED < GAME_HEIGHT)
				rect.y += PADDLE_SPEED;
			else if (down && rect.y + rect.height + PADDLE_SPEED >= GAME_HEIGHT)
				rect.y = GAME_HEIGHT - PADDLE_HEIGHT;
		}
	}

	public void drawPaddle(Graphics g) {
		g.setColor(GREEN);
		g.fillRect((int) rect.x, (int) rect.y, (int) rect.width, (int) rect.height);
	}

	public void drawScore(Graphics g) {
		g.setColor(WHITE);
		String scoreText = Integer.toString(score);
		Font font = new Font(SCORE_FONT, Font.PLAIN, TILE_SIZE * 2);
		int stringWidth = g.getFontMetrics(font).stringWidth(scoreText) + 1;
		g.setFont(font);
		g.drawString(scoreText, GAME_WIDTH / 2 - TILE_SIZE - stringWidth, 2 * TILE_SIZE);
	}

	public void resetPaddle() {
		rect.x = 0;
		rect.y = (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2);
	}

	public void resetScore() {
		score = 0;
	}

	public void incrementScore() {
		score++;
	}

	public Rect getRect() {
		return rect;
	}

	public int getScore() {
		return score;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
}
