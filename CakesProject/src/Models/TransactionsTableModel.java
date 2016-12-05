package Models;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Anne Gitau
 */
public class TransactionsTableModel extends AbstractTableModel{
    
    Object[][] transactions;
    Database dB;
    String[] columnNames;
    
    public TransactionsTableModel(){
        dB = Database.getDatabaseInstance();
        dB.connectToDatabase();
        columnNames = dB.transactionColumns();
        transactions = dB.getAllTrasacions();
    }
    
    @Override
    public int getRowCount() {
        return transactions.length;
    }
        

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return transactions[rowIndex][columnIndex];
    }
    
}
