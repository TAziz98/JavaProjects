package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import MMA.SponsorshipAssociation;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class SponsorsList extends JFrame {

	private JPanel contentPane;
	 private JTable table_sponsors;
	 private JButton btnCriteria;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SponsorsList frame = new SponsorsList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void show_sponsors(List<SponsorshipAssociation> sponsors) {
		System.out.println("size of sponsors list "+ sponsors.size());
		DefaultTableModel model = (DefaultTableModel)table_sponsors.getModel();
		Object[]  row = new Object[10];
		for(int i=0;i<sponsors.size();i++) {
			row[0] = sponsors.get(i).getAssociationName();
			row[1] = sponsors.get(i).getContact_email();
			row[2] = sponsors.get(i).getPhone_numb();
			model.addRow(row);
		}
	}
	/**
	 * Create the frame.
	 */
	public SponsorsList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		
		 TableModel model = new DefaultTableModel() {
		      public boolean isCellEditable(int rowIndex, int mColIndex) {
		        return false;
		      }
		    };
table_sponsors= new JTable(model);
table_sponsors.setCellSelectionEnabled(true);
table_sponsors.setModel(new DefaultTableModel(
	new Object[][] {
	},
	new String[] {
		"Association name", "Contact e-mail", "Phone number"
	}
));
//table_sponsors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
table_sponsors.setColumnSelectionAllowed(true);
table_sponsors.setBounds(10, 35, 424, 93);
table_sponsors.setColumnSelectionAllowed(false);
table_sponsors.setRowSelectionAllowed(true);
JScrollPane scrollPane = new JScrollPane(table_sponsors);
scrollPane.setBounds(10, 11, 563, 229);
contentPane.add(scrollPane);
btnCriteria = new JButton("Criteria");
btnCriteria.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseClicked(MouseEvent e) {
		Criteria c;
		try {
			c = new Criteria();
			c.show();
			dispose();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
});
btnCriteria.setBackground(SystemColor.inactiveCaption);
btnCriteria.setBounds(528, 251, 77, 23);
contentPane.add(btnCriteria);
	}

}
