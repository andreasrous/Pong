package constants;

import java.awt.Color;

public class Constants {
	public static final int FPS = 60;
	public static final int MAX_ANGLE = 60;
	public static final int WINNING_SCORE = 6;
	public static final int BALL_SPEED = 7;
	public static final int PADDLE_SPEED = 5;

	public static final int TILE_SIZE = 24;
	public static final int TILES_IN_WIDTH = 48;
	public static final int TILES_IN_HEIGHT = 32;
	public static final int GAME_WIDTH = TILE_SIZE * TILES_IN_WIDTH;
	public static final int GAME_HEIGHT = TILE_SIZE * TILES_IN_HEIGHT;

	public static final int GAME_SCENE = 1;
	public static final int MENU_SCENE = 2;
	public static final int EXIT_SCENE = 3;

	public static final int PADDLE_WIDTH = 20;
	public static final int PADDLE_HEIGHT = 125;
	public static final int BALL_SIZE = PADDLE_WIDTH;

	public static final String TEXT_FONT = "Times New Roman";
	public static final String SCORE_FONT = "Cambria Math";

	public static Color BLACK = new Color(45, 42, 46);
	public static Color RED = new Color(255, 6, 0);
	public static Color GREEN = new Color(68, 214, 44);
	public static Color YELLOW = new Color(255, 173, 0);
	public static Color BLUE = new Color(102, 153, 223);
	public static Color PURPLE = new Color(171, 157, 242);
	public static Color CYAN = new Color(89, 203, 232);
	public static Color WHITE = new Color(229, 255, 230);
	public static Color GRAY = new Color(217, 217, 214);
}
