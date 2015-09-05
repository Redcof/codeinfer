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
public class StructureObjectList {
    private String StructureName;
    private String ObjectList[];

    public StructureObjectList(String ClassName, String[] ObjectList) {
        this.StructureName = ClassName;
        this.ObjectList = ObjectList;
    }

    public String getStructureName() {
        return StructureName;
    }

    public void setStructureName(String StructureName) {
        this.StructureName = StructureName;
    }

    public String[] getObjectList() {
        return ObjectList;
    }

    public void setObjectList(String[] ObjectList) {
        this.ObjectList = ObjectList;
    }
}
