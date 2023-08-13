package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;

import Controller.AirlineController;
import Controller.AuthController;
import Controller.FlightController;
import DTO.FlightDTO;
import DTO.FlightFilterParam;
import DTO.LoginReq;
import Entity.Airline;
import Entity.Flight;
import Entity.UserEntity;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Calendar;

public class UserHomepage extends JFrame {

	private JPanel contentPane;
	private FlightController flightController = new FlightController();
	private JTable flightTable;
	private int selectedRow;
	private List<FlightDTO> flightList = flightController.getAll();
	private JTextField departureLocationSearch;
	private JTextField destinationSerach;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightList frame = new FlightList();
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


	public UserHomepage() {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 770);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Home Page");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(373, 0, 316, 33);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(112, 241, 671, 346);
		contentPane.add(scrollPane);
		
		flightTable = new JTable();
		flightTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		flightTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Code", "Departure Location", "Detination", "Departure Time", "Landing Time", "Boarding Gate"
			}
		));
		flightTable.getColumnModel().getColumn(1).setPreferredWidth(98);
		flightTable.getColumnModel().getColumn(2).setPreferredWidth(105);
		flightTable.getColumnModel().getColumn(2).setMinWidth(25);
		flightTable.getColumnModel().getColumn(2).setMaxWidth(600);
		flightTable.getColumnModel().getColumn(3).setPreferredWidth(150);
		flightTable.getColumnModel().getColumn(3).setMinWidth(25);
		flightTable.getColumnModel().getColumn(4).setPreferredWidth(150);
		flightTable.getColumnModel().getColumn(5).setPreferredWidth(95);
		scrollPane.setViewportView(flightTable);
		
		JLabel lblNewLabel_1 = new JLabel("Departure Location");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(112, 79, 127, 27);
		contentPane.add(lblNewLabel_1);
		
		departureLocationSearch = new JTextField();
		departureLocationSearch.setBounds(261, 77, 192, 27);
		contentPane.add(departureLocationSearch);
		departureLocationSearch.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Destination");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(112, 140, 127, 27);
		contentPane.add(lblNewLabel_1_1);
		
		destinationSerach = new JTextField();
		destinationSerach.setColumns(10);
		destinationSerach.setBounds(261, 142, 192, 27);
		contentPane.add(destinationSerach);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.setBounds(604, 113, 85, 33);
		contentPane.add(btnSearch);
		
		JButton btnBook = new JButton("Book");
		btnBook.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBook.setBounds(112, 633, 85, 33);
		contentPane.add(btnBook);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack.setBounds(10, 21, 85, 33);
		contentPane.add(btnBack);
		 btnBack.addActionListener(new ActionListener() {
				
	 			@Override
	 			public void actionPerformed(ActionEvent e) {
	 			
	 				
	 			SignInPage adminHomePage = new SignInPage();
	 			adminHomePage.setVisible(true);
	 			}
	 		});
		
		 btnBook.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				
					
					var flight = flightList.get(selectedRow);
				UserBookingPage flightSavePage = new UserBookingPage(flight);
				flightSavePage.setVisible(true);
				}
			});
		
		showData(flightList);
		flightTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
             

			@Override
			public void valueChanged(ListSelectionEvent e) {
				
                       selectedRow = flightTable.getSelectedRow();
                   	
       				
                  }
			
          });
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String departureLocation = departureLocationSearch.getText();
				String destination = destinationSerach.getText();
				
				FlightFilterParam filterParam = new FlightFilterParam(departureLocation,destination);
				
				flightList = flightController.getByFilter(filterParam);
				showData(flightList);
				
			}
		});
		
        }
	
	void showData(List<FlightDTO> flights) {
		
		
		DefaultTableModel tableModel =(DefaultTableModel)flightTable.getModel();
		tableModel.setRowCount(0);
		flights.forEach((flight)-> {
			tableModel.addRow(new Object[]{
					flight.getCode(),flight.getDepartureLocation(),flight.getDestination(),flight.getDepartureTime(),flight.getLandingTime(),flight.getBoardingGate()
		});
	
	});
	}
}
