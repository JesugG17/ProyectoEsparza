package com.example.esparzaproyecto.controllers;

import com.example.esparzaproyecto.models.Articulo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class CapturaController implements Initializable {

    @FXML
    RadioButton radioNuevo, radioModificar, radioEliminar;

    @FXML
    ComboBox<String> cmbFamilias;

    @FXML
    TableView<Articulo> tablaDatos;

    @FXML
    TableColumn<String, Articulo> columnClave, columnNombre, columnDescripcion, columnPrecio, columnFamilia;

    @FXML
    TextField txtClave, txtNombre, txtDescripcion, txtPrecio;

    @FXML
    ObservableList<Articulo> articulos;

    @FXML
    Label labelMessages;

    Background backgroudnInicial;
    Border borderInicial;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        articulos = FXCollections.observableArrayList();
        radioNuevo.setToggleGroup( toggleGroup );
        radioNuevo.setSelected( true );
        radioModificar.setToggleGroup( toggleGroup );
        radioEliminar.setToggleGroup( toggleGroup );


        tablaDatos.setPlaceholder(new Label("Sin articulos que mostrar"));
        tablaDatos.setItems( articulos );

        columnClave.setCellValueFactory(new PropertyValueFactory<>("clave"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnFamilia.setCellValueFactory(new PropertyValueFactory<>("famId"));

        backgroudnInicial = txtPrecio.getBackground();
        borderInicial = txtPrecio.getBorder();


        txtNombre.requestFocus();
        llenarCombo();
    }

    @FXML
    public void grabar() {
        labelMessages.setVisible( false );
        if (!validarPrecio()) {
            txtPrecio.requestFocus();
            return;
        }

        int clave = 1;
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        String idFam = cmbFamilias.getValue().substring(0, cmbFamilias.getValue().indexOf(" "));
        int famId = Integer.parseInt( idFam );

        Articulo articulo = new Articulo(clave, nombre, descripcion, precio, famId);


        articulos.add(articulo);
        tablaDatos.refresh();

        labelMessages.setText("DATOS GRABADOS EXITOSAMENTE");
        labelMessages.setVisible( true );
    }


    @FXML
    public void limpiar() {

        if (labelMessages.isVisible()) {
            labelMessages.setVisible( false );
        }


        txtClave.setText("*");
        txtClave.setDisable( true );
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        radioNuevo.setSelected( true );
        llenarCombo();
    }


    private boolean validarPrecio() {

        try {

            Double.parseDouble(txtPrecio.getText());

        } catch (Exception error) {
            labelMessages.setText("El precio debe de ser numerico");
            labelMessages.setVisible( true );
            System.out.println(error.getMessage());
            return false;
        }

        return true;
    }

    private void llenarCombo() {

        String query = "select * from familias";
        cmbFamilias.getItems().clear();
        try {
            MainController.hacerConexion();
            Statement smt = MainController.coneccion.createStatement();
            ResultSet tuplas = smt.executeQuery( query );

            while( tuplas.next() ) {
                String familia = tuplas.getInt(("famid")) + " - " + tuplas.getString("famnombre");
                cmbFamilias.getItems().add( familia );
            }


        } catch (Exception error) {
            System.out.println(error.getMessage());
        }

    }


}
