package com.tubes.po2.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import com.tubes.po2.controller.Controller;
import com.tubes.po2.src.Source;

@SuppressWarnings("serial")
public class View extends JFrame{
	
	private int gameMode = Source.GAME_MODE2X2;
	private JPanel panelsLeft[][];
	private JPanel panelsRight[][];
	
	private JLabel labelsPanelLeft[][];
	private JLabel labelsPanelRight[][];
	private boolean isPanelLeftActive = false;
	private boolean isPanelRightActive = false;
	
	private int panelLeftActiveIndexX;
	private int panelLeftActiveIndexY;
	private int panelRightActiveIndexX;
	private int panelRightActiveIndexY;
	
	private JButton btnNewGame;
	private JComboBox<String> comboGameMode = new JComboBox<String>(Source.GAME_MODES);
	
	private JPanel upperPanel;
	
	private JLabel lTimer = new JLabel("Timer : ");
	private int timeCount = 0;
	
	public View() {
		super("Game Tebak Tempel");
	}
	
	public void initView() {
		addMainPanel();
		
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
		add(upperPanel, BorderLayout.CENTER);
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
		
		comboGameMode.addItemListener((ItemListener) new Controller(this));
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(0,10,10,10);
		panel.add(comboGameMode, c);
		
		btnNewGame = new JButton("Mulai Game Baru");
		btnNewGame.setPreferredSize(new Dimension(90,30));
		btnNewGame.addActionListener(new Controller(this));
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(0,10,10,10);
		panel.add(btnNewGame, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(10,10,10,10);
		timer();
		panel.add(lTimer, c);
		
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
	
	public void timer() {
		Thread thread = new Thread() {
			public void run() {
				for(int i=0; i<100; i++) {
					try {
						lTimer.setText("Timer : "+timeCount);
						timeCount++;
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}
	
	public JPanel addLeftGamePanel() {
		panelsLeft = new JPanel[gameMode][gameMode];
		labelsPanelLeft = new JLabel[gameMode][gameMode];
		
		String gameData[][] = null;
		switch(comboGameMode.getSelectedIndex()) {
		case 0:
			gameData = Source.DATA_GAME_MODE2x2;
			break;
		case 1:
			gameData = Source.DATA_GAME_MODE3x3;
			break;
		case 2:
			gameData = Source.DATA_GAME_MODE4x4;
			break;
		}
		Source.shuffle(gameData);
		
		JPanel panelMain = new JPanel();
		panelMain.setLayout(new GridBagLayout());
	//	panelMain.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel panelContent;
		for(int i=0; i<gameMode; i++) {
			for(int j=0; j<gameMode; j++) {
				panelContent = new JPanel();
				labelsPanelLeft[i][j] = new JLabel();
				
				panelContent.setBackground(Color.BLUE);
				panelContent.setPreferredSize(new Dimension(80,100));
				panelContent.setBorder(new LineBorder(Color.BLACK, 2));
				panelContent.addMouseListener(new Controller(this, Source.PANEL_LEFT));
				
				labelsPanelLeft[i][j].setText(gameData[i][j]);
				labelsPanelLeft[i][j].setVisible(false);
				panelContent.add(labelsPanelLeft[i][j]);
				
				c.gridy = i;
				c.gridx = j;
				c.insets = new Insets(2,2,2,2);
				
				setPanelsLeftAt(panelContent, i, j);
				panelMain.add(getPanelsLeftAt(i, j), c);
			}
		}
		
		return panelMain;
	}
	
	public JPanel addRightGamePanel() {
		panelsRight = new JPanel[gameMode][gameMode];
		labelsPanelRight = new JLabel[gameMode][gameMode];
		
		String gameData[][] = null;
		switch(comboGameMode.getSelectedIndex()) {
		case 0:
			gameData = Source.DATA_GAME_MODE2x2;
			break;
		case 1:
			gameData = Source.DATA_GAME_MODE3x3;
			break;
		case 2:
			gameData = Source.DATA_GAME_MODE4x4;
			break;
		}
		Source.shuffle(gameData);
		
		JPanel panelMain = new JPanel();
		panelMain.setLayout(new GridBagLayout());
	//	panelMain.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel panelContent;
		for(int i=0; i<gameMode; i++) {
			for(int j=0; j<gameMode; j++) {
				panelContent = new JPanel();
				labelsPanelRight[i][j] = new JLabel();
				
				panelContent.setBackground(Color.BLUE);
				panelContent.setPreferredSize(new Dimension(80,100));
				panelContent.setBorder(new LineBorder(Color.BLACK, 2));
				panelContent.addMouseListener(new Controller(this, Source.PANEL_RIGHT));
				
				labelsPanelRight[i][j].setText(gameData[i][j]);
				labelsPanelRight[i][j].setVisible(false);
				panelContent.add(labelsPanelRight[i][j]);
				
				c.gridy = i;
				c.gridx = j;
				c.insets = new Insets(2,2,2,2);
				
				setPanelsRightAt(panelContent, i, j);
				panelMain.add(getPanelsRightAt(i, j), c);
			}
		}
		
		return panelMain;
	}
	
	public void render() {
		remove(upperPanel);
		addMainPanel();
		
		comboGameMode.setSelectedIndex(gameMode-2);
		
		isPanelLeftActive = false;
		isPanelRightActive = false;
		
		validate();
	}
	
	
	
	public int getGameMode() {
		return gameMode;
	}

	public void setGameMode(int gameMode) {
		this.gameMode = gameMode;
	}

	public JPanel[][] getPanelsLeft(){
		return panelsLeft;
	}
	
	public void setPanelsLeft(JPanel[][] panelsTop) {
		this.panelsLeft = panelsTop;
	}
	
	public JPanel getPanelsLeftAt(int x, int y) {
		return panelsLeft[x][y];
	}
	
	public JLabel getLabelsLeftAt(int x, int y) {
		return labelsPanelLeft[x][y];
	}
	
	public JLabel getLabelsRightAt(int x, int y) {
		return labelsPanelRight[x][y];
	}
	
	public boolean isPanelLeftActive() {
		return isPanelLeftActive;
	}

	public void setPanelLeftActive(boolean isPanelLeftActive) {
		this.isPanelLeftActive = isPanelLeftActive;
	}

	public boolean isPanelRightActive() {
		return isPanelRightActive;
	}

	public void setPanelRightActive(boolean isPanelRightActive) {
		this.isPanelRightActive = isPanelRightActive;
	}

	public void setPanelsLeftAt(JPanel panelTop, int x, int y) {
		this.panelsLeft[x][y] = panelTop;
	}
	
	public JPanel[][] getPanelsRight(){
		return panelsRight;
	}
	
	public void setPanelsRight(JPanel[][] panelsBot) {
		this.panelsRight = panelsBot;
	}
	
	public JPanel getPanelsRightAt(int x, int y) {
		return panelsRight[x][y];
	}
	
	public void setPanelsRightAt(JPanel panelBot, int x, int y) {
		this.panelsRight[x][y] = panelBot;
	}

	public JButton getBtnNewGame() {
		return btnNewGame;
	}
	
	public void setBtnNewGame(JButton btnNewGame) {
		this.btnNewGame = btnNewGame;
	}
	
	public JComboBox<String> getComboGameMode() {
		return comboGameMode;
	}
	
	public void setComboGameMode(JComboBox<String> comboGameMode) {
		this.comboGameMode = comboGameMode;
	}

	public int getPanelLeftActiveIndexX() {
		return panelLeftActiveIndexX;
	}

	public int getPanelLeftActiveIndexY() {
		return panelLeftActiveIndexY;
	}
	
	public void setPanelLeftActiveIndex(int x, int y) {
		this.panelLeftActiveIndexX = x;
		this.panelLeftActiveIndexY = y;
	}

	public int getPanelRightActiveIndexX() {
		return panelRightActiveIndexX;
	}

	public int getPanelRightActiveIndexY() {
		return panelRightActiveIndexY;
	}
	
	public void setPanelRightActiveIndex(int x, int y) {
		this.panelRightActiveIndexX = x;
		this.panelRightActiveIndexY = y;
	}
}
