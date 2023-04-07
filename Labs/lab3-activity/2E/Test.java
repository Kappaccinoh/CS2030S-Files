class Test {
    public static void main(String[] args) {
        String s[] = new String[2];
        String t[] = new String[2];
        Integer i[] = new Integer[2];
        Integer j[] = new Integer[2];

        Skeleton.<String>copy(s, t); // ok
        Skeleton.<Integer>copy(i, j); // ok
        Skeleton.<String>copy(i, j); // error
        Skeleton.<String>copy(s, j); // error
    }
}
