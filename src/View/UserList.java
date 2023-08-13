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
import Controller.AuthController;
import DTO.LoginReq;
import Entity.UserEntity;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class UserList extends JFrame {

	private JPanel contentPane;
	private AuthController authController = new AuthController();
	private JTable userTable;
	private int selectedRow;
	private List<UserEntity> userList = authController.getAll();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserList frame = new UserList();
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


	public UserList() {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Danh sách người dùng ");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(341, 28, 316, 33);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(156, 123, 671, 346);
		contentPane.add(scrollPane);
		
		userTable = new JTable();
		userTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		userTable.setModel(new DefaultTableModel(
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
				"Last Name", "First Name", "Age", "Email", "Address", "Role"
			}
		));
		userTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		userTable.getColumnModel().getColumn(2).setMinWidth(10);
		userTable.getColumnModel().getColumn(3).setPreferredWidth(120);
		userTable.getColumnModel().getColumn(3).setMinWidth(25);
		userTable.getColumnModel().getColumn(3).setMaxWidth(600);
		userTable.getColumnModel().getColumn(4).setPreferredWidth(120);
		userTable.getColumnModel().getColumn(4).setMinWidth(25);
		userTable.getColumnModel().getColumn(5).setPreferredWidth(50);
		userTable.getColumnModel().getColumn(5).setMinWidth(10);
		scrollPane.setViewportView(userTable);
		
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
		btnBack.setBounds(50, 38, 85, 33);
		contentPane.add(btnBack);
		
		
		showData(userList);
		userTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
             

			@Override
			public void valueChanged(ListSelectionEvent e) {
				
                       selectedRow = userTable.getSelectedRow();
                  }
			
          });
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id= userList.get(selectedRow).getId();
				
				var ms =	authController.deletebyId(id);
				
				if (ms.code==200) {
					 JOptionPane.showMessageDialog(null, " Successfull!", "Success", JOptionPane.DEFAULT_OPTION);
					 UserList userList = new UserList();
						userList.setVisible(true);
						
				} else {
					 JOptionPane.showMessageDialog(null, ms.message, "Warning", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
        btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				
				var user = userList.get(selectedRow);
				UpdateUserPage updateUserPage = new UpdateUserPage(user);
				updateUserPage.setVisible(true);
				
			}
		});
        btnBack.addActionListener(new ActionListener() {
			
 			@Override
 			public void actionPerformed(ActionEvent e) {
 			
 				
 			AdminHomePage adminHomePage = new AdminHomePage();
 			adminHomePage.setVisible(true);
 			}
 		});
    btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				
				var user = userList.get(selectedRow);
				SignupPage updateUserPage = new SignupPage();
				updateUserPage.setVisible(true);
				
			}
		});
		
	}
	
	void showData(List<UserEntity> users) {
		
		
		DefaultTableModel tableModel =(DefaultTableModel)userTable.getModel();
		tableModel.setRowCount(0);
		 users.forEach((user)-> {
			tableModel.addRow(new Object[]{
					user.getLast_name(),user.getFirst_name(),user.getAge(),user.getEmail(),user.getAddress(),user.getRole(),
		});
	
	});
	}
}
