import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class Main extends javax.swing.JFrame {
    private Stack<Task> tasks;
    private List<Task> tasksInProcess;
    private MyTableModel model;
    private TaskController taskController;
    ExecutorService service = Executors.newFixedThreadPool(5);
    
    public Main() {
        initComponents();
        this.setSize(500, 422);
        this.setLocationRelativeTo(null);
        tasks = new Stack<>();
        model = new MyTableModel();
        jTable1.setModel(model);
        taskController = new TaskController();
        tasksInProcess = new ArrayList<>();
        jTable1.setDefaultRenderer(Status.class, new MyTableCellRenderer());
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    model.fireTableDataChanged();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();
    }
    
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jButton1.setText("Add task");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        pack();
    }                       

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        if(tasksInProcess.size() == 5)
            return;
        Task t = taskController.createTask();
        tasks.add(t);
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                    try {
                        tasksInProcess.add(t);
                        Thread.sleep(1000 + (int)(Math.random() * ((2000 - 1000) + 1)));
                        taskController.processTask(t);
                        t.setResult(taskController.callTask(t));
                        tasksInProcess.remove(t);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
            }
        }).start();    
    }                                        
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration                   
    private class MyTableModel extends AbstractTableModel{

        @Override
        public int getRowCount() {
            return tasks.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch(columnIndex){
                case 0 : return ((Task)tasks.get(tasks.size()-1-rowIndex)).getName();
                case 1 : return ((Task)tasks.get(tasks.size()-1-rowIndex)).getStatus(); 
                case 2 : 
                    if(((Task)tasks.get(tasks.size()-1-rowIndex)).getResult() == 0)
                        return "";
                    return ((Task)tasks.get(tasks.size()-1-rowIndex)).getResult();
                default : return null;
            }
        }
        
        @Override
        public String getColumnName(int column) {
                switch (column){
                case 0 : return "Task";
                case 1: return "Status";
                case 2 : return "Result";
                default : return null;
            }
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch(columnIndex){
                case 0 : return String.class;
                case 1 : return Status.class;
                case 2 : return int.class;
                default : return null;
            }
        }
        
    
    }
    
    private class MyTableCellRenderer extends DefaultTableCellRenderer{

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            JLabel label = (JLabel) component;
            label.setBorder(new LineBorder(Color.BLACK));
            if(value.equals(Status.PENDING)){
                label.setBackground(Color.YELLOW);
                label.setForeground(Color.BLACK);
            }
            if(value.equals(Status.ACCOMPLISHED)){
                label.setBackground(Color.GREEN);
                label.setForeground(Color.BLACK);
            }
            if(value.equals(Status.FAILED)){
                label.setBackground(Color.RED);
                label.setForeground(Color.WHITE);
            }
            if(value.equals(Status.RUNNING)){
                label.setBackground(Color.BLUE);
                label.setForeground(Color.WHITE);
            }
            return label;
        }      
    }
}

