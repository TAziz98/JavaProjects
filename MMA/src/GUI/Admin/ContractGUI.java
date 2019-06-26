package GUI.Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;

import MMA.Coach;
import MMA.Contract;
import MMA.Fighter;
import MMA.Promotion;
import util.HibernateUtil;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class ContractGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblNewLabel;
	private JLabel lblHonorarium;
	private JLabel lblBonus;
	private JLabel lblFighterId;
	private JLabel lblPromotionId;
	private JButton btnNewButton;
	private JLabel lblNewLabel_1;
	private JTextField textField_5;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContractGUI frame = new ContractGUI();
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
	public ContractGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(244, 81, 111, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(244, 115, 111, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(244, 146, 111, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(244, 177, 111, 20);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(244, 211, 111, 20);
		contentPane.add(textField_4);
		
		lblNewLabel = new JLabel("Number of fights");
		lblNewLabel.setBounds(126, 81, 108, 14);
		contentPane.add(lblNewLabel);
		
		lblHonorarium = new JLabel("Honorarium");
		lblHonorarium.setBounds(126, 115, 89, 14);
		contentPane.add(lblHonorarium);
		
		lblBonus = new JLabel("bonus");
		lblBonus.setBounds(126, 146, 46, 14);
		contentPane.add(lblBonus);
		
		lblFighterId = new JLabel("Fighter Id");
		lblFighterId.setBounds(126, 177, 89, 14);
		contentPane.add(lblFighterId);
		
		lblPromotionId = new JLabel("Promotion Id");
		lblPromotionId.setBounds(126, 211, 89, 14);
		contentPane.add(lblPromotionId);
		
		btnNewButton = new JButton("Submit");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(Fighter.ViewRecordById(1).getLastName());
				 Session session = HibernateUtil.getSessionFactory().openSession();	 
				  try {
					  Contract contract = new Contract(textField_5.getText().toString(),Integer.parseInt(textField.getText().toString()), Integer.parseInt(textField_1.getText().toString()),
							  Integer.parseInt(textField_2.getText().toString()), Fighter.ViewRecordById(1), Promotion.findPromotionById(Integer.parseInt(textField_4.getText().toString())));
					session.beginTransaction();
					session.save(contract);
					session.getTransaction().commit();
				  }
				  finally {
					  session.close();
				  }
				}
		});
		btnNewButton.setBounds(356, 254, 89, 23);
		contentPane.add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("Contract");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(166, 11, 124, 20);
		contentPane.add(lblNewLabel_1);
		
		textField_5 = new JTextField();
		textField_5.setBounds(244, 50, 111, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setBounds(126, 53, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		
		
	}

}
