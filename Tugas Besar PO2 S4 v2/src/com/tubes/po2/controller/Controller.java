package com.tubes.po2.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.tubes.po2.src.Source;
import com.tubes.po2.view.View;

public class Controller implements ActionListener, ItemListener, MouseListener{

	private View view;
	private String panelPosition;
	
	public Controller(View view) {
		this.view = view;
	}
	
	public Controller(View view, String panelPosition) {
		this.view = view;
		this.panelPosition = panelPosition;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == view.getBtnNewGame()) {
			view.render();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		switch(view.getComboGameMode().getSelectedIndex()) {
		case 0:
			view.setGameMode(Source.GAME_MODE2X2);
			break;
		case 1:
			view.setGameMode(Source.GAME_MODE3X3);
			break;
		case 2:
			view.setGameMode(Source.GAME_MODE4X4);
			break;
		default:
			view.setGameMode(Source.GAME_MODE2X2);
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(panelPosition.equals(Source.PANEL_LEFT)) {  
			for(int i=0; i<view.getGameMode(); i++) {
				for(int j=0; j<view.getGameMode(); j++) {
					if(e.getSource() == view.getPanelsLeftAt(i, j)) {
						if(view.isPanelRightActive()) {
							int x = view.getPanelRightActiveIndexX();
							int y = view.getPanelRightActiveIndexY();
							
							if(view.getLabelsLeftAt(i, j).getText()
									.equals(view.getLabelsRightAt(x, y).getText())) {
								view.getPanelsLeftAt(i, j).setBackground(Color.WHITE);
								view.getLabelsLeftAt(i, j).setVisible(true);
								view.setPanelLeftActive(false);
								view.setPanelRightActive(false);
							} else {
								view.getPanelsRightAt(x, y).setBackground(Color.BLUE);
								view.getLabelsRightAt(x, y).setVisible(false);
								view.setPanelRightActive(false);
							}
							
						} else {
							view.getPanelsLeftAt(i, j).setBackground(Color.WHITE);
							view.getLabelsLeftAt(i, j).setVisible(true);
							view.setPanelLeftActiveIndex(i, j);
							view.setPanelLeftActive(true);
						}
					}
				}
			}
		} else {
			for(int i=0; i<view.getGameMode(); i++) {
				for(int j=0; j<view.getGameMode(); j++) {
					if(e.getSource() == view.getPanelsRightAt(i, j)) {
						if(view.isPanelLeftActive()) {
							int x = view.getPanelLeftActiveIndexX();
							int y = view.getPanelLeftActiveIndexY();
							
							if(view.getLabelsRightAt(i, j).getText()
									.equals(view.getLabelsLeftAt(x, y).getText())) {
								view.getPanelsRightAt(i, j).setBackground(Color.WHITE);
								view.getLabelsRightAt(i, j).setVisible(true);
								view.setPanelRightActive(false);
								view.setPanelLeftActive(false);
							} else {
								view.getPanelsLeftAt(x, y).setBackground(Color.BLUE);
								view.getLabelsLeftAt(x, y).setVisible(false);
								view.setPanelLeftActive(false);
							}
							
						} else {
							view.getPanelsRightAt(i, j).setBackground(Color.WHITE);
							view.getLabelsRightAt(i, j).setVisible(true);
							view.setPanelRightActiveIndex(i, j);
							view.setPanelRightActive(true);
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

}
