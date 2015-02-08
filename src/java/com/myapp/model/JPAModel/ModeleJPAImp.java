/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.model.JPAModel;

import com.myapp.struts.model.Employes;
import com.myapp.struts.model.IModel;
import com.myapp.struts.model.ModelException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

/**
 *
 * @author Nicolas
 */
public class ModeleJPAImp implements IModel{

    private EntityManagerFactory emf;
    
    public ModeleJPAImp(){
    }
    
    public void setEmf (EntityManagerFactory emf) {
        this.emf=emf;
    }
    
    public EntityManagerFactory getEmf () {
        return (this.emf);
    }
    
    @Override
    public void insertUser(Employes e) throws ModelException {
        EntityManager em;        
        try{
            em=emf.createEntityManager();
            em.persist(e);
        }catch (Exception ex){
            throw new ModelException(ex.getMessage());
        }
    }

    @Override
    public void deleteEmploye(String username) throws ModelException {
        EntityManager em;
        Employes e;
        try{
            em=emf.createEntityManager();
            if(em.find(Employes.class, username)!=null){
                e=em.find(Employes.class, username);
                em.remove(e);
            }
            else{
                System.err.println("------>Utilisateur introuvable<-----");
            }
        }catch (Exception ex){
            throw new ModelException(ex.getMessage());
        }
    }

    @Override
    public void updateUser(Employes e) throws ModelException {
        EntityManager em;
        try{
            em=emf.createEntityManager();
            em.merge(e);
        }catch (Exception ex){
            throw new ModelException(ex.getMessage());
        }
    }

    @Override
    public ArrayList getEmployes() throws ModelException {
        EntityManager em;
        Employes e;
        ArrayList employes = null;
        
        try{
            em=emf.createEntityManager();
            employes = new ArrayList(em.createNamedQuery("Employes.findAll", Employes.class).getResultList());
        }catch (Exception ex){
            throw new ModelException(ex.getMessage());
        }
        return employes;
    }

    @Override
    public Employes buildEmployeForm(String username) throws ModelException {
        Employes e=null;
        Employes form = null;
        EntityManager em;
        
        try{
            em=emf.createEntityManager();
            if(em.find(Employes.class, username)!=null){
                e=em.find(Employes.class, username);  
            }
            else{
                System.err.println("----->Utilisateur introuvable<-----");
            }
        }catch(Exception ex){
            throw new ModelException(ex.getMessage());
        }
        return e;
    }

    @Override
    public String getUser(String username, String password) throws ModelException {
        EntityManager em;
        Employes e = null;
        
        try{
            em=emf.createEntityManager();
            if (em.find(Employes.class,username)!=null){
                e = em.find(Employes.class, username);
            }
            else{
                System.err.println("----->Utilisateur non trouve<-----");
            }
        }catch (Exception ex){
            throw new ModelException(ex.getMessage());
        }
        return e.toString();
    }

    public void persist(Object object) {
        /* Add this to the deployment descriptor of this module (e.g. web.xml, ejb-jar.xml):
         * <persistence-context-ref>
         * <persistence-context-ref-name>persistence/LogicalName</persistence-context-ref-name>
         * <persistence-unit-name>EX-employeesPU</persistence-unit-name>
         * </persistence-context-ref>
         * <resource-ref>
         * <res-ref-name>UserTransaction</res-ref-name>
         * <res-type>javax.transaction.UserTransaction</res-type>
         * <res-auth>Container</res-auth>
         * </resource-ref> */
        try {
            Context ctx = new InitialContext();
            UserTransaction utx = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
            utx.begin();
            EntityManager em = (EntityManager) ctx.lookup("java:comp/env/persistence/LogicalName");
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
}
