package SpringBancoSql;

import java.sql.Connection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import conectar.Conectar;

@Controller
public class WebController{

    //Verifica se recebeu alguma requisição de Login para fazer link para essa página
    @RequestMapping("/login")
    public String Login(Model modelo){
        System.out.println("LOGIN 1");
        return "login";
    }

    //Verifica se recebeu alguma requisição do valor login com método POST para pegar as informações
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String cadastroCliente(Model modelo, String usuario, String email, String senha2, long cpf){
        boolean cadastroExistente = false;

        Conectar bancoNewverse = new Conectar();
        Connection statusConexao =  bancoNewverse.connectionMySql();
        cadastroExistente = bancoNewverse.databaseConfereCadastrados(email, cpf);
        bancoNewverse.dataBaseInsertCadastro(usuario, email, senha2, cpf);
        bancoNewverse.closeConnectionMySql(statusConexao);
        
        if(cadastroExistente != false){} //escreve mensagem
        return "login";
    }


    @RequestMapping(value = "/indexALOGIN", method = RequestMethod.POST)
    public String loginCliente(Model modelo, String usermail, String senha){
        boolean logado = false;

        Conectar bancoNewverse = new Conectar();
        Connection statusConexao =  bancoNewverse.connectionMySql();
        logado = bancoNewverse.databaseLogin(usermail, senha, usermail);
        String nome_usuario= bancoNewverse.databaseUsername(usermail, senha, usermail);
        bancoNewverse.closeConnectionMySql(statusConexao);
        System.out.println(logado+"<---LOGADO");
        if (logado) {
            modelo.addAttribute("mensagem1", "Olá "+nome_usuario);
        }
        else{
            modelo.addAttribute("mensagem1", "Não Logado, Clique Aqui");
        }
        return "indexALOGIN";
    }

    @RequestMapping("/contato")
    public String Contato(Model modelo){
        System.out.println("CONTATO 1");
        return "contato";
    }
}