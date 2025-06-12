Richiesto JDK 22 o superiore
Le librerie sono già configurate e presenti in libs.
Bisogna solo aggiungere la seguente stringa in VM arguments di interfaccia da Run Configurations

--module-path "${project_loc}/lib" --add-modules javafx.controls,javafx.graphics,javafx.fxml

Il gioco parte da Interfaccia.java
