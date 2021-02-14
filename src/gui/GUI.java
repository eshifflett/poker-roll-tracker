package gui;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame{

	//layout for the frame
	CardLayout layout;
	JPanel cardHolder;
	
	//Data
	List<String> gamesFile;
	List<String> stakesFile;
	List<String> locationsFile;

	private void loadFiles() {
		/*
		 * INITIALIZING DATA
		 */
		Scanner a, b, c; //Scanners for games, stakes, and locations
		try { //Initializes scanners and lists, adds from file
			a = new Scanner(new File(System.getProperty("user.dir").toString() + "\\data\\Games.txt"));
			b = new Scanner(new File(System.getProperty("user.dir").toString() + "\\data\\Stakes.txt"));
			c = new Scanner(new File(System.getProperty("user.dir").toString() + "\\data\\Locations.txt"));
			
			gamesFile = new ArrayList<String>();
			while (a.hasNextLine()){
			    gamesFile.add(a.nextLine());
			}
			a.close();
			
			stakesFile = new ArrayList<String>();
			while (b.hasNextLine()){
			    stakesFile.add(b.nextLine());
			}
			b.close();
			
			locationsFile = new ArrayList<String>();
			while (c.hasNextLine()){
			    locationsFile.add(c.nextLine());
			}
			c.close();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
	/*The GUI for the project*/
	public GUI() {
		
		loadFiles();
		
		/*
		 * INITIAL WINDOW SETUP: TITLE, SIZE, MENUBAR, CARDLAYOUT SETUP
		 */

		/*Setting title/size and making it non-resizable*/
		setTitle("Poker Earnings Tracker");
		setSize(400, 300);
		setResizable(false);

		/*Creating menu bar that will be used to add things like stakes and locations*/
		JMenuBar bar = new JMenuBar(); //Creates Bar
		JMenu add = new JMenu("Add New..."); //Creates drop down from bar
		bar.add(add); //Adding drop down
		JMenuItem game = new JMenuItem("Game"); //Creates drop down option
		JMenuItem stake = new JMenuItem("Stake"); //Creates drop down option
		JMenuItem loc = new JMenuItem("Location"); //Creates drop down option
		add.add(game); //Adds to drop down
		add.add(stake); //Adds to drop down
		add.add(loc); //Adds to drop down
		JMenu remove = new JMenu("Remove..."); //Creates drop down from bar
		bar.add(remove);
		JMenuItem gamer = new JMenuItem("Game"); //Creates drop down option
		JMenuItem staker = new JMenuItem("Stake"); //Creates drop down option
		JMenuItem locr = new JMenuItem("Location"); //Creates drop down option
		remove.add(gamer); //Adds to drop down
		remove.add(staker); //Adds to drop down
		remove.add(locr); //Adds to drop down
		setJMenuBar(bar); //Sets this bar to always be at the top of the frame

		/*cardHolder will be the panel that "holds" all of the other panels to be displayed
		 *with help from the formatting of layout. This initializes them.*/
		cardHolder = new JPanel(); //Main JPanel
		layout = new CardLayout(); //Initializing layout
		cardHolder.setLayout(layout); //Setting the layout
		getContentPane().add(cardHolder); //Adds cardHolder to frame

		
		
		
		

		/*
		 * PANEL FOR FIRST PAGE
		 */
		JPanel frontPanel = new JPanel();
		//setting the layout
		frontPanel.setLayout(new GridLayout(4, 1));

		//Creating four buttons for the main page
		JButton frontAdd = new JButton("Add a Completed Session");
		frontAdd.addActionListener(new ActionListener() { //Adding button functionality
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(cardHolder, "Add Session Page");
			}
		});
		JButton frontGen = new JButton("Generate Report");
		JButton frontAll = new JButton("All Sessions");
		JButton frontSho = new JButton("Show Bankroll Graph");

		//Setting fonts for buttons
		frontAdd.setFont(new Font("Arial", Font.PLAIN, 20));
		frontGen.setFont(new Font("Arial", Font.PLAIN, 20));
		frontAll.setFont(new Font("Arial", Font.PLAIN, 20));
		frontSho.setFont(new Font("Arial", Font.PLAIN, 20));

		//Adding buttons to panel
		frontPanel.add(frontAdd);
		frontPanel.add(frontGen);
		frontPanel.add(frontAll);
		frontPanel.add(frontSho);

		//Adding to cardHolder
		cardHolder.add(frontPanel, "Front Page");

		
		
		
		

		/*
		 * PANEL FOR SECOND PAGE
		 */
		JPanel addPanel = new JPanel();
		//setting the layout
		addPanel.setLayout(new GridLayout(7, 3, 1, 1));

		/*Now we must create 6 rows of swing stuff that will be used to take in user
		 *input regarding their sessions.*/

		//Creating and adding "Game" option:
		JLabel gameLabelLeft = new JLabel("Game:", JLabel.CENTER);
		JComboBox<Object> gameList = new JComboBox<Object>(gamesFile.toArray());
		JLabel gameLabelRight = new JLabel("", JLabel.CENTER);
		addPanel.add(gameLabelLeft);
		addPanel.add(gameList);
		addPanel.add(gameLabelRight);

		//Creating and adding "Stake" option:
		JLabel stakeLabelLeft = new JLabel("Stake:", JLabel.CENTER);
		JComboBox<Object> stakeList = new JComboBox<Object>(stakesFile.toArray());
		JLabel stakeLabelRight = new JLabel("", JLabel.CENTER);
		addPanel.add(stakeLabelLeft);
		addPanel.add(stakeList);
		addPanel.add(stakeLabelRight);

		//Creating and adding "Location" option:
		JLabel locLabelLeft = new JLabel("Location:", JLabel.CENTER);
		JComboBox<Object> locList = new JComboBox<Object>(locationsFile.toArray());
		JLabel locLabelRight = new JLabel("", JLabel.CENTER);
		addPanel.add(locLabelLeft);
		addPanel.add(locList);
		addPanel.add(locLabelRight);

		//Creating and adding "Buy-In" option:
		JLabel buyLabelLeft = new JLabel("Buy-In ($):", JLabel.CENTER);
		JTextField buyBox = new JTextField();
		JLabel buyLabelRight = new JLabel("(e.g. 10.50, 20, etc)", JLabel.CENTER);
		addPanel.add(buyLabelLeft);
		addPanel.add(buyBox);
		addPanel.add(buyLabelRight);

		//Creating and adding "Cashed Out:" option:
		JLabel cashLabelLeft = new JLabel("Cashed Out ($):", JLabel.CENTER);
		JTextField cashBox = new JTextField();
		JLabel cashLabelRight = new JLabel("(e.g. 10.50, 20, etc)", JLabel.CENTER);
		addPanel.add(cashLabelLeft);
		addPanel.add(cashBox);
		addPanel.add(cashLabelRight);

		//Creating and adding "Duration" option:
		JLabel durLabelLeft = new JLabel("Duration (Hours):", JLabel.CENTER);
		JTextField durBox = new JTextField();
		JLabel durLabelRight = new JLabel("(e.g. 8.5, 7, etc.)", JLabel.CENTER);
		addPanel.add(durLabelLeft);
		addPanel.add(durBox);
		addPanel.add(durLabelRight);

		//Creating and adding "Action Buttons" at the bottom:
		JButton mainMenu = new JButton("Main Menu");
		mainMenu.addActionListener(new ActionListener() { //Adding button functionality
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(cardHolder, "Front Page");
			}
		});
		JButton submit = new JButton("Submit");
		JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() { //Adding button functionality
			@Override
			public void actionPerformed(ActionEvent e) {
				gameList.setSelectedIndex(0);
				stakeList.setSelectedIndex(0);
				locList.setSelectedIndex(0);
				buyBox.setText("");
				cashBox.setText("");
				durBox.setText("");
			}
		});
		addPanel.add(mainMenu);
		addPanel.add(submit);
		addPanel.add(reset);

		//Adding to cardHolder
		cardHolder.add(addPanel, "Add Session Page");
		
		game.addActionListener(new ActionListener() { //Adding button functionality
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String name = JOptionPane.showInputDialog("Add Game:", null);
					if(name != null) {
						if(!name.equals("")) {
							if(!gamesFile.contains(name)) {
								BufferedWriter out = new BufferedWriter(new FileWriter(System.getProperty("user.dir").toString() + "\\data\\Games.txt", true));
								out.write(name + "\n");
								out.close();
								gameList.addItem(name);
							} else {
								JOptionPane.showMessageDialog(null,
									    "Game type already exists.",
									    "Input Error",
									    JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null,
								    "Please do not leave input blank.",
								    "Input Error",
								    JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		});
		
		stake.addActionListener(new ActionListener() { //Adding button functionality
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//Creating popup
					JTextField sbField = new JTextField(5);
					JTextField bbField = new JTextField(5);
					JPanel myPanel = new JPanel();
					myPanel.add(new JLabel("SB:"));
					myPanel.add(sbField);
					myPanel.add(Box.createHorizontalStrut(15)); // a spacer
					myPanel.add(new JLabel("BB:"));
					myPanel.add(bbField);
					int result = JOptionPane.showConfirmDialog(null, myPanel, 
							"Please Enter SB and BB", JOptionPane.OK_CANCEL_OPTION);
					//Validating and submitting input
					if (result == JOptionPane.OK_OPTION) {
						//Retrieving input
						String sbStr = sbField.getText();
						String bbStr = bbField.getText();
						//Regexes for stake input
						boolean sb = sbStr.matches("([0-9]+(\\.[0-9]{2})?)||(\\.[0-9]{2})");
						boolean bb = bbStr.matches("([0-9]+(\\.[0-9]{2})?)||(\\.[0-9]{2})");
						if(sb && bb) {
							float sbFl = Float.parseFloat(sbStr);
							float bbFl = Float.parseFloat(bbStr);
							if(sbFl <= bbFl) {
								DecimalFormat x = new DecimalFormat("0.00");
								String outString = x.format(sbFl) + "/" + x.format(bbFl);
								if(!stakesFile.contains(outString)) {
									BufferedWriter out = new BufferedWriter(new FileWriter(System.getProperty("user.dir").toString() + "\\data\\Stakes.txt", true));
									out.write(x.format(sbFl) + "/" + x.format(bbFl) + "\n");
									out.close();
									stakeList.addItem(outString);
								} else {
									JOptionPane.showMessageDialog(null,
										    "Stake already exists.",
										    "Input Error",
										    JOptionPane.ERROR_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null,
									    "SB must be less than or equal to BB.",
									    "Input Error",
									    JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null,
								    "Improper format. Good format examples: 10, 25, .50, 2.50",
								    "Input Error",
								    JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		loc.addActionListener(new ActionListener() { //Adding button functionality
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String name = JOptionPane.showInputDialog("Add Location:", null);
					if(name != null) {
						if(!name.equals("")) {
							if(!locationsFile.contains(name)) {
								BufferedWriter out = new BufferedWriter(new FileWriter(System.getProperty("user.dir").toString() + "\\data\\Locations.txt", true));
								out.write(name + "\n");
								out.close();
								locList.addItem(name);
							} else {
								JOptionPane.showMessageDialog(null,
									    "Location already exists.",
									    "Input Error",
									    JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null,
								    "Please do not leave input blank.",
								    "Input Error",
								    JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		});
		
		gamer.addActionListener(new ActionListener() { //Adding button functionality
			@Override
			public void actionPerformed(ActionEvent e) {
				if(gamesFile.size() > 0) {
					String s = (String)JOptionPane.showInputDialog(null,
		                    "Remove which?",
		                    "Remove",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    gamesFile.toArray(),
		                    gamesFile.get(0));
					if(s != null) {
						gamesFile.remove(s);
						try {
							FileChannel.open(Paths.get(System.getProperty("user.dir").toString() + "\\data\\Games.txt"), StandardOpenOption.WRITE).truncate(0).close();
							BufferedWriter out = new BufferedWriter(new FileWriter(System.getProperty("user.dir").toString() + "\\data\\Games.txt", true));
							for(String x : gamesFile) {
								out.write(x + "\n");
							}
							out.close();
							gameList.removeItem(s);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null,
						    "There are no game types to remove.",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		
		staker.addActionListener(new ActionListener() { //Adding button functionality
			@Override
			public void actionPerformed(ActionEvent e) {
				if(stakesFile.size() > 0) {
					String s = (String)JOptionPane.showInputDialog(null,
		                    "Remove which?",
		                    "Remove",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    stakesFile.toArray(),
		                    stakesFile.get(0));
					if(s != null) {
						stakesFile.remove(s);
						try {
							FileChannel.open(Paths.get(System.getProperty("user.dir").toString() + "\\data\\Stakes.txt"), StandardOpenOption.WRITE).truncate(0).close();
							BufferedWriter out = new BufferedWriter(new FileWriter(System.getProperty("user.dir").toString() + "\\data\\Stakes.txt", true));
							for(String x : stakesFile) {
								out.write(x + "\n");
							}
							out.close();
							stakeList.removeItem(s);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null,
						    "There are no game types to remove.",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		
		locr.addActionListener(new ActionListener() { //Adding button functionality
			@Override
			public void actionPerformed(ActionEvent e) {
				if(locationsFile.size() > 0) {
					String s = (String)JOptionPane.showInputDialog(null,
		                    "Remove which?",
		                    "Remove",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    locationsFile.toArray(),
		                    locationsFile.get(0));
					if(s != null) {
						locationsFile.remove(s);
						try {
							FileChannel.open(Paths.get(System.getProperty("user.dir").toString() + "\\data\\Locations.txt"), StandardOpenOption.WRITE).truncate(0).close();
							BufferedWriter out = new BufferedWriter(new FileWriter(System.getProperty("user.dir").toString() + "\\data\\Locations.txt", true));
							for(String x : locationsFile) {
								out.write(x + "\n");
							}
							out.close();
							locList.removeItem(s);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null,
						    "There are no game types to remove.",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}

		});
	}

	public static void main(String args[]) {
		GUI demo = new GUI();
		demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		demo.setVisible(true); 
	}

}
