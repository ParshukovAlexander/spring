package appline.springtest.controller;

import appline.springtest.logic.Pet;
import appline.springtest.logic.PetModel;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {

    private static final PetModel petModel = PetModel.getInstance();

    private static final AtomicInteger newId = new AtomicInteger(1);


    @PostMapping( "/creatpet")
    public String creatPet(@RequestBody Pet pet){

        petModel.add(pet,newId.getAndIncrement());

        return "Котик успешно создан)";
    }

    @GetMapping(value = "/getAll",produces = "application/json")
    public Map<Integer,Pet> getAll(){
        return  petModel.getAll();
    }

    @GetMapping(value = "/getPet",consumes = "application/json",produces = "application/json")
    public Pet getPet(@RequestBody Map<String,Integer> id){
        return petModel.getFromList(id.get("id"));
    }

    @DeleteMapping(value = "/delete",consumes = "application/json",produces = "application/json")
    public void deletePet(@RequestParam int id){
        Pet fromList = petModel.getAll().remove(id);
    }

    @PutMapping(value = "/update",consumes = "application/json",produces = "application/json")
    public void putPet(@RequestParam int id,@RequestBody Pet pet){
        Pet fromList = petModel.getAll().replace(id,pet);
    }
}
