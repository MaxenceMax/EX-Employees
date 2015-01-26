package com.myapp.struts.action;

import org.apache.struts.action.Action;
import com.myapp.struts.model.IModel;
/**
 *
 * @author Nicolas
 */
public class SuperAction extends Action{
    private IModel model = null;

    public void setIModel(IModel m) {
        this.model = m;
    }

    public IModel getIModel() {
        return this.model;
    }
}
