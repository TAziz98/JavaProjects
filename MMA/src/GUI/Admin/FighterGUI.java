package GUI.Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;

import MMA.AfroAmerican;
import MMA.Caucasian;
import MMA.Division;
import MMA.DivisionRating;
import MMA.Ethnicity;
import MMA.Fighter;
import MMA.Statistics;
import util.HibernateUtil;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class FighterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_7;
	private JLabel lblStandOffs;
	private JTextField textField_8;
	private JLabel lblDivision;
	private JLabel lblRating;
	private Division division;
	private DivisionRating rating;
	private Ethnicity ethnicity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FighterGUI frame = new FighterGUI();
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
	public FighterGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setText("");
		textField.setBounds(152, 74, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("");
		textField_1.setColumns(10);
		textField_1.setBounds(152, 105, 86, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText("");
		textField_2.setColumns(10);
		textField_2.setBounds(152, 136, 86, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setText("");
		textField_3.setColumns(10);
		textField_3.setBounds(152, 167, 86, 20);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setText("");
		textField_4.setColumns(10);
		textField_4.setBounds(152, 198, 86, 20);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setText("");
		textField_5.setColumns(10);
		textField_5.setBounds(148, 241, 46, 20);
		contentPane.add(textField_5);
		
		JLabel lblNewLabel = new JLabel("Nick name");
		lblNewLabel.setBounds(41, 80, 60, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(41, 111, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setBounds(41, 142, 60, 14);
		contentPane.add(lblLastName);
		
		JLabel lblExperience = new JLabel("Experience");
		lblExperience.setBounds(41, 173, 60, 14);
		contentPane.add(lblExperience);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(41, 204, 46, 14);
		contentPane.add(lblAge);
		
		JLabel lblStatistics = new JLabel("Statistics");
		lblStatistics.setBounds(41, 241, 46, 14);
		contentPane.add(lblStatistics);
		
		JLabel lblWins = new JLabel("Wins");
		lblWins.setBounds(158, 224, 28, 14);
		contentPane.add(lblWins);
		
		JLabel lblLosts = new JLabel("Losts");
		lblLosts.setBounds(216, 224, 28, 14);
		contentPane.add(lblLosts);
		
		textField_7 = new JTextField();
		textField_7.setText("");
		textField_7.setColumns(10);
		textField_7.setBounds(210, 241, 46, 20);
		contentPane.add(textField_7);
		
		lblStandOffs = new JLabel("Stand Offs");
		lblStandOffs.setBounds(270, 224, 60, 14);
		contentPane.add(lblStandOffs);
		
		textField_8 = new JTextField();
		textField_8.setText("");
		textField_8.setColumns(10);
		textField_8.setBounds(273, 241, 46, 20);
		contentPane.add(textField_8);
		
		lblDivision = new JLabel("Division");
		lblDivision.setBounds(365, 224, 60, 14);
		contentPane.add(lblDivision);
		
		lblRating = new JLabel("Rating");
		lblRating.setBounds(464, 224, 60, 14);
		contentPane.add(lblRating);
		
		JLabel lblFighter = new JLabel("Fighter");
		lblFighter.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFighter.setBounds(232, 11, 70, 37);
		contentPane.add(lblFighter);
		
		String values[]={"Flyweight", "Bantaweight", "Featherweight", "Lightweight","Welterweight", "Middleweight", "Lightheavyweight", "Heavyweight"};        
		JComboBox comboBox = new JComboBox(values);
		comboBox.setBounds(339, 241, 86, 20);
		comboBox.setBackground(SystemColor.inactiveCaption);
		contentPane.add(comboBox);
		
		String values2[]= {"Champion", "TOP_1", "TOP_2", "TOP_3", "TOP_4", "TOP_5", "TOP_6",
				"TOP_7", "TOP_8", "TOP_9", "TOP_10"};
		JComboBox comboBox2 = new JComboBox(values2);
		comboBox2.setBounds(449, 241, 86, 20);
		comboBox2.setBackground(SystemColor.inactiveCaption);
		contentPane.add(comboBox2);
		
		String values3[]= {"Afro-American", "Caucasian"};
		JComboBox comboBox3 = new JComboBox(values3);
		comboBox3.setBounds(152, 285, 86, 20);
		comboBox3.setBackground(SystemColor.inactiveCaption);
		contentPane.add(comboBox3);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  switch(comboBox.getSelectedItem().toString()) {	
				    
					case "Flyweight" : 
					 division = Division.FLYWEIGHT;	
					 break;
						
					case "Bantaweight" : 
						division =	Division.BANTAMWEIGHT;
						break;
						
					case "Featherweight" : 
						division = Division.FEATHERWEIGHT;	
						break;
						
					case "Lightweight" : 
						division =Division.LIGHTWEIGHT;
						break;
					
						
					case "Welterweight" : 
						division = Division.WELTERWEIGHT;
						break;
						
					case "Middleweight" : 
						division = Division.MIDDLEWEIGHT;
						break;
						
					case "Lightheavyweight" : 
						division = Division.LIGHTHEAVYWEIGHT;	
						break;
						
					case "Heavyweight" : 
						division = Division.HEAVYWEIGHT;
				        break;
  }
					
				  
				  switch(comboBox2.getSelectedItem().toString()) {	
				    
					case "Champion" : 
					 rating = DivisionRating.Champion;	
					 break;
						
					case "TOP_1" : 
						rating =	DivisionRating.TOP_1;
						break;
						
					case "TOP_2" : 
						rating = DivisionRating.TOP_2;	
						break;
						
					case "TOP_3" : 
						rating =DivisionRating.TOP_3;
						break;
					
						
					case "TOP_4" : 
						rating = DivisionRating.TOP_4;
						break;
						
					case "TOP_5" : 
						rating = DivisionRating.TOP_5;
						break;
						
					case "TOP_6" : 
						rating = DivisionRating.TOP_6;	
						break;
						
					case "TOP_7" : 
						rating = DivisionRating.TOP_7;
				        break;
				        
					case "TOP_8" : 
						rating = DivisionRating.TOP_8;
				        break;
				        
					case "TOP_9" : 
						rating = DivisionRating.TOP_9;
				        break;
				        
					case "TOP_10" : 
						rating = DivisionRating.TOP_10;
				        break;
}
				  switch(comboBox3.getSelectedItem().toString()) {	
				    
					case "Afro-American" : 
					 ethnicity = new AfroAmerican("Afro-American",true);	
					 break;
						
					case "Causian" : 
						 ethnicity = new Caucasian("Causian",true);
						break;
				  }
				
				   Session session = HibernateUtil.getSessionFactory().openSession();	 
					  try {
						
						Fighter fighter = new Fighter(textField.getText().toString(), textField_1.getText().toString(),textField_2.getText().toString(),Integer.parseInt(textField_3.getText().toString()),Integer.parseInt(textField_4.getText().toString()),
								new Statistics(Integer.parseInt(textField_5.getText().toString()), Integer.parseInt(textField_7.getText().toString()), Integer.parseInt(textField_8.getText().toString()), division, rating), ethnicity);
						session.beginTransaction();
						session.save(fighter);
						session.getTransaction().commit();
					  }
					  finally {
						  session.close();
					  }
			}
			
		});
		btnSubmit.setBounds(435, 307, 89, 23);
		contentPane.add(btnSubmit);
		
		JLabel lblEthnicity = new JLabel("Ethnicity");
		lblEthnicity.setBounds(41, 288, 46, 14);
		contentPane.add(lblEthnicity);
		
	
		

	}
}
