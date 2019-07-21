package za.co.lbnkosi.bidorbuyassesment.domain.interactor.BobInteractors;

import io.reactivex.Observable;
import retrofit2.Response;
import za.co.lbnkosi.bidorbuyassesment.domain.executor.PostExecutionThread;
import za.co.lbnkosi.bidorbuyassesment.domain.executor.ThreadExecutor;
import za.co.lbnkosi.bidorbuyassesment.domain.interactor.UseCase;
import za.co.lbnkosi.bidorbuyassesment.domain.model.ResponseModelDomain;
import za.co.lbnkosi.bidorbuyassesment.domain.repository.BobRepository;

public class GetList extends UseCase<Response<ResponseModelDomain>, GetList.Params> {

    private BobRepository bobRepository;

    public GetList(BobRepository bobRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.bobRepository = bobRepository;
    }

    @Override
    public Observable<Response<ResponseModelDomain>> buildUseCaseObservable(GetList.Params params) {
        return bobRepository.getList(params.AUTH_ID, params.PLATFORM, params.CID, params.resultsPerPage, params.includedKeywords, params.condition, params.tradeType, params.orderBy);
    }

    public static final class Params {

        private final String AUTH_ID;
        private final int PLATFORM;
        private final String CID;
        private final int resultsPerPage;
        private final String includedKeywords;
        private final String condition;
        private final String tradeType;
        private final String orderBy;

        public Params(String AUTH_ID, int PLATFORM, String CID, int resultsPerPage, String includedKeywords, String condition, String tradeType, String orderBy) {
            this.AUTH_ID = AUTH_ID;
            this.PLATFORM = PLATFORM;
            this.CID = CID;
            this.resultsPerPage = resultsPerPage;
            this.includedKeywords = includedKeywords;
            this.condition = condition;
            this.tradeType = tradeType;
            this.orderBy = orderBy;
        }

        public static Params getList(String AUTH_ID, int PLATFORM, String CID, int resultsPerPage, String includedKeywords, String condition, String tradeType, String orderBy){
            return new Params(AUTH_ID, PLATFORM, CID, resultsPerPage, includedKeywords, condition, tradeType, orderBy);
        }

    }

}
