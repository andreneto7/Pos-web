/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.transform;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author itakenami
 */
public class Exclude {

    private Map<String, Boolean> exclude;

    public Exclude() {
        exclude = new HashMap<String, Boolean>();
    }

    public Exclude exclude(String campo) {
        exclude.put(campo, Boolean.TRUE);
        return this;
    }
    
    public Map<String, Boolean> getExclude(){
        return exclude;
    }

    
}
