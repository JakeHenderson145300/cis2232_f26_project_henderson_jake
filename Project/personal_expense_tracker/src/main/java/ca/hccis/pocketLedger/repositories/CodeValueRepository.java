package ca.hccis.pocketLedger.repositories;

import ca.hccis.pocketLedger.jpa.entity.CodeValue;
import ca.hccis.pocketLedger.jpa.entity.CodeValueId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeValueRepository extends CrudRepository<CodeValue, CodeValueId> {
}