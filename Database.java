package com.assignment.clothes;

import java.sql.*;
import java.util.ArrayList;

public class Database 
{	
	
	ArrayList<ClothesItem> allItems;
	
	public Database(String username, String password)
	{
		allItems = new ArrayList<ClothesItem>(30);	//about 30 items
		getItemsByCategory("Trousers");
		getItemsByCategory("T-Shirts");
		getItemsByCategory("Jackets");
		getItemsByCategory("Shoes");
		getItemsByCategory("Sweaters");
		
	}
	
	public void getItemsByCategory(String category)
	{
		ResultSet rs;
		Connection conn = null;
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String database = 
              "jdbc:odbc:Clothes;DBQ=src/books.mdb;";
            conn = DriverManager.getConnection(database, "", "");
            Statement s = conn.createStatement();
            rs = s.executeQuery("SELECT * FROM BOOKS WHERE category = "+category+";");
            
            while ( rs.next() ) // this will step through our data row-by-row
    		{
    			allItems.add(new ClothesItem(rs.getString("picture"), rs.getString("category"), rs.getDouble("price")) );
    		}

			conn.close();
		}
		catch( ClassNotFoundException e)
		{
			System.out.println("Not the right class");
		} 
		catch (SQLException e) 
		{
			System.out.println("SQL error failed to get connection.\n Message:"+e.getMessage());
		}
	}
	
	public ClothesItem getItem(String category)
	{
		for(int i = 0; i < allItems.size(); i++ )
		{
			if( allItems.get(i).getCategory() == category)
			{
				ClothesItem temp = allItems.get(i);
				allItems.remove(temp);
				return temp;
			}
		}
		
		getItemsByCategory(category);
		return getItem(category);
	}
	
	
}