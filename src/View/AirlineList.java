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
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;

import Controller.AirlineController;
import Controller.AuthController;
import DTO.LoginReq;
import Entity.Airline;
import Entity.UserEntity;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class AirlineList extends JFrame {

	private JPanel contentPane;
	private AirlineController airlineController = new AirlineController();
	private JTable airlineTable;
	private int selectedRow;
	private List<Airline> airlineList = airlineController.getAll();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AirlineList frame = new AirlineList();
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


	public AirlineList() {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Danh sách hãng hàng không ");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(341, 28, 316, 33);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(156, 123, 671, 346);
		contentPane.add(scrollPane);
		
		airlineTable = new JTable();
		airlineTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		airlineTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Name", "Establish Time", "HeadQuaters Address", "Founder"
			}
		));
		airlineTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		airlineTable.getColumnModel().getColumn(2).setMinWidth(25);
		airlineTable.getColumnModel().getColumn(2).setMaxWidth(600);
		airlineTable.getColumnModel().getColumn(3).setPreferredWidth(120);
		airlineTable.getColumnModel().getColumn(3).setMinWidth(25);
		scrollPane.setViewportView(airlineTable);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(160, 533, 85, 33);
		contentPane.add(btnDelete);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd.setBounds(305, 533, 85, 33);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(453, 533, 85, 33);
		contentPane.add(btnUpdate);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack.setBounds(29, 38, 85, 33);
		contentPane.add(btnBack);
		
		
		showData(airlineList);
		airlineTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
             

			@Override
			public void valueChanged(ListSelectionEvent e) {
				
                       selectedRow = airlineTable.getSelectedRow();
                  }
			
          });
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id= airlineList.get(selectedRow).getId();
				
				var ms =	airlineController.deletebyId(id);
				
				if (ms.code==200) {
					 JOptionPane.showMessageDialog(null, " Successfull!", "Success", JOptionPane.DEFAULT_OPTION);
					 AirlineList userList = new AirlineList();
						userList.setVisible(true);
						
				} else {
					 JOptionPane.showMessageDialog(null, ms.message, "Warning", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
        btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				
				var airline = airlineList.get(selectedRow);
			AirlineSavePage airlineSavePage = new AirlineSavePage(airline);
				airlineSavePage.setVisible(true);
			}
		});
        btnAdd.addActionListener(new ActionListener() {
			
     			@Override
     			public void actionPerformed(ActionEvent e) {
     			
     				
     				AirlineSavePage airlineSavePage = new AirlineSavePage(null);
    				airlineSavePage.setVisible(true);
     			}
     		});
        btnBack.addActionListener(new ActionListener() {
			
 			@Override
 			public void actionPerformed(ActionEvent e) {
 			
 				
 			AdminHomePage adminHomePage = new AdminHomePage();
 			adminHomePage.setVisible(true);
 			}
 		});
	
    
		
        }
	
	
	void showData(List<Airline> airlines) {
		
		
		DefaultTableModel tableModel =(DefaultTableModel)airlineTable.getModel();
		tableModel.setRowCount(0);
		airlines.forEach((airline)-> {
			tableModel.addRow(new Object[]{
					airline.getName(),airline.getEstablishTime(),airline.getHeadQuatersAddress(),airline.getFounder()
		});
	
	});
	}
}
