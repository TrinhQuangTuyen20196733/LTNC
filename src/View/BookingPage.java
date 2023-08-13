package View;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DTO.FlightDTO;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class BookingPage extends JFrame {

    private JPanel contentPane;
    private JTable seatTable;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookingPage bookingPage = new BookingPage(null); // Replace with actual FlightDTO
        });
    }

    public BookingPage(FlightDTO flightDTO) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 775, 701);
        setTitle("Booking Page");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        DefaultTableModel tableModel = new DefaultTableModel(10, 10);
        seatTable = new JTable(tableModel);
        seatTable.setCellSelectionEnabled(true);

        int cellSize = 50; // Kích thước của mỗi ô (hình vuông)

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 6; col++) {
            	 
            	 
                tableModel.setValueAt("Seat " + (row * 6 + col + 1), row, col);
                seatTable.setRowHeight(row, cellSize);
                seatTable.getColumnModel().getColumn(col).setPreferredWidth(cellSize);
            }
        }

        contentPane.add(new JScrollPane(seatTable), BorderLayout.CENTER);
        seatTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = seatTable.getSelectedRow();
                int selectedColumn = seatTable.getSelectedColumn();
                System.out.println("Selected Row: " + selectedRow + ", Selected Column: " + selectedColumn);
            }
        });


        setVisible(true);
    }

   
}
