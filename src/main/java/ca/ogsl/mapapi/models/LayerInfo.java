package ca.ogsl.mapapi.models;

import ca.ogsl.mapapi.services.PersistenceManager;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by desjardisna on 2017-02-27.
 */
@Entity
public class LayerInfo {
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "labelFr")
    private String labelFr;
    @Basic
    @Column(name = "labelEn")
    private String labelEn;
    @Basic
    @Column(name = "valueFr")
    private String valueFr;
    @Basic
    @Column(name = "valueEn")
    private String valueEn;
    @Basic
    @Column(name = "urlFr")
    private String urlFr;
    @Basic
    @Column(name = "urlEn")
    private String urlEn;
    @Column(name = "layer_id")
    private Integer layerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabelFr() {
        return PersistenceManager.getIfNoContextLanguage(this.labelFr);
    }

    public void setLabelFr(String labelFr) {
        this.labelFr = labelFr;
    }

    public String getLabelEn() {
        return PersistenceManager.getIfNoContextLanguage(this.labelEn);
    }

    public void setLabelEn(String labelEn) {
        this.labelEn = labelEn;
    }

    public String getLabel__(){
        return PersistenceManager.getLocalizedString(this.labelFr, this.labelEn);
    }

    public String getValueFr() {
        return PersistenceManager.getIfNoContextLanguage(this.valueFr);
    }

    public void setValueFr(String valueFr) {
        this.valueFr = valueFr;
    }

    public String getValueEn() {
        return PersistenceManager.getIfNoContextLanguage(this.valueEn);
    }

    public void setValueEn(String valueEn) {
        this.valueEn = valueEn;
    }

    public String getValue__(){
        return PersistenceManager.getLocalizedString(this.valueFr, this.valueEn);
    }

    public String getUrlFr() {
        return PersistenceManager.getIfNoContextLanguage(this.urlFr);
    }

    public void setUrlFr(String urlFr) {
        this.urlFr = urlFr;
    }

    public String getUrlEn() {
        return PersistenceManager.getIfNoContextLanguage(this.urlEn);
    }

    public void setUrlEn(String urlEn) {
        this.urlEn = urlEn;
    }

    public String getUrl__(){
        return PersistenceManager.getLocalizedString(this.urlFr, this.urlEn);
    }

    public Integer getLayerId() {
        return layerId;
    }

    public void setLayerId(Integer layerId) {
        this.layerId = layerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LayerInfo layerInfo = (LayerInfo) o;

        if (id != null ? !id.equals(layerInfo.id) : layerInfo.id != null) return false;
        if (labelFr != null ? !labelFr.equals(layerInfo.labelFr) : layerInfo.labelFr != null) return false;
        if (labelEn != null ? !labelEn.equals(layerInfo.labelEn) : layerInfo.labelEn != null) return false;
        if (valueFr != null ? !valueFr.equals(layerInfo.valueFr) : layerInfo.valueFr != null) return false;
        if (valueEn != null ? !valueEn.equals(layerInfo.valueEn) : layerInfo.valueEn != null) return false;
        if (urlFr != null ? !urlFr.equals(layerInfo.urlFr) : layerInfo.urlFr != null) return false;
        if (urlEn != null ? !urlEn.equals(layerInfo.urlEn) : layerInfo.urlEn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (labelFr != null ? labelFr.hashCode() : 0);
        result = 31 * result + (labelEn != null ? labelEn.hashCode() : 0);
        result = 31 * result + (valueFr != null ? valueFr.hashCode() : 0);
        result = 31 * result + (valueEn != null ? valueEn.hashCode() : 0);
        result = 31 * result + (urlFr != null ? urlFr.hashCode() : 0);
        result = 31 * result + (urlEn != null ? urlEn.hashCode() : 0);
        return result;
    }
}