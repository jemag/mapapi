package ca.ogsl.mapapi.services;

import ca.ogsl.mapapi.models.Layer;
import ca.ogsl.mapapi.models.LayerDescription;
import ca.ogsl.mapapi.models.LayerInfo;
import ca.ogsl.mapapi.util.GenericsUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by desjardisna on 2017-02-28.
 */
@SuppressWarnings("Duplicates")
@Path("layer")
@Produces(MediaType.APPLICATION_JSON)
public class LayerService {

    public LayerService(){}

    @GET
    public Response listLayers(@QueryParam("lang") String lang){
        PersistenceManager.setLanguageContext(lang);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mapapi");
        EntityManager em = emf.createEntityManager();
        List<Layer> layers;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Layer> cq = cb.createQuery(Layer.class);
            Root<Layer> root = cq.from(Layer.class);
            TypedQuery<Layer> tq = em.createQuery(cq);
            layers = tq.getResultList();
            return Response.status(200).entity(layers).build();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Response.status(500).build();
    }

    @GET
    @Path("{id}")
    public Response getLayerForId(@QueryParam("lang") String lang, @PathParam("id") Integer id){
        PersistenceManager.setLanguageContext(lang);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mapapi");
        EntityManager em = emf.createEntityManager();
        Layer layer;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Layer> cq = cb.createQuery(Layer.class);
            Root<Layer> root = cq.from(Layer.class);
            cq.where(cb.equal(root.get("id"), id));
            TypedQuery<Layer> tq = em.createQuery(cq);
            layer = GenericsUtil.getSingleResultOrNull(tq);
            return Response.status(200).entity(layer).build();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Response.status(500).build();
    }
    @GET
    @Path("getLayerForCode")
    public Response getlayerForCode(@QueryParam("lang") String lang, @QueryParam("code") String code){
        PersistenceManager.setLanguageContext(lang);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mapapi");
        EntityManager em = emf.createEntityManager();
        Layer layer;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Layer> cq = cb.createQuery(Layer.class);
            Root<Layer> root = cq.from(Layer.class);
            cq.where(cb.equal(root.get("code"), code));
            TypedQuery<Layer> tq = em.createQuery(cq);
            layer = GenericsUtil.getSingleResultOrNull(tq);
            return Response.status(200).entity(layer).build();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Response.status(500).build();
    }

    @GET
    @Path("{id}/getLayerInformation")
    @Produces(MediaType.TEXT_HTML)
    public Response getLayerInformation(@QueryParam("lang") String lang, @PathParam("id") Integer id){
        String htmlContent="";
        htmlContent+="<div class='layerInfo'>";
        htmlContent+="<div class='layerDescription'>";

        PersistenceManager.setLanguageContext(lang);
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("mapapi");
        EntityManager em= emf.createEntityManager();
        LayerDescription layerDescription;
        List<LayerInfo> layerInfos;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<LayerDescription> cq = cb.createQuery(LayerDescription.class);
            Root<LayerDescription> root =  cq.from(LayerDescription.class);
            cq.where(cb.and(cb.equal(root.get("layerId"),id)));
            TypedQuery<LayerDescription> tq = em.createQuery(cq);
            layerDescription = tq.getSingleResult();

            CriteriaBuilder cb2= em.getCriteriaBuilder();
            CriteriaQuery<LayerInfo> cq2 = cb.createQuery(LayerInfo.class);
            Root<LayerInfo> root2 =  cq2.from(LayerInfo.class);
            cq2.where(cb2.equal(root2.get("layerId"),1));
            TypedQuery<LayerInfo> tq2= em.createQuery(cq2);
            layerInfos = tq2.getResultList();


            htmlContent+=layerDescription.getDescription__();
            htmlContent+="</div>";
            htmlContent+="<div class='layerInformations'>";
            for (LayerInfo layerInfo: layerInfos){
                htmlContent+="<span>"+layerInfo.getLabel__()+"</span><br>";
            }
            htmlContent+="</div>";
            htmlContent+="</div>";


            return Response.status(200).entity(htmlContent).build();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Response.status(500).build();
    }
}