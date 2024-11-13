package br.com.aula.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AtualizarDados {
    public static void atualizarAluno(Connection conexao, int id, String nome, int idade) throws SQLException {
        String sql = "UPDATE alunos SET nome = ?, idade = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setInt(2, idade);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }
}
