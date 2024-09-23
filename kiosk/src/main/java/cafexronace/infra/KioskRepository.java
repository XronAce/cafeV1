package cafexronace.infra;

import cafexronace.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "kiosks", path = "kiosks")
public interface KioskRepository
    extends PagingAndSortingRepository<Kiosk, Long> {}
