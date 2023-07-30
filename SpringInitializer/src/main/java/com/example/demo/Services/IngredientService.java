package com.example.demo.Services;
import com.example.demo.Entities.Ingredient;
import com.example.demo.Repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient updateIngredient(Ingredient ingredient) {
        if (ingredient.getId() == null) {
            return ingredientRepository.save(ingredient);
        } else {
            Optional<Ingredient> existingIngredientOptional = ingredientRepository.findById(ingredient.getId());
            if (existingIngredientOptional.isPresent()) {
                Ingredient existingIngredient = existingIngredientOptional.get();
                existingIngredient.setName(ingredient.getName());
                existingIngredient.setVegetarian(ingredient.isVegetarian());
                existingIngredient.setVegan(ingredient.isVegan());
                existingIngredient.setGlutenFree(ingredient.isGlutenFree());
                existingIngredient.setLactoseFree(ingredient.isLactoseFree());
                return ingredientRepository.save(existingIngredient);
            } else {
                throw new IllegalArgumentException("Ingredient with ID " + ingredient.getId() + " not found.");
            }
        }
    }
    public void deleteIngredientById(Long id) {
        ingredientRepository.deleteById(id);
    }
}
