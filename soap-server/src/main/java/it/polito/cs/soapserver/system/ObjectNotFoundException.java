package it.polito.cs.soapserver.system;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String objectName, String name) {
        super("Could not find " + objectName + " with name " + name);
    }
}
