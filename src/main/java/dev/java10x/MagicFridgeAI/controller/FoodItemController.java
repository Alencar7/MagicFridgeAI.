package dev.java10x.MagicFridgeAI.controller;

import dev.java10x.MagicFridgeAI.model.Fooditem;
import dev.java10x.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FoodItemService service;

    public FoodItemController(FoodItemService service) {
        this.service = service;
    }
    //post
    @PostMapping
    public ResponseEntity<Fooditem> criar (@RequestBody Fooditem fooditem) {
        Fooditem salvo = service.salvar(fooditem);
        return ResponseEntity.ok(salvo);
    }
    //get
    @GetMapping
    public ResponseEntity<List<Fooditem>> listar() {
        List<Fooditem> lista = service.listar();
        return ResponseEntity.ok(lista);
    }
    //update
    @PutMapping("/{id}")
    public ResponseEntity<Fooditem> atualizar(@PathVariable Long id, @RequestBody Fooditem fooditem) {
        return service.buscarPorId(id)
                .map(itemExistente -> {
                    fooditem.setId(itemExistente.getId());
                    Fooditem atualizado = service.atualizar(fooditem);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
