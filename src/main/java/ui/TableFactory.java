package ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableFactory {
    private Node table;
    private String tag = "table";
    private final String id;
    private final String name;
    private final String style;
    private final Map<String, String> attr;

    public TableFactory(final String id, final String name, final String clazz, final String style,
                        final Map<String, String> attr, final List<String> tableColumns, final List<TableRow> tableRows) {
        this.id = id;
        this.name = name;
        this.style = style;
        this.attr = attr;

        this.table = new Node(tag, id, name, clazz, style, "", "", attr);
        this.table.withNode(addTableColumns(tableColumns));
        this.table.withNode(addTableRows(tableRows));
    }

    private Node addTableRows(List<TableRow> tableRows) {
        Node tbody = new Node("tbody");
        for (TableRow row : tableRows) {
            Node tr = new Node("tr");
            for (String value : row.getValues()) {
                Node td = new Node("td");
                td.setValue(value);
                tr.withNode(td);
            }
            tbody.withNode(tr);
        }
        return tbody;
    }

    private Node addTableColumns(final List<String> tableColumns) {
        Node thead = new Node("thead");
        Node tr = new Node("tr");
        for (String col : tableColumns) {
            Node th = new Node("th");
            Map<String, String> attr = new HashMap<>();
            attr.put("scope", "col");
            th.setValue(col);
            th.setAttr(attr);
            tr.withNode(th);
        }
        thead.withNode(tr);
        return thead;
    }

    public Node getTable() {
        return this.table;
    }
}
