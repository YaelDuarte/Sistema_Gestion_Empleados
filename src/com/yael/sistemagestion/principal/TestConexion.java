package com.yael.sistemagestion.principal;

import java.sql.Connection;
import java.sql.SQLException;

import com.yael.sistemagestion.config.ConexionBD;

public class TestConexion {
    public static void main(String[] args) {
        try {
            Connection conn = ConexionBD.getConnection();
            System.out.println("✅ Conexión exitosa a la base de datos");
            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión");
            e.printStackTrace();
        }
    }
}

