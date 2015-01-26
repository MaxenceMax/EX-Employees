package com.myapp.struts.action;

import com.myapp.struts.bean.Employe;
import com.myapp.struts.formbean.EmployeForm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;


public class EditEmployeAction extends SuperAction {
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
        target = "login";
        ActionMessages errors = new ActionMessages();

        errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("errors.login.required"));

        // Retourner les erreurs eventuelles au formulaire
        // d'origine.
        if (!errors.isEmpty()) {

          saveErrors(request, errors);
        }
        // Transmettre la requete a la vue appropriee
        return (mapping.findForward(target));
    }

    if ( isCancelled(request) ) {

      // Action annulee
      return (mapping.findForward(target));
    }

    try {
        EmployeForm eForm = (EmployeForm) form;
        Employe e = new Employe();
        e.setUsername(eForm.getUsername());
        e.setPassword(eForm.getPassword());
        e.setDepid(Integer.parseInt(eForm.getDepid()));
        e.setEmail(eForm.getEmail());
        e.setPhone(e.getPhone());
        e.setRoleid(Integer.parseInt(eForm.getRoleid()));
        e.setName(eForm.getName());
        getModel().updateUser(e);
    }
    catch (Exception e) {

      target = "error";
      ActionMessages errors = new ActionMessages();

      errors.add(ActionMessages.GLOBAL_MESSAGE,
        new ActionMessage("errors.database.error",
        e.getMessage()));

      // Signaler les erreurs eventuelles
      if (!errors.isEmpty()) {

        saveErrors(request, errors);
      }
    }
    // Transmettre la requete a la vue appropriee
    return (mapping.findForward(target));
  }
}