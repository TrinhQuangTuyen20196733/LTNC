package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.AuthController;
import DTO.LoginReq;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Cursor;
import View.UserList;
import javax.swing.JPasswordField;

public class SignInPage extends JFrame {

	private JPanel contentPane;
	private JTextField userNameText;
	private JPasswordField passwordtxt;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignInPage frame = new SignInPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */


	public SignInPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 662);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Well come to HUST Airline");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(265, 25, 273, 47);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(101, 167, 87, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel password = new JLabel("Password");
		password.setFont(new Font("Tahoma", Font.PLAIN, 16));
		password.setBounds(88, 272, 100, 13);
		contentPane.add(password);
		
		userNameText = new JTextField();
		userNameText.setBounds(235, 163, 273, 39);
		contentPane.add(userNameText);
		userNameText.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(496, 424, 109, 39);
		passwordtxt = new JPasswordField();
		passwordtxt.setBounds(235, 261, 273, 39);
		contentPane.add(passwordtxt);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = userNameText.getText();
				String password = new String(passwordtxt.getPassword());
		        LoginReq loginReq = new LoginReq(userName, password);
				
				AuthController authController = new AuthController();
				var ms = authController.signIn(loginReq);
				if (ms.code==200) {
					 JOptionPane.showMessageDialog(null, "Login successfull!", "Success", JOptionPane.DEFAULT_OPTION);
					if (ms.message.equals("Admin")) {
						AdminHomePage adminHomePage = new AdminHomePage();
						adminHomePage.setVisible(true);
					} else if (ms.message.equals("User")){
						FlightList flightList = new FlightList();
						flightList.setVisible(true);
					}
					
				} else {
					 JOptionPane.showMessageDialog(null, ms.message, "Warning", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		contentPane.add(btnNewButton);
		
		JLabel RegisterText = new JLabel("Register new account?");
		RegisterText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		RegisterText.setBounds(150, 437, 171, 33);
		
		// Add event listener
		RegisterText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Label clicked!");
                SignupPage signUp = new SignupPage();
				 signUp.setVisible(true);
            }
        });
		
		contentPane.add(RegisterText);
		
		
		
	}
}
