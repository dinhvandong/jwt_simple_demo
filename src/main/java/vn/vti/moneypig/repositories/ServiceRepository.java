package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.Service;
import vn.vti.moneypig.models.User;

public interface ServiceRepository extends MongoRepository<Service, Long>
{
}
