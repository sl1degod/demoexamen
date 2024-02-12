module ru.sl1degod.demoexamen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens ru.sl1degod.demoexamen to javafx.fxml;
    exports ru.sl1degod.demoexamen;
    exports ru.sl1degod.demoexamen.controllers;
    opens ru.sl1degod.demoexamen.controllers to javafx.fxml;
    opens ru.sl1degod.demoexamen.models to javafx.fxml;
    exports ru.sl1degod.demoexamen.models;
}