package org.wahlzeit.services;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.model.User;
import org.wahlzeit.model.UserManager;
import org.wahlzeit.model.persistence.DatastoreAdapter;
import org.wahlzeit.model.persistence.ImageStorage;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;
import org.wahlzeitext.model.*;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;


public class BicyclePhotoPerstsistentTest {

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
        BicyclePhotoManager pm = BicyclePhotoManager.getInstance();
        String fileName = "filename";
        Image uploadedImage = getImageFromFile(photoFiles[0]);
        BicyclePhoto photo = pm.createPhoto(fileName, uploadedImage);
        assertNotNull(photo);
        BicyclePhoto photoLoaded = pm.getPhotoFromId(photo.getId());
        assertNotNull(photoLoaded);
    }


    @Test
    public void initLoadStoreWithBrandnameTest() throws Exception {
        URL url = getClass().getClassLoader().getResource(PICTURES_PATH);
        File file = new File(url.getPath());
        File photoDirFile = new File(file.getPath());
        FileFilter photoFileFilter = file1 -> file1.getName().endsWith(".jpg");
        File[] photoFiles = photoDirFile.listFiles(photoFileFilter);
        BicyclePhotoManager pm = BicyclePhotoManager.getInstance();
        String fileName = "filename";
        Image uploadedImage = getImageFromFile(photoFiles[0]);
        BicyclePhoto photo = pm.createPhoto(fileName, uploadedImage);
        photo.setBicycle(BicycleManager.getInstance().createBicycle("17inch", new Location(CartesianCoordinate.constructCartesianCoordinate(0, 10, 0))));
        assertNotNull(photo);
        BicyclePhoto photoLoaded = pm.getPhotoFromId(photo.getId());
        assertNotNull(photoLoaded);
        assertEquals(photo.getBicycle().getType().getName(), "17inch");
    }

    @Test
    public void initLoadStoreWithBrandnameTestWithSave() throws Exception {
        ImageStorage.setInstance(new DatastoreAdapter());
        ObjectifyService.run(new VoidWork() {
            public void vrun() {
                UserManager userManager = UserManager.getInstance();
                User user = new User("testuser1234", "test", "test@test.com");
                URL url = getClass().getClassLoader().getResource(PICTURES_PATH);
                File file = new File(url.getPath());
                File photoDirFile = new File(file.getPath());
                FileFilter photoFileFilter = file1 -> file1.getName().endsWith(".jpg");
                File[] photoFiles = photoDirFile.listFiles(photoFileFilter);
                BicyclePhotoManager pm = BicyclePhotoManager.getInstance();
                String fileName = "filename";
                Image uploadedImage = null;
                try {
                    uploadedImage = getImageFromFile(photoFiles[0]);
                } catch (IOException e) {
                    assert(false);
                    e.printStackTrace();
                }
                BicyclePhoto photo = null;
                try {
                    photo = pm.createPhoto(fileName, uploadedImage);
                } catch (Exception e) {
                    assert(false);
                    e.printStackTrace();
                }
                assertNotNull(photo);
                photo.setOwnerId(user.getId());

                try {
                    photo.setBicycle(BicycleManager.getInstance().createBicycle("17inch", new Location(CartesianCoordinate.constructCartesianCoordinate(0, 10, 0))));
                } catch (InvalidCoordinateException e) {
                    assert (false);
                    e.printStackTrace();
                }


                pm.savePhoto(photo);
                pm.loadPhotos();
                BicyclePhoto photoLoaded = pm.getPhotoFromId(photo.getId());
                assertNotNull(photoLoaded);
                assertEquals(photo.getBicycle().getType().getName(), "17inch");
            }
        });
    }


    private Image getImageFromFile(File file) throws IOException {
        String photoPath = file.getAbsolutePath();
        return ImagesServiceFactory.makeImage(Files.readAllBytes(Paths.get(photoPath)));
    }
}
