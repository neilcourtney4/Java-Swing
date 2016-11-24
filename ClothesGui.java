package com.assignment.clothes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.WindowConstants;

public class ClothesGUI extends JFrame implements ActionListener
{

	private static final long serialVersionUID = 1L;
	private JLabel totalPrice;
	private JButton selectOutfit, replaceOutfit;
	private JPanel frontEndPane, topPanel;
	private JPanel buttonHolder;
	private Database db;
	
	
	public ClothesGUI (String myTitle) 
	{
		// Set the title of the window
		super (myTitle);
		
		db = new Database("" , "");
		// Set the default size of the window
		setSize(800,600);
		setLocation(100,100);
		setContentPane(createContentPane());
		setDefaultCloseOperation (WindowConstants.DISPOSE_ON_CLOSE);
		setJMenuBar(createMenu());
		setVisible(true);
		
	}
	
	
	private Container createContentPane() 
    {
		frontEndPane = (JPanel) getContentPane();
		frontEndPane.setLayout(new BorderLayout());
		frontEndPane.addMouseListener(new MouseForPopup(this));
		
		//top panel currently empty before "Select outfit" is pressed
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 1));
		
		frontEndPane.add(topPanel, BorderLayout.NORTH);
		
		//Total Price label just with empty string so nothing displayed untill items shown
		totalPrice = new JLabel("Press Button Below To Start");
		frontEndPane.add(totalPrice, BorderLayout.CENTER);
		
		//adding buttons too
		buttonHolder = new JPanel(new GridLayout(1, 2));
		
		selectOutfit = new JButton("Select Outfit");
		selectOutfit.addActionListener(this);
		buttonHolder.add(selectOutfit);
		
		
		replaceOutfit = new JButton("Replace");	//added after the selectOutfit is pressed once
		replaceOutfit.addActionListener(this);
		frontEndPane.add(buttonHolder, BorderLayout.SOUTH);
		
		return frontEndPane;
    }
	
	private void changeText(String What )//part 2 - method so jlabel stringText will display text inputted in name field
	{
		totalPrice.setText(What);	
	}
	
	// this menu creates and returns a menu bar
	private JMenuBar createMenu()
	{
		return new JMenuBar();
	}
		
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==selectOutfit)
		{
			try
			{
				//if there is no second button displayed will throw exception
				//where button will be added
				//otherwise we don't want to add it again
				buttonHolder.getComponent(1);
			}
			catch (ArrayIndexOutOfBoundsException ex)
			{
				buttonHolder.add(replaceOutfit);
				topPanel.add(db.getItem("Trousers"));
				topPanel.add(db.getItem("T-Shirts"));
				topPanel.add(db.getItem("Jackets"));
				topPanel.add(db.getItem("Shoes"));
				topPanel.add(db.getItem("Sweaters"));
			}
			
			showTotalPrice();
		}
		
		if(e.getSource()==replaceOutfit)
		{
			if(!((ClothesItem)topPanel.getComponent(0)).isChecked())
			{
				topPanel.remove(0);
				topPanel.add(db.getItem("Trousers"), 0);
			}
			
			if(!((ClothesItem)topPanel.getComponent(1)).isChecked())
			{
				topPanel.remove(1);
				topPanel.add(db.getItem("T-Shirts"), 1);
			}
			
			if(!((ClothesItem)topPanel.getComponent(2)).isChecked())
			{
				topPanel.remove(2);
				topPanel.add(db.getItem("Jackets"), 2);
			}

			if(!((ClothesItem)topPanel.getComponent(3)).isChecked())
			{
				topPanel.remove(3);
				topPanel.add(db.getItem("Jackets"), 3);
			}
			
			if(!((ClothesItem)topPanel.getComponent(4)).isChecked())
			{
				topPanel.remove(4);
				topPanel.add(db.getItem("Shoes"), 4);
			}
			
			if(!((ClothesItem)topPanel.getComponent(5)).isChecked())
			{
				topPanel.remove(5);
				topPanel.add(db.getItem("Sweaters"), 5);
			}

			showTotalPrice();
		}
		
		
		
	}

	private void showTotalPrice()
	{
		Component[] allItems = topPanel.getComponents();
		double price = 0;
		
		for( Component item : allItems)
		{
			price += ((ClothesItem) item).getPrice();
		}
		
		changeText(String.format("Total Price: $%.2f", price));
	}
	
	public void showPane() 
	{
		setVisible(true);
	}
	
	public void hidePane() 
	{
		setVisible(false);
	}
	
	class MouseForPopup extends MouseAdapter
	{  
		JPopupMenu popupMenu;
		JMenuItem help, total, exit;
		
		public MouseForPopup(ActionListener al)
		{
			popupMenu = new JPopupMenu();
			
			help = new JMenuItem("Help");
			help.addActionListener(al);
			popupMenu.add(help);
			
			total = new JMenuItem("Total Price");
			total.addActionListener(al);
			popupMenu.add(total);
			
			exit = new JMenuItem("Exit");
			exit.addActionListener(al);
			popupMenu.add(exit);
		}
		
	    public void mousePressed( MouseEvent e ) 
	    { 
	    	checkForTriggerEvent( e ); 
	    }  
	
	    public void mouseReleased( MouseEvent e )   
	    { 
	    	checkForTriggerEvent( e ); 
	    }  
	
	    private void checkForTriggerEvent( MouseEvent e )  
	    {  
	        if ( e.isPopupTrigger() )  
	           popupMenu.show( e.getComponent(), e.getX(), e.getY() );  
	    }  
  }  	

}
