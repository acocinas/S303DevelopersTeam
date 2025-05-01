package com.model;

public class DecorationItem {
    private int idDecorationItem;
    private String nameDecorationItem;
    private String materialDecorationItem;
    private double priceDecorationItem;

    public DecorationItem(int idDecorationItem, String nameDecorationItem, String materialDecorationItem, double priceDecorationItem) {
        this.idDecorationItem = idDecorationItem;
        this.nameDecorationItem = nameDecorationItem;
        this.materialDecorationItem = materialDecorationItem;
        this.priceDecorationItem = priceDecorationItem;
    }

    public int getIdDecorationItem() {
        return idDecorationItem;
    }

    public String getNameDecorationItem() {
        return nameDecorationItem;
    }

    public String getMaterialDecorationItem() {
        return materialDecorationItem;
    }

    public double getPriceDecorationItem() {
        return priceDecorationItem;
    }

    @Override
    public String toString() {
        return "DecorationItem{" +
                "idDecorationItem=" + idDecorationItem +
                ", nameDecorationItem='" + nameDecorationItem + '\'' +
                ", materialDecorationItem='" + materialDecorationItem + '\'' +
                ", priceDecorationItem=" + priceDecorationItem +
                '}';
    }
}
