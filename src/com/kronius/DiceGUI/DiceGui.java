package com.kronius.DiceGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Random;

public class DiceGui implements ActionListener{
	private BasicLogger logger = BasicLogger.getInstance();

	// Initialize all swing objects.
    private static JFrame f = new JFrame("Dice GUI"); //create Frame
    private JPanel pnlNorth = new JPanel(); // North quadrant 
    private JPanel pnlSouth = new JPanel(); // South quadrant
    private JPanel pnlEast = new JPanel(); // East quadrant
	private JPanel pnlWest = new JPanel(); // West quadrant
	private JPanel pnlCenter = new JPanel(); // Center quadrant

	Button btnRoll = new Button("Roll");
    
    private static JLabel lblTotal = new JLabel("Total: 2");
	private static ImageIcon imageDie1 = new ImageIcon("res" + File.separator + "dice-1-md.png");
	private static ImageIcon imageDie2 = new ImageIcon("res" + File.separator + "dice-1-md.png");
	private static JLabel lblDie1 = new JLabel(imageDie1, JLabel.CENTER);
    private static JLabel lblDie2 = new JLabel(imageDie2, JLabel.CENTER);

    private ImageIcon imageDiceRoll = new ImageIcon("res" + File.separator + "diceRoll.gif");
	private JLabel lblDiceRoll = new JLabel(imageDiceRoll, JLabel.CENTER);
			
    
    // Menu
    private JMenuBar mb = new JMenuBar(); // Menubar
    private JMenu mnuFile = new JMenu("File"); // File Entry on Menu bar
    private JMenuItem mnuItemQuit = new JMenuItem("Quit"); // Quit sub item
    private JMenu mnuHelp = new JMenu("Help"); // Help Menu entry
    private JMenuItem mnuItemAbout = new JMenuItem("About"); // About Entry
    
    //private RollingGui roll;

    /** Constructor for the GUI */
    public DiceGui(){
    	logger.setLogName("DiceGui.log");
    	
    	logger.log(this.getClass().getName(), "Started"); 
    	
		// Set menubar
        f.setJMenuBar(mb);
        
		//Build Menus
        mnuFile.add(mnuItemQuit);  // Create Quit line
        mnuHelp.add(mnuItemAbout); // Create About line
        mb.add(mnuFile);        // Add Menu items to form
        mb.add(mnuHelp);

        // Add Buttons
        pnlNorth.add(btnRoll);
        pnlSouth.add(lblTotal);
        pnlEast.add(lblDie1);
		pnlWest.add(lblDie2);  
		pnlCenter.add(lblDiceRoll);
        
        // Setup Main Frame
        f.getContentPane().setLayout(new BorderLayout());
		f.getContentPane().add(pnlNorth, BorderLayout.NORTH);
		f.getContentPane().add(pnlSouth, BorderLayout.SOUTH);
        f.getContentPane().add(pnlEast, BorderLayout.EAST);
        f.getContentPane().add(pnlWest, BorderLayout.WEST);
        f.getContentPane().add(pnlCenter, BorderLayout.CENTER);
        
        lblDiceRoll.setVisible(false);
        		
		//Add Menu listener
        mnuItemQuit.addActionListener(this);
        mnuItemAbout.addActionListener(this);
		btnRoll.addActionListener(this);
    }
	
	public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnRoll){
			this.updateLabels();
		}else if (e.getSource() == mnuItemQuit){
			logger.log(this.getClass().getName(), "Quitting via menu Button");
			System.exit(0);
		}else if (e.getSource() == mnuItemAbout){
			logger.log(this.getClass().getName(), "Showing About Message");
			String aboutMessage = "Created by Aaron Graham on March 4th, 2012.";
			JOptionPane.showMessageDialog(f, aboutMessage, "About", 0, new ImageIcon("res" + File.separator + "Illuminati.png"));
		}else{
			logger.log(this.getClass().getName(), "Showing Unknown event Message" + e.getSource().toString());
			JOptionPane.showMessageDialog(f, "Unknown event." + e.getSource().toString(), "Error", 0, new ImageIcon("res" + File.separator + "error.png"));
		}
    }
	
    public void launchFrame(){
        // Display Frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack(); //Adjusts panel to components for display
        f.setVisible(true);
        logger.log(this.getClass().getName(), "Frame Loaded");
    }

	private void updateLabels(){
		
//		lblDiceRoll.setVisible(true);
//		lblDie1.setVisible(false);
//		lblDie2.setVisible(false);
//			    
//	    lblDiceRoll.setVisible(false);
//		lblDie1.setVisible(true);
//		lblDie2.setVisible(true);
		
		int size = 25;
		
		int[] arr = new int[size];
		int[] arr2 = new int[size];
		Random randomGenerator = new Random();
		for(int i = 0; i < size; i++){  
			int randomInt1 = (randomGenerator.nextInt(6)) + 1;
			arr[i] = randomInt1;
		}
		for(int j = 0; j < size; j++){  
			int randomInt2 = (randomGenerator.nextInt(6)) + 1;
			arr2[j] = randomInt2;
		}
		
		int randomInt3 = (randomGenerator.nextInt(size));
		int randomInt4 = (randomGenerator.nextInt(size));
				
		int a = 0;
		int b = 0;
		
		a = arr[randomInt3];
		b = arr2[randomInt4];

		lblDie1.setIcon(new ImageIcon("res" + File.separator + "dice-" + a + "-md.png"));
		lblDie2.setIcon(new ImageIcon("res" + File.separator + "dice-" + b + "-md.png"));
		int total = a + b;

		lblTotal.setText("Total: " + total);
		
		logger.log(this.getClass().getName(), "Roll Successful: Die 1: " + a + " Die 2: " + b + " Total Roll: " + total);
	}
	
    public static void main(String args[]){
        DiceGui gui = new DiceGui();
        gui.launchFrame();
    }
}