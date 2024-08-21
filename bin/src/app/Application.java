package App;
import java.awt.EventQueue;
import mylogin.Admin_Login;
import mylogin.UserRegistration;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.jar.JarException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.GridLayout;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Application extends JFrame{

	private JFrame aPframe;
	private JTextField txtbookname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable mysql_table;
	private JTextField textId;
    DefaultTableModel MODEL;
    private JSpinner txtqty;
	  	   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.aPframe.setVisible(true);
					 window.setLocationRelativeTo(null);	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 */
	public Application() throws NumberFormatException{
		try {
		initialize();
		Connect();
		table_load();
	}catch(Exception ece){
		
	}
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txttotal;
	 
	public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root","yourpassword");
	        }
	        catch (ClassNotFoundException ex)
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex)
	        {
	            ex.printStackTrace();
	        }
	 
	    }
	public void table_load()
    {
     try
     {
    pst = con.prepareStatement("select * from books_info");
    rs = pst.executeQuery();
    mysql_table.setModel(DbUtils.resultSetToTableModel(rs));
    
}
     catch (SQLException e)
     {
     e.printStackTrace();
  }
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		aPframe = new JFrame();
		aPframe.setVisible(true);
		aPframe.getContentPane().setBackground(new Color(135, 206, 235));
		aPframe.setFont(new Font("Tahoma", Font.BOLD, 14));
		aPframe.setForeground(Color.BLUE);
		aPframe.setTitle("Interface");
		aPframe.setSize(300,250);
		aPframe.setBackground(new Color(255, 255, 255));
		aPframe.setBounds(100, 100, 1200, 704);
		aPframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aPframe.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 235));
		panel.setFont(new Font("Yu Gothic", Font.BOLD, 54));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "REGISTRATION ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(23, 147, 535, 312);
		aPframe.getContentPane().add(panel);
		panel.setLayout(null);
		
		txtbookname = new JTextField();
		txtbookname.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtbookname.setBounds(183, 51, 231, 35);
		panel.add(txtbookname);
		txtbookname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtedition.setBounds(183, 148, 231, 38);
		panel.add(txtedition);
		txtedition.setColumns(10);
		
		txtprice = new JTextField();
		txtprice.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtprice.setBounds(183, 226, 231, 38);
		panel.add(txtprice);
		txtprice.setColumns(10);
		
		JLabel lblprice = new JLabel("Price");
		lblprice.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblprice.setBounds(53, 223, 94, 38);
		panel.add(lblprice);
		
		JLabel labelbedition = new JLabel("Edition");
		labelbedition.setFont(new Font("Tahoma", Font.BOLD, 17));
		labelbedition.setBounds(53, 137, 95, 41);
		panel.add(labelbedition);
		
		JLabel labelbname = new JLabel("Book Name");
		labelbname.setFont(new Font("Tahoma", Font.BOLD, 17));
		labelbname.setBounds(53, 46, 120, 41);
		panel.add(labelbname);
		
		JButton btnNewButton_2 = new JButton("UPDATE");  // ----------------------------------------------------UPDATE
		btnNewButton_2.setForeground(new Color(107, 142, 35));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {	    	    		
			    	String Bookname,Edition,Id,price;
			    	Bookname = txtbookname.getText();
			    	Edition = txtedition.getText();
			    	price = txtprice.getText();
			    	Id  = textId.getText();
			        String  qty=txtqty.getValue().toString();  
			        int tot = Integer.parseInt(txtqty.getValue().toString()) * Integer.parseInt(txtprice.getText());	        
			    

			    	pst = con.prepareStatement("update books_info set Bookname= ?,Edition=?,Price=? where id =?");
			    	pst.setString(1, Bookname);
			    	pst.setString(2, Edition);
			    	pst.setString(3, price);
			    	pst.setString(4,Id);
			    
			    	if(pst!=null) {
			    	pst.executeUpdate();

			    	  table_load();        
			    	txtbookname.setText("");
			    	txtedition.setText("");
			    	txtqty.setToolTipText("");
			    	txtprice.setText(""); 
			    	txtqty.getValue().toString(); 
			    	txttotal.getSize().toString();
			    	
			    	txtbookname.requestFocus();
			    	JOptionPane.showMessageDialog(null, "Record updated!!!!");
			    	}

			    	   }
			    	 
			    	catch (SQLException e1)
			    	        {
			    	e1.printStackTrace();
			    	}
			    	catch (NumberFormatException e1)
			        {
			       e1.printStackTrace();}
			    	
			        
					}
				
		});
		btnNewButton_2.setBounds(229, 486, 135, 64);
		aPframe.getContentPane().add(btnNewButton_2);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnNewButton_1 = new JButton("Clear"); // -------------------------------------------------------------CLEAR
		btnNewButton_1.setForeground(new Color(75, 0, 130));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String Id;
				Id  = textId.getText();
				try {
				pst = con.prepareStatement("delete from books_info where Id <=?");
				            pst.setString(1, Id);
				            pst.executeUpdate();
				            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
				            table_load();
				          
				            txtbookname.setText("");
				            txtedition.setText("");
				            txtprice.setText("");
				            txtbookname.requestFocus();
				}
				 
				            catch (SQLException e1) {
				e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(398, 488, 127, 61);
		aPframe.getContentPane().add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnNewButton = new JButton("EXIT");
		btnNewButton.setForeground(new Color(250, 128, 114));
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	// --------------------------------------------EXIT			
				aPframe.dispose();	  
			}
		});
		btnNewButton.setBounds(1018, 23, 135, 57);
		aPframe.getContentPane().add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  // ------------------------------------------------DELETE
				
				String Id;
				Id  = textId.getText();
				try {
				pst = con.prepareStatement("delete from books_info where Id =?");
				            pst.setString(1, Id);
				            pst.executeUpdate();
				            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
				            table_load();
				          
				            txtbookname.setText("");
				            txtedition.setText("");
				            txtprice.setText("");
				            txtbookname.requestFocus();
				}
				 
				            catch (SQLException e1) {
				e1.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDelete.setBounds(891, 596, 135, 60);
		aPframe.getContentPane().add(btnDelete);
		
		// jtable-------------------------
		mysql_table = new JTable();
		mysql_table.setFillsViewportHeight(true);
		mysql_table.setBackground(new Color(240, 230, 140));
		mysql_table.setBounds(595, 316, 581, 251);
		MODEL  = new DefaultTableModel();		
		Object[]  column = {txtbookname,txtedition,txtprice};
	    final  Object [] row = new Object[4];
	    MODEL.setColumnIdentifiers(column);
	    mysql_table.setModel(MODEL);
		aPframe.getContentPane().add(mysql_table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(176, 224, 230));
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "SEARCH BAR", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(23, 574, 535, 81);
		aPframe.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel Lblbid = new JLabel("Book Id");  // ------------------------------------------------------------BOOK ID
		Lblbid.setFont(new Font("Tahoma", Font.BOLD, 17));
		Lblbid.setBounds(37, 24, 125, 27);
		panel_1.add(Lblbid);
		
		textId = new JTextField();
		textId.setBackground(new Color(255, 255, 255));
		textId.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
				try {
				String Id = textId.getText();
				 
                pst = con.prepareStatement("select Bookname,Edition,Price from books_info where Id = ?");
                pst.setString(1, Id);
                ResultSet rs = pst.executeQuery();
 
            if(rs.next()==true)
            {
              
                String name = rs.getString(1);
                String edition = rs.getString(2);
                String price = rs.getString(3);
                
                txtbookname.setText(name);
                txtedition.setText(edition);
                txtprice.setText(price);        
                
            }  
            else
            {
            	JOptionPane.showMessageDialog(null, "NO record found Oops!");     
            }
			}catch(Exception er) {
				
			}}
			}
		});
		textId.setColumns(10);
		textId.setBounds(213, 27, 216, 27);
		panel_1.add(textId);
		
		JButton btnNewButton_3 = new JButton("Save");  //  ------------------------------------------------------------------------SAVE
		btnNewButton_3.setForeground(new Color(255, 0, 0));
		btnNewButton_3.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	
	    	try {	    	    		
	    	String Bookname,Edition,Price;
	        String  qty=txtqty.getValue().toString();
	        int tot = Integer.parseInt(txtqty.getValue().toString()) * Integer.parseInt(txtprice.getText());	        
	    	Bookname = txtbookname.getText();
	    	Edition = txtedition.getText();
	    	Price = txtprice.getText();

	    	pst = con.prepareStatement("insert into books_info(Bookname,Edition,Price,quantity,Total)values(?,?,?,?,?)");
	    	pst.setString(1, Bookname);
	    	pst.setString(2, Edition);
	    	pst.setString(3, Price);
	    	pst.setString(4,qty);
	    	pst.setInt(5,tot);
	    	
	    	if(pst!=null) {
	    	pst.executeUpdate();
	    	JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
	    	}
	    	
	    	  table_load();        
	    	txtbookname.setText("");
	    	txtedition.setText("");
	    	txtqty.setValue(" ");
	    	txtprice.setText(" ");   	
	    	txttotal.setText(String.valueOf(tot));
	    	txtbookname.requestFocus();
	    	   }
	    	 
	    	catch (SQLException e1)
	    	        {
	    	e1.printStackTrace();
	    	}
	    	catch (NumberFormatException e1)
	        {
	       e1.printStackTrace();}
	    	
	        
			}

});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_3.setBounds(64, 486, 119, 64);
		aPframe.getContentPane().add(btnNewButton_3);
		
		JButton btnView = new JButton("VIEW");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//--------------------------------------------VIEW
				
				mysql_table = new JTable();
				mysql_table.setVisible(true);
			}
		});
		btnView.setForeground(new Color(199, 21, 133));
		btnView.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnView.setBounds(629, 598, 135, 57);
		aPframe.getContentPane().add(btnView);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 182, 193));
		panel_2.setBounds(79, 10, 814, 122);
		aPframe.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblbookshoptitle = new JLabel("   Book Shop ");
		lblbookshoptitle.setForeground(new Color(0, 0, 255));
		lblbookshoptitle.setBounds(296, 34, 292, 54);
		panel_2.add(lblbookshoptitle);
		lblbookshoptitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(135, 206, 235));
		panel_3.setBounds(629, 148, 518, 158);
		aPframe.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblQty = new JLabel("QTY");
		lblQty.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblQty.setBounds(91, 34, 69, 41);
		panel_3.add(lblQty);
		
		txttotal = new JTextField();
		txttotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		txttotal.setColumns(10);
		txttotal.setBounds(288, 95, 120, 41);
		panel_3.add(txttotal);
		
		
	    txtqty = new JSpinner();
		txtqty.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
			    String  qty=txtqty.getValue().toString();
		        String price = txtprice.getText();
		        int tot = Integer.parseInt(txtqty.getValue().toString()) * Integer.parseInt(txtprice.getText());	        
		        txttotal.setText(String.valueOf(tot));}
				catch(Exception eww) {	
					
				}
			}
			
		});
		txtqty.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtqty.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		txtqty.setBounds(288, 33, 120, 41);
		panel_3.add(txtqty);
		
		JLabel lblTotalamout = new JLabel("TotalAmout");
		lblTotalamout.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTotalamout.setBounds(72, 95, 120, 41);
		panel_3.add(lblTotalamout);
	}

	
}