package GUI.Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MMA.Fighter;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FighterRemGUI extends JFrame {

	private JPanel contentPane;
	private final JTextField textField = new JTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FighterRemGUI frame = new FighterRemGUI();
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
	public FighterRemGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 361, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textField.setBounds(164, 76, 102, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblFighterNickname = new JLabel("Fighter Id");
		lblFighterNickname.setBounds(52, 79, 102, 14);
		contentPane.add(lblFighterNickname);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Fighter.removeFighter(Integer.parseInt(textField.getText().toString()));
			}
		});
		btnSubmit.setBounds(253, 138, 78, 23);
		contentPane.add(btnSubmit);
		
		JLabel lblTypeNicknameOf = new JLabel("Type Id of a fighter and click submit");
		lblTypeNicknameOf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTypeNicknameOf.setBounds(70, 24, 208, 30);
		contentPane.add(lblTypeNicknameOf);
		
		
	}

}
