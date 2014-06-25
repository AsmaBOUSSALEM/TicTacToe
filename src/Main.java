
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class Main extends JPanel{
	/*Image background = (new ImageIcon(getClass().getResource("images/background.jpg"))).getImage();
	Image back = background;
	{
	    addComponentListener(new ComponentAdapter() {
	        @Override
	        public void componentResized(final ComponentEvent e) {
	            final int w = Main.this.getWidth();
	            final int h = Main.this.getHeight();
	            background = (w > 0 && h > 0 ? back.getScaledInstance(w, h, Image.SCALE_SMOOTH) : back);
	            Main.this.repaint();
	        }
	    });
	}
	protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	    g.drawImage(background,0 , 0, this);
	}*/
	Main(Model model_,ImageIcon img1 ,ImageIcon img2,String nom1,String nom2) {
            
            
		JFrame frame = new JFrame("Tic-Tac-Toe, C'est parti !!");

		// create Model and initialize it
		//Model model = new Model();

		// create boardView, tell it about model (and controller)
		BoardView boardView = new BoardView(model_, img1, img2);		
		// tell Model about View.
		model_.addObserver(boardView);
		boardView.setPreferredSize(new Dimension(600, 500));
	    ImageIcon hh = new ImageIcon(getClass().getResource("images/3.jpg"));
		frame.setIconImage(hh.getImage());
                

		// create playerView ...
		PlayerView playerView = new PlayerView(model_,nom1,nom2);
		model_.addObserver(playerView);
		playerView.setPreferredSize(new Dimension(600, 100));

		// create toolbarView ...
		ToolBarView toolbarView = new ToolBarView(model_,boardView,playerView);
		model_.addObserver(toolbarView);
		toolbarView.setPreferredSize(new Dimension(600, 100));

                Menu m = new Menu(model_,boardView);
                
		// create the window
		JPanel p = new JPanel(new BorderLayout(3, 1));
		frame.getContentPane().add(p);
		p.add(toolbarView, BorderLayout.PAGE_START);
		p.add(boardView, BorderLayout.CENTER);
		p.add(playerView,BorderLayout.PAGE_END);
                frame.setJMenuBar(m);

		frame.setPreferredSize(new Dimension(600, 700));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
                
	}
   
}
