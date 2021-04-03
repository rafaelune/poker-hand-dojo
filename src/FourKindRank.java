import java.util.List;

public class FourKindRank extends BasePokerRank implements PokerRank {

    @Override
    public PokerHandResult handle(List<Card> cards) {
        if (super.hasNumberOfKind(cards, 4)) {
            return PokerHandResult.FOUR_KIND;
        }
        return super.handle(cards);
    }
}
