package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;

public class IndividualEvent extends JFrame {

	private JPanel contentPane;
    private JLabel lblGg;
    private JLabel lblGg_1;
    private JLabel label_2;
    private JLabel label_3;
    private JLabel label_4;
    private JLabel label_5;
    private JLabel label_6;
    private JLabel label_7;
    private String nickName;
    private NetWorth netWorth ;
    private SponsorsList sponsors;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IndividualEvent frame = new IndividualEvent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void show_event(List<String> eventData) {
		//can be changed
	    nickName = eventData.get(0).toString();
	    lblGg.setText(eventData.get(0).toString());
	    lblGg_1.setText(eventData.get(1).toString());
	    label_2.setText(eventData.get(2).toString());
	    label_3.setText(eventData.get(3).toString());
	    
		
	}
	/**
	 * Create the frame.
	 */
	public IndividualEvent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 399, 252);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(47, 79, 79));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel thumb2 = new JLabel();
		thumb2.setBounds(0, 0, 381, 212);
		contentPane.add(thumb2);
		thumb2.setIcon(new ImageIcon("C:\\Users\\Admin-Win10\\Desktop\\olk.png"));
		
		label_3 = new JLabel("");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_3.setBounds(12, 191, 66, 14);
		thumb2.add(label_3);
		
		JLabel lblNewLabel = new JLabel("Event Name");
		thumb2.add(lblNewLabel);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(10, 63, 82, 14);
		
		JLabel lblLastName = new JLabel("Place");
		thumb2.add(lblLastName);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLastName.setForeground(new Color(255, 0, 0));
		lblLastName.setBounds(10, 100, 66, 14);
		
		JLabel lblDivision = new JLabel("Date");
		thumb2.add(lblDivision);
		lblDivision.setForeground(new Color(255, 0, 0));
		lblDivision.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDivision.setBounds(10, 136, 46, 14);
		
		JLabel lblLosts = new JLabel("Ticket Price");
		thumb2.add(lblLosts);
		lblLosts.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLosts.setForeground(new Color(255, 0, 0));
		lblLosts.setBounds(10, 173, 82, 14);
		
		lblGg = new JLabel("");
		thumb2.add(lblGg);
		lblGg.setForeground(Color.WHITE);
		lblGg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGg.setBounds(10, 80, 110, 14);
		
		lblGg_1 = new JLabel("");
		thumb2.add(lblGg_1);
		lblGg_1.setForeground(Color.WHITE);
		lblGg_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGg_1.setBounds(10, 152, 100, 14);
		
		label_2 = new JLabel("");
		thumb2.add(label_2);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_2.setBounds(10, 113, 100, 14);
		
	
	}

}
