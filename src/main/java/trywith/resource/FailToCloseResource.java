package trywith.resource;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import trywith.CloseEventListener;
import trywith.Resource;

import java.io.Console;

public class FailToCloseResource extends Resource {
    public FailToCloseResource(CloseEventListener closeEventListener) {
        super(Resource.STATUS_OPEN);
    }

    @Override
    public void close() throws Exception {
        throw new NotImplementedException();
    }

}
