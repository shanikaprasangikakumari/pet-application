package pet.information.applications.repository;
import pet.information.applications.domain.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDataRepositoryImp implements PetDataRepository {
    private final Connection connection;
    private static final String SAVE_PET = "INSERT INTO pet_details(name,height,weight) VALUES(?,?,?)";
    private static final String GET_PET_BY_PETNAME = "SELECT * FROM pet_details where name = ?";
    private static final String EDIT_PET_BY_PETNAME = "UPDATE pet_details SET name = ?, height= ?,weight=? WHERE name =?";

    public PetDataRepositoryImp(Connection connection) {
        this.connection = connection;
    }

    public void save(Pet pet) {

        try (PreparedStatement statement = connection.prepareStatement(SAVE_PET)) {
            statement.setString(1, pet.getName());
            statement.setInt(2, pet.getHeight());
            statement.setInt(3, pet.getWeight());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Pet getPetByPetName(String petName) {
        try (PreparedStatement statement = connection.prepareStatement(GET_PET_BY_PETNAME)) {
            statement.setString(1, petName);
            ResultSet resultSet = statement.executeQuery();
            List<Pet> pets = getPestsFromResultSet(resultSet);
            return pets.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Pet> getPestsFromResultSet(ResultSet results) throws SQLException {
        List<Pet> pets = new ArrayList<>();
        while (results.next()) {
            Pet pet = new Pet();
            pet.setId(results.getInt(1));
            pet.setName(results.getString(2));
            pet.setHeight(results.getInt(3));
            pet.setWeight(results.getInt(4));
            pets.add(pet);
        }
        return pets;
    }

    public void editPetByPetName(Pet pet){
        try (PreparedStatement statement = connection.prepareStatement(EDIT_PET_BY_PETNAME)) {

            statement.setString(1, pet.getName());
            statement.setInt(2, pet.getHeight());
            statement.setInt(3, pet.getWeight());
            statement.setString(4, pet.getNewName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
