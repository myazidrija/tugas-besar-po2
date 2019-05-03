package com.tubes.po2.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import com.tubes.po2.src.Source;
import com.tubes.po2.view.View;

public class Controller implements ActionListener, ItemListener, MouseListener{

	private View view;
	private String panelPosition;
	private static int gameMode;

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
			view.resetScores();
			view.interruptTimerThread();
			view.resetTimer();
			view.setGameMode(gameMode);
			view.render();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		switch(view.getComboGameMode().getSelectedIndex()) {
		case 0:
			gameMode = Source.GAME_MODE2X2;
			break;
		case 1:
			gameMode = Source.GAME_MODE3X3;
			break;
		case 2:
			gameMode = Source.GAME_MODE4X4;
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
		int option;
		if(panelPosition.equals(Source.PANEL_LEFT)) {  
			for(int i=0; i<view.getGameMode(); i++) {
				for(int j=0; j<view.getGameMode(); j++) {
					if(e.getSource() == view.getPanelsLeftAt(i, j)) {
						int x = view.getPanelRightActiveIndexX();
						int y = view.getPanelRightActiveIndexY();
						
						if(view.isPanelRightActive()) {
							if(!view.getLabelsLeftAt(i, j).isVisible()) {
								if(view.getLabelsLeftAt(i, j).getText()
										.equals(view.getLabelsRightAt(x, y).getText())) {
									view.getPanelsLeftAt(i, j).setBackground(Color.WHITE);
									view.getLabelsLeftAt(i, j).setVisible(true);
									view.setPanelLeftActive(false);
									view.setPanelRightActive(false);
									view.setScores(5);
									view.interruptTimerThread();
									view.resetTimer();
									if(isConditionWin()){
                                        view.interruptTimerThread();
										option = JOptionPane.showConfirmDialog(null
												, "Anda menang dengan score : "+view.getScores()+", mau main lagi?"
												, "Game Over"
												, JOptionPane.OK_CANCEL_OPTION
												, JOptionPane.QUESTION_MESSAGE
												, null);
										if(option == 0){
											view.resetScores();
											view.resetTimer();
											view.render();
										} else {
											System.exit(0);
										}
									}
								} else {
									closedAnimation(i, j, x, y);
									view.setScores(-2);
								}
							}
						} else {
							if(!view.isPanelLeftActive()) {
								if(!view.getLabelsLeftAt(i, j).isVisible()) {
									view.getPanelsLeftAt(i, j).setBackground(Color.WHITE);
									view.getLabelsLeftAt(i, j).setVisible(true);
									view.setPanelLeftActiveIndex(i, j);
									view.setPanelLeftActive(true);
								}
							}
						}
					}
				}
			}
		} else {
			for(int i=0; i<view.getGameMode(); i++) {
				for(int j=0; j<view.getGameMode(); j++) {
					if(e.getSource() == view.getPanelsRightAt(i, j)) {
						int x = view.getPanelLeftActiveIndexX();
						int y = view.getPanelLeftActiveIndexY();
						
						if(view.isPanelLeftActive()) {
							if(!view.getLabelsRightAt(i, j).isVisible()) {
								if(view.getLabelsRightAt(i, j).getText()
										.equals(view.getLabelsLeftAt(x, y).getText())) {
									view.getPanelsRightAt(i, j).setBackground(Color.WHITE);
									view.getLabelsRightAt(i, j).setVisible(true);
									view.setPanelRightActive(false);
									view.setPanelLeftActive(false);
									view.setScores(5);
									view.interruptTimerThread();
									view.resetTimer();
									if(isConditionWin()){
                                        view.interruptTimerThread();
										option = JOptionPane.showConfirmDialog(null
												, "Anda menang dengan score : "+view.getScores()+", mau main lagi?"
												, "Game Over"
												, JOptionPane.OK_CANCEL_OPTION
												, JOptionPane.QUESTION_MESSAGE
												, null);
										if(option == 0){
											view.resetScores();
											view.resetTimer();
											view.render();
										} else {
											System.exit(0);
										}
									}
								} else {
									closedAnimation(i, j, x, y);
									view.setScores(-2);
								}
							}
							
						} else {
							if(!view.isPanelRightActive()) {
								if(!view.getLabelsRightAt(i, j).isVisible()) {
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
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	private void closedAnimation(int x1, int y1, int x2, int y2) {
		Thread thread = new Thread(() -> {

			if(panelPosition.equals(Source.PANEL_RIGHT)) {
				try {
					view.getPanelsRightAt(x1, y1).setBackground(Color.WHITE);
					view.getLabelsRightAt(x1, y1).setVisible(true);
					Thread.sleep(400);
					view.getPanelsRightAt(x1, y1).setBackground(Color.BLUE);
					view.getLabelsRightAt(x1, y1).setVisible(false);
					view.getPanelsLeftAt(x2, y2).setBackground(Color.BLUE);
					view.getLabelsLeftAt(x2, y2).setVisible(false);
					view.setPanelLeftActive(false);
				} catch(Exception e){
					e.printStackTrace();
				}
			} else {
				try {
					view.getPanelsLeftAt(x1, y1).setBackground(Color.WHITE);
					view.getLabelsLeftAt(x1, y1).setVisible(true);
					Thread.sleep(400);
					view.getPanelsLeftAt(x1, y1).setBackground(Color.BLUE);
					view.getLabelsLeftAt(x1, y1).setVisible(false);
					view.getPanelsRightAt(x2, y2).setBackground(Color.BLUE);
					view.getLabelsRightAt(x2, y2).setVisible(false);
					view.setPanelRightActive(false);
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		});

		thread.start();
	}

	private boolean isConditionWin(){
		for(int i=0; i<view.getLabelsPanelLeft().length; i++){
			for(int j=0; j<view.getLabelsPanelLeft()[0].length; j++){
				if(!view.getLabelsLeftAt(i,j).isVisible()){
					return false;
				}
				if(!view.getLabelsRightAt(i,j).isVisible()){
					return false;
				}
			}
		}
		return true;
	}

}
