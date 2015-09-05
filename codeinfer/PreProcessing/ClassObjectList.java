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
public class ClassObjectList {
    private String ClassName;
    private String ObjectList[];

    public ClassObjectList(String ClassName, String[] ObjectList) {
        this.ClassName = ClassName;
        this.ObjectList = ObjectList;
    }

    ClassObjectList() {
       ClassName = "";
       ObjectList = null;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String ClassName) {
        this.ClassName = ClassName;
    }

    public String[] getObjectList() {
        return ObjectList;
    }

    public void setObjectList(String[] ObjectList) {
        this.ObjectList = ObjectList;
    }
    
}
