package GUI.Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminGUITest extends JFrame {

	private JPanel contentPane;
	private final JButton btnNewButton = new JButton("Add");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGUITest frame = new AdminGUITest();
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
	public AdminGUITest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CoachGUI coach= new CoachGUI();
				coach.show();
				 dispose();
			}
		});
		btnNewButton.setBounds(8, 31, 79, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Coach");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(8, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CoachRemGUI c = new CoachRemGUI();
				c.show();
				dispose();
			}
		});
		btnRemove.setBounds(8, 58, 79, 23);
		contentPane.add(btnRemove);
		
		JButton button = new JButton("Add");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FighterGUI fighter = new FighterGUI();
				fighter.show();
				dispose();
			}
		});
		button.setBounds(8, 112, 79, 23);
		contentPane.add(button);
		
		JLabel lblFighter = new JLabel("Fighter");
		lblFighter.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFighter.setBounds(8, 87, 46, 23);
		contentPane.add(lblFighter);
		
		JButton button_1 = new JButton("Remove");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				FighterRemGUI f = new FighterRemGUI();
				f.show();
				dispose();
				
			}
		});
		button_1.setBounds(8, 140, 79, 23);
		contentPane.add(button_1);
		
		JLabel lblPromotion = new JLabel("Promotion");
		lblPromotion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPromotion.setBounds(8, 175, 66, 23);
		contentPane.add(lblPromotion);
		
		JButton button_2 = new JButton("Add");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PromotionGUI promotion = new PromotionGUI();
				promotion.show();
				dispose();
			}
		});
		button_2.setBounds(8, 199, 79, 23);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("Remove");
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PromotionRemGUI p = new PromotionRemGUI();
				p.show();
				dispose();
				
			}
		});
		button_3.setBounds(8, 227, 79, 23);
		contentPane.add(button_3);
		
		JLabel lblCompartment = new JLabel("Compartment");
		lblCompartment.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCompartment.setBounds(161, 6, 79, 23);
		contentPane.add(lblCompartment);
		
		JButton button_4 = new JButton("Add");
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CompartmentGUI compartment = new CompartmentGUI();
				compartment.show();
				dispose();
			}
		});
		button_4.setBounds(161, 30, 79, 23);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("Remove");
		button_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CompartmentRemGUI c = new CompartmentRemGUI();
				c.show();
				dispose();
				
			}
		});
		button_5.setBounds(161, 58, 79, 23);
		contentPane.add(button_5);
		
		JLabel lblContract = new JLabel("Contract");
		lblContract.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContract.setBounds(161, 88, 79, 23);
		contentPane.add(lblContract);
		
		JButton button_6 = new JButton("Add");
		button_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ContractGUI contract = new ContractGUI();
				contract.show();
				dispose();
			}
		});
		button_6.setBounds(161, 112, 79, 23);
		contentPane.add(button_6);
		
		JButton button_7 = new JButton("Remove");
		button_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ContractRemGUI c = new ContractRemGUI();
				c.show();
				dispose();
			}
		});
		button_7.setBounds(161, 140, 79, 23);
		contentPane.add(button_7);
		
		JLabel lblTeam = new JLabel("Team");
		lblTeam.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTeam.setBounds(161, 175, 79, 23);
		contentPane.add(lblTeam);
		
		JButton button_8 = new JButton("Add");
		button_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TeamGUI team = new TeamGUI();
				team.show();
				dispose();
			}
		});
		button_8.setBounds(161, 199, 79, 23);
		contentPane.add(button_8);
		
		JButton button_9 = new JButton("Remove");
		button_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TeamRemGUI t = new TeamRemGUI();
				t.show();
				dispose();
			}
		});
		button_9.setBounds(161, 227, 79, 23);
		contentPane.add(button_9);
		
		JLabel lblSponsorshipAssociation = new JLabel("Sponsorship Association");
		lblSponsorshipAssociation.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSponsorshipAssociation.setBounds(286, 7, 138, 23);
		contentPane.add(lblSponsorshipAssociation);
		
		JButton button_10 = new JButton("Add");
		button_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SponsorhipAssociationGUI sponsor = new SponsorhipAssociationGUI();
				sponsor.show();
				dispose();
			}
		});
		button_10.setBounds(312, 30, 79, 23);
		contentPane.add(button_10);
	}

}
