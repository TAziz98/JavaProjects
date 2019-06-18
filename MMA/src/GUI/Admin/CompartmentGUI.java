package GUI.Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.EnumSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MMA.Compartment;
import MMA.CompartmentType;
import MMA.Fighter;
import MMA.Statistics;
import util.HibernateUtil;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.event.ChangeListener;

import org.hibernate.Session;

import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CompartmentGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
    private EnumSet<CompartmentType> compartmentTypes = EnumSet.noneOf(CompartmentType.class);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompartmentGUI frame = new CompartmentGUI();
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
	public CompartmentGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 433, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(206, 83, 108, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(206, 114, 108, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(206, 145, 108, 20);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel = new JLabel("Compartment name");
		lblNewLabel.setBounds(73, 86, 102, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPeriodOfTime = new JLabel("Period of time");
		lblPeriodOfTime.setBounds(73, 117, 91, 14);
		contentPane.add(lblPeriodOfTime);
		
		JLabel lblPeriodOfHolding = new JLabel("Period of holding down");
		lblPeriodOfHolding.setBounds(73, 148, 123, 14);
		contentPane.add(lblPeriodOfHolding);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Battle arena");
		chckbxNewCheckBox.setBounds(206, 172, 97, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxTrainingArena = new JCheckBox("Training arena");
		chckbxTrainingArena.setBounds(206, 200, 97, 23);
		contentPane.add(chckbxTrainingArena);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(chckbxNewCheckBox.isSelected()) {
					compartmentTypes.add(CompartmentType.BattleArena);
				}
				if(chckbxTrainingArena.isSelected()) {
					compartmentTypes.add(CompartmentType.TrainingArena);
				}
				 Session session = HibernateUtil.getSessionFactory().openSession();	 
				  try {
					  Compartment compartment = new Compartment(textField.getText().toString(),Integer.parseInt(textField_1.getText().toString()),Integer.parseInt(textField_2.getText().toString()), compartmentTypes);		
					session.beginTransaction();
					session.save(compartment);
					session.getTransaction().commit();
				  }
				  finally {
					  session.close();
				  }
		}
		
			
		});
		btnNewButton.setBounds(337, 227, 72, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblCompartment = new JLabel("Compartment");
		lblCompartment.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblCompartment.setBounds(134, 30, 141, 20);
		contentPane.add(lblCompartment);
		
		}
}
