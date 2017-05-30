# Playr
Version 0.0.1: May 18, 2017.

## Deployed link
[playr](http://playr-project.herokuapp.com)
## Description
Team Project. Playr app enables users to CREATE or LISTEN to saved playlists. Once logged into the app users can create playlists of their favorite music genre, Users have the option of searching for their music from Youtube and Vimeo sites that have been intergrated to Playr.Add selected videos to create a new playlist and Save for future refernces. OR browse saved playlists that other users have created. Play the music and view videos on the iframe intergrated into the Playr app. Happy listening. :smile:

## Authors
* Kenneth Gitere.
* Collins Mutuma.
* Alex Kinuthia.
* William Mbotela.


## Technologies Used
  1. Java
  2. JUnit
  3.  Spark
  4. PostgreSQL
  5. Gradle
  6. JQuery


## Setup/Installation
* Clone directory
* Setup database in PSQL:
  * `CREATE DATABASE playr;`
  * `\c playr`
  * `CREATE TABLE playlists (user_id serial PRIMARY KEY, user_name varchar, track_id int, type_name varchar, trackname varchar, thumbnail varchar, tracklink varchar, date_created timestamp, security_id int, host varchar);`
  * `CREATE TABLE types (id serial PRIMARY KEY, typename varchar);`

* Type `gradle run` inside the project directory
* Navigate to ```http://localhost:4567```



## User Stories:

* As a user I want login and create my playlist.
* As a user I want save my playlist
* As a user I want to play videos as I add new music to playlist.
* As a user I want to search my music from youtube or vimeo.
* As a user I want see all my saved playlists.
* As a user I save my playlist in different genres.
* As a user I see other saved playlists.




## Known Issues
* None. kindly advise if any arise for further assistance


## Legal
* Copyright (c) 2017 Playr. All rights reserved.
