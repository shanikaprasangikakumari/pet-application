package pet.information.applications.repository;
import pet.information.applications.domain.Pet;

public interface PetDataRepository {
    void save(Pet pet);

    //Pet getUserById(int id);

    Pet getPetByPetName(String petName);

    void editPetByPetName(Pet pet);
}
