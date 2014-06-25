

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

@SuppressWarnings("serial")
public final class JSplash extends JWindow{
	
 
   public JSplash()  {
	   JPanel    pnlImage   = new JPanel();
	      ImageIcon image   = new ImageIcon(getClass().getResource( "images/TicTacToe-photo-logo.jpg" ) );
	      JLabel    lblBack = new JLabel( image );
	       
	      lblBack.setBounds( 0, 0, image.getIconWidth(), image.getIconHeight() );
	      getLayeredPane().add( lblBack, new Integer( Integer.MIN_VALUE ) );

	      pnlImage.setLayout( null );
	      pnlImage.setOpaque( false ); 

	      setContentPane( pnlImage );
	      setSize( image.getIconWidth(), image.getIconHeight() ); 
	      center();
		  setVisible(true);
		  try {
			Thread.sleep(2000);
			dispose();
			param n = new param();
		    n.setVisible(true);	
		   	n.setLocationRelativeTo(null);
			
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
   }
   
   private void center() {
	   
      Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
      int       nX  = (int) (scr.getWidth()  - getWidth()  ) / 2;
      int       nY  = (int) (scr.getHeight() - getHeight() ) / 2;

      setLocation( nX, nY );
   }
}
