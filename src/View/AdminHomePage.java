package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminHomePage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHomePage frame = new AdminHomePage();
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
	public AdminHomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 729);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ADMIN HOME PAGE");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(334, 25, 222, 49);
		contentPane.add(lblNewLabel);
		
		JButton btnUser = new JButton("Quản lý người dùng");
		btnUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUser.setBounds(130, 191, 215, 49);
		contentPane.add(btnUser);
		
		JButton btnAirline = new JButton("Quản lý hãng hàng không");
		btnAirline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAirline.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAirline.setBounds(130, 299, 215, 49);
		contentPane.add(btnAirline);
		
		JButton btnPlane = new JButton("Quản lý máy bay");
		btnPlane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPlane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPlane.setBounds(130, 404, 215, 49);
		contentPane.add(btnPlane);
		
		JButton btnFilght = new JButton("Quản lý chuyến bay");
		btnFilght.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFilght.setBounds(483, 351, 215, 49);
		contentPane.add(btnFilght);
		
		JButton btnService = new JButton("Quản lý dịch vụ trên chuyến bay");
		btnService.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnService.setBounds(470, 238, 261, 49);
		contentPane.add(btnService);
		
		JButton btnLogout = new JButton("Đăng xuất");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogout.setBounds(705, 28, 149, 49);
		contentPane.add(btnLogout);
       btnUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			UserList airlineList = new UserList();
			airlineList.setVisible(true);
			}
		});
		btnAirline.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			AirlineList airlineList = new AirlineList();
			airlineList.setVisible(true);
			}
		});
         btnPlane.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			PlaneList planeList = new PlaneList();
			planeList.setVisible(true);
			}
		});
         btnFilght.addActionListener(new ActionListener() {
 			
 			@Override
 			public void actionPerformed(ActionEvent e) {
 			FlightList planeList = new FlightList();
 			planeList.setVisible(true);
 			}
 		});
         btnService.addActionListener(new ActionListener() {
  			
  			@Override
  			public void actionPerformed(ActionEvent e) {
  			ServiceList serviceList = new ServiceList();
  			serviceList.setVisible(true);
  			}
  		});
         btnLogout.addActionListener(new ActionListener() {
   			
   			@Override
   			public void actionPerformed(ActionEvent e) {
   			SignInPage adminHomePage = new SignInPage();
   			adminHomePage.setVisible(true);
   			}
   		});
	}
}
