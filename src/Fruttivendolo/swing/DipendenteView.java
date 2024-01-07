package Fruttivendolo.swing;

import Fruttivendolo.DAO.NegozioDAO;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;


public class DipendenteView extends JFrame {

    //id, nome, cognome, cellulare, idNegozio
    private JTextField txtId = null;
    private JTextField txtNome = null;
    private JTextField txtCognome = null;
    private JTextField txtCellulare = null;
    private JComboBox<String> cmbIdNegozio = null;

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new DipendenteView();
                } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                }
            }
        });
    }
    public DipendenteView() throws SQLException, ClassNotFoundException {
        setSize(600,400);
        setTitle("Dettaglio negozio");
        //relativo a un altra finestra, se è null è al centro
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
        setVisible(true);
    }

    private void initUI() throws SQLException, ClassNotFoundException {
        // pnl centrale
        JPanel pnlCenter = new JPanel(new GridLayout(5,1));


        JPanel pnlId = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblId = new JLabel("ID");
        txtId = new JTextField(10);
        txtId.setEnabled(false);
        //aggiungo JComponent al pannello corrispondente
        pnlId.add(lblId);
        pnlId.add(txtId);
        //aggiundo pnlId al pannello centrale
        pnlCenter.add(pnlId);


        //nome
        JPanel pnlNome = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblNome = new JLabel("Nome");
        txtNome = new JTextField(30);
        pnlNome.add(lblNome);
        pnlNome.add(txtNome);
        pnlCenter.add(pnlNome);

        //cognome
        JPanel pnlCognome = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblCognome = new JLabel("Cognome");
        txtCognome = new JTextField(30);
        pnlCognome.add(lblCognome);
        pnlCognome.add(txtCognome);
        pnlCenter.add(pnlCognome);

        //Cellulare
        JPanel pnlCellulare = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblCellulare = new JLabel("Cellulare");
        txtCellulare = new JTextField(13);
        pnlCellulare.add(lblCellulare);
        pnlCellulare.add(txtCellulare);
        pnlCenter.add(pnlCellulare);

        //IdNegozio

        JPanel pnlIdNegozio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblIdNegozio = new JLabel("Negozio in cui lavora");
        cmbIdNegozio = new JComboBox<String>();
        pnlIdNegozio.add(lblIdNegozio);
        pnlIdNegozio.add(cmbIdNegozio);
        pnlCenter.add(pnlIdNegozio);
        popolaComboBox();
        JPanel pnlSud = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnNew = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");

        pnlSud.add(btnUpdate);
        pnlSud.add(btnDelete);
        pnlSud.add(btnNew);



        //aggiungo pannello centrale al frame/pagina
        add(pnlCenter, BorderLayout.CENTER);
        add(pnlSud, BorderLayout.SOUTH);
    }

    public void popolaComboBox() throws SQLException, ClassNotFoundException {
        ArrayList<String> stringheIdNegozi =  NegozioDAO.readAllIdNegozio();
        for(String s: stringheIdNegozi){
                cmbIdNegozio.addItem(s);
        }
    }

}


/*
    è il sistema che si accorge che sono state apportate delle modifiche e non i vari widget che invece vengono svegliati
    actionPerformed --> quando il sistema si accorde di un azione

    inversione di controllo --> controllo del pannello centrale e non del bottone --> sembra il contrario




 */