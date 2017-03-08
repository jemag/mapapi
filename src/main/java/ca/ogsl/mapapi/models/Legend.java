package ca.ogsl.mapapi.models;

import ca.ogsl.mapapi.services.PersistenceManager;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by desjardisna on 2017-03-03.
 */
@Entity
public class Legend {
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "urlfr")
    private String urlFr;
    @Basic
    @Column(name = "urlen")
    private String urlEn;
    @Column(name = "layer_id", insertable = false, updatable = false)
    private Integer layerId;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "layer_id")
    private Layer layer;
    @Basic
    @Column(name = "labelfr", nullable = false)
    private String labelFr;
    @Basic
    @Column(name = "labelen", nullable = false)
    private String labelEn;
    @Basic
    @Column(name = "isdefaultlegend")
    @JsonProperty(value="isDefaultLegend")
    private Boolean isDefaultLegend;

    public String getLabel__(){
        return PersistenceManager.getLocalizedString(this.labelFr, this.labelEn);
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
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrlFr() {
        return PersistenceManager.getIfNoContextLanguage(this.urlFr);
    }

    public void setUrlFr(String urlfr) {
        this.urlFr = urlfr;
    }

    public String getUrlEn() {
        return PersistenceManager.getIfNoContextLanguage(this.urlEn);
    }

    public void setUrlEn(String urlen) {
        this.urlEn = urlen;
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
    public Layer getLayer() {
        return layer;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }

    @JsonProperty(value="isDefaultLegend")
    public Boolean isDefaultLegend() {
        return isDefaultLegend;
    }

    @JsonProperty(value="isDefaultLegend")
    public void setDefaultLegend(Boolean defaultLegend) {
        isDefaultLegend = defaultLegend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Legend legend = (Legend) o;

        if (id != null ? !id.equals(legend.id) : legend.id != null) return false;
        if (urlFr != null ? !urlFr.equals(legend.urlFr) : legend.urlFr != null) return false;
        if (urlEn != null ? !urlEn.equals(legend.urlEn) : legend.urlEn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (urlFr != null ? urlFr.hashCode() : 0);
        result = 31 * result + (urlEn != null ? urlEn.hashCode() : 0);
        return result;
    }
}