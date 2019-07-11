package trywith;

import java.util.ArrayList;
import java.util.List;

public class CloseEventListener {
    private List<String> events = new ArrayList<>();

    public void send(Resource resource) {
        events.add(resource.getClass().getCanonicalName());
    }

    public boolean contains(String canonicalName) {
        return events.contains(canonicalName);
    }

    public void send(String canonicalName) {
        events.add(canonicalName);
    }

    public int events() {
        return events.size();
    }
}
