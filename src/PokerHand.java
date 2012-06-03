import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;


public class PokerHand {

	private List<Card> cards;
	
	public PokerHandResult checkHand(List<Card> cards) throws InvalidPokerHandException {
		if (cards == null || cards.size() < 5) {
			throw new InvalidPokerHandException();
		}
		this.cards = cards;
		
		if (isStraightFlush()) {
			return PokerHandResult.STRAIGHT_FLUSH;
		}
		
		if (isFour()) {
			return PokerHandResult.FOUR;
		}
		
		if (isFullHouse()) {
			return PokerHandResult.FULL_HOUSE;
		}
		
		if (isFlush()) {
			return PokerHandResult.FLUSH;
		}
		
		if (isStraight()) {
			return PokerHandResult.STRAIGHT;
		}
		
		if (isThreeKind()) {
			return PokerHandResult.THREE_KIND;
		}
		
		if (isTwoPairs()) {
			return PokerHandResult.TWO_PAIRS;
		}
		
		if (isPair()) {
			return PokerHandResult.PAIR;
		}
		
		return PokerHandResult.HIGH_CARD;
	}
	
	private boolean isFlush() {
		Card referencia = null;
		boolean retorno = true;
		
		for (Card carta : this.cards) {
			if (referencia == null) {
				referencia = carta;
			} else {
				retorno = (retorno && carta.getSuit() == referencia.getSuit());
			}
		}
		
		return retorno;
	}
	
	private boolean isStraightFlush() {
		return (this.isFlush() && this.isStraight());
	}
	
	private boolean isFour() {
		for (int index = 0; index < this.cards.size(); index++) {
			Card carta = this.cards.get(index);
			Integer contador = this.getCardCount(carta);
			if (contador == 4) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isFullHouse() {
		return (this.isThreeKind() && this.isPair());
	}
	
	private boolean isStraight() {
		TreeSet<Card> treeSet = new TreeSet<Card>(this.cards);
		Iterator<Card> iterator = treeSet.iterator();
		Card primeiraCarta = iterator.next();
		int proximoNumero = primeiraCarta.getNumber() + 1;
		
		while (iterator.hasNext()) {
			Card carta = iterator.next();
			if (carta.getNumber() != proximoNumero) {
				if (!iterator.hasNext() && 
						primeiraCarta.getNumber() == 2 && 
						carta.getNumber() == 14) {
					return true;
				} else {
					return false;
				}
			} else {
				proximoNumero = carta.getNumber() + 1;
			}
		}
		
		return true;
	}
	
	private boolean isThreeKind() {
		for (int index = 0; index < this.cards.size(); index++) {
			Card carta = this.cards.get(index);
			Integer contador = this.getCardCount(carta);
			if (contador == 3) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isTwoPairs() {
		
		Map<Integer, Integer> mapCartas = new HashMap<Integer, Integer>();
		Integer contadorPares = 0;
		
		for (int index = 0; index < this.cards.size(); index++) {
			Card carta = this.cards.get(index);
			if (!mapCartas.containsKey(carta.getNumber())) {
				Integer quantidade = this.getCardCount(carta);
				mapCartas.put(carta.getNumber(), quantidade);
				
				if (quantidade == 2 && contadorPares == 1) {
					return true;
				} else if (quantidade == 2) {
					contadorPares = contadorPares + 1;
				}
			}
		}
		
		return false;
	}
	
	private boolean isPair() {
		for (int index = 0; index < this.cards.size(); index++) {
			Card carta = this.cards.get(index);
			Integer contador = this.getCardCount(carta);
			if (contador == 2) {
				return true;
			}
		}
		
		return false;
	}
	
	private Integer getCardCount(Card referencia) {
			
		Integer contador = 0;
		for (Card item : this.cards) {
			if (referencia.getNumber() == item.getNumber()) {
				contador = contador + 1;
			}
		}
		
		return contador;
	}
}
