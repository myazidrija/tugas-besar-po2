package com.tubes.po2.view;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import com.tubes.po2.controller.Controller;
import com.tubes.po2.src.Source;

public class View extends JFrame{

    private static final String GAME_NAME = "TEBAK TEMPEL";

	private int gameMode = Source.GAME_MODE2X2;
	private JPanel[][] panelsLeft;
	private JPanel[][] panelsRight;
	
	private JLabel[][] labelsPanelLeft;
	private JLabel[][] labelsPanelRight;

	private Thread thread;

	private int panelLeftActiveIndexX;
	private int panelLeftActiveIndexY;
	private int panelRightActiveIndexX;
	private int panelRightActiveIndexY;
    private boolean isPanelLeftActive = false;
    private boolean isPanelRightActive = false;

	private JButton btnNewGame;
	private JComboBox<String> comboGameMode;

	private JPanel mainContainer2nd;
	private JPanel gamePanel;
    private GridBagConstraints cm;

	private JLabel labelTimerCount;
	private int timeCount = 0;
	private int timeLimit = 10;
	private int indexTimer = 0;

	private JLabel labelScoresCount;
	private int scores = 0;

	private String[][] gameData;

	public View() {
		super(GAME_NAME);

		getContentPane().add(mainPanel(), BorderLayout.CENTER);
	}
	
	public void initView() {
		setResizable(false);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private JPanel mainPanel() {
        JPanel mainContainer1st = new JPanel();
		mainContainer1st.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		mainContainer2nd = new JPanel();
		mainContainer2nd.setLayout(new GridBagLayout());

        cm = new GridBagConstraints();
		putOptionPanelOnMainPanel();
		putGamePanelOnMainPanel();
		
		mainContainer1st.add(mainContainer2nd);
		return mainContainer1st;
	}

	private void putGamePanelOnMainPanel(){
        cm.anchor = GridBagConstraints.NORTHWEST;
        cm.gridx = 0;
        cm.gridy = 0;
        cm.weightx = 0.5;
        cm.weighty = 0.5;
        mainContainer2nd.add(gamePanel(), cm);
    }

    private void putOptionPanelOnMainPanel(){
        cm.gridx = 0;
        cm.gridy = 1;
        cm.weightx = 0.5;
        cm.weighty = 0.5;
        mainContainer2nd.add(optionPanel(), cm);
    }

	private JPanel gamePanel() {
		gamePanel = new JPanel();
		gamePanel.setLayout(new GridBagLayout());
		gamePanel.setPreferredSize(new Dimension(720, 440));
		gamePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(10,10,10,10);
		gamePanel.add(leftGamePanel(), c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(10,10,10,10);
		gamePanel.add(rightGamePanel(), c);

        timer();

		return gamePanel;
	}
	
	private JPanel optionPanel() {
		JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new GridBagLayout());
        optionPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		GridBagConstraints c = new GridBagConstraints();

        JLabel labelGameMode = new JLabel("Pilih game mode : ");
        labelGameMode.setPreferredSize(new Dimension(80, 16));
        labelGameMode.setFont(new Font("Aquatico", Font.PLAIN, 12));
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.ipadx = 130;
		c.insets = new Insets(10,10,10,10);
        optionPanel.add(labelGameMode, c);

        comboGameMode = new JComboBox<>(Source.GAME_MODES);
		comboGameMode.addItemListener(new Controller(this));
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(0,10,10,10);
        optionPanel.add(comboGameMode, c);
		
		btnNewGame = new JButton("Start New Game");
		btnNewGame.setPreferredSize(new Dimension(90,30));
		btnNewGame.addActionListener(new Controller(this));
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(0,10,10,10);
        optionPanel.add(btnNewGame, c);

        JLabel labelTimer = new JLabel("Timer : Limit 10 seconds");
        labelTimer.setFont(new Font("Aquatico", Font.PLAIN, 12));
        labelTimer.setHorizontalAlignment(JLabel.CENTER);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.ipadx = 80;
		c.insets = new Insets(10,10,0,10);
        optionPanel.add(labelTimer, c);

        labelTimerCount = new JLabel(String.valueOf(timeCount));
        labelTimerCount.setHorizontalAlignment(JLabel.CENTER);
        labelTimerCount.setFont(new Font("Apex Mk3 ExtraLight", Font.BOLD, 50));
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 2;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.ipadx = 80;
        c.insets = new Insets(0,10,10,10);
        c.fill = GridBagConstraints.BOTH;
        optionPanel.add(labelTimerCount, c);

        JLabel labelScores = new JLabel("Scores : ");
        labelScores.setFont(new Font("Aquatico", Font.PLAIN, 12));
        labelScores.setHorizontalAlignment(JLabel.CENTER);
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(10,10,0,10);
        c.fill = GridBagConstraints.HORIZONTAL;
        optionPanel.add(labelScores, c);

        labelScoresCount = new JLabel("00");
        labelScoresCount.setFont(new Font("Apex Mk3 ExtraLight", Font.BOLD, 50));
        labelScoresCount.setHorizontalAlignment(JLabel.CENTER);
        labelScoresCount.setPreferredSize(new Dimension(90,60));
        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 2;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.insets = new Insets(0,10,10,10);
        c.fill = GridBagConstraints.NONE;
        optionPanel.add(labelScoresCount, c);

		return optionPanel;
	}
	
	private JPanel leftGamePanel() {
		panelsLeft = new JPanel[gameMode][gameMode];
		labelsPanelLeft = new JLabel[gameMode][gameMode];

		switch(gameMode-2) {
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
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel panelContent;
		for(int i=0; i<gameMode; i++) {
			for(int j=0; j<gameMode; j++) {
				panelContent = new JPanel();
				labelsPanelLeft[i][j] = new JLabel();

				panelContent.setLayout(new BorderLayout());
				panelContent.setBackground(Color.BLUE);
				panelContent.setPreferredSize(new Dimension(80,100));
				panelContent.setBorder(new LineBorder(Color.BLACK, 2));
				panelContent.addMouseListener(new Controller(this, Source.PANEL_LEFT));
				
				labelsPanelLeft[i][j].setText(gameData[i][j]);
                labelsPanelLeft[i][j].setFont(new Font("Aquatico", Font.PLAIN, 32));
                labelsPanelLeft[i][j].setHorizontalAlignment(JLabel.CENTER);
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
	
	private JPanel rightGamePanel() {
		panelsRight = new JPanel[gameMode][gameMode];
		labelsPanelRight = new JLabel[gameMode][gameMode];

		switch(gameMode-2) {
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
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel panelContent;
		for(int i=0; i<gameMode; i++) {
			for(int j=0; j<gameMode; j++) {
				panelContent = new JPanel();
				labelsPanelRight[i][j] = new JLabel();

				panelContent.setLayout(new BorderLayout());
				panelContent.setBackground(Color.BLUE);
				panelContent.setPreferredSize(new Dimension(80,100));
				panelContent.setBorder(new LineBorder(Color.BLACK, 2));
				panelContent.addMouseListener(new Controller(this, Source.PANEL_RIGHT));
				
				labelsPanelRight[i][j].setText(gameData[i][j]);
                labelsPanelRight[i][j].setFont(new Font("Aquatico", Font.PLAIN, 32));
                labelsPanelRight[i][j].setHorizontalAlignment(JLabel.CENTER);
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
		mainContainer2nd.remove(gamePanel);
		
		putGamePanelOnMainPanel();

		isPanelLeftActive = false;
		isPanelRightActive = false;

		mainContainer2nd.validate();
	}

    private void timer() {
        if(thread == null){
            thread = new Thread(() -> {
                for(indexTimer = 1; indexTimer<=timeLimit; indexTimer++) {
                    try {

                            timeCount = indexTimer;
                            if(timeCount < 10){
                                labelTimerCount.setText("0"+timeCount);
                            }
                            if(timeCount == 10) {
                                labelTimerCount.setText(""+timeCount);
                                JOptionPane.showMessageDialog(null, "Anda kalah, karena kehabisan waktu");
                                render();
                                indexTimer = 0;
                            }
                            Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        timeCount = 0;
                        labelTimerCount.setText("0"+timeCount);
                        return;
                    }
                }
            });

            thread.start();
        }
    }

	public int getGameMode() {
		return gameMode;
	}

	public void setGameMode(int gameMode) {
		this.gameMode = gameMode;
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

	private void setPanelsLeftAt(JPanel panelTop, int x, int y) {
		this.panelsLeft[x][y] = panelTop;
	}
	
	public JPanel getPanelsRightAt(int x, int y) {
		return panelsRight[x][y];
	}
	
	private void setPanelsRightAt(JPanel panelBot, int x, int y) {
		this.panelsRight[x][y] = panelBot;
	}

	public JButton getBtnNewGame() {
		return btnNewGame;
	}
	
	public JComboBox<String> getComboGameMode() {
		return comboGameMode;
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

	public void resetTimer() {
		timer();
	}

	public JLabel[][] getLabelsPanelLeft() {
		return labelsPanelLeft;
	}

	public int getScores() {
		return scores;
	}

	public void resetScores() {
		scores = 0;
        labelScoresCount.setText("0"+scores);
	}

	public void setScores(int newScores) {
		if((scores + newScores) < 0){
			scores = 0;
		} else {
			scores += newScores;
		}
		if(scores < 10){
            labelScoresCount.setText("0"+scores);
        } else {
            labelScoresCount.setText(""+scores);
        }
	}

    public void interruptTimerThread(){
	    if(thread != null){
	        thread.interrupt();
	        thread = null;
        }
    }
}
