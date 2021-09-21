import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;

public class BingoSimulationE {
	
	private int seed; 
	private int seedCards;
	private int seedWinners;
	private int seedDays;
	public static ArrayList<BingoCard> simCards = new ArrayList<>();
	private Random random;
	public static int[][] card;
	public static int [][] displayCard;
	public static ArrayList<Integer> rolling = new ArrayList<>();
	public static ArrayList<Integer> oldasf = new ArrayList<>();
	ArrayList<position> markedPositions = new ArrayList<>();
	private ArrayList<boardsKEWIE> bingoCards = new ArrayList<>();
	private BufferedImage bingoImages;
	private int count;
	private BufferedImage cardPic;
	public static ArrayList<BingoCard> winners = new ArrayList<>();
	
	public BingoSimulationE(int seed,int seedCards, int seedWinners, int seedDays){
		this.seed=seed;
		this.seedCards = seedCards;
		this.seedWinners = seedWinners;
		this.seedDays = seedDays;
	}
	
	
	public void simulate() {
		for(int i=0;i<seedCards;i++) {
			simCards.add(new BingoCard(seed));
		}
		
		trustE();
		
		while (winners.size() < seedWinners) {
			int bingoNum = callBingoBall();
			
			for (int i = 0; i < seedCards;i++) {
				BingoCard card = simCards.get(i);
				
				if (!winners.contains(card)) {
					card.checkAllNum(bingoNum);
					
					if (card.checkWinners()) {
						winners.add(card);
					}
				}
			}
		}
	}
	
	public int callBingoBall() {
		int rand = 0;
		while(oldasf.size()<75) {
			rand =  random.nextInt(75)+1;
			if ( oldasf.contains(rand) ) {
				continue;
			} else {
				oldasf.add(rand);
				return rand;
			}
		}
		
		return -1;
		
//		for(int i = 0; i<card.length;i++) {
//			for(int j=0;j<card[0].length;j++) {
//				if(card[i][j]==rand) {
//					if(rolling.contains(card[i][j])) {
//						continue;
//					}
//					
//					else{
//						rolling.add(card[i][j]);
//						//card[i][j]=0;
//						position newPos = new position(i,j);
//						markedPositions.add(newPos);
//						System.out.println(Arrays.deepToString(card));
//						Collections.sort(rolling);
//						System.out.println(rolling);
//						
//						return rand;
//					}
//		
//		}
//			}
//		}
//		
//		//System.out.println(markedPositions);
//		return rand;
		
	}
	
	
	
	
	public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
	    // Get the FontMetrics
	    FontMetrics metrics = g.getFontMetrics(font);
	    // Determine the X coordinate for the text
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    // Set the font
	    g.setFont(font);
	    //c Draw the String
	    g.drawString(text, x, y);
	}
	
	public void trustE() {
		Graphics g = bingoImages.getGraphics();
		g.drawImage(cardPic,0,0,800,800,null);
		g.drawImage(cardPic,800,0,800,800,null);
		g.drawImage(cardPic,0,800,800,800,null);
		g.drawImage(cardPic,800,800,800,800,null);
		
		int x = 32;
		int y = 100;
		int width = 130;
		int height = 142;
		
		//i is what page im on
		// j is the id of my card
		for(int j=0,i=0;j < seedCards/4;j++,i+=4) {
			g.setColor(Color.WHITE);
			g.fillRect(0,0,1800,1800);
			g.drawImage(cardPic,0,0,800,800,null);
			g.drawImage(cardPic,800,0,800,800,null);
			g.drawImage(cardPic,0,800,800,800,null);
			g.drawImage(cardPic,800,800,800,800,null);
			g.setColor(new Color(0,0,0));
			
			int[][] holdsBingo = bingoCards.get(i).getBoard(); 
			//height is 82
			int card1x = 24;
			int card1y = 90;
			int card1width = 148;
			int card1height = 141;
			Font font1 = new Font("SANS_SERIF", Font.PLAIN, 50);
			g.setFont(font1);
			g.drawString("ID: "+Integer.toString(bingoCards.get(i).getID()+1),350,810);
			for(int card1row=0;card1row<5;card1row++) {
				for(int card1col=0;card1col<5;card1col++) {
					if(card1row == 2 && card1col == 2) {
						
						Rectangle rect = new Rectangle(card1x,card1y, card1width, card1height);
						Font font = new Font("SANS_SERIF", Font.BOLD, 60);
						g.setFont(font);
						drawCenteredString(g, " Free ", rect, font);
						card1y+=card1height;
					}
					
					else 
					{
						Rectangle rect = new Rectangle(card1x, card1y, card1width, card1height);
						Font font = new Font("SANS_SERIF", Font.BOLD, 120);
						g.setFont(font);
						drawCenteredString(g, Integer.toString(holdsBingo[card1col][card1row]), rect, font);
						
						card1y+=card1height;
					}
					
					
				}
				card1y=82;
				card1x+=card1width;
				
			}
			int[][] holdsBingo2 = bingoCards.get(i+1).getBoard(); 
			//height is 82
			int card2x = 820;
			int card2y = 90;
			int card2width = 148;
			int card2height = 142;
			Font font2 = new Font("SANS_SERIF", Font.PLAIN, 50);
			g.setFont(font2);
			g.drawString("ID: "+Integer.toString(bingoCards.get(i+1).getID()+1),1150,810);
			for(int card2row=0;card2row<5;card2row++) {
				for(int card2col=0;card2col<5;card2col++) {
					if(card2row == 2 && card2col == 2) {
						
						Rectangle rect = new Rectangle(card2x,card2y, card2width, card2height);
						Font font = new Font("SANS_SERIF", Font.BOLD, 60);
						g.setFont(font);
						drawCenteredString(g, " Free ", rect, font);
						card2y+=card2height;
					}
					
					else 
					{
						Rectangle rect = new Rectangle(card2x, card2y, card2width, card2height);
						Font font = new Font("SANS_SERIF", Font.BOLD, 120);
						g.setFont(font);
						drawCenteredString(g, Integer.toString(holdsBingo2[card2col][card2row]), rect, font);
						
						card2y+=card2height;
					}
					
					
				}
				card2y=90;
				card2x+=card2width;
				
			}
			int[][] holdsBingo3 = bingoCards.get(i+2).getBoard(); 
			//height is 83
			int card3x = 24;
			int card3y = 870;
			int card3width = 148;
			int card3height = 142; 
			Font font3 = new Font("SANS_SERIF", Font.PLAIN, 50);
			g.setFont(font3);
			g.drawString("ID: "+Integer.toString(bingoCards.get(i+2).getID()+1),350,1610);
			for(int card3row=0;card3row<5;card3row++) {
				for(int card3col=0;card3col<5;card3col++) {
					if(card3row == 2 && card3col == 2) {
						
						Rectangle rect = new Rectangle(card3x,card3y, card3width, card3height);
						Font font = new Font("SANS_SERIF", Font.BOLD, 60);
						g.setFont(font);
						drawCenteredString(g, " Free ", rect, font);
						card3y+=card3height;
					}
					
					else 
					{
						Rectangle rect = new Rectangle(card3x, card3y, card3width, card3height);
						Font font = new Font("SANS_SERIF", Font.BOLD, 120);
						g.setFont(font);
						drawCenteredString(g, Integer.toString(holdsBingo3[card3col][card3row]), rect, font);
						
						card3y+=card3height;
					}
					
					
				}
				card3y=870;
				card3x+=card3width;
				
			}
			int[][] holdsBingo4 = bingoCards.get(i+3).getBoard(); 
			//height is 84
			int card4x = 820;
			int card4y = 870;
			int card4width = 148;
			int card4height = 142; 
			Font font4 = new Font("SANS_SERIF", Font.PLAIN, 50);
			g.setFont(font4);
			g.drawString("ID: "+Integer.toString(bingoCards.get(i+3).getID()+1),1150,1610);
			for(int card4row=0;card4row<5;card4row++) {
				for(int card4col=0;card4col<5;card4col++) {
					if(card4row == 2 && card4col == 2) {
						
						Rectangle rect = new Rectangle(card4x,card4y, card4width, card4height);
						Font font = new Font("SANS_SERIF", Font.BOLD, 60);
						g.setFont(font);
						drawCenteredString(g, " Free ", rect, font);
						card4y+=card4height;
					}
					
					else 
					{
						Rectangle rect = new Rectangle(card4x, card4y, card4width, card4height);
						Font font = new Font("SANS_SERIF", Font.BOLD, 120);
						g.setFont(font);
						drawCenteredString(g, Integer.toString(holdsBingo4[card4col][card4row]), rect, font);
						
						card4y+=card4height;
					}
					
					
				}
				card4y=870;
				card4x+=card4width;
				
			}
			
			
			
			//holdsBingo=bingoCards.get(j+2).getBoard();
			//holdsBingo=bingoCards.get(j+3).getBoard();
			try {
				//"C:\\Users\\slhscs218\\Downloads\\card"
				
				//String home = System.getProperty("user.home");
				//ImageIO.write(bingoImages, "png", new File(home+"/Downloads/card"));
				
				String home = System.getProperty("user.home");
				ImageIO.write(bingoImages, "png", new File(home+"/Downloads/card"+i+".png"));
			} catch(IOException e) {
				e.printStackTrace();
			}	
	}
	
	
	
} }
