package trywith;

public class Resource implements AutoCloseable{
    public static final String STATUS_OPEN = "open";

    private String status;

    public Resource(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void close() throws Exception {
        status = null;
    }

}
