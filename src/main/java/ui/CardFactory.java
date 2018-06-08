package ui;

public class CardFactory {

    private final String content;
    private final String title;

    public CardFactory(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Node getCard() {
        Node card = Bootstrap.card.getNewClass();
        card.setStyle("margin-bottom: 5%;");
        Node cardBody = Bootstrap.cardBody.getNewClass();
        Node cardTitle = Bootstrap.cardTitle.getNewClass();
        cardTitle.setValue(title);
        Node cardText = Bootstrap.cardText.getNewClass();
        cardText.setValue(content);
        cardBody.withNode(cardTitle).withNode(cardText);
        Node btn = Bootstrap.buttonLight.getNewClass();
        btn.setValue("Read more");
        cardBody.withNode(btn);
        card.withNode(cardBody);
        return card;
    }
}
