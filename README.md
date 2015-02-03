# ViacomPT
Viacom Prototype : Protoype developed to parse http://vine.co API

------
Brief application overview:
* Added the basic Android app structure, git, ..
* App packages defined
* Added java classes to handle data from API
* Application UI designed
* Attached UI to their controllers
* Added different util classes for instance NetworkUtils, LogUtils, ParseUtils
* NetworkUtils could be further optimised to handle other requests
* Parse data from API and update views accordingly.
* Added third-party libraries : bumptech/glide, google/basic-http-client, google/gson
  - bumptech/glide : dealing with imageviews, cache
  - google/basic-http-client : make http requests to API
  - google/gson : parse JSON <-> POJO
* API endpoints, debug flags can be found in Config.java
* Video quality decided based on WiFi/Cellular
* User promted to connect to network if no network found
* Error dipalyed if something goes wrong with API data

