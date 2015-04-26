package pe.com.hiper.sample.jqgrid.actions;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import pe.com.hiper.sample.jqgrid.dao.InvheaderJpaController;
import pe.com.hiper.sample.jqgrid.entidad.Invheader;
import pe.com.hiper.sample.jqgrid.forms.InvheaderForm;

/**
 *
 * @author Cesardl
 */
public class InvheaderAction extends DispatchAction {

    private static final String LOADINV = "loadinv";
    private final InvheaderJpaController inveaderDAO = new InvheaderJpaController(Persistence.createEntityManagerFactory("jgGridPU"));

    public ActionForward listarInvheader(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<Invheader> invheaders = inveaderDAO.findInvheaderEntities();
        Gson gson = new Gson();
        String json = gson.toJson(invheaders);

        HttpSession session = request.getSession();
        session.setAttribute("invheaders", invheaders);
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();

        return null;
    }

    public ActionForward loadInv(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int index = Integer.parseInt(request.getParameter("invid"));
        HttpSession session = request.getSession();
        List<Invheader> invheaders = (List<Invheader>) session.getAttribute("invheaders");
        Invheader invheader = invheaders.get(index - 1);

        log.info(invheader);
        session.setAttribute("invheader", invheader);

        return mapping.findForward(LOADINV);
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        InvheaderForm invheaderForm = (InvheaderForm) form;

        BigDecimal amount = new BigDecimal(invheaderForm.getAmount());
        BigDecimal tax = new BigDecimal(invheaderForm.getTax());

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.addHeader("Cache-Control", "no-cache"); //HTTP 1.1
        response.addHeader("Pragma", "no-cache"); //HTTP 1.0
        response.setDateHeader("Expires", 0); //prevents caching at the proxy server

        PrintWriter out = response.getWriter();

        JsonObject ob = new JsonObject();
        if (amount.intValue() == 0) {
            ob.addProperty("fail_message", "Required amount");
            out.println(ob.toString());
            out.flush();
            return null;
        } else if (tax.intValue() == 0) {
            ob.addProperty("fail_message", "Required tax");
            out.println(ob.toString());
            out.flush();
            return null;
        }

        HttpSession session = request.getSession();
        Invheader invheader = (Invheader) session.getAttribute("invheader");

        invheader.setAmount(amount);
        invheader.setTax(tax);
        invheader.setTotal(amount.add(tax));
        invheader.setNote(invheaderForm.getNote());

        inveaderDAO.edit(invheader);

        Gson gson = new Gson();
        String json = gson.toJson(invheader);
        out.println(json);
        out.flush();
        log.info("updated: " + json);
        return null;
    }
}
