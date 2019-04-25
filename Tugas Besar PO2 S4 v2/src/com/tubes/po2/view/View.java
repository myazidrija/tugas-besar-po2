package com.tubes.po2.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import com.tubes.po2.src.Source;

@SuppressWarnings("serial")
public class View extends JFrame{
	
	private int gameMode = Source.GAME_MODE4X4;
	private JPanel panelsTop[][] = new JPanel[gameMode][gameMode];
	private JPanel panelsBot[][] = new JPanel[gameMode][gameMode];
	
	public View() {
		super("Game Tebak Tempel");
	}
	
	public void initView() {
		setLayout(new GridBagLayout());
		GridBagConstraints cm = new GridBagConstraints(); 
		
		cm.anchor = GridBagConstraints.NORTHWEST;
		cm.gridx = 0;
		cm.gridy = 0;
		cm.weightx = 0.5;
		cm.weighty = 0.5;
		addMainPanel(cm);
		
		setResizable(true);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void addMainPanel(GridBagConstraints cm) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		//panel.setBackground(Color.GREEN);
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(10,10,10,10);
		panel.add(addTopPanel(), c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(10,10,10,10);
		panel.add(addBottomPanel(), c);
		
		add(panel, cm);
	}
	
	public JPanel addTopPanel() {
		JPanel panelMain = new JPanel();
		panelMain.setLayout(new GridBagLayout());
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
	
	public JPanel addBottomPanel() {
		JPanel panelMain = new JPanel();
		panelMain.setLayout(new GridBagLayout());
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
