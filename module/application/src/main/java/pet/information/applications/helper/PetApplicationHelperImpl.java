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

package pet.information.applications.helper;

import pet.information.applications.executor.PetActionExecutor;
import pet.information.applications.executor.PetActionExecutorImpl;

import java.sql.Connection;
import java.util.Scanner;

public class PetApplicationHelperImpl implements PetApplicationHelper {
    private PetActionExecutor executor;
    private final Scanner scanner;

    public PetApplicationHelperImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void init(Connection connection) {
        this.executor = new PetActionExecutorImpl(connection, scanner);
    }

    @Override
    public void startExecution() {
        System.out.println("Press 1 = Add pet Details\n" +
                "Press 2 = Edit pet Details by ID\n" +
                "Press 3 = search pet details by pet name\n" +
                "Press 4 = search owner details by pet Id");
        int actionId = scanner.nextInt();
        executor.executeAction(actionId);
    }
}
