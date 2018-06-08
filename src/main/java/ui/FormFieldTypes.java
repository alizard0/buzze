package ui;

public enum FormFieldTypes {
    text("text"),
    password("password"),
    submit("submit"),
    reset("reset"),
    radio("radio"),
    checkbox("checkbox"),
    button("button"),
    number("number");

    private final String type;

    FormFieldTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
