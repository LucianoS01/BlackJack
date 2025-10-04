package Modelo;

public  interface IObservable {

    void AgregarObserbable(IObserver o);

    void RemoverObservable(IObserver o);

    void NotificarObserver();



}
