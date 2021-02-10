package repositories;

import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import models.Guitar;


public class GuitarRepository {
    @Inject
    private List<Guitar> guitarList = new ArrayList<>();

    public int guitarId = 0;

    public List<Guitar> findAll() {
        return guitarList;
    }

    public Optional<Guitar> findById(int id) {
        return guitarList.stream().filter(guitar -> guitar.getId() == id).findFirst();
    }

    public void delete(int id) {
        guitarList = guitarList.stream().filter(guitar -> guitar.getId() != id).collect(Collectors.toList());
    }

    public void add(Guitar guitar) {
        if (!findById(guitar.getId()).isPresent()) {
            guitar.setId(++guitarId);
            guitarList.add(guitar);
        }
    }

    public void update(Guitar guitar) {
        Optional<Guitar> guitarOptional = findById(guitar.getId());
        if (guitarOptional.isPresent()) {
            Guitar guitarToUpdate = guitarOptional.get();

            guitarToUpdate.setDescription(guitar.getDescription());
            guitarToUpdate.setTitle(guitar.getTitle());
        }
    }

}
