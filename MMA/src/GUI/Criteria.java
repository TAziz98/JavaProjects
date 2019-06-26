package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Color;
import java.awt.SystemColor;

public class Criteria extends JFrame {
	private JPanel contentPane;
	private String[] cmbCriteria = {"Statistics","Event"};
	private Statistics statistics;
	private Event event;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				  Criteria frame = new Criteria();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public Criteria() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 359, 215);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setSize(400,300);
		contentPane.setLayout(null);
		setResizable(false);
		
		
		
		
		JLabel lblPleaseSelect = new JLabel();
		lblPleaseSelect.setForeground(Color.WHITE);
		lblPleaseSelect.setBackground(Color.YELLOW);
		lblPleaseSelect.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseSelect.setFont(new Font("Chiller", Font.BOLD, 20));
		lblPleaseSelect.setText("Please, select  criteria and click \"NEXT\"");
		lblPleaseSelect.setBounds(20, 50, 290, 40);

		contentPane.setSize(400,300);
		contentPane.add(lblPleaseSelect);
		
		JButton btnNext = new JButton("NEXT");
		btnNext.setForeground(Color.BLACK);
		btnNext.setBackground(SystemColor.inactiveCaption);
		btnNext.setBounds(261, 144, 64, 23);
		contentPane.add(btnNext);
		
		JComboBox comboBox = new JComboBox(cmbCriteria);
		comboBox.setBounds(111, 91, 108, 23);
		contentPane.add(comboBox);
		
		
		JLabel thumb2 = new JLabel();
		thumb2.setBounds(10, 0, 320, 188);
		contentPane.add(thumb2);
		thumb2.setIcon(new ImageIcon("C:\\Users\\Admin-Win10\\Desktop\\Dbackground1.jpg"));
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	switch(comboBox.getSelectedItem().toString()) {
				
				case "Statistics" : 
					
					statistics = new Statistics();
					statistics.show();
					 dispose();
					break;
					
				case "Event" : 
					event = new Event();
					event.show();
					dispose();
				}
			}
			
		});
		btnNext.setVisible(true);
		
		
		
		

	}
}