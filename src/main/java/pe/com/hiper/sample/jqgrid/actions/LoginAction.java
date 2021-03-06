package pe.com.hiper.sample.jqgrid.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pe.com.hiper.sample.jqgrid.forms.LoginForm;

/**
 *
 * @author Cesardl
 */
public class LoginAction extends Action {

    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return an action forward
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // extract user data
        LoginForm formBean = (LoginForm) form;
        String name = formBean.getName();
        String email = formBean.getEmail();

        // perform validation
        if (name.isEmpty() || // name parameter is empty
                !email.contains("@")) { // email lacks '@'
            formBean.setError();
            return mapping.findForward(FAILURE);
        } else {
            return mapping.findForward(SUCCESS);
        }
    }
}
