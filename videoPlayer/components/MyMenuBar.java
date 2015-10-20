package videoPlayer.components;

import info.AudioInfo;
import info.MergingInfo;
import info.VideoInfo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import universalMethods.Utility;
import videoPlayer.MainFrame;

@SuppressWarnings("serial")
public class MyMenuBar extends JMenuBar {
	
	private int currentSkipInterval = 5;
	
	public MyMenuBar(final JFrame parent) {
				
		JMenu mnFile = new JMenu("File");
		add(mnFile);
				
		JMenuItem mntmOpenNewVideo = new JMenuItem("Open New Video...");
		mntmOpenNewVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Changing the video that we are editing
				// Prompt user for the video they want to change to
						
				String newPath;
				JFileChooser videoChooser = new JFileChooser(System.getProperty("user.dir") + "/VideoFiles/");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Video Files (*.avi) or (*.mp4)", "avi", "mp4");
				videoChooser.setFileFilter(filter);
				int okReturnVal = videoChooser.showOpenDialog(getParent());
				if(okReturnVal == JFileChooser.APPROVE_OPTION) {
					newPath = videoChooser.getSelectedFile().getPath();
					if(Utility.isVideo(newPath)) {
						// Set current video path to new path
						VideoPane.setCurrentVideoPath(newPath);
						MainFrame.initialiseVideo();
						VideoPane.setPlayBtnIcon();
					} else {
						JOptionPane.showMessageDialog(parent, "The file you have chosen is not a video, please try again.");
					}
				}
				
			}
		});
		mnFile.add(mntmOpenNewVideo);
		
		JMenuItem mntmSaveProject = new JMenuItem("Save Project");
		mntmSaveProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Save current project
				JFileChooser projectSaver = new JFileChooser(System.getProperty("user.dir") + "/Projects/");
				// Extension that I made up to distinguish from normal text files => ViDivox Project
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Vidivox Project Files (*.vdp)", "vdp");
				projectSaver.setFileFilter(filter);
				File newProject = new File("./Projects/vidi_project" + Utility.fileNumber("./Projects") + ".vdp");
				projectSaver.setSelectedFile(newProject);
				int okReturnVal = projectSaver.showSaveDialog(parent);
				if(okReturnVal == JFileChooser.APPROVE_OPTION) {
					BufferedWriter bw;
					try {
						String newProjectPath = projectSaver.getSelectedFile().getPath();
						bw = new BufferedWriter(new FileWriter(new File(newProjectPath)));
						bw.write(VideoPane.getCurrentVideoPath());
						bw.write("\n" + Integer.toString((MainFrame.getCurrentTheme()).getRGB()));
						bw.write("\n" + currentSkipInterval);
						
						JOptionPane.showMessageDialog(parent, newProjectPath.substring(newProjectPath.lastIndexOf('/')+1, newProjectPath.length()) + " has been saved to the Projects folder");
						
						bw.close();
					} catch (IOException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
				}            
			}
		});
		mnFile.add(mntmSaveProject);
		
		JMenuItem mntmOpenProject = new JMenuItem("Open a Project");
		mntmOpenProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Open a saved project
				JFileChooser projectChooser = new JFileChooser(System.getProperty("user.dir") + "/Projects/");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Vidivox Project Files (*.vdp)", "vdp");
				projectChooser.setFileFilter(filter);
				int okReturnVal = projectChooser.showOpenDialog(getParent());
				if(okReturnVal == JFileChooser.APPROVE_OPTION) {
					String filePath = projectChooser.getSelectedFile().getPath();
				  	
					try {
						FileReader fr = new FileReader(filePath);
						BufferedReader br = new BufferedReader(fr);
						
						String videoPath = br.readLine();
						Color theme = new Color(Integer.parseInt(br.readLine()));
						int skipInterval = Integer.parseInt(br.readLine());
						
						// Set current video path to saved path
						VideoPane.setCurrentVideoPath(videoPath);
						MainFrame.initialiseVideo();
						VideoPane.setPlayBtnIcon();
						
						// Set current theme to saved theme
						VideoPane.setTheme(theme);
						AudioPane.setTheme(theme);
						MainFrame.setCurrentTheme(theme);
						
						//Set current skip interval
						currentSkipInterval = skipInterval;
						VideoPane.setSkipInterval(skipInterval);
						
						br.close();
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mnFile.add(mntmOpenProject);
				
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Exit the program
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
				
		JMenu mnEdit = new JMenu("Edit");
		add(mnEdit);
		
		JMenu mnSkip = new JMenu("Set Skip Interval to...");
		mnEdit.add(mnSkip);
		
		JMenuItem mntmInterval5 = new JMenuItem("5 Seconds");
		mntmInterval5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Set the interval to 5 seconds
				currentSkipInterval = 5;
				VideoPane.setSkipInterval(5);
				JOptionPane.showMessageDialog(parent, "The skip interval has been set to 5 seconds.");
			}
		});
		mnSkip.add(mntmInterval5);
		
		JMenuItem mntmInterval10 = new JMenuItem("10 Seconds");
		mntmInterval10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Set the interval to 10 seconds
				currentSkipInterval = 10;
				VideoPane.setSkipInterval(10);
				JOptionPane.showMessageDialog(parent, "The skip interval has been set to 10 seconds.");
			}
		});
		mnSkip.add(mntmInterval10);
		
		JMenuItem mntmInterval15 = new JMenuItem("15 Seconds");
		mntmInterval15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Set the interval to 15 seconds
				currentSkipInterval = 15;
				VideoPane.setSkipInterval(15);
				JOptionPane.showMessageDialog(parent, "The skip interval has been set to 15 seconds.");
			}
		});
		mnSkip.add(mntmInterval15);
		
		JMenuItem mntmInterval20 = new JMenuItem("20 Seconds");
		mntmInterval20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Set the interval to 20 seconds
				currentSkipInterval = 20;
				VideoPane.setSkipInterval(20);
				JOptionPane.showMessageDialog(parent, "The skip interval has been set to 20 seconds.");
			}
		});
		mnSkip.add(mntmInterval20);
		
		JMenu mnColor = new JMenu("Theme...");
		mnEdit.add(mnColor);
		
		JMenuItem mntmPink = new JMenuItem("Pink");
		mntmPink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Set the theme to pink
				VideoPane.setTheme(Color.pink);
				AudioPane.setTheme(Color.pink);
				MainFrame.setCurrentTheme(Color.pink);
			}
		});
		mnColor.add(mntmPink);
		
		JMenuItem mntmBlue = new JMenuItem("Blue");
		mntmBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Set the theme to blue
				VideoPane.setTheme(Color.cyan);
				AudioPane.setTheme(Color.cyan);
				MainFrame.setCurrentTheme(Color.cyan);
			}
		});
		mnColor.add(mntmBlue);
		
		JMenu mnHelp = new JMenu("Help");
		add(mnHelp);
		
		JMenuItem mntmVideo = new JMenuItem("Video Controls");
		mntmVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Bring up instructions on video control
				VideoInfo vid = new VideoInfo();
				vid.setVisible(true);
			}
		});
		mnHelp.add(mntmVideo);	
		
		JMenuItem mntmMerging = new JMenuItem("Merging");
		mntmMerging.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Bring up instructions on merging
				MergingInfo merge = new MergingInfo();
				merge.setVisible(true);
			}
		});
		mnHelp.add(mntmMerging);
		
		JMenuItem mntmCreating = new JMenuItem("Creating an MP3");
		mntmCreating.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Bring up instructions on creating mp3
				AudioInfo audio = new AudioInfo();
				audio.setVisible(true);
			}
		});
		mnHelp.add(mntmCreating);
	}

}
