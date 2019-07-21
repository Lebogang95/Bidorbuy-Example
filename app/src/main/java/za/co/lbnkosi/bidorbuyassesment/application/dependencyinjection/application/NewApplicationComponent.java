package za.co.lbnkosi.bidorbuyassesment.application.dependencyinjection.application;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import za.co.lbnkosi.bidorbuyassesment.application.dependencyinjection.presentation.BobPresentationComponent;
import za.co.lbnkosi.bidorbuyassesment.application.dependencyinjection.presentation.BobPresentationModule;

@Singleton
@Component(modules = {NewApplicationModule.class})
public interface NewApplicationComponent {

    public BobPresentationComponent bobPresentationComponent(BobPresentationModule bobPresentationModule);

}
