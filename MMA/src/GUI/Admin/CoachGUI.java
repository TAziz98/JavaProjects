package GUI.Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;

import MMA.AfroAmerican;
import MMA.Caucasian;
import MMA.Coach;
import MMA.Ethnicity;
import MMA.Fighter;
import MMA.Statistics;
import util.HibernateUtil;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CoachGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private Ethnicity ethnicity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoachGUI frame = new CoachGUI();
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
	public CoachGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(199, 40, 98, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(199, 71, 98, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(199, 102, 98, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(199, 133, 98, 20);
		contentPane.add(textField_3);
		
		String values3[]= {"Afro-American", "Caucasian"};
		JComboBox comboBox = new JComboBox(values3);
		comboBox.setBounds(199, 164, 98, 20);
		comboBox.setBackground(SystemColor.inactiveCaption);
		contentPane.add(comboBox);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(199, 195, 98, 20);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(199, 230, 98, 20);
		contentPane.add(textField_5);
		
		JLabel lblNewLabel = new JLabel("First name");
		lblNewLabel.setBounds(67, 43, 68, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setBounds(67, 74, 68, 14);
		contentPane.add(lblLastName);
		
		JLabel lblExperience = new JLabel("Experience");
		lblExperience.setBounds(67, 105, 68, 14);
		contentPane.add(lblExperience);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(67, 136, 46, 14);
		contentPane.add(lblAge);
		
		JLabel lblEthnicity = new JLabel("Ethnicity");
		lblEthnicity.setBounds(67, 167, 68, 14);
		contentPane.add(lblEthnicity);
		
		JLabel lblCoachExp = new JLabel("Salary");
		lblCoachExp.setBounds(67, 198, 68, 14);
		contentPane.add(lblCoachExp);
		
		JLabel lblExperienceCoach = new JLabel("Experience Coach");
		lblExperienceCoach.setBounds(67, 233, 98, 14);
		contentPane.add(lblExperienceCoach);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				switch(comboBox.getSelectedItem().toString()) {	
			    
				case "Afro-American" : 
				 ethnicity = new AfroAmerican("Afro-American",true);	
				 break;
					
				case "Causian" : 
					 ethnicity = new Caucasian("Causian",true);
					break;
			  }
				 Session session = HibernateUtil.getSessionFactory().openSession();	 
				  try {
					
					 Coach coach = new Coach((textField.getText().toString()), textField_1.getText().toString(), Integer.parseInt(textField_2.getText().toString()), Integer.parseInt(textField_3.getText().toString()), ethnicity,Integer.parseInt(textField_4.getText().toString()),Integer.parseInt(textField_5.getText().toString()));
					session.beginTransaction();
					session.save(coach);
					session.getTransaction().commit();
				  }
				  finally {
					  session.close();
				  }
			
			}
		});
		
		btnNewButton.setBounds(325, 282, 89, 23);
		contentPane.add(btnNewButton);
	}

}
