package com.acisa.cultivos.modelos;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the plantacionessectores database table.
 *
 */
@Entity
@Table(name = "plantacionessectores")
@NamedQuery(name = "Plantacionessectore.findAll", query = "SELECT p FROM PlantacionesSectores p")
public class PlantacionesSectores implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PlantacionesSectoresPK id;

    public PlantacionesSectoresPK getId() {
        return id;
    }

    public void setId(PlantacionesSectoresPK id) {
        this.id = id;
    }

    private double superficie;

    public PlantacionesSectores() {
    }

    public double getSuperficie() {
        return this.superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

}
