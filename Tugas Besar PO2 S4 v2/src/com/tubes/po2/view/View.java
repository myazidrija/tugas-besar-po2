package com.tubes.po2.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.tubes.po2.src.Source;

@SuppressWarnings("serial")
public class View extends JFrame{
	
	private int gameMode = Source.GAME_MODE4X4;
	private JPanel panelsTop[][] = new JPanel[gameMode][gameMode];
	private JPanel panelsBot[][] = new JPanel[gameMode][gameMode];
	
	private JPanel upperPanel;
	
	public View() {
		super("Game Tebak Tempel");
	}
	
	public void initView() {
		addMainPanel();
		
		add(upperPanel, BorderLayout.CENTER);
		setResizable(true);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void addMainPanel() {
		upperPanel = new JPanel();
		upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	//	upperPanel.setBackground(Color.BLUE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
	//	panel.setBackground(Color.CYAN);
		GridBagConstraints cm = new GridBagConstraints();
		
		cm.anchor = GridBagConstraints.NORTHWEST;
		cm.gridx = 0;
		cm.gridy = 0;
		cm.weightx = 0.5;
		cm.weighty = 0.5;
		addGamePanel(panel, cm);
		
		//.anchor = GridBagConstraints.NORTHWEST;
		cm.gridx = 0;
		cm.gridy = 1;
		cm.weightx = 0.5;
		cm.weighty = 0.5;
		addOptionPanel(panel, cm);
		
		upperPanel.add(panel);
	}
	
	public void addGamePanel(JPanel upperPanel, GridBagConstraints cm) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setPreferredSize(new Dimension(720, 440));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
	//	panel.setBackground(Color.GREEN);
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(10,10,10,10);
		panel.add(addLeftGamePanel(), c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(10,10,10,10);
		panel.add(addRightGamePanel(), c);
		
		upperPanel.add(panel, cm);
	}
	
	public void addOptionPanel(JPanel upperPanel, GridBagConstraints cm) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		//panel.setBackground(Color.ORANGE);
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.ipadx = 150;
		c.insets = new Insets(10,10,0,10);
		panel.add(new JLabel("Pilih game mode : "), c);
		
		JComboBox<String> combo = new JComboBox<String>(Source.GAME_MODES);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(0,10,10,10);
		panel.add(combo, c);
		
		JButton button = new JButton("Mulai Game Baru");
		button.setPreferredSize(new Dimension(90,30));
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(0,10,10,10);
		panel.add(button, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(10,10,10,10);
		panel.add(new JLabel("Timer : "), c);
		
		c.anchor = GridBagConstraints.NORTHEAST;
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.ipadx = 167;
		c.insets = new Insets(10,10,10,10);
		panel.add(new JLabel("Score : "), c);
		
		upperPanel.add(panel, cm);
	}
	
	public JPanel addLeftGamePanel() {
		JPanel panelMain = new JPanel();
		panelMain.setLayout(new GridBagLayout());
	//	panelMain.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel panelContent;
		for(int i=0; i<gameMode; i++) {
			for(int j=0; j<gameMode; j++) {
				panelContent = new JPanel();
				panelContent.setPreferredSize(new Dimension(80,100));
				panelContent.setBorder(new BevelBorder(BevelBorder.RAISED));
				c.gridy = i;
				c.gridx = j;
				c.insets = new Insets(2,2,2,2);
				
				setPanelsTopAt(panelContent, j, i);
				panelMain.add(getPanelsTopAt(j, i), c);
			}
		}
		
		return panelMain;
	}
	
	public JPanel addRightGamePanel() {
		JPanel panelMain = new JPanel();
		panelMain.setLayout(new GridBagLayout());
	//	panelMain.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel panelContent;
		for(int i=0; i<gameMode; i++) {
			for(int j=0; j<gameMode; j++) {
				panelContent = new JPanel();
				panelContent.setPreferredSize(new Dimension(80,100));
				panelContent.setBorder(new BevelBorder(BevelBorder.RAISED));
				c.gridy = i;
				c.gridx = j;
				c.insets = new Insets(2,2,2,2);
				
				setPanelsBotAt(panelContent, j, i);
				panelMain.add(getPanelsBotAt(j, i), c);
			}
		}
		
		return panelMain;
	}
	
	public void render() {
		remove(upperPanel);
		addMainPanel();
	}
	
	public JPanel[][] getPanelsTop(){
		return panelsTop;
	}
	
	public void setPanelsTop(JPanel[][] panelsTop) {
		this.panelsTop = panelsTop;
	}
	
	public JPanel getPanelsTopAt(int inX, int inY) {
		return panelsTop[inX][inY];
	}
	
	public void setPanelsTopAt(JPanel panelTop, int inX, int inY) {
		this.panelsTop[inX][inY] = panelTop;
	}
	
	public JPanel[][] getPanelsBot(){
		return panelsBot;
	}
	
	public void setPanelsBottom(JPanel[][] panelsBot) {
		this.panelsBot = panelsBot;
	}
	
	public JPanel getPanelsBotAt(int inX, int inY) {
		return panelsBot[inX][inY];
	}
	
	public void setPanelsBotAt(JPanel panelBot, int inX, int inY) {
		this.panelsBot[inX][inY] = panelBot;
	}
}
