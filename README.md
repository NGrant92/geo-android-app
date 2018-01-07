# Geocache Android App
## Assignment #1 for Android App Dev

### Author Details:

**Name:** Niall Grant

**Github repo:** https://github.com/NGrant92/geo-android-app

### Description:

Geocaching is a form of Orienteering or 'treasure hunting' that is run and maintained by the players
themselves. The players can put a "cache" wherever they think is appropriate and set a location to it
online, others can go visit it and usually leave notes for the next visitors.

This app allows users to create/update/delete their own caches while also being able to view other
user caches. Users can also favourite any cache they wish. There is a built in map so the user can
track their location without leaving the app, along with built in Email and and the ability to update
their settings.

### Details:
##### Fully implemented features:
- Account creation and editing
- Cache creation and editing
- Seperate lists of all caches and of user's specific caches
- Favouriting Caches
- Google maps to track location without having to leave the app
- Email integration
- User and Cache persistence
- Add cache location using maps*
- Edit location of Cache*
- Tracking Caches in map*
- List of favourite caches*
- Add Images to Caches*
- Google Account Sign In*

New*

##### Features in Development:

- Star rating for each caches
- A list of players who visted the cache
- A scoreboard for players who earned points finding caches

##### References:

- Most of the code is based on the MyRent and CoffeeMate labs: https://edge.moodle.wit.ie/
- Camera integration: https://wit-hdip-computer-science.github.io/semester-2-mobile-app-dev/topic11-a/index.html
- onBackPressed() in Welcome and GeoMenu: https://stackoverflow.com/a/26492794

##### Google Services:

- A personal API key is required for the app to access Google Services
- To create your own API Key go to: https://developers.google.com/identity/sign-in/android/start-integrating
- Store the key in app/res/values/keys.xml (Don't forget to add keys.xml to .gitignore)
- Store it like so: \<string name="google_maps_key">api key goes here\</string>
- The key needs to be referenced in the Android Manifest, but if you name your resource "google_maps_key" as shown above, the manifest should find the key on the next rebuild. 

##### Images:

![login](http://res.cloudinary.com/ngrant/image/upload/v1515329272/00-login_ywn6xg.png)

![nav drawer](http://res.cloudinary.com/ngrant/image/upload/v1515329272/02-nav-drawer_h9kyny.png)

![map](http://res.cloudinary.com/ngrant/image/upload/v1515329272/05-map_hb81q8.png)

![add cache](http://res.cloudinary.com/ngrant/image/upload/v1515329272/03-add-cache_aboocl.png)

![edit cache](http://res.cloudinary.com/ngrant/image/upload/v1515329272/04-edit-cache_czaodw.png)

![cache list](http://res.cloudinary.com/ngrant/image/upload/v1515329273/07-cache-list_nhmycb.png)