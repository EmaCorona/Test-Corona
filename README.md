#Test Geckosoft - Emanuele Corona (emanuele.corona@outlook.it)

Per quanto riguarda i requisiti del test l'endpoint dell'API richiesto sarebbe '/resize-images'.

Tuttavia essendo la prima volta che mi capita un task del genere in Java sono riuscito ad effettuare
correttamente la miniaturizzazione delle immagini ma non la serializzazione all'interno di un oggetto per
fornire come response una lista di ImageResponseDTO contenenti il nome del file e l'immagine miniaturizzata.

Ho deciso comunque di sviluppare altre due API di mia iniziativa perchè comunque sono riuscito ad eseguire
il processo di miniaturizzazione delle immagini, ma ho un problema solamente a serializzarle all'interno di un oggetto o una lista.

Le API in questione sono la /resize e la /resize-and-save-images.

Endpoint esposti:

    - /resize: Effettua il ridimensionamento di una singola immagine e la fornisce come response.
    - /resize-images: Ridimensiona una lista di immagini ricevute in input e fornisce una response sotto forma di json.
    - /resize-and-save-images: Ridimensiona una lista di immagini ricevute in input e le salva in una cartella locale al progetto (resizedimages).

La classe per far partire l'applicativo è GeckosoftApplication.


Come testare gli endpoint:

    - /resize: Andare sulla sezione form-data nel body di Postman e inserire un singolo attributo
               con valore della key = "file" e nel value selezionare l'immagine da ridimensionare
               cambiando tipo sulla key e impostando file.

    - /resize-images: Andare sulla sezione form-data nel body di Postman e inserire un singolo attributo
                      con valore della key = "files" e nel value selezionare la lista di immagini da ridimensionare
                      cambiando tipo sulla key e impostando file.

    - /resize-and-save-images: Andare sulla sezione form-data nel body di Postman e inserire un singolo attributo
                               con valore della key = "files" e nel value selezionare la lista di immagini da ridimensionare
                               cambiando tipo sulla key e impostando file. Una volta eseguita l'API le immagini si troveranno
                               all'interno della cartella resizedimages (refreshare la cartella/progetto per visualizzare le immagini).

    Fare attenzione perchè nel primo endpoint la key si chiama "file" mentre negli altri due endpoint "files" dato che gestisco delle liste.


 A livello di flusso del codice ho seguito il flusso standard di Spring MVC aggiungendo un layer chiamato "Command"
 che si contrappone tra Controller e Service e serve a sviluppare la logica di ogni singola API, in modo tale da tenere
 il layer del Service più pulito. Il flusso del codice è quindi: Controller -> Service -> Command.

 Ho effettuato la gestione delle eccezioni creandomi delle mie eccezioni custom,
 ovvero: GeckosoftException, ImageNotLoadedException, ImageProcessingException.


 Tecnologie utilizzate:
- Framework: Spring Boot

- Maven: Gestore di progetto in Java che permette di scaricare automaticamente le dipendenze necessarie al progetto
         indicate all'interno del file pom.xml.

- Spring-boot-starter-web: Dipendenza di avvio per progetti Spring Boot che contiene gli strumenti necessari per poter sviluppare
                           applicazioni web e servizi REST.

- Lombok: libreria in grado di generare automaticamente getter, setter, costruttori con tutti gli argomenti e senza argomenti.
