package conectar;

import java.sql.*;


public class Conectar {

    private static Connection conexao_MySql = null;
    private static String localBD = "localhost";
    private static String LINK = "jdbc:mysql://" + localBD + ":3306/newverse";
    private static final String usuario = "root";
    private static final String senha = "";

    // Método para fazer a conexão com um banco de dados MySql
    public Connection connectionMySql() {
        try {
            conexao_MySql = DriverManager.getConnection(LINK, usuario, senha);
            System.out.println("conexão OK!");
        } catch (SQLException e) {
            throw new RuntimeException("Ocorreu um problema na conexão com o BD", e);
        }
        return conexao_MySql;
    }


    public void consulta(Connection con) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Cliente");
            System.out.println("Consulta ao banco:");
            while (rs.next()) {
                System.out.println("cod: " + rs.getInt(1) + " - Nome: " + rs.getString(2) + " - Email: " + rs.getString(3));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public Boolean databaseConfereCadastrados(String Email,  long CPF) {
        Connection connection = connectionMySql();
        boolean existeCadastro = false;
        String sql = "Select id_cliente, email, CPF"
                + "from Cliente "
                + "where email=?";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(sql);
            //Efetua a troca do '?' pelos valores na query
            preparedStmt.setString(1, Email);
            ResultSet result = preparedStmt.executeQuery();
            //valida resultado
            while (result.next()) {
                int id_cliente = result.getInt("id_cliente");
                String email = result.getString("email");
                String cpf = result.getString("cpf");
                System.out.println("Identificador de Cliente: " + id_cliente);
                System.out.println("Email: " + email);
                System.out.println("Cpf : " + cpf);
                if (id_cliente != 0) {
                    existeCadastro = true;
                }else{
                    existeCadastro = false;
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return existeCadastro;
    }

    public Boolean databaseLogin(String UsernameEmail,  String Senha, String EmailUsername) {
        Connection connection = connectionMySql();
        boolean logado = false;
        String sql = "SELECT nome_usuario, email, senha FROM Cliente where senha=? and (email = ? or nome_usuario = ? )";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(sql);
            //Efetua a troca do '?' pelos valores na query
            preparedStmt.setString(1, Senha);
            preparedStmt.setString(2, UsernameEmail);
            preparedStmt.setString(3, EmailUsername);
            ResultSet result = preparedStmt.executeQuery();
            //valida resultado
            while (result.next()) {
                String senha = result.getString("senha");
                String email = result.getString("email");
                String username = result.getString("nome_usuario");
                System.out.println("Nome Usuario: " + username);
                System.out.println("Email: " + email);
                System.out.println("Senha : " + senha);
                logado = true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return logado;
    }


    public String databaseUsername(String UsernameEmail,  String Senha, String EmailUsername) {
        Connection connection = connectionMySql();
        String username = "";
        String sql = "SELECT nome_usuario FROM Cliente where senha=? and (email = ? or nome_usuario = ? )";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(sql);
            //Efetua a troca do '?' pelos valores na query
            preparedStmt.setString(1, Senha);
            preparedStmt.setString(2, UsernameEmail);
            preparedStmt.setString(3, EmailUsername);
            ResultSet result = preparedStmt.executeQuery();
            //valida resultado
            while (result.next()) {
                username = result.getString("nome_usuario");
                System.out.println("Nome Usuario: " + username);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return username;
    }

    public void dataBaseInsertCadastro(String Username, String Email, String Senha, long CPF) {
        Connection connection = connectionMySql();
        String sql = "INSERT INTO Cliente (nome_usuario, email, senha, CPF) VALUES (?,?,?,?)";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(sql);
            //Efetua a troca do '?' pelos valores na query             
            preparedStmt.setString(1, Username);
            preparedStmt.setString(2, Email);
            preparedStmt.setString(3, Senha);
            preparedStmt.setLong(4, CPF);
            preparedStmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void closeConnectionMySql(Connection con) {
        try {
            if (con != null) {
                con.close();
                System.out.println("Fechamento OK");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ocorreu um problema para encerrar a conexão com o BD.", e);
        }
    }
}