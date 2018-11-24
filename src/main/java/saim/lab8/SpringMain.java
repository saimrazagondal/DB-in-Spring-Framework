package saim.lab8;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class SpringMain {
    Scanner scan = new Scanner(System.in);
    int choice , id;
    person per;

    /*
     * Constructor
     */
    public SpringMain() {
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        applicationContext.registerShutdownHook();
        DAOImp hibernateDaoImp = (DAOImp) applicationContext.getBean("hibernateDaoImp");

        System.out.println("What would you like to do? Please enter number: \n1-Register:\n2-Retrieve:\n3-Update:\n4-Delete:");
        choice = scan.nextInt();
        scan.nextLine();

            switch (choice) {
                case 1:
                    per = getInfo();
                    hibernateDaoImp.register(per);
                    break;

                case 2:
                    System.out.println("Please enter corresponding id: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    per = hibernateDaoImp.retrieve(id);
                    printPerson(per);
                    break;

                case 3:
                    System.out.println("Enter id of person: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    per = getInfo();
                    per.setId(id);
                    hibernateDaoImp.update(per);
                    break;

                case 4:
                    System.out.println("Please enter corresponding id: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    hibernateDaoImp.delete(id);
                    break;

                default:
                    System.out.println("Wrong value entered!\nExiting application...\n\n");
            }

        applicationContext.close();
    }



    /*
     * Get information about a person and return an object
     */
    public person getInfo(){
        String name, fatherName, organization, mobile, username, password;

        System.out.println("Enter name: ");
        name = scan.nextLine();
        System.out.println("Enter father name: ");
        fatherName = scan.nextLine();
        System.out.println("Enter organization: ");
        organization = scan.nextLine();
        System.out.println("Enter mobile: ");
        mobile = scan.nextLine();
        System.out.println("Enter username: ");
        username = scan.nextLine();
        System.out.println("Enter password: ");
        password = scan.nextLine();

        return new person(name, fatherName, organization, mobile, username, password);
    }


    /*
     * Takes in a person object
     * Prints out the person object attributes using getter methods
     */
    public void printPerson(person per){
        System.out.println("\nName: " + per.getName() +
                "\nFather Name: " + per.getFatherName() +
                "\nOrganization: " + per.getOrganization() +
                "\nMobile: " + per.getMobile() +
                "\nUsername: " + per.getUsername() +
                "\nPassword: " + per.getPassword() + "\n\n");
    }



    /*
     * Main Method
     */
    public static void main(String[] args) {
        new SpringMain();
    }
}
