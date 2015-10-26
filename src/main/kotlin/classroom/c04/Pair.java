package classroom.c04;

import org.jetbrains.annotations.NotNull;

public class Pair<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>>
        implements Comparable<Pair<? extends T1, ? extends T2>> {
    private T1 first;
    private T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    @Override
    public int compareTo(@NotNull Pair<? extends T1, ? extends T2> other) {
        //if (other == null) { return 1; }
        int firstCompare = first.compareTo(other.first);
        if (firstCompare > 0) { return  1; }
        if (firstCompare < 0) { return -1; }
        int secondCompare = second.compareTo(other.second);
        if (secondCompare > 0) { return  1; }
        if (secondCompare < 0) { return -1; }
        return 0;
    }

    @Override
    public boolean equals(Object object) { //Pair<T1, T2> pair) {
        if (object == null) { return false; }
        //if (object instanceof Pair<T1, T2>) {}
        if (!(object instanceof Pair)) { return false; }
        //Pair<T1, T2> pair = (Pair<T1, T2>) object;
        Pair pair = (Pair) object;
        return first.equals(pair.first) && second.equals(pair.second);
    }

    @Override
    public int hashCode() {
        int firstHashCode  = first .hashCode();
        int secondHashCode = second.hashCode();
        return firstHashCode * 17 + secondHashCode;
    }
}
