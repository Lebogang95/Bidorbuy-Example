package za.co.lbnkosi.bidorbuyassesment.data.bobNetworking;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import za.co.lbnkosi.bidorbuyassesment.data.entity.BobEntities.ResponseModelData;

public class BobRetrofitImpl implements BobRetrofit {

    private Response<ResponseModelData> _response;

    @Override
    public Observable<Response<ResponseModelData>> getList(String AUTH_ID, int PLATFORM, String CID, int resultsPerPage, String includedKeywords, String condition, String tradeType, String orderBy) {
        return Observable.create(emitter -> {
            Observable<Response<ResponseModelData>> response = RetrofitComponent.getInstance().bobService.getList(AUTH_ID, PLATFORM, CID, resultsPerPage, includedKeywords, condition, tradeType, orderBy);
            response.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<ResponseModelData>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Response<ResponseModelData> responseModelDataResponse) {
                           _response = responseModelDataResponse;
                        }

                        @Override
                        public void onError(Throwable e) {
                            emitter.onError(e);
                        }

                        @Override
                        public void onComplete() {
                            emitter.onNext(_response);
                        }
                    });

        });
    }
}
