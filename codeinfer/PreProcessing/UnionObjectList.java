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
public class UnionObjectList {
    private String UnionName;
    private String ObjectList[];

    public UnionObjectList(String ClassName, String[] ObjectList) {
        this.UnionName = ClassName;
        this.ObjectList = ObjectList;
    }

    public String getUnionName() {
        return UnionName;
    }

    public void setUnionName(String UnionName) {
        this.UnionName = UnionName;
    }

    public String[] getObjectList() {
        return ObjectList;
    }

    public void setObjectList(String[] ObjectList) {
        this.ObjectList = ObjectList;
    }
}
