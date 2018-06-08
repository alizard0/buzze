package ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormFactory {
    private final String id;
    private final String name;
    private final String style;
    private final Map<String, String> attr;
    private Node form;
    private String tag = "form";

    public FormFactory(final String id, final String name, final String clazz, final String style, final String action,
                       final Map<String, String> attr, final List<FormField> fields) {
        this.id = id;
        this.name = name;
        this.style = style;

        if(attr == null) {
            this.attr = new HashMap<>();
        } else {
            this.attr = attr;
        }
        this.attr.put("action", action);

        this.form = new Node(tag, id, name, clazz, style, "", "", attr);
        for(FormField field: fields){
            this.form.withNode(field.getNode());
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return style;
    }

    public Map<String, String> getAttr() {
        return attr;
    }

    public Node getForm() {
        return form;
    }

    public String getTag() {
        return tag;
    }
}
