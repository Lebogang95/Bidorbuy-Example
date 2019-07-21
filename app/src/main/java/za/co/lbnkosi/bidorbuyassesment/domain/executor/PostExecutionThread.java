package za.co.lbnkosi.bidorbuyassesment.domain.executor;

import io.reactivex.Scheduler;

public interface PostExecutionThread {
    Scheduler getScheduler();
}
