package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import MMA.Division;
import MMA.Fighter;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class Statistics extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private StatisticsList statsList;
	private List<Fighter> fighters;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Statistics frame = new Statistics();
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
	public Statistics() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 361, 229);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setSize(400,300);
		contentPane.setLayout(null);
		
		JLabel lblPleaseSelect = new JLabel();
		lblPleaseSelect.setBackground(SystemColor.desktop);
		lblPleaseSelect.setForeground(SystemColor.text);
		lblPleaseSelect.setFont(new Font("Chiller", Font.BOLD, 20));
		lblPleaseSelect.setText("Please, browse fighters and click \"NEXT\"");
		lblPleaseSelect.setBounds(36, 77, 285, 16);
		contentPane.setSize(400,300);
		contentPane.add(lblPleaseSelect);
		
		String values[]={"Flyweight", "Bantaweight", "Featherweight", "Lightweight","Welterweight", "Middleweight", "Lightheavyweight", "Heavyweight"};        
		JComboBox comboBox = new JComboBox(values);
		comboBox.setBackground(SystemColor.inactiveCaption);
		comboBox.setBounds(61, 104, 110, 20);
		contentPane.add(comboBox);
		contentPane.setSize(400,300);
		contentPane.setLayout(null);
		
		
		textField = new JTextField();
		textField.setBackground(SystemColor.inactiveCaption);
	
		textField.setToolTipText("");
		textField.setBounds(205, 104, 100, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setForeground(Color.GRAY);
		

		JLabel thumb2 = new JLabel();
		thumb2.setBounds(0, 0, 343, 188);
		contentPane.add(thumb2);
		thumb2.setIcon(new ImageIcon("C:\\Users\\Admin-Win10\\Desktop\\l3.jpg"));
		
		JLabel lblDivision = new JLabel();
		lblDivision.setForeground(Color.WHITE);
		lblDivision.setText("Division");
		lblDivision.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDivision.setBounds(79, 76, 53, 16);
		contentPane.add(lblDivision);
		
		JLabel lblNickName = new JLabel();
		lblNickName.setForeground(Color.WHITE);
		lblNickName.setText("Nick name");
		lblNickName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNickName.setBounds(212, 76, 66, 16);
		contentPane.add(lblNickName);
		//		textField.addFocusListener(new FocusListener() {
		//		    @Override
		//		    public void focusGained(FocusEvent e) {
		//		        if (textField.getText().equals("Search")) {
		//		            textField.setText("");
		//		            textField.setForeground(Color.BLACK);
		//		        }
		//		    }
		//		    @Override
		//		    public void focusLost(FocusEvent e) {
		//		        if (textField.getText().isEmpty()) {
		//		            textField.setForeground(Color.GRAY);
		//		            textField.setText("Search");
		//		        }
		//		    }
		//		    });
	//	CircleButton btnNext = new CircleButton("Click me!");
	//	btnNext.setForeground(SystemColor.activeCaptionBorder);
				JButton btnNext = new JButton();
				btnNext.setText("NEXT");
				btnNext.setForeground(SystemColor.desktop);
				thumb2.add(btnNext);
				btnNext.setBackground(SystemColor.inactiveCaption);
				btnNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						fighters = new ArrayList<Fighter>();
						if(textField.getText().toString().isEmpty()) {
					//		System.out.println("Gggggg");
						System.out.println(comboBox.getSelectedItem().toString());
	    switch(comboBox.getSelectedItem().toString()) {	
	    
						case "Flyweight" : 
						 fighters = Fighter.ViewTopFighters(Division.FLYWEIGHT);	
						 break;
							
						case "Bantaweight" : 
							fighters =	Fighter.ViewTopFighters(Division.BANTAMWEIGHT);
							break;
							
						case "Featherweight" : 
							fighters = Fighter.ViewTopFighters(Division.FEATHERWEIGHT);	
							break;
							
						case "Lightweight" : 
							fighters = Fighter.ViewTopFighters(Division.LIGHTWEIGHT);
							break;
						
							
						case "Welterweight" : 
							fighters = Fighter.ViewTopFighters(Division.WELTERWEIGHT);
							break;
							
						case "Middleweight" : 
							fighters = Fighter.ViewTopFighters(Division.MIDDLEWEIGHT);
							break;
							
						case "Lightheavyweight" : 
							fighters = Fighter.ViewTopFighters(Division.LIGHTHEAVYWEIGHT);	
							break;
							
						case "Heavyweight" : 
							fighters = Fighter.ViewTopFighters(Division.HEAVYWEIGHT);
					        break;
	    }
						}
						else {
							System.out.println(textField.getText());
						fighters.add(Fighter.ViewRecordByNickName(textField.getText().toString()));
						}
						
						 statsList = new StatisticsList();
						 System.out.println(fighters.size());
						 statsList.show_fighters(fighters);
						 statsList.show();
						 dispose();
						 
						//lblPleaseSelect.setText(Fighter.ViewRecordByNickName(textField.getText()));
					}
				});
				btnNext.setBounds(260, 149, 64, 22);
	}
}
