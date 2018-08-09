/*
 * Copyright (c) Copyright 2010-2018 hSenid Mobile Solutions (Pvt) Limited.
 *               All Rights Reserved.
 *
 *               These materials are unpublished, proprietary, confidential source code of
 *               hSenid Mobile Solutions (Pvt) Limited and constitute a TRADE SECRET
 *               of hSenid Mobile Solutions (Pvt) Limited.
 *
 *               hSenid Mobile Solutions (Pvt) Limited retains all title to and intellectual
 *               property rights in these materials.
 */

package pet.information.applications.executor;

import pet.information.applications.domain.Pet;
import pet.information.applications.domain.PetOwner;
import pet.information.applications.repository.OwnerDataRepository;
import pet.information.applications.repository.OwnerDataRepositoryImp;
import pet.information.applications.repository.PetDataRepository;
import pet.information.applications.repository.PetDataRepositoryImp;
import java.sql.Connection;
import java.util.Scanner;

public class PetActionExecutorImpl implements PetActionExecutor {
    private final PetDataRepository petDataRepository;
    private final OwnerDataRepository ownerDataRepository;
    private final Scanner scanner;

    public PetActionExecutorImpl(Connection connection, Scanner scanner) {
        this.petDataRepository = new PetDataRepositoryImp(connection);
        this.ownerDataRepository = new OwnerDataRepositoryImp(connection);
        this.scanner = scanner;
    }

    @Override
    public void executeAction(int actionId) {
        if (actionId == 1) {
            this.savePetDetails();
        } else if (actionId == 2) {
            this.editPetDetails();
        } else if (actionId == 3) {
            this.searchPetDetails();
        } else if (actionId == 4) {
            this.searchOwnerDetails();
        } else {
            System.out.println("Enter valid number to select actionId");
        }
    }

    public void savePetDetails(){
        Pet pet= new PetObject(scanner).petObject();
        petDataRepository.save(pet);
    }

    public void editPetDetails(){
        Pet pet= new PetObject(scanner).petObject();
        System.out.println("Enter Id you want to replace with ");
        int petId = scanner.nextInt();
        pet.setId(petId);
        petDataRepository.editPetByPetName(pet);
    }

    public void searchPetDetails(){
        System.out.println("Enter name ");
        String searchPetName = scanner.next();
        Pet retrievedUser = petDataRepository.getPetByPetName(searchPetName);
        System.out.println("---- Found the Pet ---");
        System.out.println(retrievedUser);
    }

    public void searchOwnerDetails(){
        System.out.println("Enter pet id ");
        int searchOwnerId = scanner.nextInt();
        PetOwner retrievedUser = ownerDataRepository.getOwnerDetailsByPetId(searchOwnerId);
        System.out.println("---- Found the owner details of the owner ---");
        System.out.println(retrievedUser);
    }
}
