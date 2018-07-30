package pet.information.applications;

import pet.information.applications.domain.Pet;
import pet.information.applications.repository.PetDataRepository;
import pet.information.applications.repository.PetDataRepositoryImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class SimpleApplication {
    public static void main(String args[]) throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pet", "shanika", "beyondm");
        PetDataRepository repository = new PetDataRepositoryImp(connection);

        Scanner scanner =new Scanner(System.in);

        System.out.println("Press 1 = Add pet Details\nPress 2 = Edit pet Details by name\nPress 3 = search details by name");
        int option=scanner.nextInt();

        if(option==1) {
            //add pet details
            System.out.println("Add Pet details");
            System.out.println("-------------------------------");
            System.out.println("Enter name ");
            String petName = scanner.next();

            System.out.println("Enter height ");
            int petHeight = scanner.nextInt();

            System.out.println("Enter weight ");
            int petWeight = scanner.nextInt();

            Pet pet = new Pet();
            pet.setName(petName);
            pet.setHeight(petHeight);
            pet.setWeight(petWeight);


            repository.save(pet);
        }else if(option==2) {
            //Edit pet details
            System.out.println("Edit Pet details");
            System.out.println("-------------------------------");

            System.out.println("Enter name ");
            String newPetName = scanner.next();

            System.out.println("Enter height ");
            int newOPetHeight = scanner.nextInt();

            System.out.println("Enter weight ");
            int newPetWeight = scanner.nextInt();

            System.out.println("Name you want to replace with ");
            String oldPetName = scanner.next();

            Pet newPet = new Pet();
            newPet.setName(newPetName);
            newPet.setHeight(newOPetHeight);
            newPet.setWeight(newPetWeight);
            newPet.setNewName(oldPetName);


            repository.editPetByPetName(newPet);
        }else if(option==3) {
            //Search pet details by name
            System.out.println("Search Pet details");
            System.out.println("-------------------------------");
            System.out.println("Enter name ");
            String searchPetName = scanner.next();

            Pet retrievedUser = repository.getPetByPetName(searchPetName);
            System.out.println("---- Found the Pet ---");
            System.out.println(retrievedUser);
        }else{
            System.out.println("Enter valid numberto select option");
        }
    }
}
