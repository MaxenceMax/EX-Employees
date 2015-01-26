package com.myapp.struts.action;

import com.myapp.struts.bean.Employe;
import com.myapp.struts.model.ModelException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;

public class GetEmployeAction extends SuperAction {
  @Override
  public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response)
    throws IOException, ServletException {

    // Cible par defaut en cas de succes
    String target = "success";

    // Teste sur l'identification de l'utilisateur
      HttpSession session = request.getSession();
      if ( session.getAttribute("USER") == null ) {

        // L'utilisateur n'est pas identifie
        target = "welcome";
        ActionMessages errors = new ActionMessages();

        errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("errors.login.required"));

        // Retourner les erreurs eventuelles au formulaire d'origine
        if (!errors.isEmpty()) {

          saveErrors(request, errors);
        }
 
    }

    if ( isCancelled(request) ) {

      // Annulation depuis la liste des employes
      return (mapping.findForward(target));
    }

    try {
        Employe e = getIModel().buildEmployeForm(request.getParameter("username"));
      
      if ( e == null ) {
      
        System.err.println("---->e est null<----");
      }
      
      if ("request".equals(mapping.getScope())) {
      
        System.err.println("---->request<----");
        request.setAttribute(mapping.getAttribute(), e);
        System.err.println("---->request<----");
      }
      else {
      
        System.err.println("---->session<----");
        session = request.getSession();
        System.err.println("---->session<----");
        session.setAttribute(mapping.getAttribute(), e);
      }
    }
    catch (ModelException e) {

      System.err.println("Setting target to error");
      System.err.println("---->" + e.getMessage() + "<----");
      target = "error";
      ActionMessages errors = new ActionMessages();

      errors.add(ActionMessages.GLOBAL_MESSAGE,
        new ActionMessage("errors.database.error", e.getMessage()));

      // Retourner les erreurs eventuelles
      if (!errors.isEmpty()) {

        saveErrors(request, errors);
      }
    }
    // Transmettre la requete a la vue appropriee
    return (mapping.findForward(target));
  }
}