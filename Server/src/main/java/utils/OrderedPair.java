package utils;

/**
 * Pair is class that contain two element of Type P and Q
 *
 * @param <P> type of first component, should be Comparable
 * @param <Q> type of second component, should be Comparable
 */
public class OrderedPair<P extends Comparable<P>, Q extends Comparable<Q>> implements Comparable<OrderedPair<P, Q>> {
	private P first;
	private Q second;

	/**
	 * construct a pair of first and second parameter
	 *
	 * @param e1 first element of pair
	 * @param e2 second element of pair
	 */
	public OrderedPair(P e1, Q e2) {
		this.first = e1;
		this.second = e2;
	}

	/**
	 * method to get first element
	 *
	 * @return first element of pair
	 */
	public P getFirst() {
		return first;
	}

	/**
	 * change first element to only parameter
	 *
	 * @param first to replace first element
	 */
	public void setFirst(P first) {
		this.first = first;
	}

	/**
	 * method to get second element
	 *
	 * @return second element of pair
	 */
	public Q getSecond() {
		return second;
	}

	/**
	 * change second element to only parameter
	 *
	 * @param second to replace second element
	 */
	public void setSecond(Q second) {
		this.second = second;
	}

	/**
	 * @param o other pair to compare this to
	 * @return result of compare between this object and other object
	 */
	@Override
	public int compareTo(OrderedPair<P, Q> o) {
		if (first.compareTo(o.getFirst()) != 0)
			return first.compareTo(o.getFirst());
		return second.compareTo(o.getSecond());
	}
}
