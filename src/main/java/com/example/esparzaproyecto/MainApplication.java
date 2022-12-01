package com.example.esparzaproyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/*
TODO:
1.- EL NUEVO PUEDE MODIFICAR SI DOY A GRABAR 2 VECES CONSECUTIVAS
2.- VALIDAR LO DEL LOGIN Y PERMISOS
3.- ARREGLAR EL COMBO EN CONSULTAS
4.- PONER ETIQUETA EN CADA CAMPO QUE FALTE POR INGRESAR
5.- MENSAJE PERSONALIZADO PARA CUANDO EL PROCEDIMIENTO ALMACENADO NO EXISTA
 */

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}