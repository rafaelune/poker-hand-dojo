import java.util.List;


public class PokerHandEvaluator {

	private final PokerRank straightFlushHandler;

	public PokerHandEvaluator() {
		OnePairRank onePairRank = new OnePairRank();

		TwoPairsRank twoPairsRank = new TwoPairsRank();
		twoPairsRank.setNextHandler(onePairRank);

		ThreeKindRank threeKindRank = new ThreeKindRank();
		threeKindRank.setNextHandler(twoPairsRank);

		StraightRank straightRank = new StraightRank();
		straightRank.setNextHandler(threeKindRank);

		FlushRank flushRank = new FlushRank();
		flushRank.setNextHandler(straightRank);

		FullHouseRank fullHouseRank = new FullHouseRank();
		fullHouseRank.setNextHandler(flushRank);

		PokerRank fourKindHandler = new FourKindRank();
		fourKindHandler.setNextHandler(fullHouseRank);

		straightFlushHandler = new StraightFlushRank();
		straightFlushHandler.setNextHandler(fourKindHandler);
	}

	/**
	 * Checks poker hand rank.
	 * @param cards of a poker hand.
	 * @return PokerHandResult
	 * @throws InvalidPokerHandException
	 */
	public PokerHandResult checkHand(List<Card> cards) throws InvalidPokerHandException {
		if (cards == null || cards.size() < 5) {
			throw new InvalidPokerHandException();
		}
		return straightFlushHandler.handle(cards);
	}
}
