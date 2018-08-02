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

package pet.information.applications.repository;

import pet.information.applications.domain.PetOwner;
import pet.information.applications.repository.PetDataRepository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OwnerDataRepositoryImp implements OwnerDataRepository {


     private final Connection connection;

    private static final String GET_PET_BY_PET_ID = "select pet_owner.name,pet_owner.age from pet_details,pet_owner where pet_details.Id=? and pet_owner.ownerId=pet_details.ownerId";


    public OwnerDataRepositoryImp(Connection connection) {
        this.connection = connection;
    }
    public PetOwner getOwnerDetailsByPetId(int petId) {
        try (PreparedStatement statement = connection.prepareStatement(GET_PET_BY_PET_ID)) {

            statement.setInt(1, petId);
            ResultSet resultSet = statement.executeQuery();
            List<PetOwner> owners = getPestsFromResultSet(resultSet);
            return owners.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        }

    public List<PetOwner> getPestsFromResultSet(ResultSet results) throws SQLException {
            List<PetOwner> owners  = new ArrayList<>();
            while (results.next()) {
                PetOwner owner =new PetOwner();
                owner.setName(results.getString(1));
                owner.setAge(results.getInt(2));
                owners.add(owner);
            }
            return owners;
        }

    }

