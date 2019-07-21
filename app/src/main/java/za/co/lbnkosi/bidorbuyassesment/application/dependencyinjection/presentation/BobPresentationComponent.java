package za.co.lbnkosi.bidorbuyassesment.application.dependencyinjection.presentation;

import dagger.Subcomponent;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.base.BaseActivity;

@Subcomponent(modules = {BobPresentationModule.class})
public interface BobPresentationComponent {

    void inject(BaseActivity baseActivity);

}
