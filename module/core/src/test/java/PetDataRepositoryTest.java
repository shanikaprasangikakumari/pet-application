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
import pet.information.applications.domain.Pet;
import pet.information.applications.repository.PetDataRepository;
import pet.information.applications.repository.PetDataRepositoryImp;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class PetDataRepositoryTest {
    private static PetDataRepository dataRepository;

    @BeforeClass
    public static void init() throws Exception {
        InputStream input = null;
        Properties prop = new Properties();
        input = new FileInputStream("/home/shanika/MavenProjects/pet-application/module/core/src/test/resources/config.properties");
        prop.load(input);
        Connection connection = DriverManager.getConnection(prop.getProperty("port")+prop.getProperty("database"),prop.getProperty("user"),prop.getProperty("password"));
        dataRepository = new PetDataRepositoryImp(connection);
    }

    @Test
    public void savePetDataTest() {
        Pet pet = new Pet();
        pet.setName("test");
        pet.setWeight(10);
        pet.setHeight(10);
        dataRepository.save(pet);
        Pet test = dataRepository.getPetByPetName("test");
        assertNotNull(test);
        assertEquals(10, pet.getWeight());
        assertEquals(10, pet.getHeight());
    }

    @Test
    public void searchPetByNameTest() {
        Pet pet = dataRepository.getPetByPetName("test");
        assertNotNull(pet);
        assertEquals(10, pet.getWeight());
        assertEquals(10, pet.getHeight());
    }

    @Test
    public void editPetByPetNameTest() {
        Pet pet = new Pet();
        pet.setName("editName");
        pet.setWeight(10);
        pet.setHeight(10);
        pet.setId(40);
        dataRepository.editPetByPetName(pet);
        assertNotNull(pet);
        assertEquals("editName", pet.getName());
    }
}
