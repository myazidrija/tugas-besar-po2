package com.tubes.po2.src;

import java.util.Random;

public class Source {
	public static final String PANEL_LEFT = "PANEL_LEFT";
	public static final String PANEL_RIGHT = "PANEL_RIGHT";
	
	public static final int GAME_MODE2X2 = 2;
	public static final int GAME_MODE3X3 = 3;
	public static final int GAME_MODE4X4 = 4;
	
	public static final String[][] DATA_GAME_MODE2x2= new String[][] {
		{"A", "B"},
		{"C", "D"}
	};
	public static final String[][] DATA_GAME_MODE3x3= new String[][] {
		{"A", "B", "C"},
		{"D", "E", "F"},
		{"G", "H", "I"}
	};
	public static final String[][] DATA_GAME_MODE4x4= new String[][] {
		{"A", "B", "C", "D"},
		{"E", "F", "G", "H"},
		{"I", "J", "K", "L"},
		{"M", "N", "O", "P"}
	};
	
	public static final String[] GAME_MODES= new String[] {"Mode : 2x2", "Mode : 3x3", "Mode : 4x4"};
	
	public static void shuffle(String[][] data) {
		Random rand = new Random();
		int randX;
		int randY;
		
		String temp;
		for(int i=0; i<data.length; i++) {
			for(int j=0; j<data[i].length; j++) {
				randY = rand.nextInt(data.length);
				randX = rand.nextInt(data[i].length);
				
				temp = data[i][j];
				data[i][j] = data[randY][randX];
				data[randY][randX] = temp;
			}
		}
	}
}
