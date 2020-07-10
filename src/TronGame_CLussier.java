import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class TronGame_CLussier extends JFrame
{	
	public static Board_CLussier myBoard;
	public static Tron_CLussier tron1;
	public static Tron_CLussier tron2;
	public TronGame_CLussier() throws InterruptedException
	{
		super("Tron_CLussier");
		while((Board_CLussier.tron2Win < 5) && (Board_CLussier.tron1Win < 5))
		{
			myBoard = new Board_CLussier(400, 250);
			tron2 = new Tron_CLussier(myBoard.NUM_ROWS/6, myBoard.NUM_COLS/2-1,2,1,myBoard);
			tron1 = new Tron_CLussier(myBoard.NUM_ROWS*5/6 ,myBoard.NUM_COLS/2,4,3,myBoard);
			
			setLayout(new BorderLayout());
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setResizable(false);
			add(myBoard, BorderLayout.CENTER);
			setBackground(Color.BLACK);
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
			keyListener();
			runGame();
			myBoard.repaint();
			
			Thread.sleep(1500);
		}
	}
	
	public void keyListener()
	{
		addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e) 
			{
				switch (e.getKeyCode())
				{
					case KeyEvent.VK_UP:
						tron1.setDir(0); 
						break;
						
					case KeyEvent.VK_DOWN:
						tron1.setDir(2); 
						 break;
					
					case KeyEvent.VK_LEFT:
						tron1.setDir(3); 
						 break;
					
					case KeyEvent.VK_RIGHT:
						tron1.setDir(1); 
						 break;
						 
					case KeyEvent.VK_W:
						tron2.setDir(0); 
						 break;
						 
					case KeyEvent.VK_A:
						tron2.setDir(3);
						 break;
						 
					case KeyEvent.VK_S:
						tron2.setDir(2);
						 break;
						 
					case KeyEvent.VK_D:
						tron2.setDir(1);
						 break;
				}
			}
		});
	}
	
	
	public void runGame() throws InterruptedException
	{
		
		
		while (tron1.checkCollisions() && tron2.checkCollisions())
		{
			tron1.move();
			tron2.move();
			
			//Counts number of wins
			if (tron1.checkCollisions() == false)
			{
				tron1.isDead = true;
				Board_CLussier.tron2Win++;
			}
			else if (tron2.checkCollisions() == false)
			{
				tron2.isDead = true;
				Board_CLussier.tron1Win++;
			}
			
			//Rainbow mode
			Board_CLussier.colr++;
			if(Board_CLussier.colr == 255) {Board_CLussier.colr =0; Board_CLussier.phase++;}
			if(Board_CLussier.phase == 6) {Board_CLussier.phase = 1;}
			myBoard.repaint();
			Thread.sleep(10);
		}
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		new TronGame_CLussier();
	}
	
	
}



