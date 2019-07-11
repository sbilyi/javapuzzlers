package trywith.resource;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import trywith.CloseEventListener;
import trywith.Resource;

public class FailToOpenResource extends Resource {

    private CloseEventListener closeEventListener;

    public FailToOpenResource(CloseEventListener closeEventListener) {
        super(Resource.STATUS_OPEN);
        this.closeEventListener = closeEventListener;
        throw new NotImplementedException();
    }

    @Override
    public void close() throws Exception {
        closeEventListener.send(this);
        super.close();
    }
}
