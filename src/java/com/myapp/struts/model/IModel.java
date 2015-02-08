/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.model;

import java.util.ArrayList;

/**
 *
 * @author Nicolas
 */
public interface IModel {
    public void insertUser(Employes e) throws ModelException;
    
    public void deleteEmploye(String username) throws ModelException;
    
    public void updateUser(Employes e) throws ModelException;
    
    public ArrayList getEmployes() throws ModelException;
    
    public Employes buildEmployeForm(String username) throws ModelException;
    
    public String getUser(String username, String password) throws ModelException;
}
