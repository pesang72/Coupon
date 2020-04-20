package du.kakaocrop.coupon.api.constant;

import java.util.HashMap;
import java.util.Map;

public enum StoredProcedureStatus {
    SUCCESS(1),
    FAIL(-1),
    ALREADY_PROVIDED(-2), // 쿠폰 이미 발급받은 상태
    COUPON_EXHAUSTED(-3), // 쿠폰 소진
    UNKNOWN(-9999);

    int code;
    private static Map<Integer, StoredProcedureStatus> map;

    static {
        map = new HashMap<>();
        for (StoredProcedureStatus spr : StoredProcedureStatus.values()) {
            map.put(spr.code, spr);
        }
    }

    StoredProcedureStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static StoredProcedureStatus getRes(Integer code) {
        if(code ==null)
            return UNKNOWN;

        if (map.containsKey(code)){
            return map.get(code);
        }

        return UNKNOWN;
    }
}
