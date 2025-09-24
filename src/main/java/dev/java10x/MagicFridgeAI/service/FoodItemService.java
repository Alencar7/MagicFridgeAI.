package dev.java10x.MagicFridgeAI.service;

import dev.java10x.MagicFridgeAI.model.Fooditem;
import dev.java10x.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    private FoodItemRepository repository;

    public FoodItemService(FoodItemRepository repository) {
        this.repository = repository;
    }

    public Fooditem salvar(Fooditem fooditem) {
        return repository.save(fooditem);
    }

    public List<Fooditem> listar() {
        return repository.findAll();
    }

    public Optional<Fooditem> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Fooditem atualizar(Fooditem fooditem) {
        return repository.save(fooditem);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
