/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;



public class param extends JFrame {
	Model model = new Model() ;
	private JComboBox liste1,liste2;
	private JTextField champ1,champ2;
	private JRadioButton bouton1,bouton2;
	private boolean ok = false;
	private JPanel panneauJ2ok;
	private ImageIcon croix = new ImageIcon(getClass().getResource("images/croix.png"));
	private ImageIcon smurf = new ImageIcon(getClass().getResource("images/smurf.jpg"));
	private ImageIcon corn = new ImageIcon(getClass().getResource("images/spongbob.jpg"));
	private ImageIcon wheel = new ImageIcon(getClass().getResource("images/girl.jpg"));
	private ImageIcon icon = new ImageIcon(getClass().getResource("images/3.jpg"));
	ImageIcon[] listeIcones = new ImageIcon[] {croix,smurf,corn,wheel};

	ImageIcon img1;
	ImageIcon img2;

	public param() {
		super("Bienvenue au Tic Tac Toe, Amuzez vous bien !!");
		this.setIconImage(icon.getImage());
		liste1 = new JComboBox(this.listeIcones);
		liste2 = new JComboBox(this.listeIcones);
		liste2.setSelectedIndex(1);
		JPanel panneauNb = new JPanel(new GridLayout(1,2));
		bouton1 = new JRadioButton("Un",true);
		bouton1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (bouton2.isSelected()){ bouton2.setSelected(false);

				}
				else {bouton1.setSelected(true);
				champ2.setText("CPU");
				champ2.setEditable(false);
				model.changeMode();
				panneauJ2ok.setBorder(BorderFactory.createTitledBorder(" CPU "));
				}
			}
		});
		
		
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          	}
          	catch(Exception e) {
            e.printStackTrace();
          	}
	
		
		bouton2 = new JRadioButton("Deux",false);
		bouton2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (bouton1.isSelected()) bouton1.setSelected(false);
				else {
					champ2.setText("");
					champ2.setEditable(true);
					model.changeMode();
					bouton2.setSelected(true); 
					panneauJ2ok.setBorder(BorderFactory.createTitledBorder(" Joueur 2 ")); 	
				}
			}
		});
		img1 =(ImageIcon) liste1.getSelectedItem();
		img2 =(ImageIcon) liste2.getSelectedItem();

		panneauNb.add(bouton1);
		panneauNb.add(bouton2);
		JPanel panneauNbok = new JPanel(new GridLayout(0,3));
		panneauNbok.add(new JLabel(""));
		panneauNbok.add(panneauNb);
		
		panneauNbok.setBorder(BorderFactory.createTitledBorder(" Nombre de joueurs "));
		panneauNbok.setBackground(Color.decode("#48c0db"));
		getContentPane().add(panneauNbok,BorderLayout.NORTH);
		
		JPanel panneauCentral = new JPanel(new GridLayout(0,2));
		
		JPanel panneauJ1 = new JPanel(new GridLayout(0,2));
		panneauJ1.add(new JLabel("Nom",SwingConstants.CENTER));
		champ1 = new JTextField();
		panneauJ1.add(champ1);
		JLabel sym = new JLabel("Symbole",SwingConstants.CENTER);
		JLabel symb = new JLabel("Symbole",SwingConstants.CENTER);
		symb.setFont(new Font("Buxton Sketch",Font.ITALIC,25));
		sym.setFont(new Font("Buxton Sketch",Font.ITALIC,25));
		
		JPanel panneauJ1bis = new JPanel(new GridLayout(0,2));
		panneauJ1bis.add(symb);

		panneauJ1bis.add(liste1);
		
		JPanel panneauJ1ok = new JPanel(new BorderLayout());
		panneauJ1ok.add(panneauJ1,BorderLayout.NORTH);
		panneauJ1ok.add(panneauJ1bis,BorderLayout.CENTER);
		panneauJ1ok.setBorder(BorderFactory.createTitledBorder(" Joueur 1 "));
		JPanel panneauJ2 = new JPanel(new GridLayout(0,2));
		panneauJ2.add(new JLabel("Nom",SwingConstants.CENTER));
		champ2 = new JTextField("CPU");
		champ2.setEditable(false);
		panneauJ2.add(champ2);
		JPanel panneauJ2bis = new JPanel(new GridLayout(0,2));
		panneauJ2bis.add(sym);
		panneauJ2bis.add(liste2);
		
		panneauJ2ok = new JPanel(new BorderLayout());
		panneauJ2ok.add(panneauJ2,BorderLayout.NORTH);
		panneauJ2ok.add(panneauJ2bis,BorderLayout.CENTER);
		panneauJ2ok.setBorder(BorderFactory.createTitledBorder(" CPU "));
		
		panneauCentral.add(panneauJ1ok);
		panneauCentral.add(panneauJ2ok);
		panneauCentral.setBorder(BorderFactory.createTitledBorder(" Noms et symboles "));
		panneauCentral.setBackground(Color.decode("#48c0db"));
		getContentPane().add(panneauCentral,BorderLayout.CENTER);
		
		JPanel panneauBoutons = new JPanel(new GridLayout(0,5));
		panneauBoutons.add(new JLabel());
		JButton ok = new JButton("Jouer");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom1 = champ1.getText();
				String nom2 = champ2.getText();
				ImageIcon img1 = (ImageIcon) liste1.getSelectedItem();
				ImageIcon img2 = (ImageIcon) liste2.getSelectedItem();
				if (nom1 == null || nom1.length() == 0 || nom2 == null || nom2.length() == 0 || nom1.equals(nom2)){
					JOptionPane.showMessageDialog(null,"Vérifiez que vous avez bien entré deux noms, \net qu'ils sont différents.","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				else if (liste1.getSelectedIndex() == liste2.getSelectedIndex()) {
					JOptionPane.showMessageDialog(null,"Veuillez choisir deux symboles différents.","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				else {
					param.this.setVisible(false);
					Main m = new Main(model, img1, img2,nom1,nom2);
					
				}
			}
		});
		panneauBoutons.add(ok);
		panneauBoutons.add(new JLabel());
		panneauBoutons.setBackground(Color.decode("#48c0db"));
		JButton quit = new JButton("Quitter");
		quit.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { System.exit(0);}});

		panneauBoutons.add(quit);
		getContentPane().add(panneauBoutons,BorderLayout.SOUTH);

		setResizable(false);
		setBounds(225,175,550,280);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0) ; 
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setOK(boolean ok) {
		this.ok = ok;
	}

	public boolean getOK() { return ok;}

	public int getNB() {
		return (bouton1.isSelected())?1:2;
	}
}
