import java.util.List;

public class FullHouseRank extends BasePokerRank implements PokerRank{
    @Override
    public PokerHandResult handle(List<Card> cards) {
        if (super.hasNumberOfKind(cards, 3) && super.hasNumberOfKind(cards, 2)) {
            return PokerHandResult.FULL_HOUSE;
        }
        return super.handle(cards);
    }
}
