package com.assignment.clothes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClothesItem extends JPanel
{
	private JCheckBox checkBox;
	private JLabel price, pictureItem;
	private double realPrice;
	
	public ClothesItem(String pic, String category, double price)
	{
		checkBox = new JCheckBox(category);
		this.realPrice = price;
		this.price = new JLabel("Price: "+String.format("$%.2f", price));
		
		pictureItem = new JLabel();
		pictureItem.setIcon(new ImageIcon(ClothesItem.class.getResource(pic)));
	
		setLayout(new BorderLayout());
		add(checkBox, BorderLayout.NORTH);
		add(pictureItem, BorderLayout.CENTER);
		add(this.price, BorderLayout.SOUTH);
	}
	
	public double getPrice()
	{
		return realPrice;
	}
	
	public String getCategory()
	{
		return checkBox.getText();
	}

	public boolean isChecked()
	{
		return checkBox.isSelected();
	}
	
}
