package com.ssau.esalab;

import com.ssau.esalab.controller.CarController;
import com.ssau.esalab.controller.CarDealerController;
import com.ssau.esalab.controller.ManagerController;
import com.ssau.esalab.controller.ModelController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class HelloApplication extends Application {

    @Override
    public Set<Class<?>> getClasses(){
        final Set<Class<?>> classes=new HashSet<>();
        classes.add(CarController.class);
        classes.add(ModelController.class);
        classes.add(ManagerController.class);
        classes.add(CarDealerController.class);
        return classes;

    }
}