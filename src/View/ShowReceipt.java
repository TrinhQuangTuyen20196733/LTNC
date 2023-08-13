package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DTO.FlightDTO;
import Entity.Flight;
import Entity.Receipt;
import Entity.Service;
import Entity.Ticket;
import Entity.UserEntity;
import Repository.IMyRepository;
import Repository.MyRepository;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;

public class ShowReceipt extends JFrame {

	private JPanel contentPane;
	 private IMyRepository<Flight> flightRepository =  new MyRepository<>().getInstance(Flight.class);
	 private IMyRepository<Ticket> ticketRepository =  new MyRepository<>().getInstance(Ticket.class);
	 private IMyRepository<Receipt> receiptRepository =  new MyRepository<>().getInstance(Receipt.class);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfirmPage frame = new ConfirmPage(null,null);
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
	public ShowReceipt(Ticket ticket, Service service) {
		int flightId = ticket.getFlightId();
		var seatId = ticket.getSeatId();
		 Flight flight = flightRepository.getById(flightId);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1019, 665);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hóa đơn của bạn");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(390, 89, 452, 48);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_2 = new JLabel("Mã số chuyến bay");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(91, 182, 129, 20);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel MSCB = new JLabel(flight.getCode());
		MSCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		MSCB.setBounds(303, 182, 129, 20);
		contentPane.add(MSCB);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Địa điểm xuất phát");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1.setBounds(91, 241, 150, 20);
		contentPane.add(lblNewLabel_1_2_1);
		
		JLabel departureLocation = new JLabel(flight.getDepartureLocation());
		departureLocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		departureLocation.setBounds(303, 241, 129, 20);
		contentPane.add(departureLocation);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Địa điểm đến");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1_1.setBounds(91, 305, 150, 20);
		contentPane.add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel(flight.getDestination());
		lblNewLabel_1_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1_1_1.setBounds(302, 305, 150, 20);
		contentPane.add(lblNewLabel_1_2_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_2 = new JLabel("Thời gian xuất phát");
		lblNewLabel_1_2_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1_1_2.setBounds(91, 374, 150, 20);
		contentPane.add(lblNewLabel_1_2_1_1_2);
		
		JLabel departureTime = new JLabel(flight.getDepartureTime().toString());
		departureTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		departureTime.setBounds(302, 374, 150, 20);
		contentPane.add(departureTime);
		
		JLabel lblNewLabel_1_2_1_1_2_1 = new JLabel("Thời gian hạ cánh");
		lblNewLabel_1_2_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1_1_2_1.setBounds(91, 434, 150, 20);
		contentPane.add(lblNewLabel_1_2_1_1_2_1);
		
		JLabel landingTime = new JLabel(flight.getLandingTime().toString());
		landingTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		landingTime.setBounds(302, 434, 150, 20);
		contentPane.add(landingTime);
		
		JLabel lblNewLabel_1_2_1_1_2_1_1 = new JLabel("Giá vé");
		lblNewLabel_1_2_1_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1_1_2_1_1.setBounds(570, 182, 150, 20);
		contentPane.add(lblNewLabel_1_2_1_1_2_1_1);
		
		JLabel Cost = new JLabel(ticket.getCost()+"đ");
		Cost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Cost.setBounds(748, 182, 150, 20);
		contentPane.add(Cost);
		
		JLabel lblNewLabel_1_2_1_1_2_1_1_1 = new JLabel("Cổng vào");
		lblNewLabel_1_2_1_1_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1_1_2_1_1_1.setBounds(570, 263, 150, 20);
		contentPane.add(lblNewLabel_1_2_1_1_2_1_1_1);
		
		JLabel boardingGate = new JLabel(flight.getBoardingGate());
		boardingGate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		boardingGate.setBounds(730, 263, 150, 20);
		contentPane.add(boardingGate);
		
		JLabel lblNewLabel_1_2_1_1_2_1_1_1_1 = new JLabel("Dịch vụ");
		lblNewLabel_1_2_1_1_2_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1_1_2_1_1_1_1.setBounds(570, 326, 150, 20);
		contentPane.add(lblNewLabel_1_2_1_1_2_1_1_1_1);
		
		JLabel Service = new JLabel(service.getName());
		Service.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Service.setBounds(732, 326, 150, 20);
		contentPane.add(Service);
		
		JLabel lblNewLabel_1_2_1_1_2_1_1_1_1_1 = new JLabel("Giá dịch vụ");
		lblNewLabel_1_2_1_1_2_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1_1_2_1_1_1_1_1.setBounds(570, 396, 150, 20);
		contentPane.add(lblNewLabel_1_2_1_1_2_1_1_1_1_1);
		
		JLabel ServiceCost = new JLabel(service.getCost()+"đ");
		ServiceCost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ServiceCost.setBounds(732, 396, 150, 20);
		contentPane.add(ServiceCost);
		
		JLabel lblNewLabel_1_2_1_1_2_1_1_1_1_1_1 = new JLabel("Tổng tiền");
		lblNewLabel_1_2_1_1_2_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1_1_2_1_1_1_1_1_1.setBounds(570, 450, 150, 20);
		contentPane.add(lblNewLabel_1_2_1_1_2_1_1_1_1_1_1);
		
		JLabel payment = new JLabel((ticket.getCost()+service.getCost())+"đ");
		payment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		payment.setBounds(732, 450, 150, 20);
		contentPane.add(payment);
	}
}
