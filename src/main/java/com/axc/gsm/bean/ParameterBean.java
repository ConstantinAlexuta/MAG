package com.axc.gsm.bean;


import com.axc.gsm.database.OracleDatabaseHelper;
import com.axc.gsm.model.Parameter;
import com.axc.gsm.model.Parameter;
import com.axc.gsm.repository.ParameterRepository;
import org.fluttercode.datafactory.impl.DataFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class ParameterBean {

    private List<Parameter> parameterList = new ArrayList<>();

    @PostConstruct
    private void postConstruct() {
            parameterList = OracleDatabaseHelper.parameterRepository().getAll();
    }

    public List<Parameter> getParameterList() {
        return parameterList;
    }

}
 
