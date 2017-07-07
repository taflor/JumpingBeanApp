 

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonListener;

public class PanelGame extends JPanel implements Runnable{	
	
	// building start positions
	int build1x = 640, build2x = 940, build3x = 1140;
	
	// bean positions
	int BASEX = 100;
    int BASEY = 355;
    
	// score/others
	int score = 0;
	int speed = 0;
	int jump = 10;
	int buildmove = -5;
	
	// thread
	Thread game;
    
	// Booleans
	boolean jumpBean;
    public boolean go; 
    public boolean GameOver;
    
    // Parent frame
    private JFrame parent;
    
    // building array
    int[][] buildarr = {{50, 50, 50},
    					{80,60,80}};
   
    public PanelGame(JFrame frame1) {
    	parent = frame1;
    	go = true;
    	game = new Thread(this);
    	game.start();
    }
    
    public void moveBuilding1 (int i){
    	build1x += i;
    	
    	if (build1x < -80){
			build1x = 690;
			score++;
		}
    	
    	repaint();
    }
    public void moveBuilding2 (int i){
    	build2x += i;
    	
    	if (build2x < -80){
			build2x = 690;
			score++;
		}
    	
    	repaint();
    }
    public void moveBuilding3 (int i){
    	build3x += i;
    	
    	if (build3x < -80){
			build3x = 690;
			score++;
		}
    	
    	repaint();
    }
    
    public void keyPressed(KeyEvent evt){
        switch(evt.getKeyCode()){

            // jumpBean
            case KeyEvent.VK_SPACE :
            	if (BASEY == 355)
            	     jumpBean = true;
                break; 
            case KeyEvent.VK_ENTER:
            	if (GameOver == true){
            		parent.dispose();
            		JumpingBeanApp thisClass = new JumpingBeanApp();
                    thisClass.setVisible(true);
            	}
            	
        }
    }

    public void keyReleased(KeyEvent evt){
        switch(evt.getKeyCode()){

                // un Jump Bean
                case KeyEvent.VK_SPACE:
                        jumpBean = false;
                        break;     
        }
    }

    public void jumpBean(){
		
    	if (jumpBean == true)
			BASEY -= jump;
		
        BeanPosition(BASEY);
    }
    
    public void BeanPosition(int y){
    	this.BASEY = y;
    	
    	repaint();
    }
    
    
    public void run(){
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

    	while (true){
    		if(go == true){
    			
    	
    			
    			speed ++;
    			if (speed == 1000){
    				speed = 0;
    				buildmove -= 2;
    			}
    			
    			moveBuilding1(buildmove);
    			moveBuilding2(buildmove);
    			moveBuilding3(buildmove);
    			
    			jumpBean();
    			
    			if (BASEY <= 150)
    				jumpBean = false;
    			
    			try {
    				Thread.sleep(50);
    			}
    			catch(InterruptedException ex){                                
    			}

    			// Bean Falls
    			if (jumpBean == false && this.BASEY < 355)
    				BASEY += jump;
    			
    			if (build1x < 132 && build1x > 55){
    				if (BASEY+39 >= 320){
    					GameOver = true;
    					repaint();
    					go = false;
    				}
    			}
    			
    			if (build2x < 132 && build2x > 55){
    				if (BASEY+39 >= 340){
    					GameOver = true;
    					repaint();
    					go = false;
    				}
    		
    			}
    			
    			if (build3x < 132 && build3x > 55){
    				if (BASEY+39 >= 320){
    					GameOver = true;
    					repaint();
    					go = false;
    				}		
    			}
    			
    			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

    		}
   		}

    }
    
    
    
    public void paintComponent(Graphics gc){

        setOpaque(false);

        super.paintComponent(gc);
        
    	
        //background
        gc.setColor(Color.cyan);
        gc.fillRect(0, 0, 640, 480);
        
        //building1
        gc.setColor(Color.GRAY);
        gc.fillRect(build1x, 320, buildarr[0][0], buildarr[1][0]);
        
        //building2
        gc.setColor(Color.GRAY);
        gc.fillRect(build2x, 340, buildarr[0][1], buildarr[1][1]);
        
        //building3
        gc.setColor(Color.GRAY);
        gc.fillRect(build3x, 320, buildarr[0][2], buildarr[1][2]);
        
        //ground
        gc.setColor(Color.GREEN);
        gc.fillRect(0, 400, 640, 100);
        
        //sun
		gc.setColor(Color.yellow);
		gc.fillOval(-20, -20, 80, 80); 
        
        // Bean
        // body
		gc.setColor(Color.yellow);
		gc.fillArc(BASEX, BASEY, 30, 40, 90, 180);			// back arc
		gc.fillOval(BASEX+5, BASEY, 20, 21);					// top oval
		gc.fillOval(BASEX+5, BASEY+20, 20, 21);				// bottom oval
		
		// face
		gc.setColor(Color.black);
		gc.drawArc(BASEX+15, BASEY+10, 15, 7, 180, 90);		// mouth
		gc.fillOval(BASEX+12, BASEY+5, 7, 7);					// eyes
		
		// shoes
		gc.setColor(Color.red);
		gc.fillOval(BASEX+12, BASEY+39, 17, 7);				// shoes
		
		// hat
		gc.setColor(Color.orange);
		gc.fillOval(BASEX, BASEY-5, 30, 7);					// lid of hat
		gc.fillArc(BASEX+8, BASEY-10, 15, 20, 0, 180);		// round part of hat
		
		gc.setColor(Color.red);
		gc.drawArc(BASEX+9, BASEY-6, 13, 4, 180, 180);		// hat ribbon
		
		// Score
		Font font1 = new Font ("Monospaced", Font.BOLD, 30);
		gc.setFont(font1);
		gc.drawString("Jumped: "+score, 450, 30);
		
		
		if(GameOver) {
			 Font font = new Font ("Monospaced", Font.BOLD, 60);
			 Font font2 = new Font ("Monospaced", Font.BOLD, 30);
			 gc.setFont(font);
			 gc.drawString("Game Over", 150, 200);
			 gc.setFont(font2);
			 gc.drawString("Hit Enter to Play Again", 100, 235);
		}
        
    }
    


}
    
    






