package ui;

import javafx.util.Pair;
import util.CheckUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NavbarFactory {

    private List<Pair<String, String>> navbar;
    private String brandName;

    public NavbarFactory(final String brandName, final Pair<String, String>... options) {
        CheckUtil.isNotNull(brandName);
        this.brandName = brandName;
        navbar = new ArrayList<>();
        for (Pair<String, String> option : options) {
            navbar.add(option);
        }
    }

    public Node getNavbar() {
        Node navbar = Bootstrap.navbar.getNewClass();
        navbar.setStyle("width: 80%; margin-top:2%; margin-left:10%");
        Node brand = Bootstrap.navbarBrand.getNewClass();
        brand.setValue(brandName);
        navbar.withNode(brand);
        Node toggler = Bootstrap.navbarToggler.getNewClass();
        Node collapse = Bootstrap.navbarCollapse.getNewClass();
        Node items = Bootstrap.navbarItemList.getNewClass();
        for (Pair<String, String> navOpt : this.navbar) {
            Node itemValue = Bootstrap.navbarItemLink.getNewClass();
            itemValue.setValue(navOpt.getKey());
            itemValue.setAttr(new HashMap<>());
            itemValue.getAttr().put("href", navOpt.getValue());
            Node item = Bootstrap.navbarItem.getNewClass().withNode(itemValue);
            items.withNode(item);
        }
        collapse.withNode(items);
        navbar.withNode(toggler).withNode(collapse);
        return navbar;
    }

}
