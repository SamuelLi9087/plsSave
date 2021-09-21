import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class boardsKEWIE {
	//when it won
	// if it won
	//bingoboard
	
	private int days;
	private boolean winQuestion;
	private int id;
	private int[][] board;
	private int[][] markedBoard;
	private BufferedImage bingoImages;
	
	
	public boardsKEWIE(int id, int[][] newBoard, int[][] markedBoard) {
		this.id = id;
		board = newBoard;
		this.markedBoard = markedBoard;
		
	}
	
	public int getID() {
	    return id;
	}
	
	public void setWin(int days, boolean winQuestion) {
		this.days = days;
		this.winQuestion = winQuestion;
	}
	
	public int[][] getBoard(){
		return board;
		
	}
	public boolean contains(int ethansNum) {
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				if(board[i][j]==ethansNum) {
					return true;
				}
			}
		}
		return false;
		}
	
	public void checkBall(int k) {
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				if(board[i][j]==k) {
					markedBoard[i][j]=0;
				}
			}
		}
		// go thru bingoCards, then checkBall on each of them, then update which have been marked
		}
	
	
	
	
	
	
	
	}
