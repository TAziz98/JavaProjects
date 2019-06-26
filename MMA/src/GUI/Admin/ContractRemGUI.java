package GUI.Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MMA.Contract;
import MMA.Fighter;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ContractRemGUI extends JFrame {

	private JPanel contentPane;
	private final JTextField textField = new JTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContractRemGUI frame = new ContractRemGUI();
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
	public ContractRemGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 355, 207);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textField.setBounds(176, 79, 99, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Fighter Id");
		lblNewLabel.setBounds(63, 82, 89, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Contract.removeSpecificContract(Integer.parseInt(textField.getText().toString()));
			}
		});
		btnSubmit.setBounds(258, 134, 71, 23);
		contentPane.add(btnSubmit);
		
		JLabel lblPleaseTypeFighters = new JLabel("Please type fighter's Id and press submit");
		lblPleaseTypeFighters.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPleaseTypeFighters.setBounds(66, 32, 227, 14);
		contentPane.add(lblPleaseTypeFighters);
	}

}
