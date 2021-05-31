public class MyArrayList {

    private Object[] array = new Object[10];
    private int end;

    public Object get(int index) {
        if (index >= end) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    public void set(int index, Object element) {
        if (index >= end) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = element;
    }

    public void add(Object element) {
        if (filledUp()) {
            array = copy();
            array[end] = element;
            return;
        }
        array[end++] = element;
    }

    public void add(int index, Object element) {
        if (index > end) {
            throw new IndexOutOfBoundsException();
        }

        if (filledUp()) {
            array = copy();
        }

        ++end;
        Object[] copy = new Object[array.length];
        for (int i = 0; i < end + 1; i++) {
            if (i < index) {
                copy[i] = array[i];
            } else if (i == index) {
                copy[i] = element;
                copy[i + 1] = array[i];
            } else {
                copy[i + 1] = array[i];
            }
        }

        array = copy;
    }

    public int indexOf(Object object) {
        for (int i = 0; i < end; i++) {
            if (array[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object object) {
        for (int i = end - 1; i >= 0; i--) {
            if (array[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        array = new Object[5];
        end = 0;
    }

    public int size() {
        return end;
    }

    private boolean filledUp() {
        return end == array.length;
    }

    private Object[] copy() {
        Object[] copy = new Object[array.length * 2];
        if (end >= 0) System.arraycopy(array, 0, copy, 0, end);
        return copy;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof MyArrayList) {
            MyArrayList that = (MyArrayList) object;
            if (size() != that.size()) {
                return false;
            }
            for (int i = 0; i < size(); i++) {
                if (get(i) != that.get(i)) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < end - 1; i++) {
            sb.append(array[i]).append(", ");
        }
        sb.append(array[end - 1]).append("]");
        return sb.toString();
    }
}
