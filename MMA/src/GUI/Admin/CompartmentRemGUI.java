package GUI.Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MMA.Compartment;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CompartmentRemGUI extends JFrame {

	private JPanel contentPane;
	private final JTextField textField = new JTextField();
	private final JButton btnSubmit = new JButton("Submit");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompartmentRemGUI frame = new CompartmentRemGUI();
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
	public CompartmentRemGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 366, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textField.setBounds(195, 80, 87, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblCoachName = new JLabel("Compartment Id");
		lblCoachName.setBounds(59, 83, 109, 14);
		contentPane.add(lblCoachName);
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Compartment.removeCompartment(Integer.parseInt(textField.getText().toString()));
			}
		});
		btnSubmit.setBounds(262, 136, 78, 20);
		contentPane.add(btnSubmit);
		
		JLabel lblNewLabel = new JLabel("Please type compartment's Id and press submit");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(46, 41, 304, 14);
		contentPane.add(lblNewLabel);
	}

}
