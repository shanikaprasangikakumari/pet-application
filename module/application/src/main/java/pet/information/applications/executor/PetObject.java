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
import java.util.Scanner;

public class PetObject implements CaptureUserInputs{
    private final Scanner scanner;
    private static String petName;
    private static int petHeight;
    private static int petWeight;

    public PetObject(Scanner scanner) {
        this.scanner = scanner;

    }

    @Override
    public void getUserInputs() {
        System.out.println("Enter name ");
        petName = scanner.next();
        System.out.println("Enter height ");
        petHeight = scanner.nextInt();
        System.out.println("Enter weight ");
        petWeight = scanner.nextInt();
    }

    public Pet petObject(){
        new PetObject(scanner).getUserInputs();
        Pet pet = new Pet();
        pet.setName(petName);
        pet.setHeight(petHeight);
        pet.setWeight(petWeight);
        return pet;
    }
}
