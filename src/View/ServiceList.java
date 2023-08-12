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
import Controller.ServiceController;
import DTO.LoginReq;
import Entity.Airline;
import Entity.Service;
import Entity.UserEntity;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class ServiceList extends JFrame {

	private JPanel contentPane;
	private ServiceController serviceController = new ServiceController();
	private JTable serviceTable;
	private int selectedRow;
	private List<Service> serviceList = serviceController.getAll();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServiceList frame = new ServiceList();
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


	public ServiceList() {
		

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
		scrollPane.setBounds(156, 123, 671, 220);
		contentPane.add(scrollPane);
		
		serviceTable = new JTable();
		serviceTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		serviceTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Name", "Code", "Cost"
			}
		));
		serviceTable.getColumnModel().getColumn(0).setPreferredWidth(60);
		serviceTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		serviceTable.getColumnModel().getColumn(2).setMinWidth(25);
		scrollPane.setViewportView(serviceTable);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(156, 459, 85, 33);
		contentPane.add(btnDelete);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd.setBounds(331, 459, 85, 33);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(485, 459, 85, 33);
		contentPane.add(btnUpdate);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack.setBounds(30, 38, 85, 33);
		contentPane.add(btnBack);
		
		 btnBack.addActionListener(new ActionListener() {
				
	 			@Override
	 			public void actionPerformed(ActionEvent e) {
	 			
	 				
	 			AdminHomePage adminHomePage = new AdminHomePage();
	 			adminHomePage.setVisible(true);
	 			}
	 		});
		
	    
		
		
		showData(serviceList);
		serviceTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
             

			@Override
			public void valueChanged(ListSelectionEvent e) {
				
                       selectedRow = serviceTable.getSelectedRow();
                  }
			
          });
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id= serviceList.get(selectedRow).getId();
				
				var ms =	serviceController.deletebyId(id);
				
				if (ms.code==200) {
					 JOptionPane.showMessageDialog(null, " Successfull!", "Success", JOptionPane.DEFAULT_OPTION);
					 ServiceList userList = new ServiceList();
						userList.setVisible(true);
						
				} else {
					 JOptionPane.showMessageDialog(null, ms.message, "Warning", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
        btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				
				var service = serviceList.get(selectedRow);
			ServiceSavePage serviceSavePage = new ServiceSavePage(service);
			serviceSavePage.setVisible(true);
			}
		});
        btnAdd.addActionListener(new ActionListener() {
			
     			@Override
     			public void actionPerformed(ActionEvent e) {
     			
     				
     				ServiceSavePage serviceSavePage = new ServiceSavePage(null);
     				serviceSavePage.setVisible(true);
     			}
     		});
		
        }
	
	void showData(List<Service> services) {
		
		
		DefaultTableModel tableModel =(DefaultTableModel)serviceTable.getModel();
		tableModel.setRowCount(0);
		services.forEach((service)-> {
			tableModel.addRow(new Object[]{
					service.getName(),service.getCode(),service.getCost()
		});
	
	});
	}
}
