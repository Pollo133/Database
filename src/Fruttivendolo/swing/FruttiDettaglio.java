package Fruttivendolo.swing;

import Fruttivendolo.DAO.FruttoDAO;
import Fruttivendolo.Frutto;
import Fruttivendolo.Stagionalita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FruttiDettaglio extends JDialog implements ActionListener {

    private static final String INVALID_NAME = "[~#@*+%{}<>\\[\\]|\"\\_^;]";
    private static final String VALID_PRICE = "^\\d+(\\.\\d+)?$";
    private Frutto frutto = null;
    private JTextField txtId = null;
    private JTextField txtNome = null;
    private JTextField txtCosto = null;
    private JComboBox<Stagionalita> cmbStagionalita = null;
    private JButton btnUpdate = null;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FruttiDettaglio(new Frutto());
            }
        });
    }

    public FruttiDettaglio(Frutto frutto){
        this.frutto = frutto;

        setTitle("Frutti Dettaglio");
        setSize(400,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        initUI();
        populate();

        setVisible(true);
    }

    private void initUI(){
        JPanel pnlCentre = new JPanel(new GridLayout(4,1));


        JLabel lblId = new JLabel("Id");
        txtId = new JTextField(7);
        JPanel pnlId = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlId.add(lblId);
        pnlId.add(txtId);
        pnlCentre.add(pnlId);

        JLabel lblNome = new JLabel("Nome");
        txtNome = new JTextField(31);
        JPanel pnlNome = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlNome.add(lblNome);
        pnlNome.add(txtNome);
        pnlCentre.add(pnlNome);

        JLabel lblStagionalita = new JLabel("Stagionalita");
        cmbStagionalita = new JComboBox<Stagionalita>(Stagionalita.values());
        JPanel pnlStagionalita = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlStagionalita.add(lblStagionalita);
        pnlStagionalita.add(cmbStagionalita);
        pnlCentre.add(pnlStagionalita);

        JLabel lblCosto = new JLabel("CostoKg");
        txtCosto = new JTextField(10);
        JPanel pnlCosto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlCosto.add(lblCosto);
        pnlCosto.add(txtCosto);
        pnlCentre.add(pnlCosto);

        add(pnlCentre, BorderLayout.CENTER);

        //bottone

        JPanel pnlSouth = new JPanel(new FlowLayout());
        btnUpdate = new JButton("Update");
        pnlSouth.add(btnUpdate);

        add(pnlSouth, BorderLayout.SOUTH);

        btnUpdate.addActionListener(this);
    }

    private void populate() {
        txtId.setText(Frutto.getId() + "");
        txtId.setEnabled(false);
        txtNome.setText(frutto.getNome());
        txtCosto.setText(frutto.getCostoKg()/100.0 + "");
        cmbStagionalita.setSelectedItem(frutto.getStagionalita() + "");
    }
    
    public void actionPerformed(ActionEvent e){
        Frutto frutto = new Frutto();
        if(e.getSource() == btnUpdate){
            if(areFieldsValid()) {
                frutto.setNome(txtNome.getText());
                frutto.setStagionalita((Stagionalita) cmbStagionalita.getSelectedItem());
                frutto.setCostoKg((int) Float.parseFloat(txtCosto.getText())*100);
            }
        }
    }

    private boolean areFieldsValid() {
        Pattern pattern = Pattern.compile(INVALID_NAME);
        Matcher matcher = pattern.matcher(txtNome.getText());

        if (matcher.find()) {
            txtNome.setBackground(Color.RED);
            txtNome.setForeground(Color.WHITE);
            return false;
        }
        txtNome.setBackground(Color.WHITE);
        txtNome.setForeground(Color.BLACK);

        if (! txtCosto.getText().matches(VALID_PRICE)) {

            txtCosto.setBackground(Color.RED);
            txtCosto.setForeground(Color.WHITE);
            return false;
        }
        txtCosto.setBackground(Color.WHITE);
        txtCosto.setForeground(Color.BLACK);
        return true;
    }

}
