package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import MMA.Fighter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class StatisticsList extends JFrame {

	private JPanel contentPane;
//	private JTable table;
	private Object[][] datas;
    private JTable table_fighters;
    private List<String> fighterData;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatisticsList frame = new StatisticsList();
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

		
	public void show_fighters(List<Fighter> fighters) {
		System.out.println( fighters.get(0).getAge());
		DefaultTableModel model = (DefaultTableModel)table_fighters.getModel();
		Object[]  row = new Object[10];
		for(int i=0;i<fighters.size();i++) {
			row[0] = fighters.get(i).getNickName();
			row[1] = fighters.get(i).getLastName();
			row[2] = fighters.get(i).getAge();
			row[3] = fighters.get(i).getStatistics().getDivision();
			row[4] = fighters.get(i).getStatistics().getDivisionRating();
			row[5] = fighters.get(i).getStatistics().getNumberOfWins();
			row[6] = fighters.get(i).getStatistics().getNumberofLosts();
			row[7] = fighters.get(i).getStatistics().getNumberOfStandOffs();
			model.addRow(row);
		}
		
	}
	
	public StatisticsList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 296);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel thumb2 = new JLabel();
		thumb2.setBounds(38, 0, 80, 53);
		contentPane.add(thumb2);
		thumb2.setIcon(new ImageIcon("C:\\Users\\Admin-Win10\\Desktop\\jh.png"));
		
		
		JLabel lblPleaseSelect = new JLabel();
		lblPleaseSelect.setBackground(SystemColor.desktop);
		lblPleaseSelect.setForeground(SystemColor.desktop);
		lblPleaseSelect.setFont(new Font("Chiller", Font.BOLD, 30));
		lblPleaseSelect.setText("Please, select a row and click ");
		lblPleaseSelect.setBounds(180, 18, 376, 26);
		contentPane.setSize(400,300);
		contentPane.add(lblPleaseSelect);
		
		 TableModel model = new DefaultTableModel() {
		      public boolean isCellEditable(int rowIndex, int mColIndex) {
		        return false;
		      }
		    };
table_fighters = new JTable(model);
table_fighters.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseClicked(MouseEvent e) {
		fighterData = new ArrayList<String>();
		DefaultTableModel model =(DefaultTableModel)table_fighters.getModel();
		int selectedRowIndex = table_fighters.getSelectedRow();
		IndividualStatistics indStats = new IndividualStatistics();
		//System.out.println(model.getValueAt(selectedRowIndex, 0).toString());
		fighterData.add(model.getValueAt(selectedRowIndex, 0).toString());
		fighterData.add(model.getValueAt(selectedRowIndex, 1).toString());
		fighterData.add(model.getValueAt(selectedRowIndex, 2).toString());
		fighterData.add(model.getValueAt(selectedRowIndex, 3).toString());
		fighterData.add(model.getValueAt(selectedRowIndex, 4).toString());
		fighterData.add(model.getValueAt(selectedRowIndex, 5).toString());
		fighterData.add(model.getValueAt(selectedRowIndex, 6).toString());
		fighterData.add(model.getValueAt(selectedRowIndex, 7).toString());
		indStats.show_stats(fighterData);
		indStats.show();
		dispose();
		
	}
});
table_fighters.setCellSelectionEnabled(true);
table_fighters.setModel(new DefaultTableModel(
	new Object[][] {
	},
	new String[] {
		"First name", "Last name", "Age", "Division", "Rating", "Wins", "Losts", "Stand Offs"
	}
));
//table_fighters.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
table_fighters.setColumnSelectionAllowed(true);
table_fighters.setBounds(10, 35, 424, 93);
table_fighters.setColumnSelectionAllowed(false);
table_fighters.setRowSelectionAllowed(true);
JScrollPane scrollPane = new JScrollPane(table_fighters);
scrollPane.setBounds(7, 64, 608, 185);
contentPane.add(scrollPane);


	}
}
