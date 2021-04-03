import java.util.List;

public interface PokerRank {
    void setNextHandler(PokerRank nextHandler);
    PokerHandResult handle(List<Card> cards);
}
