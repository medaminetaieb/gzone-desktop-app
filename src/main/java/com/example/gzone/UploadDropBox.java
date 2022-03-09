package com.example.gzone;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.users.DbxUserUsersRequests;
import com.dropbox.core.v2.users.FullAccount;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class UploadDropBox {
    private static final String ACCESS_TOKEN = "sl.BDeSKlEIsPR0Nq0GfS2ajeJziLEG31IuBVQ1WRMY2nvFoDMIZIcUnzJfZeuxsv1Ny3ybgsDN20fnkmfmsnIx-5PmUHoJiSyJL477sxTAxvc7tVW1go6ehFHXLrHUEozZqMjqNlSNFcpd";

    protected static void uploadPhoto(){
    	System.out.println("Hi");

		try {

        DbxRequestConfig config;
        config = new DbxRequestConfig("dropbox/gzone1");
        DbxClientV2 client;
        client = new DbxClientV2(config, ACCESS_TOKEN);
        FullAccount account;
        DbxUserUsersRequests r1 = client.users();
        account = r1.getCurrentAccount();
        System.out.println(account.getName().getDisplayName());

        // Get files and folder metadata from Dropbox root directory
        ListFolderResult result = client.files().listFolder("");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }

    } catch (
    DbxException ex1) {
        ex1.printStackTrace();
    }


    DbxRequestConfig config;
    config = new DbxRequestConfig("dropbox/gzone1");
    DbxClientV2 client;
    client = new DbxClientV2(config, ACCESS_TOKEN);
		try (
    InputStream in = new FileInputStream("C:\\Users\\samib\\Documents\\NetBeansProjects\\gzone\\src\\main\\resources\\com\\example\\gzone\\killua.png")) {
        System.out.println("aaaa");
        FileMetadata metadata = client.files().uploadBuilder("/killuaaaaaaaaaaaaaaaa.png")
                .uploadAndFinish(in);

    } catch (
    FileNotFoundException e) {
        e.printStackTrace();
        System.out.println("qa");
    } catch (
    IOException e) {
        e.printStackTrace();
        System.out.println("aads");
    } catch (
    UploadErrorException e) {
        e.printStackTrace();
        System.out.println("6");
    } catch (DbxException e) {
        e.printStackTrace();
        System.out.println("ds");

    }
}}

