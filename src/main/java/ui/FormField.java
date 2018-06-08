package ui;

import java.util.Map;

import static util.CheckUtil.isNotNull;

public class FormField {
    private String label;
    private String value;
    private FormFieldTypes type;
    private String tag = "input";
    private Map<String, String> attr;

    public Node getNode() {
        return new Node(tag, value, "", type.getType(), attr);
    }

    public String getLabel() {
        return label;
    }

    public FormField withLabel(final String label) {
        isNotNull(label);
        this.label = label;
        return this;
    }

    public String getValue() {
        return value;
    }

    public FormField withValue(final String value) {
        isNotNull(value);
        this.value = value;
        return this;
    }

    public FormFieldTypes getType() {
        return type;
    }

    public FormField setType(final FormFieldTypes type) {
        isNotNull(type);
        this.type = type;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public FormField withTag(final String tag) {
        isNotNull(tag);
        this.tag = tag;
        return this;
    }

    public Map<String, String> getAttr() {
        return attr;
    }

    public FormField withAttr(final Map<String, String> attr) {
        isNotNull(attr);
        this.attr = attr;
        return this;
    }
}
