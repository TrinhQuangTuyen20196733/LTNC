package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Config.StatusConstant;
import Controller.PlaneController;
import DTO.FlightDTO;
import DTO.PlaneRes;
import Entity.Plane;
import Entity.Seat;
import Entity.Ticket;
import Repository.IMyRepository;
import Repository.MyRepository;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserBookingPage extends JFrame {

	private JPanel contentPane;
	private JTable seatTable;

	 private IMyRepository<Plane> planeRepository =  new MyRepository<>().getInstance(Plane.class);
	 private IMyRepository<Seat> seatRepository =  new MyRepository<>().getInstance(Seat.class);
	 private IMyRepository<Ticket> ticketRepository =  new MyRepository<>().getInstance(Ticket.class);
	 private int selectedRow;
	 private int  selectedColumn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserBookingPage frame = new UserBookingPage(null);
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
	public UserBookingPage(FlightDTO flightDTO) {
		Plane plane  = planeRepository.getOneByQuery("SELECT * FROM plane WHERE code='"+flightDTO.getPlaneCode()+"'");
		 List<Seat> seats = seatRepository.getByQuery("SELECT * FROM seat WHERE planeId="+plane.getId());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1007, 776);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bạn vui lòng chọn vé");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(411, 24, 312, 29);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(115, 113, 783, 425);
		contentPane.add(scrollPane);
		
		  int rowCount = plane.getSeatRow();
	        int columnCount = plane.getSeatColumn();
	        DefaultTableModel tableModel = new DefaultTableModel(rowCount, columnCount);
	        seatTable = new JTable(tableModel);
	        seatTable.setDefaultRenderer(Object.class, new CustomTableCellRenderer(flightDTO,seats));


        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                tableModel.setValueAt("R" + i + "- C" + j, i, j);
            }
        }
		 seatTable.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                 selectedRow = seatTable.getSelectedRow();
	                 selectedColumn = seatTable.getSelectedColumn();
	                System.out.println("Selected Row: " + selectedRow + ", Selected Column: " + selectedColumn);
	            }
	        });
	        scrollPane.setViewportView(seatTable);
		scrollPane.setViewportView(seatTable);
		
		JButton btnService = new JButton("Chọn dịch vụ");
		btnService.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnService.setBounds(446, 617, 157, 43);
		contentPane.add(btnService);
		btnService.addActionListener(new ActionListener() {
				
	 			@Override
	 			public void actionPerformed(ActionEvent e) {
	 			
	 				Ticket ticket = ticketRepository.getOneByQuery("select * from ticket "
	 						+ "inner join seat on ticket.seatId = seat.id "
	 						+ "where seat.rowSeat="+ selectedRow+ " and seat.columnSeat="+ selectedColumn+" and ticket.flightId="+flightDTO.getId());
	 				ChooseService chooseService = new ChooseService(ticket);
	 				chooseService.setVisible(true);
	 			};
	 			
	 		});
	}
	 private class CustomTableCellRenderer extends DefaultTableCellRenderer {
		 private FlightDTO flightDTO;
		private  List<Seat> seats ;
		
	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	            int cellSize = Math.min(table.getRowHeight(row), table.getColumnModel().getColumn(column).getWidth());
	            component.setPreferredSize(new Dimension(cellSize, cellSize));

	            if (!ktEmpty(row,column,seats,flightDTO.getId())) {
	                component.setBackground(Color.RED);
	            } else {
	                component.setBackground(table.getBackground());
	            }

	            return component;
	        }
	        CustomTableCellRenderer(FlightDTO flightDTO,List<Seat> seats) {
	        	this.flightDTO = flightDTO;
	        	this.seats= seats;
	        }
	    }
	 boolean ktEmpty(int row, int column,List<Seat> seats,int flightId){
		 boolean kt = true;
		 for (Seat seat :seats) {
			
			 if (seat.getRowSeat()==row && seat.getColumnSeat()==column) {
				 Ticket ticket  = ticketRepository.getOneByQuery("SELECT * FROM ticket WHERE seatId="+seat.getId()+" AND flightId="+flightId);
					if (ticket!=null &&ticket.getStatus()==StatusConstant.BOOKED) {
						kt=false;
					}
			 }
		 }
		return kt;
		 
	 }
}
