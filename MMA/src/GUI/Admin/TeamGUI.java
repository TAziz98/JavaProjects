package GUI.Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;

import MMA.Compartment;
import MMA.DivisionRating;
import MMA.Fighter;
import MMA.Statistics;
import MMA.Team;
import MMA.Workout;
import MMA.WorkoutTypes;
import util.HibernateUtil;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class TeamGUI extends JFrame {

	private JPanel contentPane;
	private final JTextField textField = new JTextField();
	private JTextField textField_1;
	private WorkoutTypes workoutType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamGUI frame = new TeamGUI();
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
	public TeamGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textField.setBounds(201, 71, 107, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Team name");
		lblNewLabel.setBounds(94, 74, 76, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblWorkout = new JLabel("Workout");
		lblWorkout.setBounds(94, 114, 76, 14);
		contentPane.add(lblWorkout);
		
		String values2[]= {"Full Contact", "Cross Fit", "Grappling"};
		JComboBox comboBox = new JComboBox(values2);
		comboBox.setBounds(201, 111, 107, 20);
		comboBox.setBackground(SystemColor.inactiveCaption);
		contentPane.add(comboBox);
		
		JLabel lblCompartment = new JLabel("Compartment name");
		lblCompartment.setBounds(73, 154, 118, 14);
		contentPane.add(lblCompartment);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(201, 151, 107, 20);
		contentPane.add(textField_1);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  switch(comboBox.getSelectedItem().toString()) {	
				    
					case "Full Contact" : 
					 workoutType = WorkoutTypes.FullContact;	
					 break;
						
					case "Grappling" : 
						 workoutType = WorkoutTypes.Grappling;
						break;
						
					case "Cross Fit" : 
						 workoutType = WorkoutTypes.CrossFit;
						break;
				  }
				 Session session = HibernateUtil.getSessionFactory().openSession();	 
				  try {
					 Workout workout =  new Workout(comboBox.getSelectedItem().toString(), workoutType, "Strikes");
					  Team team = new Team();
						team.setCompartment(Compartment.findComparmentById(Integer.parseInt(textField_1.getText().toString())));
						team.setTeamsName(textField.getText().toString());
						team.doWorkout(workout);
					session.beginTransaction();
					session.save(workout);
					session.save(team);
					session.getTransaction().commit();
				  }
				  finally {
					  session.close();
				  }
				
			}
		});
		btnSubmit.setBounds(300, 202, 76, 23);
		contentPane.add(btnSubmit);
		
		JLabel lblTeam = new JLabel("Team");
		lblTeam.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTeam.setBounds(162, 22, 97, 25);
		contentPane.add(lblTeam);
		
		
		
		
	}

}
