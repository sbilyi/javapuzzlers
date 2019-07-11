package trywith.resource;

import trywith.CloseEventListener;
import trywith.Resource;

public class RegularAutoClosableResource extends Resource {


    private CloseEventListener closeEventListener;

    public RegularAutoClosableResource(CloseEventListener closeEventListener) {
        super(Resource.STATUS_OPEN);
        this.closeEventListener = closeEventListener;
    }

    @Override
    public void close() throws Exception {
        closeEventListener.send(RegularAutoClosableResource.class.getCanonicalName());
        super.close();
    }
}
