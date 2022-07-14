package Controller;

import Model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UpdateCarrello", value = "/UpdateCarrello")
public class UpdateCarrello extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index= Integer.parseInt( request.getParameter("index"));
        String qt=request.getParameter("quantita");
        Integer quantita = 0;
        if(qt!=null)
            quantita = Integer.parseInt(qt);
        HttpSession snn=request.getSession();
        ListaVinili service= (ListaVinili) snn.getAttribute("libreria");
        Ordine carrello= (Ordine) snn.getAttribute("carrello");
        Vinile v=carrello.getCarrello().get(index).getArticolo();
        int actual=carrello.getCarrello().get(index).getQuantita();
        int remain=service.getQuantitaVin(v)-actual;
        int total=service.getQuantitaVin(v);
        carrello.getCarrello().get(index).setQuantita(quantita);
        carrello.refreshCost();
        ArrayList<Vinile> removed= carrello.check();
        Utente u= (Utente) snn.getAttribute("utente");
        if(u!=null){
            ArrayList<Vinile> lista = OrdineDAO.uploadOrdine(u,carrello,service);
            snn.setAttribute("removedVinil",lista);
        }
        else{
            snn.setAttribute("removedVinil",removed);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/carrello.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
