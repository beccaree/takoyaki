package info;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class MergingInfo extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MergingInfo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel title_panel = new JPanel();
		contentPane.add(title_panel, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("How To Merge");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		title_panel.add(lblTitle);
		
		JPanel bottom = new JPanel();
		contentPane.add(bottom, BorderLayout.CENTER);
		bottom.setLayout(new BorderLayout(0, 0));
		
		JPanel buttons_panel = new JPanel();
		buttons_panel.setBackground(Color.DARK_GRAY);
		bottom.add(buttons_panel, BorderLayout.NORTH);
		buttons_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		final JLabel lblSubtitle = new JLabel("Merging audio at the beginning");
		final JTextArea textArea = new JTextArea("Instructions plz thx");
		
		JButton btnMergeAudioAt = new JButton("Merge Audio at Beginning");
		btnMergeAudioAt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblSubtitle.setText("Merging audio at the beginning");
				// change explanation
			}
		});
		buttons_panel.add(btnMergeAudioAt);
		
		JButton btnMergeAudioHere = new JButton("Merge Audio here...");
		btnMergeAudioHere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSubtitle.setText("Merging audio at any point");
				//change explanation
			}
		});
		buttons_panel.add(btnMergeAudioHere);
		
		JPanel explain_panel = new JPanel();
		explain_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		bottom.add(explain_panel);
		explain_panel.setLayout(new BorderLayout(0, 0));
		
		lblSubtitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		explain_panel.add(lblSubtitle, BorderLayout.NORTH);
		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		explain_panel.add(textArea);
	}

}
