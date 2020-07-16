package org.xpdojo.bank;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Clock {
    public LocalDateTime now(ZoneId zoneId) {
        return LocalDateTime.now(zoneId);
    }
}
