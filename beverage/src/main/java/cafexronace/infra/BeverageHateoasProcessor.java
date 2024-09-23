package cafexronace.infra;

import cafexronace.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeverageHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Beverage>> {

    @Override
    public EntityModel<Beverage> process(EntityModel<Beverage> model) {
        return model;
    }
}
