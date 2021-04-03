import java.util.List;

public class FlushRank extends StraightFlushRank implements PokerRank {
    @Override
    public PokerHandResult handle(List<Card> cards) {
        if (super.isFlush(cards)) {
            return PokerHandResult.FLUSH;
        }
        return super.handle(cards);
    }
}
