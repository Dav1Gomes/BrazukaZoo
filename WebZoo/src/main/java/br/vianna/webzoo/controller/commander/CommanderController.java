package br.vianna.webzoo.controller.commander;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.controller.commander.actions.impl.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/web")
public class CommanderController extends HttpServlet {

    private static final Map<String, ICommanderAction> comandos = new HashMap<>();

    static {
        comandos.put("viewHomePage", new CallViewHomePageAction());
        comandos.put(null, new CallViewHomePageAction());
        comandos.put("login", new CallViewLoginPageAction());
        comandos.put("logout", new CallLogoutAction());
        comandos.put("error", new CallViewErrorPageAction());
        //comandos.put("valLog", new CallValidaLoginAction());
        comandos.put("regVisitante", new CallRegistroVisitanteAction());
        //comandos.put("agendarVisita", new CallAgendarVisitaAction());
        comandos.put("listarVisitas", new CallListarVisitasAction());
        comandos.put("cadastrarAnimal", new CallCadastrarAnimalAction());
        comandos.put("viewCadastroAnimal", new CallViewCadastroAnimalAction());
        comandos.put("viewEditarAnimal", new CallViewEditarAnimalAction());
        comandos.put("editarAnimal", new CallViewEditarAnimalAction());
        comandos.put("formEditarAnimal", new CallFormEditarAnimalAction());
        comandos.put("atualizarAnimal", new CallAtualizarAnimalAction());
        comandos.put("CallCadastrarFuncionarioAction",new CallCadastrarFuncionarioAction());
        comandos.put("CallRegistrarAlimentacaoAction", new CallRegistrarAlimentacaoAction());
        comandos.put("viewRegistrarAlimentacao", new CallViewRegistrarAlimentacaoAction());
        comandos.put("viewHistoricoAlimentacao", new CallViewHistoricoAlimentacaoFuncionarioAction());
        comandos.put("viewHistoricoAlimentacaoAdmin", new CallViewHistoricoAlimentacaoAdminAction());
        comandos.put("viewTodosAnimais", new CallViewTodosAnimaisAction());
        comandos.put("cancelarVisita", new CallCancelarVisitaAction());
        comandos.put("viewDashboardAdmin", new CallViewDashboardAdminAction());
        comandos.put("viewVisitasAdmin", new CallListarTodasVisitasAction());
        comandos.put("viewFuncionarios", new CallViewFuncionariosAction());
        comandos.put("formEditarFuncionario", new CallFormEditarFuncionarioAction());
        comandos.put("atualizarFuncionario", new CallAtualizarFuncionarioAction());
        comandos.put("excluirFuncionario", new CallExcluirFuncionarioAction());
        comandos.put("cadastrarFornecedor", new CallCadastrarFornecedorAction());
        comandos.put("listarFornecedores", new CallListarFornecedoresAction());
        comandos.put("viewRegistrarTransferencia", new CallViewRegistrarTransferenciaAction());
        comandos.put("registrarTransferencia", new CallRegistrarTransferenciaAction());
        comandos.put("viewHistoricoTransferenciasFuncionario", new CallViewHistoricoTransferenciasFuncionarioAction());
        comandos.put("viewHistoricoTransferenciasAdmin", new CallViewHistoricoTransferenciasAdminAction());
        comandos.put("viewFornecedores", new CallViewFornecedoresAction());


    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ac = req.getParameter("ac");
        System.out.println("🔍 AC recebido: " + ac);
        ICommanderAction act = comandos.get(ac);

        if (act == null) {
            req.setAttribute("msg", "Comando '" + ac + "' não encontrado.");
            comandos.get("error").execute(req, resp);
            return;
        }

        String destino = act.execute(req, resp);

        if (destino != null) {
            req.getRequestDispatcher(destino).forward(req, resp);
        }

        System.out.println("AC recebido: " + ac);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

}
