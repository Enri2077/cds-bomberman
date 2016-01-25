package game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import entity.Player;
import graphics.Screen;
import graphics.Sprite;
import input.Keyboard;
import level.Level;
import physics.Updater;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private int gridWidth = 10;
	private int gridHeight = 10;
	private int width = (3 + 2*gridWidth)*Sprite.SPRITE_SIZE, height = (3 + 2*gridHeight)*Sprite.SPRITE_SIZE;
	private int scale = 2;
	private Dimension size;
	private String title = "Bomberman";
	private Thread thread;
	private boolean running = false;
//	private int levelCounter = 0;
	
	private Screen screen;
	private Keyboard input;
//	private Player player;
	private Level level;
//	private Font font;
	private Updater updater;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Game() {
		size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		frame = new JFrame();
		screen = new Screen(width, height);
		input = new Keyboard();
		
		setLevel();
		updater = new Updater(level);
		addKeyListener(input);
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
	//	int frames = 0;
	//	int updates = 0;
		requestFocus();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				update();
			//	updates++;
				delta--;
			}
			
			render();
	//		frames++;
			
			if((System.currentTimeMillis() - timer) > 1000) {
				//frame.setTitle(title + " Alpha - " + updates + " ups , " + frames + " fps");
			//	frames = 0;
			//	updates = 0;
				timer += 1000;
			}
		}
	}
	
	public void update() {	
		input.update();
		updater.update();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		screen.renderLevel(level);
		
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	private void setLevel() {
	//	levelCounter++;
		level = new Level(width,height);
		Player player = new Player(1.5f,2.5f,input,1);
		level.players.add(player);
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(game.title + " Alpha");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}

}
