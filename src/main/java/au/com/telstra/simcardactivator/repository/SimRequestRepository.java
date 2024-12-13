package au.com.telstra.simcardactivator.repository;

import au.com.telstra.simcardactivator.models.SimRequest;
import org.springframework.data.repository.CrudRepository;

public interface SimRequestRepository extends CrudRepository<SimRequest, Long> {


    SimRequest findByID(long ID);
}
