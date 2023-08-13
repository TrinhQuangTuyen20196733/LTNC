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
import Entity.Ticket;
import Entity.UserEntity;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class ChooseService extends JFrame {

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
					ChooseService frame = new ChooseService(null);
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


	public ChooseService(Ticket ticket) {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Xin mời bạn chọn dịch vụ");
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
		
		JButton btnBack = new JButton("Back");
		
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack.setBounds(30, 38, 85, 33);
		contentPane.add(btnBack);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConfirm.setBounds(460, 456, 114, 33);
		contentPane.add(btnConfirm);
		
		 btnBack.addActionListener(new ActionListener() {
				
	 			@Override
	 			public void actionPerformed(ActionEvent e) {
	 			
	 				
	 			UserHomepage adminHomePage = new UserHomepage();
	 			adminHomePage.setVisible(true);
	 			}
	 		});
		
		 btnConfirm.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				
					
					var service = serviceList.get(selectedRow);
					ConfirmPage confirmPage = new ConfirmPage(ticket,service);
					confirmPage.setVisible(true);
				
				}
			});
		
		
		showData(serviceList);
		serviceTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
             

			@Override
			public void valueChanged(ListSelectionEvent e) {
				
                       selectedRow = serviceTable.getSelectedRow();
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
