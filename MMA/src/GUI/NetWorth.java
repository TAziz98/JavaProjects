package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MMA.Fighter;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class NetWorth extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private Integer net_worth;
	private JLabel label;
	private JLabel label_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NetWorth frame = new NetWorth();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void showNetWorth(String nickName) {
	  net_worth = Fighter.getAnnualSalary(nickName);
	  lblNewLabel.setText(net_worth.toString()+"$");
		
	}
	/**
	 * Create the frame.
	 */
	public NetWorth() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 361, 228);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel thumb2 = new JLabel();
		thumb2.setBounds(23, 0, 99, 150);
		contentPane.add(thumb2);
		thumb2.setIcon(new ImageIcon("C:\\Users\\Admin-Win10\\Downloads\\ytey.png"));
				
						
						
						lblNewLabel = new JLabel("");
						contentPane.add(lblNewLabel);
						lblNewLabel.setFont(new Font("Sylfaen", Font.PLAIN, 17));
						lblNewLabel.setForeground(new Color(255, 165, 0));
						lblNewLabel.setBounds(196, 96, 130, 32);
						
						label_1 = new JLabel("Net Worth");
						contentPane.add(label_1);
						label_1.setForeground(Color.BLACK);
						label_1.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
						label_1.setBounds(196, 68, 90, 32);
	}
}
