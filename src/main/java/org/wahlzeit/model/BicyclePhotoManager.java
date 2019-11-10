package org.wahlzeit.model;

import com.google.appengine.api.images.Image;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;
import java.util.ArrayList;
import java.util.Collection;

public class BicyclePhotoManager extends PhotoManager {
    protected static final BicyclePhotoManager instance = new BicyclePhotoManager();

    /**
     *
     */
    public static final BicyclePhotoManager getInstance() {
        return instance;
    }

    @Override
    public BicyclePhoto getPhotoFromId(PhotoId id) {
        if (id == null) {
            return null;
        }

        BicyclePhoto result = doGetPhotoFromId(id);

        if (result == null) {
            result = BicyclePhotoFactory.getInstance().loadPhoto(id);
            if (result != null) {
                doAddPhoto(result);
            }
        }

        return result;
    }

    @Override
    protected BicyclePhoto doGetPhotoFromId(PhotoId id) {
        return (BicyclePhoto)super.doGetPhotoFromId(id);
    }

    @Override
    public void loadPhotos() {
        Collection<BicyclePhoto> existingPhotos = ObjectifyService.run(new Work<Collection<BicyclePhoto>>() {
            @Override
            public Collection<BicyclePhoto> run() {
                Collection<BicyclePhoto> existingPhotos = new ArrayList<BicyclePhoto>();
                readObjects(existingPhotos, BicyclePhoto.class);
                return existingPhotos;
            }
        });

        for (BicyclePhoto photo : existingPhotos) {
            if (!doHasPhoto(photo.getId())) {
                //log.config(LogBuilder.createSystemMessage().
                        //addParameter("Load Photo with ID", photo.getIdAsString()).toString());
                loadScaledImages(photo);
                doAddPhoto(photo);
            } else {
                //log.config(LogBuilder.createSystemMessage().
                       // addParameter("Already loaded Photo", photo.getIdAsString()).toString());
            }
        }

       // log.info(LogBuilder.createSystemMessage().addMessage("All photos loaded.").toString());
    }

    public BicyclePhoto createPhoto(String filename, Image uploadedImage) throws Exception {
        PhotoId id = PhotoId.getNextId();
        BicyclePhoto result = PhotoUtil.createBicyclePhoto(filename, id, uploadedImage);
        addPhoto(result);
        return result;
    }
}
