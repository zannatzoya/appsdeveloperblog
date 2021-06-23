/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appsdeveloperblog.ws.api.albums.controllers;

import com.appsdeveloperblog.ws.api.albums.response.AlbumRest;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/albums")
public class AlbumsController {
    
	@Autowired
	Environment env;
	
    @GetMapping
    public List<AlbumRest> getAlbums() { 
        
    	//System.out.println("Album Working on port: " + env.getProperty("local.server.port"));
    	
        AlbumRest album1 = new AlbumRest();
        album1.setAlbumId("albumIdHere");
        album1.setUserId("1");
        album1.setAlbumTitle("Album one title : "+env.getProperty("local.server.port"));
        album1.setAlbumDescription("Album 1 description");
        album1.setAlbumUrl("Album 1 URL");
        
        AlbumRest album2 = new AlbumRest();
        album2.setAlbumId("albumIdHere");
        album2.setUserId("2");
        album2.setAlbumTitle("Album two title");
        album2.setAlbumDescription("Album 2 description");
        album2.setAlbumUrl("Album 2 URL");
         
        return Arrays.asList(album1, album2);
    }
 
}
