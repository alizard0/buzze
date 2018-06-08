package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Node {

    private String id, name, clazz, style, tag, value, type;
    private Map<String, String> attr;
    private List<Node> nodes;

    public Node(final String tag) {
        this.tag = tag;
        this.nodes = new ArrayList<>();
    }

    public Node(final String tag, final String value) {
        this.tag = tag;
        this.nodes = new ArrayList<>();
        this.value = value;
    }

    public Node(final String tag, final String value, final String clazz, final String type, final Map<String, String> attr) {
        this.tag = tag;
        this.nodes = new ArrayList<>();
        this.value = value;
        this.clazz = clazz;
        this.type = type;
        this.attr = attr;
    }

    public Node(final String tag, final String id, final String name, final String clazz, final String type,
                final String style, final String value, final Map<String, String> attr) {
        this.tag = tag;
        this.id = id;
        this.name = name;
        this.clazz = clazz;
        this.style = style;
        this.nodes = new ArrayList<>();
        this.value = value;
        this.type = type;
        this.attr = attr;
    }

    private String getHTML(final String child) {
        String tagAux = "<" + tag;
        if (id != null && id.length() > 0) tagAux += " id='" + id + "'";
        if (name != null && name.length() > 0) tagAux += " name='" + name + "'";
        if (clazz != null && clazz.length() > 0) tagAux += " class='" + clazz + "'";
        if (style != null && style.length() > 0) tagAux += " style='" + style + "'";
        if (attr != null && attr.size() > 0) {
            for (String attribute : attr.keySet()) {
                tagAux += " " + attribute + "='" + attr.get(attribute) + "'";
            }
        }
        String innerContent = "";
        if (child != null && child.length() > 0) {
            if (value != null && value.length() > 0) {
                innerContent += value + child;
            } else {
                innerContent += child;
            }
        } else if (value != null && value.length() > 0) {
            innerContent += value;
        }
        return tagAux + ">" + innerContent + "</" + tag + ">";
    }

    public String getHTMLTree() {
        return generateHTML(this);
    }

    public Node withNode(final Node node) {
        this.nodes.add(node);
        return this;
    }

    public boolean removeNode(final Node node) {
        return this.nodes.remove(node);
    }

    private String generateHTML(final Node node) {
        if (node.nodes.size() == 0) return node.getHTML("");
        String childrenHtml = "";
        for (Node child : node.nodes) {
            childrenHtml += generateHTML(child);
        }
        return node.getHTML(childrenHtml);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void withClazz(String clazz) {
        this.clazz += " " + clazz;
    }

    public Map<String, String> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, String> attr) {
        this.attr = attr;
    }
}
