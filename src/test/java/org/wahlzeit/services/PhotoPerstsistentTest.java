package org.wahlzeit.services;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoManager;
import org.wahlzeit.model.User;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


public class PhotoPerstsistentTest {

    private static final String PICTURES_PATH = "pictures";


    @ClassRule
    public static RuleChain ruleChain = RuleChain.
            outerRule(new LocalDatastoreServiceTestConfigProvider()).
            around(new RegisteredOfyEnvironmentProvider());

    @Test
    public void initLoadStoreTest() throws Exception {
        URL url = getClass().getClassLoader().getResource(PICTURES_PATH);
        File file = new File(url.getPath());
        File photoDirFile = new File(file.getPath());
        FileFilter photoFileFilter = file1 -> file1.getName().endsWith(".jpg");
        File[] photoFiles = photoDirFile.listFiles(photoFileFilter);
        PhotoManager pm = PhotoManager.getInstance();
        String fileName = "filename";
        Image uploadedImage = getImageFromFile(photoFiles[0]);
        Photo photo = pm.createPhoto(fileName, uploadedImage);
        assertNotNull(photo);
        Photo photoLoaded = pm.getPhotoFromId(photo.getId());
        assertNotNull(photoLoaded);
    }


    private Image getImageFromFile(File file) throws IOException {
        String photoPath = file.getAbsolutePath();
        return ImagesServiceFactory.makeImage(Files.readAllBytes(Paths.get(photoPath)));
    }
}
