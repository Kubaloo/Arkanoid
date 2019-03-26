package Graphics;



import java.awt.image.BufferedImage;

public class Assets {
	public static BufferedImage background, menuBackground,startButton;
	public static void init(){
		background =  ImageLoader.loadImage("/background/pepe3.png");
		menuBackground = ImageLoader.loadImage("/background/pepe2.png");
		startButton = ImageLoader.loadImage("/buttons/start.png");
	}
}

