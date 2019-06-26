package GUI.Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MMA.Team;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TeamRemGUI extends JFrame {

	private JPanel contentPane;
	private final JTextField textField = new JTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamRemGUI frame = new TeamRemGUI();
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
	public TeamRemGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 357, 199);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textField.setBounds(181, 68, 112, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Team Id");
		lblNewLabel.setBounds(75, 71, 96, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPleaseTypeTeams = new JLabel("Please type team's Id and click submit");
		lblPleaseTypeTeams.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPleaseTypeTeams.setBounds(75, 24, 247, 14);
		contentPane.add(lblPleaseTypeTeams);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Team.removeSpecificTeam(Integer.parseInt(textField.getText().toString()));
			}
		});
		btnSubmit.setBounds(266, 121, 65, 23);
		contentPane.add(btnSubmit);
	}

}
