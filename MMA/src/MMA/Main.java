package MMA;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.Criteria;
import GUI.Admin.AdminGUITest;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Main extends JFrame {

	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("USER");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 377, 215);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Criteria c;
				try {
					c = new Criteria();
					c.show();
				    dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(73, 86, 61, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblAdmin = new JLabel("ADMIN");
		lblAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminGUITest admin = new AdminGUITest();
				admin.show();
				dispose();
			}
		});
		lblAdmin.setForeground(Color.WHITE);
		lblAdmin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAdmin.setBounds(212, 86, 103, 31);
		contentPane.add(lblAdmin);
		
		JLabel lblPleaseChooseWho = new JLabel("Please  choose who are you and click next ");
		lblPleaseChooseWho.setForeground(Color.WHITE);
		lblPleaseChooseWho.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPleaseChooseWho.setBounds(55, 40, 267, 14);
		contentPane.add(lblPleaseChooseWho);
	}

}
