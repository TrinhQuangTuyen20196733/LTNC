package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.AuthController;
import DTO.LoginReq;
import Entity.UserEntity;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class SignupPage extends JFrame {

	private JPanel contentPane;
	private JTextField firstNametxt;
	private JTextField lastNametxt;
	private JTextField emailtxt;
	private JTextField phoneNumbertxt;
	private JTextField addresstxt;
	private JTextField nationalitytxt;
	private JPasswordField passwordtxt;
	private JPasswordField cmndtxt;

	
	/**
	 * Create the frame.
	 */
	public SignupPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 731);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HUST Airline Wellcome! Create an account");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(264, 10, 389, 28);
		contentPane.add(lblNewLabel);
		
		JLabel firstNameText = new JLabel("First Name");
		firstNameText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		firstNameText.setBounds(100, 67, 103, 28);
		contentPane.add(firstNameText);
		
		JLabel lastNameText = new JLabel("Last Name");
		lastNameText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lastNameText.setBounds(100, 117, 103, 28);
		contentPane.add(lastNameText);
		
		JLabel adressText = new JLabel("Address");
		adressText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		adressText.setBounds(100, 430, 103, 28);
		contentPane.add(adressText);
		
		JLabel ageText = new JLabel("Age");
		ageText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ageText.setBounds(100, 283, 103, 28);
		contentPane.add(ageText);
		
		JLabel phoneNumberText = new JLabel("PhoneNumber");
		phoneNumberText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		phoneNumberText.setBounds(100, 336, 103, 28);
		contentPane.add(phoneNumberText);
		
		JLabel cmndText = new JLabel("CMND");
		cmndText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmndText.setBounds(100, 384, 103, 28);
		contentPane.add(cmndText);
		
		JLabel nationalitytText = new JLabel("Nationality");
		nationalitytText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nationalitytText.setBounds(100, 485, 103, 28);
		contentPane.add(nationalitytText);
		
		JLabel emailText = new JLabel("Email");
		emailText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailText.setBounds(100, 170, 103, 28);
		contentPane.add(emailText);
		
		JLabel passwordText = new JLabel("Password");
		passwordText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordText.setBounds(100, 225, 103, 28);
		contentPane.add(passwordText);
		
		firstNametxt = new JTextField();
		firstNametxt.setBounds(223, 71, 274, 26);
		contentPane.add(firstNametxt);
		firstNametxt.setColumns(10);
		
		lastNametxt = new JTextField();
		lastNametxt.setColumns(10);
		lastNametxt.setBounds(223, 121, 274, 26);
		contentPane.add(lastNametxt);
		
		emailtxt = new JTextField();
		emailtxt.setColumns(10);
		emailtxt.setBounds(223, 170, 274, 26);
		contentPane.add(emailtxt);
		
		phoneNumbertxt = new JTextField();
		phoneNumbertxt.setColumns(10);
		phoneNumbertxt.setBounds(223, 340, 274, 26);
		contentPane.add(phoneNumbertxt);
		
		addresstxt = new JTextField();
		addresstxt.setColumns(10);
		addresstxt.setBounds(223, 434, 274, 26);
		contentPane.add(addresstxt);
		
		nationalitytxt = new JTextField();
		nationalitytxt.setColumns(10);
		nationalitytxt.setBounds(223, 489, 274, 26);
		contentPane.add(nationalitytxt);
		
		passwordtxt = new JPasswordField();
		passwordtxt.setBounds(223, 229, 274, 26);
		contentPane.add(passwordtxt);
		
		JSpinner agetxt = new JSpinner();
		agetxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		agetxt.setBounds(223, 284, 73, 27);
		contentPane.add(agetxt);
		
		cmndtxt = new JPasswordField();
		cmndtxt.setBounds(223, 391, 274, 26);
		contentPane.add(cmndtxt);
		
		JLabel gendertxtx = new JLabel("Gender");
		gendertxtx.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gendertxtx.setBounds(351, 288, 65, 18);
		contentPane.add(gendertxtx);
		
		JComboBox gendertxt = new JComboBox();
		gendertxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gendertxt.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Other"}));
		gendertxt.setBounds(426, 286, 73, 27);
		contentPane.add(gendertxt);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRole.setBounds(100, 545, 65, 18);
		contentPane.add(lblRole);
		
		JComboBox roletxt = new JComboBox();
		roletxt.setModel(new DefaultComboBoxModel(new String[] {"User", "Admin"}));
		roletxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		roletxt.setBounds(223, 541, 73, 27);
		contentPane.add(roletxt);
		
		JButton registerButton = new JButton("Register");
		registerButton.setBackground(Color.BLUE);
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerButton.setBounds(550, 602, 103, 42);
		
		//Add Action Listener
		    registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String firstName = firstNametxt.getText();
				String lastName = lastNametxt.getText();
				String email = emailtxt.getText();
				String phoneNumber = phoneNumbertxt.getText();
				String address = addresstxt.getText();
				String nationality= nationalitytxt.getText();
				String password = new String(passwordtxt.getPassword());
			    String cmnd =new String(cmndtxt.getPassword());
			    int age = (int)agetxt.getValue();
				String gender = (String)gendertxt.getSelectedItem();
				String role = (String)roletxt.getSelectedItem();
				
	       UserEntity userEntity = new UserEntity(firstName,lastName,address,gender,age,phoneNumber,cmnd,nationality,email,password,role);
				
				AuthController authController = new AuthController();
				var ms = authController.create(userEntity);
				
				if (ms.code==200) {
					 JOptionPane.showMessageDialog(null, " Successfull!", "Success", JOptionPane.DEFAULT_OPTION);
					 SignInPage userList = new SignInPage();
					 userList.setVisible(true);
				} else {
					 JOptionPane.showMessageDialog(null, ms.message, "Warning", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		contentPane.add(registerButton);
	}
}
