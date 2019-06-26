package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MMA.Promotion;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.SystemColor;
import java.awt.Color;

public class Event extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private List<MMA.Event> events;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Event frame = new Event();
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
	public Event() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 374, 234);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JLabel thumb2 = new JLabel();
		thumb2.setForeground(Color.WHITE);
		thumb2.setFont(new Font("Tahoma", Font.BOLD, 14));
		thumb2.setBounds(0, 26, 373, 181);
		contentPane.add(thumb2);
		thumb2.setIcon(new ImageIcon("C:\\Users\\Admin-Win10\\Desktop\\h5j.jpg"));
		contentPane.setSize(400,300);
		

		textField = new JTextField();
		thumb2.add(textField);
		textField.setForeground(SystemColor.inactiveCaption);
		textField.setBounds(211,  85, 96, 24);
		textField.setColumns(10);
		
		JLabel lblPleaseSelect = new JLabel();
		contentPane.add(lblPleaseSelect);
		lblPleaseSelect.setBackground(Color.GRAY);
		lblPleaseSelect.setForeground(Color.BLACK);
		lblPleaseSelect.setFont(new Font("Chiller", Font.BOLD, 20));
		lblPleaseSelect.setText("Please, browse events and click \"NEXT\"");
		lblPleaseSelect.setBounds(48, 0, 285, 24);
		
		
		
		JButton btnNewButton_1 = new JButton("Main");
		thumb2.add(btnNewButton_1);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				events = new ArrayList<MMA.Event>();
				events.add(Promotion.getLastEvent());
				 EventsList eventsList = new EventsList();
				 eventsList.show_events(events);
				 eventsList.show();
				 dispose();
			}
		});
		btnNewButton_1.setBounds(62, 85, 89, 23);
		
		JButton btnNewButton = new JButton("NEXT");
		thumb2.add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				events = new ArrayList<MMA.Event>();
				events = Promotion.getEventById(Integer.parseInt(textField.getText().toString()));
				 EventsList eventsList = new EventsList();
				 eventsList.show_events(events);
				 eventsList.show();
				 dispose();
			}
		});
		btnNewButton.setBounds(286, 145, 64, 22);
		
		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setForeground(Color.WHITE);
		lblId.setBounds(249, 68, 46, 14);
		thumb2.add(lblId);
	}
}
