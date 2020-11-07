package top.laonaailifa.middleware.netty.bioStudy.bio;

import java.util.concurrent.*;

public class TimeServerHandlerExecutorPool implements Executor {

    private ExecutorService executorService;

    public TimeServerHandlerExecutorPool(int maxPoolSize, int queueSize) {
        this.executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                maxPoolSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public TimeServerHandlerExecutorPool(int coraPoolSize, int maxPoolSize, int queueSize) {
        this.executorService = new ThreadPoolExecutor(coraPoolSize, maxPoolSize,
                120L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(queueSize));
    }

    @Override
    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }
}
