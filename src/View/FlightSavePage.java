package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.exceptions.AssertionFailedException;

import Controller.AirlineController;
import Controller.AuthController;
import Controller.FlightController;
import Controller.PlaneController;
import DTO.FlightDTO;
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
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

public class FlightSavePage extends JFrame {

	private JPanel contentPane;
	private JTextField codetxt;
	private FlightController flightController = new FlightController();
	private PlaneController planeController = new PlaneController();
	private JTextField departureLocationtxt;
	private JTextField destinationtxt;
	private JTextField boardingGatetxt;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightSavePage frame = new FlightSavePage(null);
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
	public FlightSavePage(FlightDTO flight) {
		List<PlaneRes>  planeList = planeController.getAll();
		var planeCodeList = planeList.stream().map(plane1->{
			return plane1.getCode();
		}).toArray();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 731);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Save flight information");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(262, 60, 389, 28);
		contentPane.add(lblNewLabel);
		
		JLabel firstNameText = new JLabel("Code");
		firstNameText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		firstNameText.setBounds(52, 127, 103, 28);
		contentPane.add(firstNameText);
		
		codetxt = new JTextField(flight!=null?flight.getCode():"");
		codetxt.setBounds(223, 129, 294, 30);
		contentPane.add(codetxt);
		codetxt.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(Color.LIGHT_GRAY);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(605, 625, 103, 42);
		
		
		contentPane.add(btnSave);
		
		
		
		JLabel lblAirline = new JLabel("Departure Time");
		lblAirline.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAirline.setBounds(52, 341, 127, 28);
		contentPane.add(lblAirline);
		
		JLabel emailText_1 = new JLabel("Landing Time");
		emailText_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailText_1.setBounds(52, 416, 127, 28);
		contentPane.add(emailText_1);
		
		JSpinner departureTimetxt = new JSpinner();
		departureTimetxt.setModel(new SpinnerDateModel(new Date(1696881600000L), null, null, Calendar.DAY_OF_YEAR));
		departureTimetxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		departureTimetxt.setBounds(223, 338, 294, 35);
		contentPane.add(departureTimetxt);
		
		JSpinner landingTimetxt = new JSpinner();
		landingTimetxt.setModel(new SpinnerDateModel(new Date(1691600400000L), null, null, Calendar.DAY_OF_YEAR));
		landingTimetxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		landingTimetxt.setBounds(223, 413, 294, 35);
		contentPane.add(landingTimetxt);
		
		JLabel emailText_1_1_1 = new JLabel("Boarding Gate");
		emailText_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailText_1_1_1.setBounds(52, 503, 127, 28);
		contentPane.add(emailText_1_1_1);
		

		
		
		JLabel lblD = new JLabel("Departure Location");
		lblD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblD.setBounds(52, 198, 158, 28);
		contentPane.add(lblD);
		
		departureLocationtxt = new JTextField(flight!=null?flight.getDepartureLocation():"");
		departureLocationtxt.setColumns(10);
		departureLocationtxt.setBounds(223, 200, 294, 30);
		contentPane.add(departureLocationtxt);
		
		JLabel lblDestination = new JLabel("Destination");
		lblDestination.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDestination.setBounds(52, 263, 158, 28);
		contentPane.add(lblDestination);
		
		destinationtxt = new JTextField(flight!=null?flight.getDestination():"");
		destinationtxt.setColumns(10);
		destinationtxt.setBounds(223, 265, 294, 30);
		contentPane.add(destinationtxt);
		
		boardingGatetxt = new JTextField(flight!=null?flight.getBoardingGate():"");
		boardingGatetxt.setColumns(10);
		boardingGatetxt.setBounds(223, 503, 294, 30);
		contentPane.add(boardingGatetxt);
		
		JLabel emailText_1_1_1_1 = new JLabel("Plane");
		emailText_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailText_1_1_1_1.setBounds(52, 581, 127, 28);
		contentPane.add(emailText_1_1_1_1);
		
		JComboBox planetxt = new JComboBox();
		planetxt.setBounds(223, 582, 152, 30);
		planetxt.setModel(new DefaultComboBoxModel(planeCodeList));
		contentPane.add(planetxt);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack.setBackground(Color.LIGHT_GRAY);
		btnBack.setBounds(33, 33, 103, 42);
		contentPane.add(btnBack);
		
		 btnBack.addActionListener(new ActionListener() {
				
	 			@Override
	 			public void actionPerformed(ActionEvent e) {
	 			
	 				
	 			FlightList adminHomePage = new FlightList();
	 			adminHomePage.setVisible(true);
	 			}
	 		});
		//Add Action Listener
	    btnSave.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int id = (flight!=null) ? (flight.getId()) : 0;
			String code = codetxt.getText();
			String departureLocation= departureLocationtxt.getText();
			String destination = destinationtxt.getText();
			
			
			 Date departureTime1 = (Date) departureTimetxt.getValue();
             Instant instant = departureTime1.toInstant();
             ZoneId zoneId = ZoneId.systemDefault();
             LocalDateTime departureTime = instant.atZone(zoneId).toLocalDateTime();
             Date landingtime2 = (Date) landingTimetxt.getValue();
             Instant instant2 = landingtime2.toInstant();
             ZoneId zoneId2 = ZoneId.systemDefault();
             LocalDateTime landingTime = instant2.atZone(zoneId2).toLocalDateTime();
			
			
			String boardGate = boardingGatetxt.getText();
			String planeCode = (String)planetxt.getSelectedItem();
			
			FlightDTO flightdto = new FlightDTO(id,code,departureLocation,destination,departureTime,landingTime,boardGate,planeCode);
			
			
			
		
       
		MessageResponse ms = new MessageResponse();
		if (flight!=null) {
		 ms =  flightController.update(flightdto);
			
		} else {
			
			ms =  flightController.create(flightdto);
				
			}
		if (ms.code==200) {
			JOptionPane.showMessageDialog(null, " Successfull!", "Success", JOptionPane.DEFAULT_OPTION);
			FlightList flightList = new FlightList();
			flightList.setVisible(true);
			
		}
		 else {
			 JOptionPane.showMessageDialog(null, ms.message, "Warning", JOptionPane.WARNING_MESSAGE);
		}
		
			
		}
	});
	}
}
