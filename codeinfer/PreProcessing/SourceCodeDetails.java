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
public class SourceCodeDetails {
    private String Attribute;
    private String Value;

    public SourceCodeDetails(String Attribute, String Value) {
        this.Attribute = Attribute;
        this.Value = Value;
    }

    public String getAttribute() {
        return Attribute;
    }

    public void setAttribute(String Attribute) {
        this.Attribute = Attribute;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }   
    
}
