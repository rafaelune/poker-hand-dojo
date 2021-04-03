import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class PokerHandEvaluatorTest {
	
	private PokerHandEvaluator hand;
	private List<Card> cards;
	
	@Before
	public void setup() {
		hand = new PokerHandEvaluator();
		cards = new ArrayList<Card>();
	}
	
	@Test(expected = InvalidPokerHandException.class)
	public void checkCardsListIsNull() throws InvalidPokerHandException {
		hand.checkHand(null);
	}
	
	@Test(expected = InvalidPokerHandException.class)
	public void checkNumberOfCards() throws InvalidPokerHandException {
		cards.add(new Card(5, Suit.HEARTS));
		cards.add(new Card(7, Suit.SPADES));
		cards.add(new Card(11, Suit.DIAMONDS));
		hand.checkHand(cards);
	}
	
	@Test
	public void checkHighCardRank() {
		cards.add(new Card(5, Suit.HEARTS));
		cards.add(new Card(7, Suit.SPADES));
		cards.add(new Card(11, Suit.DIAMONDS));
		cards.add(new Card(4, Suit.CLUBS));
		cards.add(new Card(6, Suit.CLUBS));
		try {
			Assert.assertEquals(PokerHandResult.HIGH_CARD, hand.checkHand(cards));
		} catch (InvalidPokerHandException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void checkOnePairRank() {
		cards.add(new Card(5, Suit.HEARTS));
		cards.add(new Card(11, Suit.SPADES));
		cards.add(new Card(11, Suit.DIAMONDS));
		cards.add(new Card(4, Suit.CLUBS));
		cards.add(new Card(6, Suit.CLUBS));
		try {
			Assert.assertEquals(PokerHandResult.ONE_PAIR, hand.checkHand(cards));
		} catch (InvalidPokerHandException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void checkTwoPairsRank() {
		cards.add(new Card(5, Suit.HEARTS));
		cards.add(new Card(8, Suit.SPADES));
		cards.add(new Card(11, Suit.DIAMONDS));
		cards.add(new Card(8, Suit.CLUBS));
		cards.add(new Card(5, Suit.DIAMONDS));
		try {
			Assert.assertEquals(PokerHandResult.TWO_PAIRS, hand.checkHand(cards));
		} catch (InvalidPokerHandException e) {
			Assert.fail();
		}
	}
	
	
	@Test
	public void checkThreeKindRank() {
		cards.add(new Card(3, Suit.HEARTS));
		cards.add(new Card(7, Suit.SPADES));
		cards.add(new Card(3, Suit.DIAMONDS));
		cards.add(new Card(8, Suit.CLUBS));
		cards.add(new Card(3, Suit.CLUBS));
		try {
			Assert.assertEquals(PokerHandResult.THREE_KIND, hand.checkHand(cards));
		} catch (InvalidPokerHandException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void checkStraightRankStartingWithAce() { // SEQUENCIA NUMERICA INDEP. NAIPE
		cards.add(new Card(3, Suit.HEARTS));
		cards.add(new Card(2, Suit.SPADES));
		cards.add(new Card(14, Suit.DIAMONDS));
		cards.add(new Card(5, Suit.CLUBS));
		cards.add(new Card(4, Suit.CLUBS));
		try {
			Assert.assertEquals(PokerHandResult.STRAIGHT, hand.checkHand(cards));
		} catch (InvalidPokerHandException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void checkStraightRankEndingWithAce() { // SEQUENCIA NUMERICA INDEP. NAIPE
		cards.add(new Card(11, Suit.HEARTS));
		cards.add(new Card(10, Suit.SPADES));
		cards.add(new Card(14, Suit.DIAMONDS));
		cards.add(new Card(12, Suit.CLUBS));
		cards.add(new Card(13, Suit.CLUBS));
		try {
			Assert.assertEquals(PokerHandResult.STRAIGHT, hand.checkHand(cards));
		} catch (InvalidPokerHandException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void checkFlushRank() {
		cards.add(new Card(10, Suit.HEARTS));
		cards.add(new Card(7, Suit.HEARTS));
		cards.add(new Card(6, Suit.HEARTS));
		cards.add(new Card(2, Suit.HEARTS));
		cards.add(new Card(3, Suit.HEARTS));
		try {
			Assert.assertEquals(PokerHandResult.FLUSH, hand.checkHand(cards));
		} catch (InvalidPokerHandException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void checkFullHouseRank() {
		cards.add(new Card(10, Suit.HEARTS));
		cards.add(new Card(10, Suit.SPADES));
		cards.add(new Card(10, Suit.DIAMONDS));
		cards.add(new Card(2, Suit.HEARTS));
		cards.add(new Card(2, Suit.CLUBS));
		try {
			Assert.assertEquals(PokerHandResult.FULL_HOUSE, hand.checkHand(cards));
		} catch (InvalidPokerHandException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void checkFourRank() {
		cards.add(new Card(10, Suit.HEARTS));
		cards.add(new Card(10, Suit.SPADES));
		cards.add(new Card(10, Suit.DIAMONDS));
		cards.add(new Card(2, Suit.CLUBS));
		cards.add(new Card(10, Suit.CLUBS));
		try {
			Assert.assertEquals(PokerHandResult.FOUR_KIND, hand.checkHand(cards));
		} catch (InvalidPokerHandException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void checkStraightFlushRankStartingWithAce() {
		cards.add(new Card(3, Suit.HEARTS));
		cards.add(new Card(2, Suit.HEARTS));
		cards.add(new Card(14, Suit.HEARTS));
		cards.add(new Card(5, Suit.HEARTS));
		cards.add(new Card(4, Suit.HEARTS));
		try {
			Assert.assertEquals(PokerHandResult.STRAIGHT_FLUSH, hand.checkHand(cards));
		} catch (InvalidPokerHandException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void checkStraightFlushRankEndingWithAce() {
		cards.add(new Card(11, Suit.HEARTS));
		cards.add(new Card(10, Suit.HEARTS));
		cards.add(new Card(14, Suit.HEARTS));
		cards.add(new Card(12, Suit.HEARTS));
		cards.add(new Card(13, Suit.HEARTS));
		try {
			Assert.assertEquals(PokerHandResult.STRAIGHT_FLUSH, hand.checkHand(cards));
		} catch (InvalidPokerHandException e) {
			Assert.fail();
		}
	}
}
