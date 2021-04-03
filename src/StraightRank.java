import java.util.List;

public class StraightRank extends StraightFlushRank implements PokerRank {
    @Override
    public PokerHandResult handle(List<Card> cards) {
        if (isStraight(cards)) {
            return PokerHandResult.STRAIGHT;
        }
        return super.handle(cards);
    }
}
