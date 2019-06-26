package GUI.Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;

import MMA.Compartment;
import MMA.Fighter;
import MMA.SponsorshipAssociation;
import MMA.Team;
import MMA.Workout;
import util.HibernateUtil;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SponsorhipAssociationGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtPrivateTextfieldTextfield;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SponsorhipAssociationGUI frame = new SponsorhipAssociationGUI();
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
	public SponsorhipAssociationGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(214, 67, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		txtPrivateTextfieldTextfield = new JTextField();
		txtPrivateTextfieldTextfield.setBounds(214, 98, 86, 20);
		contentPane.add(txtPrivateTextfieldTextfield);
		txtPrivateTextfieldTextfield.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(214, 129, 86, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(214, 160, 86, 20);
		contentPane.add(textField_2);
		
		JLabel lblAssociationName = new JLabel("Association name");
		lblAssociationName.setBounds(98, 70, 96, 14);
		contentPane.add(lblAssociationName);
		
		JLabel lblContactEmail = new JLabel("Contact email");
		lblContactEmail.setBounds(98, 101, 96, 14);
		contentPane.add(lblContactEmail);
		
		JLabel lblPhoneNumber = new JLabel("Phone number");
		lblPhoneNumber.setBounds(98, 132, 96, 14);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblFighterNickname = new JLabel("Fighter Id");
		lblFighterNickname.setBounds(98, 163, 96, 14);
		contentPane.add(lblFighterNickname);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 Session session = HibernateUtil.getSessionFactory().openSession();	 
				  try {
					  SponsorshipAssociation association = new SponsorshipAssociation(textField.getText().toString(),
							  txtPrivateTextfieldTextfield.getText().toString(),textField_1.getText().toString());
					  association.getSponsoredFighters().add(Fighter.ViewRecordById(Integer.parseInt(textField_2.getText().toString())));
					session.beginTransaction();
					session.save(association);
					session.getTransaction().commit();
				  }
				  finally {
					  session.close();
				  }
			}
		});
		btnSubmit.setBounds(320, 214, 89, 23);
		contentPane.add(btnSubmit);
		
		JLabel lblSponsorshipAssociation = new JLabel("Sponsorship Association");
		lblSponsorshipAssociation.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSponsorshipAssociation.setBounds(126, 11, 247, 31);
		contentPane.add(lblSponsorshipAssociation);
	}

}
