package entities;

import static constants.Constants.BALL_SIZE;
import static constants.Constants.BALL_SPEED;
import static constants.Constants.GAME_HEIGHT;
import static constants.Constants.GAME_WIDTH;
import static constants.Constants.MAX_ANGLE;
import static constants.Constants.WHITE;
import static constants.Constants.WINNING_SCORE;

import java.awt.Graphics;
import java.security.SecureRandom;

import main.Game;

public class Ball {

	private double vx;
	private double vy;
	private Game game;
	private SecureRandom random;

	private Rect rect;
	private Rect leftPaddle;
	private Rect rightPaddle;

	public Ball(Game game, Rect leftPaddle, Rect rightPaddle) {
		this.game = game;
		random = new SecureRandom();
		rect = new Rect((GAME_WIDTH / 2) - (BALL_SIZE / 2), (GAME_HEIGHT / 2) - (BALL_SIZE / 2), BALL_SIZE, BALL_SIZE);
		this.leftPaddle = leftPaddle;
		this.rightPaddle = rightPaddle;
		resetBall();
	}

	public void update() {
		move();
	}

	public void render(Graphics g) {
		g.setColor(WHITE);
		g.fillOval((int) rect.x, (int) rect.y, (int) rect.width, (int) rect.height);
	}

	private double calculateAngle(Rect paddle) {
		double relativeIntersectY = (paddle.y + paddle.height / 2.0) - (rect.y + rect.height / 2.0);
		double normalIntersectY = relativeIntersectY / (paddle.height / 2.0);
		double theta = normalIntersectY * MAX_ANGLE;
		return Math.toRadians(theta);
	}

	public void resetBall() {
		rect.x = (GAME_WIDTH / 2) - (BALL_SIZE / 2);
		rect.y = (GAME_HEIGHT / 2) - (BALL_SIZE / 2);

		double theta = Math.toRadians(random.nextInt(70));

		int signVx = random.nextInt(2);
		int signVy = random.nextInt(2);

		if (signVx == 0)
			signVx = -1;

		if (signVy == 0)
			signVy = -1;

		vx = signVx * Math.cos(theta) * BALL_SPEED;
		vy = signVy * Math.sin(theta) * BALL_SPEED;
	}

	public void move() {
		if (vx < 0) {
			if (rect.x <= leftPaddle.x + leftPaddle.width && rect.x + rect.width >= leftPaddle.x
					&& rect.y >= leftPaddle.y && rect.y <= leftPaddle.y + leftPaddle.height) {
				double theta = calculateAngle(leftPaddle);
				vx = Math.abs(Math.cos(theta) * BALL_SPEED);
				vy = -1.0 * Math.sin(theta) * BALL_SPEED;
			} else if (rect.x + rect.width < leftPaddle.x) {
				game.getGameScene().getEnemy().incrementScore();

				if (game.getGameScene().getEnemy().getScore() != WINNING_SCORE) {
					game.getGameScene().getEnemy().resetPaddle();
					game.getGameScene().getPlayer().resetPaddle();
					resetBall();
				}
			}
		} else if (vx > 0) {
			if (rect.x + rect.width >= rightPaddle.x && rect.x <= rightPaddle.x + rightPaddle.width
					&& rect.y >= rightPaddle.y && rect.y <= rightPaddle.y + rightPaddle.height) {
				double theta = calculateAngle(rightPaddle);
				vx = -1.0 * Math.abs(Math.cos(theta) * BALL_SPEED);
				vy = -1.0 * Math.sin(theta) * BALL_SPEED;
			} else if (rect.x + rect.width > rightPaddle.x + rightPaddle.width) {
				game.getGameScene().getPlayer().incrementScore();

				if (game.getGameScene().getPlayer().getScore() != WINNING_SCORE) {
					game.getGameScene().getPlayer().resetPaddle();
					game.getGameScene().getEnemy().resetPaddle();
					resetBall();
				}
			}
		}

		if (vy > 0 && rect.y + rect.height > GAME_HEIGHT || vy < 0 && rect.y < 0)
			vy *= -1;

		rect.x += vx;
		rect.y += vy;
	}

	public Rect getRect() {
		return rect;
	}
}
