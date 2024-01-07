package Fruttivendolo.swing;

import Fruttivendolo.DAO.FruttoDAO;
import Fruttivendolo.DAO.GenericDAO;
import Fruttivendolo.Frutto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

class FruttiList extends JFrame implements ActionListener {

    private JButton btnDetail = null;
    private JButton btnDelete = null;
    private JButton btnAdd = null;
    private JTable tblFrutti = null;
    private JMenuItem mniNew = null;
    private JMenuItem mniSave = null;
    private JMenuItem mniOpen = null;
    private JMenuItem mniExit = null;
    private JMenuItem mniAbout = null;

    private ArrayList<Object> frutti = null;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {       //classe anonima
            @Override
            public void run() {
                new FruttiList(new ArrayList<Object>());
            }
        });
       
    }
    public FruttiList(ArrayList<Object> objs){
        this.frutti = new ArrayList<>();
        for(Object obj: objs)
                frutti.add((Frutto) obj);

        setTitle("Frutti List");
        setSize(400,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initUI();
        populate();

        setVisible(true);
    }


    private void initUI(){

        JMenuBar mnbNorth = new JMenuBar();
        JMenu mnuFile = new JMenu( "File");

        //item
        mniNew = new JMenuItem("New");
        mnuFile.add(mniNew);
        mniNew.addActionListener(this);

        mniOpen = new JMenuItem("Open...");
        mnuFile.add(mniOpen);
        mniOpen.addActionListener(this);

        mniSave = new JMenuItem("Save");
        mnuFile.add(mniSave);
        mniSave.addActionListener(this);

        mnuFile.addSeparator();

        mniExit = new JMenuItem("Exit");
        mniExit.addActionListener(this);
        mnuFile.add(mniExit);

        JMenu mnuHelp= new JMenu("Help");
        mniAbout = new JMenuItem("About");
        mnuHelp.add(mniAbout);
        mniAbout.addActionListener(this);


        mnbNorth.add(mnuFile);
        mnbNorth.add(mnuHelp);

        this.add(mnbNorth, BorderLayout.NORTH);

        //costruire una tabella--> tante righe quindi metto barra di scorrimento
        tblFrutti = new JTable();

        JScrollPane pnlCenter = new JScrollPane(tblFrutti);
        add(pnlCenter, BorderLayout.CENTER);

        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAdd = new JButton("Add...");
        btnDelete = new JButton("Delete");
        btnDetail = new JButton("Detail...");

        pnlSouth.add(btnAdd);
        pnlSouth.add(btnDelete);
        pnlSouth.add(btnDetail);

        add(pnlSouth, BorderLayout.SOUTH);

        btnAdd.addActionListener(this);
        btnDetail.addActionListener(this);
        btnDelete.addActionListener(this);



    }

    private void populate(){
        String[] cols = {"Id", "Nome", "Stagionalita", "CostoKg"};
        //non ti permette di modificare tutte le celle
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for(String col: cols){
            model.addColumn(col);
        }

        for(Object frutto: frutti){
            model.addRow(((Frutto)frutto).toRow());
        }

        tblFrutti.setModel(model);
        tblFrutti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    public void actionPerformed(ActionEvent e) {
        int selected = tblFrutti.getSelectedRow();
        if(e.getSource() == btnAdd) {
            Frutto frutto = new Frutto();
            new FruttiDettaglio(frutto);
        }

        if(e.getSource() == btnDelete) {
            if (selected == -1)
                return;
            Frutto frutto = (Frutto) frutti.get(selected);
            frutti.remove(frutto);
        }

        if(e.getSource() == btnDetail) {
            if(selected == -1) return;
            Frutto frutto = (Frutto) frutti.get(selected);
            new FruttiDettaglio(frutto);
        }
        populate(); //serve per modificare dopo che modifico

        if(e.getSource() == mniNew) {
            frutti.clear();
            populate();
        }

        if(e.getSource() == mniOpen) {
            try {
                open();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }

        if(e.getSource() == mniSave) {
            try {
                save();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }

        if(e.getSource() == mniExit) {
            System.exit(0);
        }

        if(e.getSource() == mniAbout) {
            //fa comparire una finestrella messaggio
            JOptionPane.showMessageDialog(this, "SIAMO FIGHISSIMI", "BELO TITOLO",JOptionPane.INFORMATION_MESSAGE);
        }


    }

    private void save() throws SQLException, ClassNotFoundException {

        JFileChooser fc = new JFileChooser("./");
        int result = fc.showSaveDialog(this);

        if(result != JFileChooser.APPROVE_OPTION) return;

        String fileName = (fc.getSelectedFile().getAbsolutePath());
        GenericDAO.setDbName(fileName);

        FruttoDAO.deleteAll();

        for(Object frutto: frutti){
            FruttoDAO.create((Frutto)frutto);
        }
    }

    private void open() throws SQLException, ClassNotFoundException {
        //scegliere un file
        JFileChooser fc = new JFileChooser("./");

        //tra parentesi chide il component dialog cisò dove mettere la schemata
        int result = fc.showOpenDialog(this); // mette dove stiamo lavorando
        // result indica se ha aperto o chiuso o che ha fatto

        //ha premuto ok
        if(result != JFileChooser.APPROVE_OPTION) return;
        frutti.clear();

        String fileName = (fc.getSelectedFile().getAbsolutePath());
        GenericDAO.setDbName(fileName);
        frutti = FruttoDAO.readAll();
        populate();
    }
}


//finestre modali --> finestra che blocca le altri finché non viene chiusa
/*
inversione di controllo --> action Listener
quando vengono cliccati lo capiamo e facciamo roba tramite getSource


anche altri listener
    WindowsListener --> elementi quando ci sono casini --> quando viene chiusa una finestra se non è salvato
        closing quando si sta per chiudere
        close quando è già chiusa

    DocumentListener --> all'interno di un component
        ogni cosa che scrive o fa genere un evento
        controllare se è salvato o no ogni volta che digita qualcosa


    Dialog predefiniti
        selezionale colore
        selezionare font
        selenzionare file per aprirlo
        selezionare file per salvarlo

        restituiscono un intero --> enumerazione che dice cosa è stato fatto

    openFile()
    saveAs()
    saveFìle()
    YesNoCancel

    Scrivere blocco note con swing

    Menu
        ActionListener per capire quando vengono cliccate

        barra menu --> JMenubar
            JMenu
                JItem
       se si riesce si mettono icone e scorciatoie


    permettere di esposrtare come altri file con frutti

 */