package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.exceptions.AssertionFailedException;

import Controller.AirlineController;
import Controller.AuthController;
import Controller.PlaneController;
import DTO.LoginReq;
import DTO.MessageResponse;
import DTO.PlaneDTO;
import DTO.PlaneRes;
import Entity.Airline;
import Entity.Plane;
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
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Calendar;

public class PlaneSavePage extends JFrame {

	private JPanel contentPane;
	private JTextField codetxt;
	private AirlineController airlineController = new AirlineController();
	private PlaneController planeController = new PlaneController();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlaneSavePage frame = new PlaneSavePage(null);
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
	public PlaneSavePage(PlaneRes plane) {
		List<Airline>  airlineList = airlineController.getAll();
		var airlineNameList = airlineList.stream().map(airline->{
			return airline.getName();
		}).toArray();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 731);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Save plane information");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(265, 23, 389, 28);
		contentPane.add(lblNewLabel);
		
		JLabel firstNameText = new JLabel("Code");
		firstNameText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		firstNameText.setBounds(76, 127, 103, 28);
		contentPane.add(firstNameText);
		
		JLabel establishTime = new JLabel("Type");
		establishTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		establishTime.setBounds(76, 198, 127, 28);
		contentPane.add(establishTime);
		
		JLabel emailText = new JLabel("Cost");
		emailText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailText.setBounds(76, 276, 127, 28);
		contentPane.add(emailText);
		
		codetxt = new JTextField(plane!=null?plane.getCode():"");
		codetxt.setBounds(223, 129, 294, 30);
		contentPane.add(codetxt);
		codetxt.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(Color.LIGHT_GRAY);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(605, 625, 103, 42);
		
		
		contentPane.add(btnSave);
		
		JComboBox typetxt = new JComboBox();
		typetxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		typetxt.setModel(new DefaultComboBoxModel(new String[] {"Small", "Medium", "Big"}));
		typetxt.setBounds(225, 198, 76, 28);
		typetxt.setSelectedItem(plane!=null?plane.getType():"");
		contentPane.add(typetxt);
		
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(plane!=null?plane.getCost():0, 0, 1000, 1);
		
		JSpinner costtxt = new JSpinner(spinnerModel);
		costtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		costtxt.setBounds(223, 273, 294, 35);
		contentPane.add(costtxt);
		
		JLabel lblAirline = new JLabel("Airline");
		lblAirline.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAirline.setBounds(76, 341, 127, 28);
		contentPane.add(lblAirline);
		
		JComboBox airlinetxt = new JComboBox();
		airlinetxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		airlinetxt.setBounds(225, 341, 76, 28);
		airlinetxt.setModel(new DefaultComboBoxModel(airlineNameList));
		contentPane.add(airlinetxt);
		
		JLabel emailText_1 = new JLabel("Row");
		emailText_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailText_1.setBounds(76, 416, 127, 28);
		contentPane.add(emailText_1);
		
		JSpinner rowtxt = new JSpinner();
		rowtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rowtxt.setBounds(223, 413, 294, 35);
		contentPane.add(rowtxt);
		
		JLabel emailText_1_1 = new JLabel("Column");
		emailText_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailText_1_1.setBounds(76, 487, 127, 28);
		contentPane.add(emailText_1_1);
		
		JSpinner columntxt = new JSpinner();
		columntxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		columntxt.setBounds(223, 484, 294, 35);
		contentPane.add(columntxt);
		
		JLabel emailText_1_1_1 = new JLabel("Max Speed");
		emailText_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailText_1_1_1.setBounds(76, 566, 127, 28);
		contentPane.add(emailText_1_1_1);
		

		SpinnerNumberModel spinnerModel2 = new SpinnerNumberModel(plane!=null?plane.getMaximumSpeed():0, 0, 1000, 1);
		JSpinner maxSpeedtxt = new JSpinner(spinnerModel2);
		maxSpeedtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		maxSpeedtxt.setBounds(223, 563, 294, 35);
		
		contentPane.add(maxSpeedtxt);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack.setBackground(Color.LIGHT_GRAY);
		btnBack.setBounds(25, 23, 103, 42);
		contentPane.add(btnBack);
		
		 btnBack.addActionListener(new ActionListener() {
				
	 			@Override
	 			public void actionPerformed(ActionEvent e) {
	 			
	 				
	 			PlaneList adminHomePage = new PlaneList();
	 			adminHomePage.setVisible(true);
	 			}
	 		});
		//Add Action Listener
	    btnSave.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int id = (plane!=null) ? (plane.getId()) : 0;
			int cost = (int)costtxt.getValue();
			String code = codetxt.getText();
			String type = (String)typetxt.getSelectedItem();
			String airlineName = (String)airlinetxt.getSelectedItem();
			Optional<Airline> airlineOptional = airlineList.stream().filter(air-> air.getName().equals(airlineName)).findFirst(); 
		    int airlineId = airlineOptional.isPresent() ? airlineOptional.get().getId() : 0;
			int row = (int)rowtxt.getValue();
			int column = (int)columntxt.getValue();
			int maxSpeed = (int)maxSpeedtxt.getValue();
			
       PlaneDTO planeDTO = new PlaneDTO( id,type,cost,maxSpeed, airlineId, code, row, column);
        
			MessageResponse ms = new MessageResponse();
			if (plane!=null) {
			 ms =  planeController.update(planeDTO);
			} else {
				ms =  planeController.create(planeDTO);
			}
			if (ms.code==200) {
				 JOptionPane.showMessageDialog(null, " Successfull!", "Success", JOptionPane.DEFAULT_OPTION);
			 PlaneList planeList = new PlaneList();
			 planeList.setVisible(true);
				
			} else {
				 JOptionPane.showMessageDialog(null, ms.message, "Warning", JOptionPane.WARNING_MESSAGE);
			}
			
		}
	});
	}
}
