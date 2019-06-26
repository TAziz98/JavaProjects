package GUI.Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MMA.Coach;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CoachRemGUI extends JFrame {

	private JPanel contentPane;
	private final JTextField textField = new JTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoachRemGUI frame = new CoachRemGUI();
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
	public CoachRemGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 358, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textField.setBounds(182, 68, 100, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblCoachNick = new JLabel("Coach Id");
		lblCoachNick.setBounds(86, 71, 86, 14);
		contentPane.add(lblCoachNick);
		
		JLabel lblPleaseTypeCoachs = new JLabel("Please type coach's id and click submit");
		lblPleaseTypeCoachs.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPleaseTypeCoachs.setBounds(73, 31, 222, 14);
		contentPane.add(lblPleaseTypeCoachs);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Coach.removeCoach(Integer.parseInt(textField.getText().toString()));
			}
		});
		btnSubmit.setBounds(257, 132, 75, 23);
		contentPane.add(btnSubmit);
	}

}
