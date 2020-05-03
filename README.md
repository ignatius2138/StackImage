My app is a template shopping app for the ShutterStock stock image selling website. It uses:
1)Retrofit to fetch results from ShutterStock API;
2)Moshi library to bind json response to Kotlin data classes;
3)Kotlin coroutines for fetching results from network and Room database;
4)Recycler view to show network and database results in a scrolling list view;
5)Picasso library to load images from Internet;
6)Used architecture is MVVM;
7)You could use search view to search results from ShutterStock library and use right edge drawerview to filter searched results;
8)You can see detailed information screen when you tap on a selected image;
9)And then you could add this image to a cart. Cart is maintained using Room database library;
10)There is support for multiple accounts with they own cart and purchase history.
11)And so on....
