package utils;

import java.util.Collections;
import java.util.Vector;

public class VectorUtils {
	public static <E extends Comparable<E>> Vector<E> fastUnique(Vector<E> vector) {
		Collections.sort(vector);
		Vector<E> uniqueVector = new Vector<>();
		for (E element : vector) {
			if (uniqueVector.isEmpty() || !uniqueVector.lastElement().equals(element)) {
				uniqueVector.add(element);
			}
		}
		return uniqueVector;
	}

	public static <E> Vector<E> unique(Vector<E> vector) {
		Vector<E> uniqueVector = new Vector<>();
		for (E element : vector) {
			boolean isNew = true;
			for (E uniqueElement : uniqueVector) {
				if (element == uniqueElement) {
					isNew = false;
					break;
				}
			}
			if (isNew)
				uniqueVector.add(element);
		}
		return uniqueVector;
	}
}
