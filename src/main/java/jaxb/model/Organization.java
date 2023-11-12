package jaxb.model;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "organization")
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization {

    private String orgName;
    private int numbDepartments;

    @XmlElementWrapper(name = "departments")
    @XmlElement(name = "department")

    private List<Department> departments;

    /**
     * This default constructor is required if there are other constructors.
     */
    public Organization() {

    }

    public Organization(String orgName, int numbDepartments) {
        this.orgName= orgName;
        this.numbDepartments = numbDepartments;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getNumbDepartments() {
        return numbDepartments;
    }

    public void setNumbDepartments(int numbDepartments) {
        this.numbDepartments = numbDepartments;
    }


    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
