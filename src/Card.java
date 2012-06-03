
public class Card implements Comparable<Card> {
	private Integer number;
	private Suit naipe;
	
	public Card(Integer number, Suit suit){
		this.number = number;
		this.naipe = suit;
	}

	public Integer getNumber() {
		return number;
	}

	public Suit getSuit() {
		return naipe;
	}

	@Override
	public int compareTo(Card o) {
		if (this.getNumber() > o.getNumber()) {
			return 1;
		}
		
		if (this.getNumber() < o.getNumber()) {
			return -1;
		}
		
		return 0;
	}
}
