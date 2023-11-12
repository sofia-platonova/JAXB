package jaxb.test;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: User
 * Date: 17.01.2022
 * Time: 19:43
 */

import jaxb.model.Department;
import jaxb.model.Employee;
import jaxb.model.Organization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TextExample {
    private static final String XML_FILE = "dept-info.xml";

    public static void main(String[] args) {

        Employee emp1 = new Employee("E01", "Tom", null);
        Employee emp2 = new Employee("E02", "Mary", "E01");
        Employee emp3 = new Employee("E03", "John", null);

        Employee emp4 = new Employee("E04", "Lisa", null);
        Employee emp5 = new Employee("E05", "Matt", "E01");
        Employee emp6 = new Employee("E06", "Will", null);

        List<Employee> list1 = new ArrayList<Employee>();
        List<Employee> list2 = new ArrayList<Employee>();
        List<Employee> list3 = new ArrayList<Employee>();
        list1.add(emp1);
        list1.add(emp2);

        list2.add(emp3);
        list2.add(emp4);

        list3.add(emp5);
        list3.add(emp6);

        Department dept1 = new Department("D01", "ACCOUNTING", "NEW YORK");
        Department dept2 = new Department("D02", "MARKETING", "LONDON");
        Department dept3 = new Department("D03", "MANAGEMENT", "MOSCOW");

        List<Department> list11 = new ArrayList<Department>();
        list11.add(dept1);
        list11.add(dept2);
        list11.add(dept3);

        dept1.setEmployees(list1);
        dept2.setEmployees(list2);
        dept3.setEmployees(list3);

        Organization org = new Organization("ItCompany", list1.size());
        org.setDepartments(list11);

        try {
            // create JAXB context and instantiate marshaller
            JAXBContext context = JAXBContext.newInstance(Organization.class);

            // (1) Marshaller : Java Object to XML content.
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(org, System.out);

            // Write to File
            File outFile = new File(XML_FILE);
            m.marshal(org, outFile);

            System.err.println("Write to file: " + outFile.getAbsolutePath());

            // (2) Unmarshaller : Read XML content to Java Object.
            Unmarshaller um = context.createUnmarshaller();

            // XML file create before.

            Organization orgFromFile1 = (Organization)  um.unmarshal(new FileReader(
                    XML_FILE));
            List<Department> deps = orgFromFile1.getDepartments();
            for (Department dep : deps) {
                System.out.println("Department: " + dep.getDeptName());
                List<Employee> emps = dep.getEmployees();
                for (Employee emp : emps) {
                    System.out.println("Employee: " + emp.getEmpName());
                };
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


