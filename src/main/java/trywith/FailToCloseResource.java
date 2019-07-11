package trywith;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import trywith.CloseEventListener;
import trywith.Resource;

public class FailToCloseResource extends Resource {
    public FailToCloseResource(CloseEventListener closeEventListener) {
        super(Resource.STATUS_OPEN);
    }

    @Override
    public void close() throws Exception {
        throw new NotImplementedException();
    }
}
