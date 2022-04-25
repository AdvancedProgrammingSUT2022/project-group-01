package utils;
public class Pair<P extends Comparable<P>, Q extends Comparable<Q>> implements Comparable<Pair<P, Q>> {
	private P first;
	private Q second;

	public Pair(P e1, Q e2){
		this.first = e1;
		this.second = e2;
	}
	public P getFirst(){ return first; }
	public Q getSecond(){ return second; }

	public void setFirst(P first) {
		this.first = first;
	}
	public void setSecond(Q second) {
		this.second = second;
	}

	@Override
	public int compareTo(Pair<P, Q> o) {
		if(first.compareTo(o.getFirst()) != 0)
			return first.compareTo(o.getFirst());
		return second.compareTo(o.getSecond());
	}
}
