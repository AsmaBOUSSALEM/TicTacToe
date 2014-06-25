

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ToolBarView extends JPanel implements Observer {

	final static int NUM_MOVES = 2;
	final static int ILLEGAL = 3;
	final static int WIN = 4;
	final static int DRAW = 5;
	int scoreX=0,scoreO=0;
	BoardView bv ;
	PlayerView pv;

	// the model that this view is showing
	private Model model;
	// the button of the toolbar
	//private JButton button;
	// the message of the toolbar
	private JLabel label;
	private JLabel scorO;
	private JLabel scorX;
	public ToolBarView(Model model_,BoardView bov, PlayerView p) {
		Font curFont;
		bv=bov;
		pv=p;
		pv.name1=p.name1;
		pv.name2=p.name2;
		// create UI
		setBackground(Color.WHITE);
		
		scorO = new JLabel();
		scorX = new JLabel();
		scorO.setFont(new Font("Buxton Sketch",Font.ITALIC,22));
		scorX.setFont(new Font("Buxton Sketch",Font.ITALIC,22));
		scorO.setForeground(Color.decode("#015daa"));
		scorX.setForeground(Color.decode("#015daa"));
		scorO.setText(" Score du joueur "+ pv.name1 +": "+scoreO+" \t    ");
		scorX.setText(" Score du joueur "+ pv.name2 +": "+scoreX+"  \t   ");
		Box b = new Box(BoxLayout.X_AXIS);
		
		b.add(scorO);
		b.add(Box.createRigidArea(new Dimension(25,25)));
		b.add(scorX);
		b.add(Box.createGlue());
		// the label used to display the game info message
		label = new JLabel("", JLabel.CENTER);
		curFont = label.getFont();
		label.setFont(new Font("Buxton Sketch",Font.ITALIC,20));
		label.setForeground(Color.decode("#7d7d7d"));
		label.setAlignmentX(CENTER_ALIGNMENT);
		this.add(label);
		this.add(b);
		//this.add(Box.createRigidArea(new Dimension(12,59)));
		
		label.setPreferredSize(new Dimension(340, 50));

		
		// set the model
		model = model_;

		
	}

	
	

	// Observer interface
	@Override
	public void update(Observable o, Object arg) {
		

		int gameStatus = model.getStatus();

		// set different message content according to the game status
		switch (gameStatus) {
		case NUM_MOVES:
			int moves = 9 - model.getBlanks();
			this.label.setText(moves + " coups.");
			break;
		case ILLEGAL:
			this.label.setText("Coup ill�gal ??!!");
			break;
		case WIN:
			int playerTurn = model.getTurn();
			if (playerTurn == 0) {
				
				this.label.setFont(new Font("Dialog",20,20));
				scoreO++;
				
				this.label.setText("O Wins ");
				scorO.setText(" Score du player O : "+scoreO+" \t    ");
				scorX.setText(" Score du player X : "+scoreX+" \t    ");
				
				
				
				
			} else {
				
				this.label.setFont(new Font("Angelic",20,20));
				scoreX++;

				this.label.setText("X Wins ");
				scorO.setText(" Score du player O : "+scoreO+" \t    ");
				scorX.setText(" Score du player X : "+scoreX+" \t    ");
				
				
				
				
			}
			break;
		case DRAW:
			this.label.setText("Game over, Match nul");
			this.label.setFont(new Font("Dialog",20,20));
			
			break;

		default:
			break;
		}
	}
	 
}
