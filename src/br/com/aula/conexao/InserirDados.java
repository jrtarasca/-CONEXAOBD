package br.com.aula.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InserirDados {
    public static void inserirAluno(Connection conexao, String nome, int idade) throws SQLException {
        String sql = "INSERT INTO alunos (nome, idade) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setInt(2, idade);
            stmt.executeUpdate();
        }
    }
}
