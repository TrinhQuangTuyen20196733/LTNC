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
import Controller.PlaneController;
import DTO.LoginReq;
import DTO.PlaneRes;
import Entity.Airline;
import Entity.UserEntity;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class PlaneList extends JFrame {

	private JPanel contentPane;
	private PlaneController planeController = new PlaneController();
	private JTable planeTable;
	private int selectedRow;
	private List<PlaneRes> planeList = planeController.getAll();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlaneList frame = new PlaneList();
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


	public PlaneList() {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Danh sách máy bay ");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(341, 28, 316, 33);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(156, 123, 671, 346);
		contentPane.add(scrollPane);
		
		planeTable = new JTable();
		planeTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		planeTable.setModel(new DefaultTableModel(
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
				"Code", "Type", "Maximum Speed", "Airline"
			}
		));
		planeTable.getColumnModel().getColumn(1).setPreferredWidth(87);
		planeTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		planeTable.getColumnModel().getColumn(2).setMinWidth(25);
		planeTable.getColumnModel().getColumn(2).setMaxWidth(600);
		planeTable.getColumnModel().getColumn(3).setPreferredWidth(120);
		planeTable.getColumnModel().getColumn(3).setMinWidth(25);
		scrollPane.setViewportView(planeTable);
		
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
		btnBack.setBounds(29, 28, 85, 33);
		contentPane.add(btnBack);
		 btnBack.addActionListener(new ActionListener() {
				
	 			@Override
	 			public void actionPerformed(ActionEvent e) {
	 			
	 				
	 			AdminHomePage adminHomePage = new AdminHomePage();
	 			adminHomePage.setVisible(true);
	 			}
	 		});
		
		showData(planeList);
		planeTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
             

			@Override
			public void valueChanged(ListSelectionEvent e) {
				
                       selectedRow = planeTable.getSelectedRow();
                  }
			
          });
		
		 btnDelete.addActionListener(new ActionListener() {
		  
		 @Override public void actionPerformed(ActionEvent e) { int id=
		  planeList.get(selectedRow).getId();
		  
		  var ms = planeController.deletebyId(id);
		  
		  if (ms.code==200) { JOptionPane.showMessageDialog(null, " Successfull!",
		  "Success", JOptionPane.DEFAULT_OPTION);
		  PlaneList planeList = new PlaneList();
		  planeList.setVisible(true);
		  
		  } else { JOptionPane.showMessageDialog(null, ms.message, "Warning",
		  JOptionPane.WARNING_MESSAGE); }
		  
		  } });
		 
      btnUpdate.addActionListener(new ActionListener() {
			
		@Override
		public void actionPerformed(ActionEvent e) {
		
		
			var plane = planeList.get(selectedRow);
		PlaneSavePage planeSavePage = new PlaneSavePage(plane);
		planeSavePage.setVisible(true);
		}
	});
       btnAdd.addActionListener(new ActionListener() {
			
    	   @Override
   			public void actionPerformed(ActionEvent e) {
    			
    				
    				PlaneSavePage planeSavePage = new PlaneSavePage(null);
    				planeSavePage.setVisible(true);
   			}    		});
	
        }
	
	void showData(List<PlaneRes> planes) {
		
		
		DefaultTableModel tableModel =(DefaultTableModel)planeTable.getModel();
		tableModel.setRowCount(0);
		planes.forEach((airline)-> {
			tableModel.addRow(new Object[]{
					airline.getCode(),airline.getType(),airline.getMaximumSpeed(),airline.getAirlineName()
		});
	
	});
	}
}
