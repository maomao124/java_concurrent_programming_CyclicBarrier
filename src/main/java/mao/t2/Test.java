package mao.t2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Project name(项目名称)：java并发编程_CyclicBarrier
 * Package(包名): mao.t2
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/14
 * Time(创建时间)： 13:06
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args)
    {
        for (int i = 0; i < 300; i++)
        {
            int finalI = i;
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        log.debug("线程" + (finalI + 1) + "开始运行");
                        cyclicBarrier.await();
                        if (finalI % 10 == 0)
                        {
                            log.info("----------------->满足条件，开始运行");
                        }
                        else
                        {
                            log.debug("线程" + (finalI + 1) + "结束运行");
                        }
                    }
                    catch (InterruptedException | BrokenBarrierException e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();

            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
