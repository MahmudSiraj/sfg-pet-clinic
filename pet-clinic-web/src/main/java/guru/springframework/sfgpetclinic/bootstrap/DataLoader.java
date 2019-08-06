package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Mahmud");
        owner1.setLastName("Siraj");
        owner1.setAddress("5 Kendal Avenue");
        owner1.setCity("Manchester");
        owner1.setTelephone("0161368986");

        Pet mahmudPet = new Pet();
        mahmudPet.setPetType(savedDogPetType);
        mahmudPet.setOwner(owner1);
        mahmudPet.setBirthDate(LocalDate.now());
        mahmudPet.setName("Charlie");
        owner1.getPets().add(mahmudPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Juan");
        owner2.setLastName("Mata");
        owner1.setAddress("5 Old Trafford");
        owner1.setCity("Salford");
        owner1.setTelephone("0161348922");

        Pet juanPet = new Pet();
        juanPet.setPetType(savedCatPetType);
        juanPet.setOwner(owner2);
        juanPet.setBirthDate(LocalDate.now());
        juanPet.setName("William");
        owner2.getPets().add(juanPet);

        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Jose");
        vet1.setLastName("Mourinho");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Arsene");
        vet2.setLastName("Wenger");

        vetService.save(vet2);

        System.out.println("Loaded vets....");

    }
}
