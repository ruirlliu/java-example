package com.example.demo.code;

/**
 * @author lr
 * @date 2021/1/25
 */
public enum OrdinalEnum {

    ONE,
    TEO,
    THREE,
    FOUR,
    FIVE;

    private int mask;

    private void mask() {
        mask = (1 << ordinal());
    }

    public int getMask() {
        return mask;
    }

    public boolean in(int flag) {
        return (getMask() & flag) != 0;
    }

    public int add(int oMask) {
        return oMask | getMask();
    }

    public int remove(int oMask) {
        return oMask & ~getMask();
    }

    public static void main(String[] args) {
        int x = 10;

        x -= 5;
        System.out.println(x);
    }

}
