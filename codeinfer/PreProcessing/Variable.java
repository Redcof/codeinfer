/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeinfer.PreProcessing;

/**
 *
 * @author soumen
 */
public class Variable {
    private String TYPE = new String();
    private String NAME = new String();

    public Variable() {
        this.NAME = "";
        this.TYPE = "";
    }
    
    public boolean equalsName(String name)
    {
        return this.NAME.equals(name);
    }
    
    public String returnType()
    {
        return this.TYPE;
    }
    
    public Variable(String type,String name)
    {
        this.TYPE = type;
        this.NAME = name;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
}
