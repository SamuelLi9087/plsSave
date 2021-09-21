import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class BingoPanel extends JPanel implements KeyListener{
	private static final int WIDTH = 1500;
	private static final int HEIGHT = 1000;
	private int num;
	private BufferedImage cardPic;
	private BingoCard bcard;
	
	
	public BingoPanel(int seed, int seedCards) {
		bcard = new BingoCard(seed,seedCards);
		num = 0;
		
		setSize(WIDTH,HEIGHT);
		try {
			cardPic = ImageIO.read(BingoPanel.class.getResource("/images/BigBingoCard.jpg"));
		}
		catch(Exception E) {
			System.out.println("Exception Error");
			return;
		}
		addKeyListener(this);
		
	}
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}
	
	public void paint(Graphics g) {
		
		g.drawImage(cardPic,0,0,1000,1000,null);
		bcard.fillCard(g);
		Font stringFont = new Font( "SansSerif", Font.PLAIN, 18 );
		g.setFont(stringFont);
		g.drawString("The Bingo Ball number is displayed\n in the middle space.",1000,200);
		Font regFont = new Font("SansSerif", Font.PLAIN, 150);
		g.setFont(regFont);
		Graphics2D gasdf = (Graphics2D)g;
		gasdf.setColor(new Color(0,234,25,255));
		gasdf.fillOval(410,462,184,164);
		gasdf.setColor(java.awt.Color.black);
	
		//gasdf.setFont(new Font("TimesRoman", Font.PLAIN,100));
		//gasdf.drawString("Bingo Ball",400,660);
		
		if(num>-2) {
			
			if(num<10) {
				g.drawString("0"+num,413,600);
			}
			else {
				g.drawString("" + num,413,600);
			}
			
		}
		
		if(bcard.checkWinners()==true) {
			g.setColor(new Color(0,0,0,255));
			g.drawString("Bingo!",1005,500);
			removeKeyListener(this);
			
		}
		
		
		ArrayList<position> markedPositions = bcard.getMarkedPositions();
		
		
		
		
		int x = 41;
		int y = 132;
		int width = 184;
		int height = 164;
		for(int i=0;i<markedPositions.size();i++) {
			int row = markedPositions.get(i).getRow();
			int col = markedPositions.get(i).getCol();
				if(row==0) {
					y=132;
				}
				else if(row==1) {
					y=132+height;
				}
				else if(row==2) {
					y=132 + (2*height);
				}
				else if(row == 3) {
					y=132 + (3*height);
				}
				else if(row==4) {
					y=132+(4*height);
				}
				
				if(col ==0) {
					x=40;
				}
				if(col ==1) {
					x=40+184;
				}
				if(col ==2) {
					x=40+(2*184);
				}
				if(col ==3) {
					x=40+(3*184);
				}
				if(col ==4) {
					x=40+(4*184);
				}
				
			 
				
				Graphics2D ga = (Graphics2D)g;
				ga.setColor(new Color(255,0,0,100));
				ga.fillOval(x,y,width,height);
				
				
				
				
		}
		
	
		
		
		
	
			
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("asdfasdfasd");
		//num = bcard.callBingoBallTest();
		num = bcard.callBingoBall();
		repaint();
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}