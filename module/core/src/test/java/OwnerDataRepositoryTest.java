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

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pet.information.applications.domain.PetOwner;
import pet.information.applications.repository.OwnerDataRepository;
import pet.information.applications.repository.OwnerDataRepositoryImp;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class OwnerDataRepositoryTest {
    private static OwnerDataRepository ownerDataRepository;

    @BeforeClass
    public  void init() throws Exception {
        InputStream input = null;
        Properties prop = new Properties();
        input = new FileInputStream("/home/shanika/MavenProjects/pet-application/module/core/src/test/resources/config.properties");
        prop.load(input);
        Connection connection = DriverManager.getConnection(prop.getProperty("port")+prop.getProperty("database"),prop.getProperty("user"),prop.getProperty("password"));
        ownerDataRepository = new OwnerDataRepositoryImp(connection);
    }

    @Test
    public void searchPetByOwnerTest() {
        PetOwner pet = ownerDataRepository.getOwnerDetailsByPetId(48);
        assertNotNull(pet);
        assertEquals("Nimmi", pet.getName());
        assertEquals(27, pet.getAge());
    }
}
