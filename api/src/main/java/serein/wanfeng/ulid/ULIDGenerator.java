package serein.wanfeng.ulid;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @date: 2024-05-07 14:53
 * @author: luozh
 * @description: ulid生成器
 */
public class ULIDGenerator {

    private static final long EPOCH = Instant.parse("2001-07-30T00:00:00Z").toEpochMilli();
    private static final char[] ENCODING = "0123456789ABCDEFGHJKMNPQRSTVWXYZ".toCharArray();
    private static final int ENCODING_LENGTH = ENCODING.length;
    private static final int TIMESTAMP_LENGTH = 10;
    private static final int RANDOMNESS_LENGTH = 16;
    private static final AtomicLong LAST_TIMESTAMP_MS = new AtomicLong(Long.MIN_VALUE);
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateULID() {
        long timestampMs = getCurrentTimestampMs();
        StringBuilder sb = new StringBuilder(TIMESTAMP_LENGTH + RANDOMNESS_LENGTH);
        encodeLong(timestampMs - EPOCH, sb, TIMESTAMP_LENGTH);
        encodeLong(generateRandomness(), sb, RANDOMNESS_LENGTH);
        return sb.toString();
    }

    private static long getCurrentTimestampMs() {
        long now = Instant.now().toEpochMilli();
        while (true) {
            long lastTimestampMs = LAST_TIMESTAMP_MS.get();
            if (now > lastTimestampMs) {
                if (LAST_TIMESTAMP_MS.compareAndSet(lastTimestampMs, now)) {
                    return now;
                }
            } else {
                return LAST_TIMESTAMP_MS.incrementAndGet();
            }
        }
    }

    private static long generateRandomness() {
        byte[] randomness = new byte[8];
        RANDOM.nextBytes(randomness);
        return bytesToLong(randomness);
    }

    private static void encodeLong(long value, StringBuilder sb, int length) {
        sb.setLength(length);
        for (int i = length - 1; i >= 0; i--) {
            sb.setCharAt(i, ENCODING[(int) (value % ENCODING_LENGTH)]);
            value /= ENCODING_LENGTH;
        }
    }

    private static long bytesToLong(byte[] bytes) {
        long value = 0;
        for (byte b : bytes) {
            value = (value << 8) | (b & 0xff);
        }
        return value;
    }
}
