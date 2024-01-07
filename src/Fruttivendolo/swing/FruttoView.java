package Fruttivendolo.swing;

import Fruttivendolo.DAO.FruttoDAO;
import Fruttivendolo.Frutto;
import Fruttivendolo.Stagionalita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class FruttoView extends JFrame implements ActionListener{

    private JTextField txtId = null;
    private JTextField txtNome = null;
    private JComboBox<Stagionalita> cmbStagionalita = null;
    private JTextField txtCostoKg = null;

    private JButton btnAdd = null;
    private JButton btnUpdate = null;



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {//classe anonima
            @Override
            public void run() {
                new FruttoView(new Frutto(3, "Mela", Stagionalita.ESTIVA, 130));
            }
        });
    }

    private FruttoView(Frutto f){
        setTitle("Frutto");
        setSize(400,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initUI();
        populate(f);
        setVisible(true);
    }


    private void initUI() {
        JLabel lblId = new JLabel("ID: ");
        this.txtId = new JTextField(5);
        txtId.setEnabled(false); //non ci clicchi più
        JPanel pnlId = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlId.add(lblId);
        pnlId.add(txtId);

        JLabel lblNome = new JLabel("DESCRIZIONE: ");
        this.txtNome = new JTextField(30);
        JPanel pnlNome = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlNome.add(lblNome);
        pnlNome.add(txtNome);

        //ComboBox --> selezione multipla
        JLabel lblStagionalita = new JLabel("STAGIONALITA: ");
        this.cmbStagionalita = new JComboBox<>(Stagionalita.values());
        JPanel pnlStagionalita = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlStagionalita.add(lblStagionalita);
        pnlStagionalita.add(cmbStagionalita);


        JLabel lblCostoKg = new JLabel("COSTO: ");
        this.txtCostoKg = new JTextField(10);
        JPanel pnlCostoKg = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlCostoKg.add(lblCostoKg);
        pnlCostoKg.add(txtCostoKg);

        //region ButtonsAction
        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(this);


        btnAdd = new JButton("Add");
        btnAdd.addActionListener(this);
        //endregion

        JPanel pnlCenter = new JPanel(new GridLayout(4, 1));

        pnlCenter.add(pnlId);
        pnlCenter.add(pnlNome);
        pnlCenter.add(pnlStagionalita);
        pnlCenter.add(pnlCostoKg);

        add(pnlCenter, BorderLayout.CENTER);
        //panel con flowlayout che permettere in sequenza più jComponent
        //creo bottoni per creare e eliminare

        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        pnlSouth.add(btnUpdate);
        pnlSouth.add(btnAdd);

        add(pnlSouth, BorderLayout.SOUTH);
    }

    private void populate(Frutto frutto){
        txtId.setText(""+ frutto.getId());
        txtNome.setText(frutto.getNome());
        cmbStagionalita.setSelectedItem(frutto.getStagionalita());
        txtCostoKg.setText(""+ frutto.getCostoKg()/100.0);
    }

    private boolean isPriceValid(){
        String price = txtCostoKg.getText();

        String RGX_CONSTOKG = "^\\$?(?=\\(.*\\)|[^()]*$)\\(?\\d{1,3}(,?\\d{3})?(\\.\\d\\d?)?\\)?$";
        return price.matches(RGX_CONSTOKG);
    }

    public boolean checkField(){
        String nome = txtNome.getText();
        if(nome.isEmpty()){
            txtNome.setBackground(Color.RED);
            txtNome.setForeground(Color.WHITE);
            return false;
        }else{
            txtNome.setBackground(Color.WHITE);
            txtNome.setForeground(Color.BLACK);
        }
        if(!isPriceValid()){
            txtCostoKg.setBackground(Color.RED);
            txtCostoKg.setForeground(Color.WHITE);
            return false;
        }else{
            txtCostoKg.setBackground(Color.WHITE);
            txtCostoKg.setForeground(Color.BLACK);
        }
        if (cmbStagionalita.getSelectedIndex()<0){
            cmbStagionalita.setBackground(Color.RED);
            cmbStagionalita.setForeground(Color.WHITE);
            return false;
        }else{
            cmbStagionalita.setBackground(Color.WHITE);
            cmbStagionalita.setForeground(Color.BLACK);
        }
        return true;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if (e.getSource() == btnUpdate) {
                if (checkField()== true) {
                    int id = Integer.parseInt(txtId.getText());
                    String nome = txtNome.getText();
                    Stagionalita stagionalita = (Stagionalita) cmbStagionalita.getSelectedItem();
                    float costoKg = Float.parseFloat(txtCostoKg.getText());
                    FruttoDAO.update(new Frutto(id, nome, stagionalita, (int) costoKg));
                }
            }
            if (e.getSource() == btnAdd) {
                if (checkField() == true) {
                    String nome = txtNome.getText();
                    Stagionalita stagionalita = (Stagionalita) cmbStagionalita.getSelectedItem();
                    float costoKg = Float.parseFloat(txtCostoKg.getText());
                    FruttoDAO.create(new Frutto(null, nome, stagionalita, (int) costoKg));
                }
            }
        }catch(SQLException | RuntimeException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }
}



