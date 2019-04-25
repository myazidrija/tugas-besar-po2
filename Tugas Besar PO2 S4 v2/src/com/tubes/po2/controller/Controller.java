package com.tubes.po2.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.tubes.po2.src.Source;
import com.tubes.po2.view.View;

public class Controller implements ActionListener, ItemListener{

	private View view;
	
	public Controller(View view) {
		this.view = view;
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

}
