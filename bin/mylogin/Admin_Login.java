package mylogin;

import java.awt.BorderLayout;
import App.Application;
import java.awt.EventQueue;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Admin_Login extends JFrame {

	private JPanel contentPane;
	private JTextField usertxt;
	private JPasswordField passwordField;
	 private JButton btnNewButton;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Login frame = new Admin_Login();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public Admin_Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 939, 703);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(10, 88, 905, 566);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("USER_ID");
		lblNewLabel_1.setBounds(97, 161, 111, 35);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("PASSWORD");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(97, 250, 128, 39);
		panel.add(lblNewLabel_1_1);
		
		usertxt = new JTextField();
		usertxt.setColumns(10);
		usertxt.setBounds(292, 150, 248, 39);
		panel.add(usertxt);
		
	    btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String userId = usertxt.getText();
			 	 String password =  passwordField.getText();
	                try {
	                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project",
	                        "root", "CHIRU@2002@10@6");

	                    PreparedStatement st = (PreparedStatement) connection
	                        .prepareStatement("Select user_name, password from  user_info where user_name=? and password=?");

	                    st.setString(1, userId);
	                    st.setString(2, password);
	                    ResultSet rs = st.executeQuery();
	                    if (rs.next()) {
	                    	
	                        dispose();	  
	                        Application window2= new Application();                                              	                      
	                       window2.setLocationRelativeTo(null);	
	                        JOptionPane.showMessageDialog(btnNewButton,"Welcome "+ userId+" You have successfully logged in");
                          } else {
	                        JOptionPane.showMessageDialog(btnNewButton, "plz. Enter Valid Username or Password");
	                    }
	                } catch (SQLException sqlEx) {
	                    sqlEx.printStackTrace();
	                } 
			}
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setBounds(358, 382, 152, 39);
		panel.add(btnNewButton);
		
		JButton btnCreate = new JButton("CREATE");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRegistration lg=new UserRegistration();    // Here it the calling packeage of the Jframe
                lg.setLocationRelativeTo(null);		
				lg.setVisible(true);
				dispose();
				
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnCreate.setBounds(98, 382, 152, 39);
		panel.add(btnCreate);
		
		JLabel lblNewLabel_2 = new JLabel("If already user?");
		lblNewLabel_2.setForeground(new Color(0, 0, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2.setBounds(373, 446, 167, 31);
		panel.add(lblNewLabel_2);
		
		JLabel lm_label = new JLabel();
		lm_label.setBounds(635, 10, 243, 241);
		panel.add(lm_label);
		lm_label.setFont(new Font("Tahoma", Font.BOLD, 15));
		lm_label.setIcon(new ImageIcon("C:\\Users\\chira\\Downloads\\login icon.png"));
		
		JLabel lblNewLabel = new JLabel("   USER_ADMIN login");
		lblNewLabel.setBounds(211, 27, 269, 64);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setBackground(new Color(135, 206, 235));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(292, 250, 248, 39);
		panel.add(passwordField);
		
	      
	    
	}
}