package za.co.lbnkosi.bidorbuyassesment.data.entity.mapper;

import za.co.lbnkosi.bidorbuyassesment.data.entity.BobEntities.ResponseModelData;
import za.co.lbnkosi.bidorbuyassesment.domain.model.ResponseModelDomain;

public class BobMapper {

    public ResponseModelDomain transform(ResponseModelData responseModelData) {
        ResponseModelDomain responseModelDomain = null;
        if(responseModelData != null) {
            responseModelDomain = new ResponseModelDomain();
            responseModelDomain.setPageNumber(responseModelData.getPageNumber());
            responseModelDomain.setResultsPerPage(responseModelData.getResultsPerPage());
            responseModelDomain.setTotalResults(responseModelData.getTotalResults());
            responseModelDomain.setTrade(responseModelData.getTrade());
        }
        return responseModelDomain;
    }

}
