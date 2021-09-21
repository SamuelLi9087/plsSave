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
import javax.swing.text.Position;
public class BingoCard {
	
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
	
	public BingoCard(int seed) {
		count =0;
		random = new Random(seed);
		card = new int[5][5];
		displayCard = new int[5][5];
//		seedCards = (seedCards+3)/4 *4;
		generateCard();
		
		bingoImages = new BufferedImage(2550,2000,BufferedImage.TYPE_INT_ARGB);
		
		try {
			cardPic = ImageIO.read(BingoPanel.class.getResource("/images/BigBingoCard.jpg"));
		}
		catch(Exception E) {
			System.out.println("Exception Error");
			return;
		}
		
		System.out.println(Arrays.deepToString(bingoCards.get(0).getBoard()));
		System.out.println(Arrays.deepToString(bingoCards.get(1).getBoard()));
		System.out.println(Arrays.deepToString(bingoCards.get(2).getBoard()));
		System.out.println(Arrays.deepToString(bingoCards.get(3).getBoard()));
		
//		paintImage(seedCards);
	}
	
	
	public void checkAllNum(int num) {
//		for(int i = 0; i<bingoCards.size();i++) {
//			bingoCards.get(i).checkBall(num);
//				
//			
//		}
		
		for (int r = 0;r<5;r++) {
			for(int c=0;c<5;c++) {
				if(card[r][c]==num) {
					card[r][c]=0;
				}
			}
		}
	}
	
	
	public void generateCard() {
		int min = 1;
		int max = 16;
		displayCard = new int[5][5];
		ArrayList<Integer> ttroud = new ArrayList<>();
		
		for(int col=0;col<card[0].length;col++) {
			for(int row=0;row<card.length;row++) {
				
				int rand = random.nextInt(max-min)+min;
				while(ttroud.contains(rand)) {
					rand=random.nextInt(max-min)+min;
					
				}
				displayCard[row][col] = rand;
				card[row][col] = rand;
				ttroud.add(rand);
			}
		min+=15;
		max+=15;
		}
		displayCard[2][2] = 0;
		
		card[2][2] = 0;
		
		bingoCards.add(new boardsKEWIE(count,displayCard,card));
		count++;
	}
	
	
	public void paintImage(int seedCards) {
		
		
		}
		
		
		
		
		
		//height is 100
				//width is 32
				//heihgt 130
				//width 142
			
			
		
		
		/*try {
			//"C:\\Users\\slhscs218\\Downloads\\card"
			
			//String home = System.getProperty("user.home");
			//ImageIO.write(bingoImages, "png", new File(home+"/Downloads/card"));
			
			String home = System.getProperty("user.home");
			ImageIO.write(bingoImages, "png", new File(home+"/Downloads/card"+i+".png"));
		} catch(IOException e) {
			e.printStackTrace();
		}	*/
	}
	
	
	
	
	//Width - 182 HEIGHT - 164
	//40- width 132 - height
	public void fillCard(Graphics g) {
		//g.fillRect(40,132,182,164);
		int x = 40;
		int y = 132;
		int width = 182;
		int height = 164;
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				if(i == 2 && j == 2) {
					
					Rectangle rect = new Rectangle(x, y, width, height);
					Font font = new Font("SANS_SERIF", Font.BOLD, 80);
					g.setFont(font);
					drawCenteredString(g, " ", rect, font);
					y+=height;
				}
				
				else 
				{
					Rectangle rect = new Rectangle(x, y, width, height);
					Font font = new Font("SANS_SERIF", Font.BOLD, 150);
					g.setFont(font);
					drawCenteredString(g, Integer.toString(displayCard[j][i]), rect, font);
					y+=height;
				}
				
				
			}
			
			y=132;
			x+=width;
		}
		
		
		
		
	}
	/*public void trespass(int hit) {
		
	}*/
	public int callBingoBall() {
		int rand = 0;
		while(oldasf.size()<75) {
			rand =  random.nextInt(75)+1;
			if ( oldasf.contains(rand) ) {
				continue;
			} else {
				oldasf.add(rand);
				break;
			}
		}
		
		for(int i = 0; i<card.length;i++) {
			for(int j=0;j<card[0].length;j++) {
				if(card[i][j]==rand) {
					if(rolling.contains(card[i][j])) {
						continue;
					}
					
					else{
						rolling.add(card[i][j]);
						card[i][j]=0;
						position newPos = new position(i,j);
						markedPositions.add(newPos);
						System.out.println(Arrays.deepToString(card));
						Collections.sort(rolling);
						System.out.println(rolling);
						
						return rand;
					}
		
		}
			}
		}
		
		//System.out.println(markedPositions);
		return rand;
		
	}

	
	public boolean checkWinners() {
		boolean won  = false;
		for(int x = 0; x<5;x++) {
            if((card[x][0] ==0&& card[x][1] ==0&& card[x][2]==0 && card[x][3]==0 && card[x][4]==0) || (card[0][x]==0 && card[1][x]==0 && card[2][x]==0 && card[3][x]==0 && card[4][x]==0)) {
                won = true;
                System.out.println("wonners");
                                
                return true;
                                
                
            }
            
        }
        if((card[0][0] == 0 && card[1][1] ==0 && card[2][2]==0 && card[3][3]==0 && card[4][4]==0) || (card[0][4]==0 && card[1][3]==0 && card[2][2]==0 && card[3][1]==0 && card[4][0]==0)) {
            won = true;
            System.out.println("wonners");
                        
            return true;
        }
        return false;
		
	}
	
	
	public ArrayList<position> getMarkedPositions() {
		return markedPositions;
	}
		
	
	
	
	public String toString() {
		return Arrays.deepToString(card);
			}
			

}