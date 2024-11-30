package com.example.delivery.delivery_backend.service;

import com.example.delivery.delivery_backend.model.Dish;
import com.example.delivery.delivery_backend.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Dish addDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public Dish getDishById(Long id) {
        Optional<Dish> dishOptional = dishRepository.findById(id);
        return dishOptional.orElse(null);
    }

    public Dish updateDish(Long id, Dish dishDetails) {
        Optional<Dish> dish = dishRepository.findById(id);
        if (dish.isPresent()) {
            Dish existingDish = dish.get();
            existingDish.setName(dishDetails.getName());
            existingDish.setDescription(dishDetails.getDescription());
            existingDish.setPrice(dishDetails.getPrice());
            existingDish.setImage(dishDetails.getImage());
            return dishRepository.save(existingDish);
        }
        return null;
    }

    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }
}