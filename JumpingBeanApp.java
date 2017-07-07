import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JumpingBeanApp extends JFrame {
		
		private JPanel jContentPane = null;
        private PanelGame Gpanel = null; // This is the panel of the game class

        private PanelGame getPanel() {
                if (Gpanel == null) {
                        Gpanel = new PanelGame(this); // The panel is created
                        
                }
                return Gpanel;
        }

        public JumpingBeanApp() {
                super();
                initializeGame();
        
                // Listeners for the keyboard
                this.addKeyListener(new KeyAdapter() {
                	public void keyPressed(KeyEvent evt) {
                		formKeyPressed(evt);
                	}

                	public void keyReleased(KeyEvent evt) {
                		formKeyReleased(evt);
                	}
                });        
        }
    
    //  Here i'm stating the method that will send the key pressed to the game class
        private void formKeyPressed(KeyEvent evt){
        	Gpanel.keyPressed(evt);
        }

    //  Here i'm stating the method that will send the key released to the game class
        private void formKeyReleased(KeyEvent evt){
        	Gpanel.keyReleased(evt);
        }

        private void initializeGame() {
                this.setResizable(false);
                this.setBounds(new Rectangle(312, 184, 250, 250)); // Position on the desktop
                this.setMinimumSize(new Dimension(640, 500));
                this.setMaximumSize(new Dimension(640, 500));
                this.setContentPane(getJContentPane());
                this.setTitle("Jumping Bean");
        }

        private JPanel getJContentPane() {
                if (jContentPane == null) {
                        jContentPane = new JPanel();
                        jContentPane.setLayout(new BorderLayout());
                        jContentPane.add(getPanel(), BorderLayout.CENTER);
                }
                return jContentPane;
        }
}




	

