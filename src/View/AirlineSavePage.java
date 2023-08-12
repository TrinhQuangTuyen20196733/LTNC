package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.exceptions.AssertionFailedException;

import Controller.AirlineController;
import Controller.AuthController;
import DTO.LoginReq;
import DTO.MessageResponse;
import Entity.Airline;
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
import java.util.Date;
import java.util.Calendar;

public class AirlineSavePage extends JFrame {

	private JPanel contentPane;
	private JTextField nametxt;
	private JTextField headerQuaterTxt;
	private JTextField foundertxt;
	private AirlineController airlineController = new AirlineController();

	
	/**
	 * Create the frame.
	 */
	public AirlineSavePage(Airline airline) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 731);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Save airline information");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(238, 67, 389, 28);
		contentPane.add(lblNewLabel);
		
		JLabel firstNameText = new JLabel("Name");
		firstNameText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		firstNameText.setBounds(76, 169, 103, 28);
		contentPane.add(firstNameText);
		
		JLabel establishTime = new JLabel("Establish Time");
		establishTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		establishTime.setBounds(76, 255, 127, 28);
		contentPane.add(establishTime);
		
		JLabel emailText = new JLabel("HeadQuaters");
		emailText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailText.setBounds(76, 350, 127, 28);
		contentPane.add(emailText);
		
		JLabel passwordText = new JLabel("Founder");
		passwordText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordText.setBounds(76, 437, 103, 28);
		contentPane.add(passwordText);
		
		nametxt = new JTextField(airline !=  null ? airline.getName(): "");
		nametxt.setBounds(223, 169, 294, 30);
		contentPane.add(nametxt);
		nametxt.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(Color.LIGHT_GRAY);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(598, 515, 103, 42);
		
		
		contentPane.add(btnSave);
		
		JSpinner establishTimetxt = new JSpinner();
		establishTimetxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		establishTimetxt.setModel(new SpinnerDateModel(new Date(1691514000000L), null, null, Calendar.DAY_OF_YEAR));
		establishTimetxt.setBounds(223, 253, 294, 30);
		contentPane.add(establishTimetxt);
		
		headerQuaterTxt = new JTextField(airline !=  null ? airline.getHeadQuatersAddress(): "");
		headerQuaterTxt.setColumns(10);
		headerQuaterTxt.setBounds(223, 348, 294, 30);
		contentPane.add(headerQuaterTxt);
		
		foundertxt = new JTextField(airline !=  null ? airline.getFounder(): "");
		foundertxt.setColumns(10);
		foundertxt.setBounds(223, 439, 294, 30);
		contentPane.add(foundertxt);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack.setBackground(Color.LIGHT_GRAY);
		btnBack.setBounds(34, 62, 103, 42);
		contentPane.add(btnBack);
		 btnBack.addActionListener(new ActionListener() {
				
	 			@Override
	 			public void actionPerformed(ActionEvent e) {
	 			
	 				
	 			AirlineList adminHomePage = new AirlineList();
	 			adminHomePage.setVisible(true);
	 			}
	 		});
		//Add Action Listener
	    btnSave.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int id = (airline!=null) ? (airline.getId()) : 0;
			String name = nametxt.getText();
			Date establishTime = (Date)establishTimetxt.getValue();
			String headQuatersAddress = headerQuaterTxt.getText();
			String founder = foundertxt.getText();
			
			
           Airline  airline1 = new Airline(name,establishTime,headQuatersAddress,founder);
           airline1.setId(id);
			MessageResponse ms = new MessageResponse();
			if (airline!=null) {
				 ms =  airlineController.update(airline1);
			} else {
				ms =  airlineController.create(airline1);
			}
			if (ms.code==200) {
				 JOptionPane.showMessageDialog(null, " Successfull!", "Success", JOptionPane.DEFAULT_OPTION);
				 AirlineList airlineList = new AirlineList();
				 airlineList.setVisible(true);
				
			} else {
				 JOptionPane.showMessageDialog(null, ms.message, "Warning", JOptionPane.WARNING_MESSAGE);
			}
			
		}
	});
	}
}
