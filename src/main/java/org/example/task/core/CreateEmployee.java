package org.example.task.core;


// final
public interface CreateEmployee<ID> {

    public ID getCreateID();

    /**
     *
     * @return '公司/部门/姓名/职务'
     */
    public String getCreateName();

    public String getCreateSimpleName();
}
