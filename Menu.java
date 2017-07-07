import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

public class Menu extends JFrame{
	private JButton HowTo, Play;
	private JFrame Dframe;
	private JLabel Title;
	private JPanel ButtonMenu;
	private JPanel Picture;
	private JTextArea Directions;
	
	public Menu(){
		
		setMinimumSize(new Dimension(640, 500));
        setMaximumSize(new Dimension(640, 500));
		setBounds(new Rectangle(312, 184, 250, 250));
		setLayout (new GridLayout(3,1));
		setPreferredSize(new Dimension (700, 400));
		setBackground (Color.blue);
		
		Play = new JButton ("Play");
		HowTo = new JButton ("Directions");
		
		ButtonListener listener = new ButtonListener();
		Play.addActionListener(listener);
		HowTo.addActionListener(listener);
		
		// game title
		Title = new JLabel ("JUMPING BEAN", JLabel.CENTER);
		Font font = new Font("Serif", Font.BOLD, 40);
		Title.setFont (font);
		Title.setForeground(Color.green);
		
		
		// directions button
		Directions = new JTextArea();
		Directions.setPreferredSize(new Dimension(300, 200));
		Directions.setWrapStyleWord(true);
		Directions.setLineWrap(true);
		Directions.setEnabled(false);
		
		
		// button menu box
		ButtonMenu = new JPanel();
		ButtonMenu.setPreferredSize(new Dimension (299, 60));
		ButtonMenu.setBackground (Color.red);
		ButtonMenu.add(Play);
		ButtonMenu.add(HowTo);
		
		
		// inserts my jumping bean
	    Picture = new JPanel();
	    Picture.setSize(300, 300);
	    JLabel label = new JLabel(new ImageIcon(image));
	    Picture.setBackground(Color.blue);
	    Picture.add(label);
	    Picture.setVisible(true);
		
		add(Title);
		add(Picture);
		add(ButtonMenu);
	       
	}
	
	// get jumping bean picture

	Image image = null; {
    try {
        URL url = new URL("http://thumb15.shutterstock.com/thumb_small/83138/83138,1171831933,2/stock-vector-mexican-jumping-bean-2709818.jpg");
        image = ImageIO.read(url);
    } catch (IOException e) {
    	e.printStackTrace();
    }

}
	
	
	// button actions
	private class ButtonListener implements ActionListener{
		public void actionPerformed (ActionEvent event){
			if (event.getSource() == Play){
                    JumpingBeanApp thisClass = new JumpingBeanApp();
                    thisClass.setVisible(true);
			}
			else{
				//setLayout (new BorderLayout());
				Dframe = new JFrame ("Directions");
				Dframe.setMinimumSize(new Dimension(350, 250));
				JPanel Drec = new JPanel();
				Drec.setPreferredSize(new Dimension (300, 200));
				Drec.setBackground (Color.blue);
				Dframe.add(Drec);
				Dframe.pack();
				Dframe.setVisible(true);
				
				Drec.add(Directions);
				Directions.setText(" \n DIRECTIONS! \n\n " +
						"Jumping bean is a very simple game. Jump the  bean with SPACE BAR" +
						" and avoid smashing into  a building." +
						"  \n\n Good Luck!");
			}
		}
	}
	
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {

	            public void run() {
	                    Menu thisClass = new Menu();
	                    thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                    thisClass.setVisible(true);
	            }

	    });
	}

}
	