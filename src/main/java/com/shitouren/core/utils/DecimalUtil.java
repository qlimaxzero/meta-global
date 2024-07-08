package com.shitouren.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class DecimalUtil {

    public static final int SCALE = 2;

    public static final RoundingMode MODE = RoundingMode.HALF_UP;

    public static BigDecimal add(String a, String b, int scale) {
        if (a == null && b == null) {
            return BigDecimal.ZERO;
        }
        if (a == null || b == null) {
            return a == null ? new BigDecimal(b) : new BigDecimal(a);
        }
        return add(new BigDecimal(a), new BigDecimal(b), scale);
    }

    public static BigDecimal add(BigDecimal a, BigDecimal b, int scale) {
        if (a == null && b == null) {
            return BigDecimal.ZERO;
        }
        if (a == null || b == null) {
            return a == null ? b : a;
        }
        return a.add(b).setScale(scale, MODE);
    }

    public static BigDecimal add(String a, String b) {
        return add(a, b, SCALE);
    }

    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        return add(a, b, SCALE);
    }

    public static BigDecimal add(BigDecimal ...arr) {
        BigDecimal x = BigDecimal.ZERO;
        for (BigDecimal a : arr) {
            if (a == null) {
                throw new IllegalArgumentException("不能为空!");
            }
            x = x.add(a);
        }
        return scale(x);
    }

    public static BigDecimal sub(String a, String b) {
        if (b != null) {
            b = "-" + b;
        }
        return add(a, b, SCALE);
    }

    public static BigDecimal sub(BigDecimal a, BigDecimal b) {
        if (b != null) {
            b = b.negate();
        }
        return add(a, b, SCALE);
    }

    public static BigDecimal div(String a, String b) {
        if (b == null) {
            throw new IllegalArgumentException("除数不能为空!");
        }
        return Optional.ofNullable(a)
                .map(BigDecimal::new)
                .map(d -> d.divide(new BigDecimal(b), SCALE, MODE))
                .orElse(BigDecimal.ZERO);
    }

    public static BigDecimal div(BigDecimal a, BigDecimal b) {
        if (a == null && b == null) {
            throw new IllegalArgumentException("除数不能为空!");
        }
        return Optional.ofNullable(a)
                .map(d -> d.divide(b, SCALE, MODE))
                .orElse(BigDecimal.ZERO);
    }

    public static BigDecimal multiply(BigDecimal a, long c) {
        if (a == null) {
            throw new IllegalArgumentException("不能为空!");
        }
        return a.multiply(new BigDecimal(String.valueOf(c))).setScale(SCALE, MODE);
    }

    public static BigDecimal multiply(BigDecimal a, BigDecimal b, long c) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("不能为空!");
        }
        return a.multiply(b).multiply(new BigDecimal(String.valueOf(c))).setScale(SCALE, MODE);
    }

    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        if (a == null) {
            throw new IllegalArgumentException("不能为空!");
        }
        return a.multiply(b).setScale(SCALE, MODE);
    }

    public static BigDecimal multiply(BigDecimal ...arr) {
        BigDecimal x = BigDecimal.ONE;
        for (BigDecimal a : arr) {
            if (a == null) {
                throw new IllegalArgumentException("不能为空!");
            }
            x = x.multiply(a);
        }
        return scale(x);
    }

    /**
     * greater than
     */
    public static boolean gt0(BigDecimal a) {
        if (a == null) {
            return false;
        }
        return a.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * greater than or equal
     */
    public static boolean ge0(BigDecimal a) {
        if (a == null) {
            return true;
        }
        return a.compareTo(BigDecimal.ZERO) >= 0;
    }

    public static boolean lt0(BigDecimal a) {
        if (a == null) {
            return false;
        }
        return a.compareTo(BigDecimal.ZERO) < 0;
    }

    public static boolean le0(BigDecimal a) {
        if (a == null) {
            return true;
        }
        return a.compareTo(BigDecimal.ZERO) <= 0;
    }

    public static boolean eq0(BigDecimal a) {
        if (a == null) {
            return true;
        }
        return a.compareTo(BigDecimal.ZERO) == 0;
    }

    public static BigDecimal of(Object o) {
        if (o == null) {
            return scale(BigDecimal.ZERO);
        } else if (o instanceof BigDecimal) {
            return scale((BigDecimal) o);
        } else if (o instanceof String) {
            return scale(new BigDecimal(String.valueOf(o)));
        } else if (o instanceof Double) {
            return scale(new BigDecimal(String.valueOf(o)));
        } else if (o instanceof Integer) {
            return scale(new BigDecimal(String.valueOf(o)));
        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal scale(BigDecimal o) {
        return o.setScale(SCALE, MODE);
    }

    public static BigDecimal scale(BigDecimal o, int scale) {
        return o.setScale(scale, MODE);
    }

    public static BigDecimal scale(BigDecimal o, int scale, RoundingMode mode) {
        return o.setScale(scale, mode);
    }

}
