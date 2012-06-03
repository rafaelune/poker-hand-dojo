import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class JogadaTest {
	
	private PokerHand hand;
	private List<Card> cards;
	
	@Before
	public void setup() {
		hand = new PokerHand();
		cards = new ArrayList<Card>();
	}
	
	@Test(expected = InvalidPokerHandException.class)
	public void validarPassagemDeCartas() throws InvalidPokerHandException {
		hand.checkHand(null);
		Assert.fail("Cartas null.");
	}
	
	@Test(expected = InvalidPokerHandException.class)
	public void validarNumeroDeCartas() throws InvalidPokerHandException {
		cards.add(new Card(5, Suit.HEARTS));
		cards.add(new Card(7, Suit.SPADES));
		cards.add(new Card(11, Suit.DIAMONDS));
		hand.checkHand(cards);
		Assert.fail("Número de cartas inválido.");
	}
	
	@Test
	public void validarJogadaCartaMaior() {
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
	public void validarJogadaComPar() {
		cards.add(new Card(5, Suit.HEARTS));
		cards.add(new Card(11, Suit.SPADES));
		cards.add(new Card(11, Suit.DIAMONDS));
		cards.add(new Card(4, Suit.CLUBS));
		cards.add(new Card(6, Suit.CLUBS));
		try {
			Assert.assertEquals(PokerHandResult.PAIR, hand.checkHand(cards));
		} catch (InvalidPokerHandException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void validarJogadaComDoisPares() {
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
	public void validarJogadaTrinca() {
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
	public void validarJogadaStraightIniciadaComAS() { // SEQUENCIA NUMERICA INDEP. NAIPE
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
	public void validarJogadaStraightFinalizadaComAS() { // SEQUENCIA NUMERICA INDEP. NAIPE
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
	public void validarJogadaFlush() {
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
	public void validarJogadaFullHouse() {
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
	public void validarJogadaFour() {
		cards.add(new Card(10, Suit.HEARTS));
		cards.add(new Card(10, Suit.SPADES));
		cards.add(new Card(10, Suit.DIAMONDS));
		cards.add(new Card(2, Suit.CLUBS));
		cards.add(new Card(10, Suit.CLUBS));
		try {
			Assert.assertEquals(PokerHandResult.FOUR, hand.checkHand(cards));
		} catch (InvalidPokerHandException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void validarJogadaStraightFlushIniciadoComAS() {
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
	public void validarJogadaStraightFlushFinalizadaComAS() {
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
