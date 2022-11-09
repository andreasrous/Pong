package main;

import static constants.Constants.EXIT_SCENE;
import static constants.Constants.FPS;
import static constants.Constants.GAME_SCENE;
import static constants.Constants.MENU_SCENE;

import java.awt.Graphics;

import scenes.ExitScene;
import scenes.GameScene;
import scenes.MenuScene;

public class Game implements Runnable {

	private int state;
	private boolean running;
	private Thread gameThread;
	private GameScene gameScene;
	private MenuScene menuScene;
	private ExitScene exitScene;
	private GamePanel gamePanel;
	private GameFrame gameFrame;

	public Game() {
		running = true;
		state = MENU_SCENE;
		gameScene = new GameScene(this);
		menuScene = new MenuScene(this);
		exitScene = new ExitScene(this);
		gamePanel = new GamePanel(this);
		gameFrame = new GameFrame(gamePanel);
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update() {
		if (state == GAME_SCENE)
			gameScene.update();
		else if (state == MENU_SCENE)
			menuScene.update();
		else if (state == EXIT_SCENE)
			exitScene.update();
	}

	public void render(Graphics g) {
		if (state == GAME_SCENE)
			gameScene.render(g);
		else if (state == MENU_SCENE)
			menuScene.render(g);
		else if (state == EXIT_SCENE)
			exitScene.render(g);
	}

	public void windowFocusLost() {
		if (state == GAME_SCENE)
			gameScene.reset();
	}

	public void exitGame() {
		running = false;
		gameFrame.dispose();
	}

	public void deleteGameScene() {
		gameScene = null;
	}

	public void createGameScene() {
		gameScene = new GameScene(this);
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public GameScene getGameScene() {
		return gameScene;
	}

	public MenuScene getMenuScene() {
		return menuScene;
	}

	public ExitScene getExitScene() {
		return exitScene;
	}

	@Override
	public void run() {
		int frames = 0;
		double delta = 0;
		double timePerFrame = 1000000000.0 / FPS;
		long previousTime = System.nanoTime();
		double lastCheck = System.currentTimeMillis();

		while (running) {
			long currentTime = System.nanoTime();
			delta += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (delta >= 1) {
				update();
				gamePanel.repaint();
				frames++;
				delta--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				System.out.println("FPS: " + frames);
				lastCheck += 1000;
				frames = 0;
			}
		}
	}

	public static void main(String[] args) {
		new Game();
	}
}
