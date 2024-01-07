package Fruttivendolo.swing;

import Fruttivendolo.DAO.FruttoDAO;
import Fruttivendolo.DAO.NegozioDAO;
import Fruttivendolo.Frutto;
import Fruttivendolo.Negozio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class NegozioView extends JFrame implements ActionListener {

    private JButton btnDetail = null;
    private JButton btnDelete = null;
    private JButton btnAdd = null;
    private JTable tblNegozi = null;
    private ArrayList<Negozio> negozi = null;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {       //classe anonima
            @Override
            public void run() {
                try {
                    ArrayList<Object> negozi;
                    negozi = NegozioDAO.readAll();
                    new FruttiList(negozi);
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public NegozioView(ArrayList<Object> objs){
        this.negozi = new ArrayList<Negozio>();
        for(Object obj: objs)
            negozi.add((Negozio) obj);

        setTitle("Negozi List");
        setSize(400,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initUI();
        populate();

        setVisible(true);
    }

    private void populate() {

    }

    private void initUI() {
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
