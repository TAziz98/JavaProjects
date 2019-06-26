package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import MMA.Fighter;
import java.awt.Color;

public class EventsList extends JFrame {

	private JPanel contentPane;
	private JTable table_events;
	private List<String> eventData;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventsList frame = new EventsList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void show_events(List<MMA.Event> events) {
		DefaultTableModel model = (DefaultTableModel)table_events.getModel();
		Object[]  row = new Object[10];
		for(int i=0;i<events.size();i++) {
			row[0] = events.get(i).getEventName();
			row[1] = events.get(i).getDateOfEvent();
			row[2] =  events.get(i).getEventPlace();
			row[3] = events.get(i).getEntrancePrice()+"$";
			model.addRow(row);
		}
		
	}
	
	/**
	 * Create the frame.
	 */
	public EventsList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 296);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		
		JLabel lblPleaseSelect = new JLabel();
		lblPleaseSelect.setBackground(SystemColor.desktop);
		lblPleaseSelect.setForeground(SystemColor.desktop);
		lblPleaseSelect.setFont(new Font("Chiller", Font.BOLD, 30));
		lblPleaseSelect.setText("Please, select a row and click ");
		lblPleaseSelect.setBounds(132, 24, 323, 26);
		contentPane.setSize(400,300);
		contentPane.add(lblPleaseSelect);
		
		JLabel thumb2 = new JLabel();
		thumb2.setBounds(30, 0, 92, 60);
		contentPane.add(thumb2);
		thumb2.setIcon(new ImageIcon("C:\\Users\\Admin-Win10\\Desktop\\ht.jpg"));
		
		 TableModel model = new DefaultTableModel() {
		      public boolean isCellEditable(int rowIndex, int mColIndex) {
		        return false;
		      }
		    };
table_events = new JTable(model);
table_events.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseClicked(MouseEvent e) {
		eventData = new ArrayList<String>();
		DefaultTableModel model =(DefaultTableModel)table_events.getModel();
		int selectedRowIndex = table_events.getSelectedRow();
		IndividualEvent indEvent = new IndividualEvent();
		//System.out.println(model.getValueAt(selectedRowIndex, 0).toString());
		eventData.add(model.getValueAt(selectedRowIndex, 0).toString());
		eventData.add(model.getValueAt(selectedRowIndex, 1).toString());
		eventData.add(model.getValueAt(selectedRowIndex, 2).toString());
		eventData.add(model.getValueAt(selectedRowIndex, 3).toString());
		indEvent.show_event(eventData);
		indEvent.show();
		dispose();
		
	}
});
contentPane.setLayout(null);
table_events.setCellSelectionEnabled(true);
table_events.setModel(new DefaultTableModel(
	new Object[][] {
	},
	new String[] {
		"Event name", "Date", "Place", "Ticket Price"
	}
));
//table_events.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
table_events.setColumnSelectionAllowed(true);
table_events.setBounds(10, 35, 424, 93);
table_events.setColumnSelectionAllowed(false);
table_events.setRowSelectionAllowed(true);
JScrollPane scrollPane = new JScrollPane(table_events);
scrollPane.setBounds(10, 61, 501, 198);
contentPane.add(scrollPane);

	}

}
