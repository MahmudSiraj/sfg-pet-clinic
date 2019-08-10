package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }


    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);


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
        owner2.setAddress("5 Old Trafford");
        owner2.setCity("Salford");
        owner2.setTelephone("0161348922");

        Pet juanPet = new Pet();
        juanPet.setPetType(savedCatPetType);
        juanPet.setOwner(owner2);
        juanPet.setBirthDate(LocalDate.now());
        juanPet.setName("William");
        owner2.getPets().add(juanPet);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(mahmudPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Homeless and unemployed cat");

        visitService.save(catVisit);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Jose");
        vet1.setLastName("Mourinho");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Arsene");
        vet2.setLastName("Wenger");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded vets....");
    }
}
