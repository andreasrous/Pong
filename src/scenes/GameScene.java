package scenes;

import static constants.Constants.BLACK;
import static constants.Constants.EXIT_SCENE;
import static constants.Constants.GAME_HEIGHT;
import static constants.Constants.GAME_WIDTH;
import static constants.Constants.MENU_SCENE;
import static constants.Constants.WHITE;
import static constants.Constants.WINNING_SCORE;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.KeyEvent;

import entities.Ball;
import entities.Enemy;
import entities.Player;
import main.Game;

public class GameScene {

	private Game game;
	private Player player;
	private Enemy enemy;
	private Ball ball;

	public GameScene(Game game) {
		this.game = game;
		player = new Player(game);
		enemy = new Enemy(game);
		ball = new Ball(game, player.getRect(), enemy.getRect());
	}

	public void update() {
		checkForWinner();
		player.update();
		enemy.update();
		ball.update();
	}

	public void render(Graphics g) {
		drawBackground(g);
		player.render(g);
		enemy.render(g);
		ball.render(g);
	}

	public void reset() {
		game.setState(MENU_SCENE);
		player.setUp(false);
		player.setDown(false);
	}

	public void checkForWinner() {
		int playerScore = player.getScore();
		int enemyScore = enemy.getScore();

		if (playerScore == WINNING_SCORE) {
			game.getExitScene().setText("Player wins");
			game.setState(EXIT_SCENE);
		}

		if (enemyScore == WINNING_SCORE) {
			game.getExitScene().setText("Enemy wins");
			game.setState(EXIT_SCENE);
		}
	}

	public void drawBackground(Graphics g) {
		g.setColor(BLACK);
		g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		g.setColor(WHITE);
		Graphics2D g2 = (Graphics2D) g;
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 10 }, 0);
		g2.setStroke(dashed);
		g2.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);
	}

	public Player getPlayer() {
		return player;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public Ball getBall() {
		return ball;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
			player.setUp(true);

		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
			player.setDown(true);
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
			player.setUp(false);

		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
			player.setDown(false);
	}
}
