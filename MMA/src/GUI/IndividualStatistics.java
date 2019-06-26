package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import MMA.Fighter;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class IndividualStatistics extends JFrame {

	private JPanel contentPane;
    private JLabel label;
    private JLabel label_1;
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
					IndividualStatistics frame = new IndividualStatistics();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void show_stats(List<String> fighterData) {
		//can be changed
	    nickName = fighterData.get(0).toString();
	    label.setText(fighterData.get(0).toString());
	    label_1.setText(fighterData.get(1).toString());
	    label_2.setText(fighterData.get(2).toString());
	    label_3.setText(fighterData.get(3).toString());
	    label_4.setText(fighterData.get(4).toString());
	    label_5.setText(fighterData.get(5).toString());
	    label_6.setText(fighterData.get(6).toString());
	    label_7.setText(fighterData.get(7).toString());
	    
		
	}
	

	/**
	 * Create the frame.
	 */
	public IndividualStatistics() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 377, 240);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JLabel thumb2 = new JLabel();
		thumb2.setBounds(220, 0, 138, 216);
		contentPane.add(thumb2);
		thumb2.setIcon(new ImageIcon("C:\\Users\\Admin-Win10\\Desktop\\hj.png"));
		
		JLabel lblNewLabel = new JLabel("Age");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.PLAIN, 13));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(23, 56, 82, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblDivision = new JLabel("Division");
		lblDivision.setBackground(SystemColor.desktop);
		lblDivision.setForeground(SystemColor.desktop);
		lblDivision.setFont(new Font("Sitka Subheading", Font.PLAIN, 13));
		lblDivision.setBounds(23, 78, 66, 14);
		lblNewLabel.setForeground(Color.BLACK);
		contentPane.add(lblDivision);
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setFont(new Font("Sitka Subheading", Font.PLAIN, 13));
		lblLastName.setForeground(SystemColor.desktop);
		lblLastName.setBounds(23, 34, 66, 14);
		lblNewLabel.setForeground(Color.BLACK);
		contentPane.add(lblLastName);
		
		JLabel lblLosts = new JLabel("Losts");
		lblLosts.setBackground(Color.RED);
		lblLosts.setFont(new Font("Sitka Subheading", Font.PLAIN, 13));
		lblLosts.setForeground(Color.RED);
		lblLosts.setBounds(23, 142, 46, 14);
		lblNewLabel.setForeground(Color.BLACK);
		contentPane.add(lblLosts);
		
		JLabel lblRating = new JLabel("Rating");
		lblNewLabel.setForeground(Color.BLACK);
		lblRating.setFont(new Font("Sitka Subheading", Font.PLAIN, 13));
		lblRating.setBounds(23, 98, 46, 16);
		contentPane.add(lblRating);
		
		JLabel lblWins = new JLabel("Wins");
		lblWins.setForeground(new Color(34, 139, 34));
		lblNewLabel.setForeground(Color.BLACK);
		lblWins.setFont(new Font("Sitka Subheading", Font.PLAIN, 13));
		lblWins.setBounds(23, 120, 46, 14);
		contentPane.add(lblWins);
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setFont(new Font("Sitka Subheading", Font.PLAIN, 13));
		lblNewLabel.setForeground(Color.BLACK);
		lblFirstName.setBounds(23, 10, 66, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblStandOffs = new JLabel("Stand Offs");
		lblStandOffs.setForeground(new Color(95, 158, 160));
		lblNewLabel.setForeground(Color.BLACK);
		lblStandOffs.setFont(new Font("Sitka Subheading", Font.PLAIN, 13));
		lblStandOffs.setBounds(23, 164, 77, 14);
		contentPane.add(lblStandOffs);
		
		label = new JLabel("");
		lblNewLabel.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(115, 10, 66, 14);
		contentPane.add(label);
		
		label_1 = new JLabel("");
		lblNewLabel.setForeground(Color.BLACK);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setBounds(115, 34, 90, 14);
		contentPane.add(label_1);
		
		label_2 = new JLabel("");
		lblNewLabel.setForeground(Color.BLACK);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_2.setBounds(115, 56, 66, 14);
		contentPane.add(label_2);
		
		label_3 = new JLabel("");
		lblNewLabel.setForeground(Color.BLACK);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_3.setBounds(115, 78, 95, 14);
		contentPane.add(label_3);
		
		label_4 = new JLabel("");
		lblNewLabel.setForeground(Color.BLACK);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_4.setBounds(115, 98, 66, 14);
		contentPane.add(label_4);
		
		label_5 = new JLabel("");
		label_5.setForeground(new Color(34, 139, 34));
		lblNewLabel.setForeground(Color.BLACK);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_5.setBounds(115, 120, 66, 14);
		contentPane.add(label_5);
		
		label_6 = new JLabel("");
		label_6.setForeground(new Color(255, 0, 0));
		label_6.setBackground(Color.RED);
		lblNewLabel.setForeground(Color.BLACK);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_6.setBounds(115, 142, 66, 14);
		contentPane.add(label_6);
		
		label_7 = new JLabel("");
		label_7.setForeground(new Color(95, 158, 160));
		label_7.setBackground(new Color(95, 158, 160));
		lblNewLabel.setForeground(Color.BLACK);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_7.setBounds(115, 164, 66, 14);
		contentPane.add(label_7);
		
		JButton btnNewButton = new JButton("Net Worth");
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(nickName);
				netWorth = new NetWorth();
				netWorth.showNetWorth(nickName);
				netWorth.show();
				dispose();
				
			}
		});
		btnNewButton.setBounds(23, 184, 95, 23);
		contentPane.add(btnNewButton);
		
		JButton btnSponsorsList = new JButton("Sponsors");
		btnSponsorsList.setHorizontalAlignment(SwingConstants.LEADING);
		btnSponsorsList.setBackground(SystemColor.inactiveCaption);
		btnSponsorsList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sponsors = new SponsorsList();
				sponsors.show_sponsors(Fighter.getSponsorsList(nickName));
				sponsors.show();
				dispose();
			}
		});
		btnSponsorsList.setBounds(122, 184, 95, 23);
		contentPane.add(btnSponsorsList);
		
		JLabel lblPleaseSelectMore = new JLabel("Please, select more details.");
		lblPleaseSelectMore.setForeground(SystemColor.text);
		lblPleaseSelectMore.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPleaseSelectMore.setBounds(99, 11, 167, 14);
		contentPane.add(lblPleaseSelectMore);
	}
}
