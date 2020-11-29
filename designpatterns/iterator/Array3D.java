package iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Array3D<T> implements Iterable<T> {
    private int n;
    private int m;
    private int k;
    private final List<List<List<T>>> array3D;

    public Array3D(List<List<List<T>>> array3D) {
        this.array3D = array3D;
        n = array3D.size();
        m = array3D.get(0).size();
        k = array3D.get(0).get(0).size();
    }


    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }


    private class MyLinkedListIterator implements Iterator<T> {
        private int curI = 0;
        private int curJ = 0;
        private int curK = 0;

        public boolean hasNext() {
            return curI < n &&
                    curJ < m &&
                    curK < k;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T value = array3D.get(curI).get(curJ).get(curK);

            curK++;

            if (curK >= k) {
                curK = 0;
                curJ++;
            }

            if (curJ >= m) {
                curJ = 0;
                curI++;
            }

            return value;
        }
    }


    public static void main(String[] argv) {
        final Array3D<Integer> array3D = new Array3D<>(
                Arrays.asList(
                        Arrays.asList(
                                Arrays.asList(1, 2, 3),
                                Arrays.asList(11, 22, 33)
                        ),
                        Arrays.asList(
                                Arrays.asList(4, 5, 6),
                                Arrays.asList(44, 55, 66)
                        )
                )
        );

        for (Integer v : array3D) {
            System.out.println(v);
        }
    }
}
