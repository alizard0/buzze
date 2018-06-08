package ui;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public enum Bootstrap {

    html("html"),
    head("head", "", "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\">\n<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>\n" +
            "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js\" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\" crossorigin=\"anonymous\"></script>\n" +
            "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>"),
    body("body"),
    div("div"),
    row("div", "row", ""),
    col("div", "col", ""),
    container("div", "container", ""),
    containerFluid("div", "container-fluid", ""),
    h1("h1"),
    h2("h2"),
    h3("h3"),
    h4("h4"),
    h5("h5"),
    alertPrimary("div", "alert alert-primary", ""),
    alertSecondary("div", "alert alert-secondary", ""),
    alertSuccess("div", "alert alert-success", ""),
    alertDanger("div", "alert alert-danger", ""),
    alertWarning("div", "alert alert-warning", ""),
    alertInfo("div", "alert alert-primary", ""),
    alertLight("div", "alert alert-primary", ""),
    alertDark("div", "alert alert-primary", ""),
    badgePrimary("span", "badge badge-primary", ""),
    badgeSecondary("span", "badge badge-secondary", ""),
    badgeSuccess("span", "badge badge-success", ""),
    badgeDanger("span", "badge badge-danger", ""),
    badgeWarning("span", "badge badge-warning", ""),
    badgeInfo("span", "badge badge-info", ""),
    badgeLight("span", "badge badge-light", ""),
    badgeDark("span", "badge badge-dark", ""),
    badgePillPrimary("span", "badge badge-pill badge-primary", ""),
    badgePillSecondary("span", "badge badge-pill badge-secondary", ""),
    badgePillSuccess("span", "badge badge-pill badge-success", ""),
    badgePillDanger("span", "badge badge-pill badge-danger", ""),
    badgePillWarning("span", "badge badge-pill badge-warning", ""),
    badgePillInfo("span", "badge badge-pill badge-info", ""),
    badgePillLight("span", "badge badge-pill badge-light", ""),
    badgePillDark("span", "badge badge-pill badge-dark", ""),
    buttonPrimary("button", "btn btn-primary", "", "button"),
    buttonSecondary("button", "btn btn-secondary", "", "button"),
    buttonSuccess("button", "btn btn-success", "", "button"),
    buttonDanger("button", "btn btn-danger", "", "button"),
    buttonWarning("button", "btn btn-warning", "", "button"),
    buttonInfo("button", "btn btn-info", "", "button"),
    buttonLight("button", "btn btn-light", "", "button"),
    buttonDark("button", "btn btn-dark", "", "button"),
    buttonOutlinePrimary("button", "btn btn-outline-primary", "", "button"),
    buttonOutlineSecondary("button", "btn btn-outline-secondary", "", "button"),
    buttonOutlineSuccess("button", "btn btn-outline-success", "", "button"),
    buttonOutlineDanger("button", "btn btn-outline-danger", "", "button"),
    buttonOutlineWarning("button", "btn btn-outline-warning", "", "button"),
    buttonOutlineInfo("button", "btn btn-outline-info", "", "button"),
    buttonOutlineLight("button", "btn btn-outline-light", "", "button"),
    buttonOutlineDark("button", "btn btn-outline-dark", "", "button"),
    card("div", "card", "", ""),
    cardImage("img", "card-img-top", "", ""),
    cardBody("div", "card-body", "", ""),
    cardText("p", "card-text", "", ""),
    cardTitle("h5", "card-title", "", ""),
    dropdown("div", "dropdown", "", ""),
    dropdownButton("button", "btn btn-secondary dropdown-toggle", "", "button",
            new Pair<String, String>("id", "dropdownMenuButton"), new Pair<>("data-toggle", "dropdown"),
            new Pair<String, String>("aria-haspopup", "true"), new Pair<>("aria-expanded", "false")),
    dropdownMenu("div", "dropdown-menu", "", "", new Pair<>("aria-labelledby", "dropdownMenuButton")),
    dropdownItem("a", "dropdown-item", "", "", new Pair<>("href", "#")),
    form("form"),
    formGroup("div", "form-group", ""),
    inputText("input", "", "", "text"),
    inputNumber("input", "", "", "number"),
    inputFile("input", "", "", "file"),
    navbar("nav", "navbar navbar-expand-lg navbar-light bg-light", ""),
    navbarToggler("button", "navbar-toggler", "<span class=\"navbar-toggler-icon\"></span>", "button", new Pair<>("data-toggle", "collapse"),
            new Pair<>("data-toggle", "collapse"),
            new Pair<>("data-target", "#navbarTogglerDemo03"),
            new Pair<>("aria-controls", "navbarTogglerDemo03"),
            new Pair<>("aria-expanded", "false"),
            new Pair<>("aria-label", "Toggle navigation")),
    navbarBrand("a", "navbar-brand", "", "", new Pair<>("href", "#")),
    navbarCollapse("div", "collapse navbar-collapse", "", "", new Pair<>("id", "navbarTogglerDemo03")),
    navbarItemList("ul", "navbar-nav mr-auto mt-2 mt-lg-0", ""),
    navbarItem("li", "nav-item active", ""),
    navbarItemLink("a", "nav-link", "", "");

    private String value, type;
    private String clazz, tag;
    private Map<String, String> attr;

    Bootstrap(final String tag) {
        this.tag = tag;
        this.value = "";
        this.type = "";
        this.clazz = "";
        this.attr = new HashMap<>();
    }

    Bootstrap(final String tag, final String clazz, final String value) {
        this.clazz = clazz;
        this.tag = tag;
        this.value = value;
        this.type = "";
        this.attr = new HashMap<>();
    }

    Bootstrap(final String tag, final String clazz, final String value, final String type, final Pair<String, String>... listAttr) {
        this.clazz = clazz;
        this.tag = tag;
        this.value = value;
        this.type = type;
        this.attr = new HashMap<>();
        for (Pair<String, String> pair : listAttr) {
            attr.put(pair.getKey(), pair.getValue());
        }
    }


    public Node getNewClass() {
        return new Node(tag, value, clazz, type, attr);
    }
}
