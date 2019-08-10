package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

    @Override
    public Set<Visit> findAll() {
        return null;
    }

    @Override
    public Visit findById(Long id) {
        return null;
    }

    @Override
    public Visit save(Visit visit) {

        if(visit.getPet() == null ||  visit.getPet().getOwner() == null
            || visit.getPet().getId() == null){
            throw new RuntimeException("Invalid Visit");
        }
        return super.save(visit);
    }

    @Override
    public void delete(Visit visit) {
        super.deleteByObject(visit);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }
}
