/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Asma
 */
public class Menu extends JMenuBar {
    	private static final String messageAide = ""+
		"Ce programme vous permet de jouer\n"+ 
		"au Morpion contre, au choix,\n"+
		"un adversaire humain ou l'ordinateur.\n"+
		"\nAu lancement du programme, chaque joueur entre\n"+ 
		"son nom et choisit un symbole dans la liste.\n"+
		"Dans le cas d'un jeu \"un joueur\", le joueur\n"+
		"choisit le symbole de l'ordinateur.\n"+
		"\nChaque joueur joue a tour de rï¿½le.\n"+
		"Une fois la partie terminï¿½e, le jeu vous invite a rejouer.\n"+
		"Vous continuez la partie en cours, les scores ï¿½voluants\n"+
		"aprï¿½s chaque partie.\n"+
		"\nLe choix d'une nouvelle partie dans le menu \"Partie\"\n"+
		"rï¿½initialise les scores ï¿½ zï¿½ro.";
	
	private static final String messageApd = ""+
	
	"\nVersion : Février 2014\n";
 Menu(final Model model,BoardView b)   {
     super();
     final BoardView bb = b;
		JMenu fichier = new JMenu("Partie");
		fichier.setMnemonic(KeyEvent.VK_P);
		JMenuItem nouveau = new JMenuItem("Nouvelle Partie");
		nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
		nouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int k =JOptionPane.showConfirmDialog(null,"Cette action entrainera la remise ï¿½ zï¿½ro des scores.\nEtes vous sur(e) ?","Nouvelle partie",JOptionPane.YES_NO_OPTION);
				if (k == JOptionPane.OK_OPTION){ model.newGame(); bb.clear(); }
			}
		});
		fichier.add(nouveau);
		JMenuItem quit = new JMenuItem("Quitter");
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fichier.add(quit);
		this.add(fichier);
		JMenu help = new JMenu("?");
		JMenuItem aide = new JMenuItem("Aide");
		aide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,InputEvent.CTRL_MASK));
		aide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,messageAide,"Aide",JOptionPane.INFORMATION_MESSAGE);
			}
		});	
		help.add(aide);
		JMenuItem apd = new JMenuItem("A propos de ...");
		apd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));
		apd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,messageApd,"A propos de ...",JOptionPane.INFORMATION_MESSAGE);
			}
		});	
		help.add(apd);
		this.add(help);
 }
 
}
