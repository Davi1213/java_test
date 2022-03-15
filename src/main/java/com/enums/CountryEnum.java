package main.java.com.enums;

import lombok.Getter;

/**
 *
 */
public enum CountryEnum {
    //one(1, value1, value2,...);

    one(1, "AA"), two(2, "BB"),
    three(3, "CC"), four(4, "DD"),
    five(5, "EE"), six(6, "FF");

    @Getter
    Integer retCode;

    @Getter
    private String retMessage;

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum countryEnumForEach(int index) {
        CountryEnum[] countryEnums = CountryEnum.values();
        for (CountryEnum anEnum : countryEnums) {
            if (index == anEnum.getRetCode()) {
                return anEnum;
            }
        }
        return null;
    }
}
