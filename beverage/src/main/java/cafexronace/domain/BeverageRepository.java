package cafexronace.domain;

import cafexronace.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel="beverages", path="beverages")
public interface BeverageRepository extends PagingAndSortingRepository<Beverage, >{
}