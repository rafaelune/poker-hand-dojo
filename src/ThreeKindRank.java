import java.util.List;

public class ThreeKindRank extends BasePokerRank implements PokerRank {
    @Override
    public PokerHandResult handle(List<Card> cards) {
        if (super.hasNumberOfKind(cards, 3)) {
            return PokerHandResult.THREE_KIND;
        }
        return super.handle(cards);
    }
}
