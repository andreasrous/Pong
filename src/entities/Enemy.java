package entities;

import static constants.Constants.GAME_HEIGHT;
import static constants.Constants.GAME_WIDTH;
import static constants.Constants.PADDLE_HEIGHT;
import static constants.Constants.PADDLE_SPEED;
import static constants.Constants.PADDLE_WIDTH;
import static constants.Constants.RED;
import static constants.Constants.SCORE_FONT;
import static constants.Constants.TILE_SIZE;
import static constants.Constants.WHITE;

import java.awt.Font;
import java.awt.Graphics;

import main.Game;

public class Enemy extends Player {

	public Enemy(Game game) {
		super(game);
		rect = new Rect(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
				PADDLE_HEIGHT);
	}

	@Override
	public void drawPaddle(Graphics g) {
		g.setColor(RED);
		g.fillRect((int) rect.x, (int) rect.y, (int) rect.width, (int) rect.height);
	}

	@Override
	public void drawScore(Graphics g) {
		g.setColor(WHITE);
		String scoreText = Integer.toString(score);
		Font font = new Font(SCORE_FONT, Font.PLAIN, TILE_SIZE * 2);
		g.setFont(font);
		g.drawString(scoreText, GAME_WIDTH / 2 + TILE_SIZE, 2 * TILE_SIZE);
	}

	@Override
	public void resetPaddle() {
		rect.x = GAME_WIDTH - PADDLE_WIDTH;
		rect.y = (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2);
	}

	@Override
	public void move() {
		if (game.getGameScene().getBall().getRect().y < rect.y) {
			if (rect.y - PADDLE_SPEED > 0) {
				rect.y -= PADDLE_SPEED;
			} else {
				rect.y = 0;
			}
		} else if (game.getGameScene().getBall().getRect().y + game.getGameScene().getBall().getRect().height > rect.y
				+ rect.height) {
			if (rect.y + rect.height + PADDLE_SPEED < GAME_HEIGHT) {
				rect.y += PADDLE_SPEED;
			} else {
				rect.y = GAME_HEIGHT - PADDLE_HEIGHT;
			}
		}
	}
}
