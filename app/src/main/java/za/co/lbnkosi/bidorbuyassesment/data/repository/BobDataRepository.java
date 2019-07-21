package za.co.lbnkosi.bidorbuyassesment.data.repository;

import io.reactivex.Observable;
import retrofit2.Response;
import za.co.lbnkosi.bidorbuyassesment.data.entity.BobEntities.ResponseModelData;
import za.co.lbnkosi.bidorbuyassesment.data.entity.mapper.BobMapper;
import za.co.lbnkosi.bidorbuyassesment.data.repository.datasource.BobDataSource.BobDataStore;
import za.co.lbnkosi.bidorbuyassesment.data.repository.datasource.BobDataSource.BobDataStoreFactory;
import za.co.lbnkosi.bidorbuyassesment.domain.model.ResponseModelDomain;
import za.co.lbnkosi.bidorbuyassesment.domain.repository.BobRepository;

public class BobDataRepository implements BobRepository {

    private final BobDataStore bobDataStore;
    private final BobMapper bobMapper;

    public BobDataRepository(BobDataStoreFactory bobDataStoreFactory, BobMapper bobMapper) {
        this.bobMapper = bobMapper;
        bobDataStore = bobDataStoreFactory.createRemoteDataStore();
    }

    @Override
    public Observable<Response<ResponseModelDomain>> getList(String AUTH_ID, int PLATFORM, String CID, int resultsPerPage, String includedKeywords, String condition, String tradeType, String orderBy) {
        return null;
    }

    /*@Override
    public Observable<Response<ResponseModelData>> getList(String AUTH_ID, int PLATFORM, String CID, int resultsPerPage, String includedKeywords, String condition, String tradeType, String orderBy) {
        return bobDataStore.getList(AUTH_ID, PLATFORM, CID, resultsPerPage, includedKeywords, condition, tradeType, orderBy);
    }*/
}
