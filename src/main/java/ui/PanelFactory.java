package ui;

public class PanelFactory {
    private int width;
    private int height;
    private String backgroundColor;
    private Node inner;

    public PanelFactory() {
        this.inner = Bootstrap.containerFluid.getNewClass();
        this.inner.setStyle("padding-top:1%;");
    }

    public PanelFactory(int width) {
        this.width = width;
        this.inner = Bootstrap.containerFluid.getNewClass();
        this.inner.setStyle("padding-top:1%;");
    }

    public PanelFactory(int width, int height) {
        this.width = width;
        this.height = height;
        this.inner = Bootstrap.containerFluid.getNewClass();
        this.inner.setStyle("padding-top:1%;");
    }

    public Node getPanel() {
        Node panel = Bootstrap.div.getNewClass();
        if (width > 0 && height == 0) {
            panel.setStyle("width: " + width + "%; margin-top:1%; margin-left:10%;");
        } else if (width == 0 && height > 0) {
            panel.setStyle("height: " + height + "%; margin-top:1%; margin-left:10%;");
        } else if (width > 0 && height > 0) {
            panel.setStyle("width: " + width + "%; height: " + height + "%;  margin-top:1%; margin-left:10%;");
        }

        if (backgroundColor != null && backgroundColor.length() > 0) {
            panel.setStyle(panel.getStyle() + " background-color: " + backgroundColor + ";");
        }
        panel.withNode(inner);
        return panel;
    }

    public void withBackgroundColor(String hex) {
        this.backgroundColor = hex;
    }

    public Node getInner() {
        return inner;
    }

    public void setInner(Node inner) {
        this.inner = inner;
    }
}
