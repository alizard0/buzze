package ui;

import java.util.List;

public class TableRow {

    private final List<String> values;

    public TableRow(final List<String> values) {
        this.values = values;
    }

    public List<String> getValues() {
        return values;
    }
}
