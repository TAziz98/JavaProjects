package GUI.Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;

import MMA.Promotion;
import util.HibernateUtil;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PromotionGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblEstablished;
	private JTextField textField_1;
	private JLabel lblPromotion;
	private JButton btnSubmit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PromotionGUI frame = new PromotionGUI();
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
	public PromotionGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(202, 102, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPromotionName = new JLabel("Promotion name");
		lblPromotionName.setBounds(99, 105, 93, 14);
		contentPane.add(lblPromotionName);
		
		lblEstablished = new JLabel("Established");
		lblEstablished.setBounds(99, 145, 93, 14);
		contentPane.add(lblEstablished);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(202, 142, 86, 20);
		contentPane.add(textField_1);
		
		lblPromotion = new JLabel("Promotion");
		lblPromotion.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblPromotion.setBounds(148, 45, 118, 23);
		contentPane.add(lblPromotion);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  Session session = HibernateUtil.getSessionFactory().openSession();	 
				  try {
				Promotion promotion = new Promotion(textField.getText().toString(),Integer.parseInt(textField_1.getText().toString()));
				session.beginTransaction();
				session.save(promotion);
				session.getTransaction().commit();
				textField.setText("");  textField_1.setText("");
			  }
			  finally {
				  session.close();
			  }
			}
		});
		btnSubmit.setBounds(310, 201, 89, 23);
		contentPane.add(btnSubmit);
	}

}
