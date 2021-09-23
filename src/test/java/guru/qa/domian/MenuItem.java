package guru.qa.domian;

public enum MenuItem {
    DISCOUNTS("Скидки"),
    CASHBACK("Кэшбэк"),
    COUPONS("Купоны");

    private String desc;

    MenuItem(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}

