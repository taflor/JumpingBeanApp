import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Objects extends JPanel {
	private final int BASEX = 100, BASEY = 300;
	BufferedImage img;
    JFrame window;
	
	public Objects () {
		setBackground(Color.blue);
		setPreferredSize(new Dimension (700, 400));
		img = new BufferedImage(640, 480, BufferedImage.OPAQUE);
		window = new JFrame() {
			 
            public void paint(Graphics g) {
                g.drawImage(img, 0, 0, rootPane);
            }
        };
 
        window.setSize(640, 480);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        window.setVisible(true);
        animate();
        paintComponent(null);

	}
	
	// draw my baby bean
	public void paintComponent (Graphics page) {
		super.paintComponent(page);
		
		// body
		page.setColor(Color.yellow);
		page.fillArc(BASEX, BASEY, 30, 40, 90, 180);			// back arc
		page.fillOval(BASEX+5, BASEY, 20, 21);					// top oval
		page.fillOval(BASEX+5, BASEY+20, 20, 21);				// bottom oval
		
		// face
		page.setColor(Color.black);
		page.drawArc(BASEX+15, BASEY+10, 15, 7, 180, 90);		// mouth
		page.fillOval(BASEX+12, BASEY+5, 7, 7);					// eyes
		
		// shoes
		page.setColor(Color.red);
		page.fillOval(BASEX+12, BASEY+39, 17, 7);				// shoes
		
		// hat
		page.setColor(Color.orange);
		page.fillOval(BASEX, BASEY-5, 30, 7);					// lid of hat
		page.fillArc(BASEX+8, BASEY-10, 15, 20, 0, 180);		// round part of hat
		
		page.setColor(Color.red);
		page.drawArc(BASEX+9, BASEY-6, 13, 4, 180, 180);		// hat ribbon
		
		
	}
	
	public void animate() {
        Graphics g = img.getGraphics();
       
        int z = 1;
        int l = 0;
        int y = 1000;
         
        int t = 640;
        int i = -50;
        
        while(i > -700) {
        	//increase scroll speed
        	l++;
        	if(l == y){
        		y = y+500;
        		z++;
        	}
        	
        	// move buildings
          	i-=z;
          	t-=z;
          	
          	//background
            g.setColor(Color.white);
            g.fillRect(0, 0, 640, 480);
            
            //building1
            g.setColor(Color.GRAY);
            g.fillRect(t, 320, 50, 80);
            
            //building2
            g.setColor(Color.GRAY);
            g.fillRect(i, 320, 50, 80);
            
            //ground
            g.setColor(Color.GREEN);
            g.fillRect(0, 400, 640, 100);
            
            //sun
    		g.setColor(Color.yellow);
    		g.fillOval(-20, -20, 80, 80); 
            
            
            window.repaint();
            
            if (i < -640)
            	i = 640;
            if (t < -640)
            	t = 640;
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
            	
            }
        }

	}}
