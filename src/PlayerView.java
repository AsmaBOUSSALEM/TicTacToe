

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class PlayerView extends JPanel implements Observer{

	// the model that this view is showing
	private Model model;
	final static int SELECT_TURN = 0;
	final static int FIRST_MOVE = 1;

	public JRadioButton player1;
	public JRadioButton player2;
	public ButtonGroup group;
	public String name1;
	public String name2;
	private JLabel label1;
	private JLabel label2;
	public PlayerView(Model model_,String name1,String name2) {
		
		Font curFont;
		// create UI
		this.setBackground(Color.decode("#48bfdb") );
		//this.setLayout(new FlowLayout(FlowLayout.LEFT, 80, 0));
		this.setLayout(new GridLayout(1,2));
		
		this.name1=name1;
		this.name2=name2;
		// set the model
		model = model_;
		JPanel p1 = new JPanel();
		p1.setBackground(Color.WHITE);
		label1 = new JLabel("Choisissez le joueur");
		label2 = new JLabel("qui va jouer en premier");
		curFont = label1.getFont();
		label1.setFont(new Font("Buxton Sketch",Font.ITALIC,17));
		label1.setForeground(Color.decode("#015daa"));
	//label.setAlignmentX(CENTER_ALIGNMENT);
		label1.setAlignmentY(CENTER_ALIGNMENT);
		this.add(label1);
		curFont = label2.getFont();
		label2.setFont(new Font("Buxton Sketch",Font.ITALIC,17));
		label2.setForeground(Color.decode("#015daa"));
	//label.setAlignmentX(CENTER_ALIGNMENT);
		label2.setAlignmentY(CENTER_ALIGNMENT);
		p1.add(label1);
		p1.add(label2);
		this.add(p1);
		// radio button of the player O
		player1 = new JRadioButton(name1);
		player1.setPreferredSize(new Dimension(100, 75));
		curFont = player1.getFont();
		player1.setFont(new Font(curFont.getFontName(), curFont.getStyle(), 19));
		player1.setBackground(Color.WHITE);
		player1.setForeground(Color.decode("#015daa"));
		player1.setFocusable(false);
		player1.setAlignmentX(CENTER_ALIGNMENT);
		// radio button of the player X
		player2 = new JRadioButton(name2);
		player2.setPreferredSize(new Dimension(100, 75));
		player2.setFont(new Font(curFont.getFontName(), curFont.getStyle(), 19));
		player2.setForeground(Color.decode("#015daa"));
		player2.setBackground(Color.WHITE);
		player2.setFocusable(false);
		player2.setAlignmentX(CENTER_ALIGNMENT);
		// create the radio button group
		group = new ButtonGroup();
		group.add(player1);
		group.add(player2);
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		p.setLayout(new GridLayout(2,1));
		p.add(player1);
		p.add(player2);
		this.add(p);
		// register the controller
		this.registerController();
	}
	/* the controller part */

	void registerController() {
		this.player1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// if game not start, set the player turn and change status to
				// first move
				if (model.getStatus() == 0) {
					player1.setSelected(true);
					model.setStatus(1);
					model.setTurn(0);
				}

				// if status is first move, only set the player turn
				if (model.getStatus() == 1) {
					model.setTurn(0);
				}

				// if other status disable the selection
				if (model.getStatus() != 0 || model.getStatus() != 1) {
					if (model.getTurn() == 0) {
						player1.setSelected(true);
					} else {
						player2.setSelected(true);
					}
				}
			}
		});

		this.player2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// if game not start, set the player turn and change status to
				// first move
				if (model.getStatus() == 0) {
					model.setStatus(1);
					model.setTurn(1);
				}

				// if status is first move, only set the player turn
				if (model.getStatus() == 1) {
					model.setTurn(1);

					// if in PVE mode, let AI move immediately for simplicity
					if (model.getMode() == 1) {
						// emulate the AI thinking process
						player2.setText("AI thinking...");

						// delay for AI thinking process
						new Thread(new Runnable() {
							public void run() {
								try {
									Thread.sleep(1500);
								} catch (InterruptedException ignore) {
								}
								model.AIMove();
							}
						}).start();
					}
				}

				// if other status disable the selection
				if (model.getStatus() != 0 || model.getStatus() != 1) {
					if (model.getTurn() == 0) {
						player1.setSelected(true);
					} else {
						player2.setSelected(true);
					}
				}
			}
		});
	}

	// Observer interface
	@Override
	public void update(Observable o, Object arg) {
		int gameStatus = model.getStatus();

		// set different message content according to the game status
		switch (gameStatus) {
		case SELECT_TURN:
			this.label1.setText("Choisissez le joueur");
			this.label2.setText("qui va jouer en premier");
			break;
		case FIRST_MOVE:
			this.label1.setText("Changez le joueur qui va commencer,");
			this.label2.setText("ou faites votre premier coup.");
			break;
		}
		// change the player name according to the mode
		if (model.getMode() == 0) {
			player2.setText("Player X");
		} else {
			player2.setText("AI X");
		}

		// if new game, clear the player turn
		if (model.getStatus() == 0) {
			group.clearSelection();
		}

		// if game starts, update the player turn
		if (model.getStatus() == 2) {
			if (model.getTurn() == 0) {
				player1.setSelected(true);
			} else {
				if (model.getMode() == 1)
					// emulate the AI thinking process
					player2.setText("AI thinking...");
				player2.setSelected(true);
			}
		}
	}
}
