package com.mrcoder.framecommon.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: 编号生成器
 * @author: mrcoder
 */
public class NumberGeneratorUtil {

    // 机器码生成Key
    public static final String NUMBER_GENERATOR_MACHINE_CODE_KEY = "simpleframe:number:generator:machinecode:seed";

    // 种子
    private static AtomicLong SEED = new AtomicLong(1L);

    // 种子除数
    private static final int SEED_DIVISOR = 512; // 2^9，只有9比特位供存放，不能更大了

    // 置换容器
    private static final int[] remainderMapper = new int[512];

    // 机器码：从redis获取，值范围[0,255]
    private static Long machineCode = new Long(new Random().nextInt(255));

    // 机器码设置标记
    private static boolean machineCodeSeted = false;

    static {
        List<Integer> initList = new ArrayList<Integer>();
        for (int i = 0; i < 512; ++i)
            initList.add(i);
        for (int i = 0; i < remainderMapper.length; ++i) {
            remainderMapper[i] = initList.remove(new Double(Math.random() * initList.size()).intValue());
        }
    }

    /**
     * 初始化机器码
     *
     * @param machineCodeSeed
     */
    public static void configureMachineCode(Long machineCodeSeed) {
        if (null == machineCodeSeed) {
            throw new RuntimeException("传入了异常的机器码！");
        }
        machineCode = Math.abs(machineCodeSeed) % 255;
        machineCodeSeted = true;
    }

    /**
     * 生成ID
     *
     * @return
     */
    public static Long generateUniqueId() {
        if (!machineCodeSeted) {
            throw new RuntimeException("机器码未初始化");
        }
        Calendar calendar = Calendar.getInstance();
        long dateSeed = (calendar.get(Calendar.YEAR) % 100) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
        long timeSeed = calendar.get(Calendar.HOUR_OF_DAY) * 3600 + calendar.get(Calendar.MINUTE) * 60 + calendar.get(Calendar.SECOND);
        long timePart = dateSeed * 100000 + timeSeed;

        long stepSeed = SEED.incrementAndGet();
        int seedPart = (int) (stepSeed & (SEED_DIVISOR - 1)); // 求余
        seedPart = remainderMapper[seedPart]; // 置换
        long machineCodeMixSeed = (machineCode << 9) + seedPart;

        long orderId = timePart * 100000 + machineCodeMixSeed;
        return orderId;
    }

    /**
     * 生成编号
     *
     * @return
     */
    public static String generateUniqueNumber() {
        return String.valueOf(generateUniqueId());
    }

    /**
     * 生成随机密码
     *
     * @param length
     * @return
     */
    public static String generatePassword(int length) {
        // 最终生成的密码
        String password = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 随机生成0或1，用来确定是当前使用数字还是字母 (0则输出数字，1则输出字母)
            int charOrNum = random.nextInt(2);
            if (charOrNum == 1) {
                // 随机生成0或1，用来判断是大写字母还是小写字母 (0则输出小写字母，1则输出大写字母)
                int temp = random.nextInt(2) == 1 ? 65 : 97;
                password += (char) (random.nextInt(26) + temp);
            } else {
                // 生成随机数字
                password += random.nextInt(10);
            }
        }
        return password;
    }
}

