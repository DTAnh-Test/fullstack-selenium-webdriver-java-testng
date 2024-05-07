package javaTester.javaOop.javaAccModiFirst;

public class Annimal {
    // Private:
        // Chi cho phep truy cap trong pham vi class
        // Khong duoc phep truy cap o ngoai class (cung hoac khac package)
        // Khong duoc phep truy cap boi class con (ke thua)
    // Default:
        // Cho phep truy cap trong pham vi class
        // Cho phep truy cap tu 1 class khac nhung cung package
        // Khong cho phep truy cap tu class khac nhwng khac package thong qua khoi taoj doi tuong
        // Khong duoc phep truy cap boi class con (ke thua)
    // Protected:
        // Cho phep truy cap trong pham vi class
        // Cho phep truy cap tu 1 class khac nhung cung package
        // Khong cho phep truy cap tu class khac nhwng khac package thong qua khoi taoj doi tuong
        // Cho phep truy cap boi class con (ke thua)
    // Public:
        // Cho phep truy cap trong pham vi class
        // Cho phep truy cap tu 1 class khac nhung cung package
        // Cho phep truy cap tu class khac nhwng khac package thong qua khoi taoj doi tuong
        // Cho phep truy cap boi class con (ke thua)
    private String annimalName;
    String color; // default, trong cung class hoac cac class cung package goi toi duoc
    protected int eyeNumber; // trong cung class hoac cac class cung package goi toi duoc thong qua tinh ke thua
    public float weight;

    // Property
    private int ssdSize; // Chi dung trong class nay

    // Method
    private void setSsdSize(int ssdSize){
        this.ssdSize = ssdSize;
    }

    public static void main(String[] args){
        Annimal comp = new Annimal();
        comp.ssdSize = 500;
        comp.setSsdSize(600);
    }
}
