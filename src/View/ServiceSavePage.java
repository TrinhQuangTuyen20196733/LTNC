package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.exceptions.AssertionFailedException;

import Controller.AirlineController;
import Controller.AuthController;
import Controller.ServiceController;
import DTO.LoginReq;
import DTO.MessageResponse;
import Entity.Airline;
import Entity.Service;
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
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import java.util.Date;
import java.util.Calendar;

public class ServiceSavePage extends JFrame {

	private JPanel contentPane;
	private JTextField nametxt;
	private JTextField codetxt;
	private ServiceController serviceController = new ServiceController();

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServiceSavePage frame = new ServiceSavePage(null);
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
	public ServiceSavePage(Service service) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 731);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Save airline information");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(334, 47, 389, 28);
		contentPane.add(lblNewLabel);
		
		JLabel firstNameText = new JLabel("Name");
		firstNameText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		firstNameText.setBounds(76, 169, 103, 28);
		contentPane.add(firstNameText);
		
		JLabel emailText = new JLabel("Code");
		emailText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailText.setBounds(76, 251, 127, 28);
		contentPane.add(emailText);
		
		JLabel passwordText = new JLabel("Cost");
		passwordText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordText.setBounds(76, 332, 103, 28);
		contentPane.add(passwordText);
		
		nametxt = new JTextField(service !=  null ? service.getName(): "");
		nametxt.setBounds(223, 169, 294, 30);
		contentPane.add(nametxt);
		nametxt.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(Color.LIGHT_GRAY);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(365, 440, 103, 42);
		
		
		contentPane.add(btnSave);
		
		codetxt = new JTextField(service !=  null ? service.getCode(): "");
		codetxt.setColumns(10);
		codetxt.setBounds(223, 253, 294, 30);
		contentPane.add(codetxt);
		
		SpinnerNumberModel spinnerModel = 	new SpinnerNumberModel(service!=null?service.getCost():0, 0, 1000000, 1);
	
		JSpinner costtxt = new JSpinner(spinnerModel);
		costtxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		costtxt.setBounds(223, 322, 294, 37);
		contentPane.add(costtxt);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack.setBackground(Color.LIGHT_GRAY);
		btnBack.setBounds(29, 47, 103, 42);
		contentPane.add(btnBack);
		
		contentPane.add(btnBack);
		 btnBack.addActionListener(new ActionListener() {
				
	 			@Override
	 			public void actionPerformed(ActionEvent e) {
	 			
	 				
	 			ServiceList adminHomePage = new ServiceList();
	 			adminHomePage.setVisible(true);
	 			}
	 		});
		//Add Action Listener
	    btnSave.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int id = (service!=null) ? (service.getId()) : 0;
			String name = nametxt.getText();
			String code = codetxt.getText();
			int cost = (int)costtxt.getValue();
			
			
			
           Service  service1 = new Service(id,name,code,cost);
           
			MessageResponse ms = new MessageResponse();
			if (service!=null) {
				 ms =  serviceController.update(service1);
			} else {
				ms =  serviceController.create(service1);
			}
			if (ms.code==200) {
				 JOptionPane.showMessageDialog(null, " Successfull!", "Success", JOptionPane.DEFAULT_OPTION);
				 ServiceList serviceList = new ServiceList();
				 serviceList.setVisible(true);
				
			} else {
				 JOptionPane.showMessageDialog(null, ms.message, "Warning", JOptionPane.WARNING_MESSAGE);
			}
			
		}
	});
	}
}
