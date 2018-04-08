
package cn.neucloud.server.actors.service.sercurity;

public class UserPrincipal {

    private final Type type;
    private final String value;

    public UserPrincipal(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public enum Type {
        USER_NAME,
        PUBLIC_ID
    }

}
