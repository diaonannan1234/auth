package org.example.task.core.casbin;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ModelRegistry {

    private  Map<String, File> modelMap = new HashMap<>(70);

    public ModelRegistry(){
        init("examples/");
    }

    private void init(String path){
        File flie =  new File(path);
        File[] fs = flie.listFiles();
        for(File f : fs){
            if(f.isFile() && f.getName().endsWith(".conf")){
                modelMap.put(f.getName(), f);
            }
        }
    }
}
