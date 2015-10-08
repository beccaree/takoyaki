package videoPlayer;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * @author Rebecca Lee (Isabel Zhuang - prototype)
 * This class contains all the user interface implementation and functionality of the video player.
 */
public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainFrame(String videoPath, Color theme) {
		setTitle("VIDIVOX - Video/Audio Overlay Platform");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1000, 650);
		
		// Top menu bar implementation -------------------------------------------------->
		JMenuBar menuBar = new MyMenuBar(this);
		setJMenuBar(menuBar);
		
		// Video player implementation -------------------------------------------------->
		JPanel videoPane = new VideoPane(videoPath, this, theme);
		
		// Audio editing implementation ------------------------------------------------->
		JPanel audioPane = new AudioPane(this, theme);
				
				
		// Adding the two different panels to the two sides of the split pane ----------->
		JSplitPane splitPane = new JSplitPane();
		setContentPane(splitPane);
		splitPane.setResizeWeight(0.9); // Resizes the frames in a 8:2 ratio
		splitPane.setLeftComponent(videoPane);
		splitPane.setRightComponent(audioPane);
		splitPane.setDividerLocation(700 + splitPane.getInsets().left);
				
		this.setVisible(true);
		
		VideoPane.setCurrentVideoPath(videoPath);
		
		initialiseVideo();
	}
	
	public static void initialiseVideo() {
		VideoPane.video.playMedia(VideoPane.getCurrentVideoPath()); // Play the video
		// Sets the progress bar to the length of the current video
		final int[] vidLength = {0}; // Initialize as array so final value can be changed
		while(vidLength[0] == 0) {
			vidLength[0] = (int)((VideoPane.video.getLength())/1000);
		}
		VideoPane.setMaxBar(vidLength[0]);
		VideoPane.video.setVolume(50); // Set initial volume to 50 (same as JSlider default value)
	}
}
